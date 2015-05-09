package entries;

import java.io.File;
import java.util.Vector;

public abstract class Entry extends File {

	Vector<Entry> mvEntries = new Vector<Entry>();
	/**
	 * Serial
	 */
	private static final long serialVersionUID = 1L;
	
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
}
