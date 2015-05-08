package entries;

import java.util.Vector;

public class FolderEntry extends Entry{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Entry> 	mvEntries;
	private String 			msPath;
	
	public FolderEntry(String psPath){
		super(psPath);
		msPath = psPath;
		
	}
}
