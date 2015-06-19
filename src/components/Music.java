package components;

import entries.Entry;

public class Music extends Component {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String msPath ;
	private Entry moEntry;
	private boolean bHasPicture = false;
	
	public Music(String psPath){
		this.msPath = psPath;
	}
	
	public boolean hasPicture(){
		return bHasPicture;
	}
	
	public void setHasPicture(boolean pbHasPicture){
		bHasPicture = pbHasPicture;
	}

	/**
	 * @return the msPath
	 */
	public String getMsPath() {
		return msPath;
	}

	/**
	 * @param msPath the msPath to set
	 */
	public void setMsPath(String psPath) {
		this.msPath = psPath;
	}

	/**
	 * @return the moEntry
	 */
	public Entry getMoEntry() {
		return moEntry;
	}

	/**
	 * @param moEntry the moEntry to set
	 */
	public void setMoEntry(Entry moEntry) {
		this.moEntry = moEntry;
	}
}
