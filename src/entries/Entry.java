package entries;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import tools.UtilsFile;

public abstract class Entry extends File {

	Vector<Entry> mvEntries = new Vector<Entry>();
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	private static final int  nbPictures = 4;
	
	public Entry(String pPathname) {
		super(pPathname);
	}
	
	public void setMvEntries(Vector<Entry> pvEntry){
		mvEntries = pvEntry;
	}
	
	public void setMvEntries(File[] poFiles){
		for(File file : poFiles){
			Entry oEntry ; 
			if(file.isDirectory()){
				oEntry = new FolderEntry(file.getAbsolutePath());
			}else{
				oEntry = new FileEntry(file.getAbsolutePath());
			}
			mvEntries.add(oEntry);
		}
	}
	
	public Vector<Entry> getMvEntries(){
		return mvEntries;
	}
	
	public static Vector<Entry> getEntries(File[] poFiles){
		Vector<Entry> vEntries = new Vector<Entry>();
		for(File file : poFiles){
			Entry oEntry ; 
			if(file.isDirectory()){
				oEntry = new FolderEntry(file.getAbsolutePath());
			}else{
				oEntry = new FileEntry(file.getAbsolutePath());
			}
			vEntries.add(oEntry);
		}
		return vEntries;
	}

	public String toString(){
		return this.getAbsolutePath() + " : \n" + mvEntries; 
	}
	
	public List<Object> getThumbnails() throws IOException{
		List<Object> thumbnailsList = new ArrayList<>(); 
		for (Entry entry : mvEntries){
			if (entry instanceof FolderEntry){
				thumbnailsList.add(entry.getThumbnails());
			}else {
				FileEntry fileEntry = (FileEntry)entry;
				if (UtilsFile.isPicture(fileEntry)){
					thumbnailsList.add(Files.readAllBytes(fileEntry.toPath()));
					if (thumbnailsList.size() >= nbPictures){
						return thumbnailsList;
					}
				}
			}
		}
		return thumbnailsList;
	}
}
