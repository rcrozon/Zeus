package components;

import java.util.ArrayList;

public class Playlist extends Component {
	
	private static final long 	serialVersionUID = 1L;
	private ArrayList<Music> 	moMusics;
	
	public Playlist(String name, ArrayList<Music> poMusics){
		this.name = name;
		this.moMusics = poMusics;
	}
	

	/**
	 * @return the moEntry
	 */
	public ArrayList<Music> getMoMusics() {
		return moMusics;
	}

	/**
	 * @param moEntry the moEntry to set
	 */
	public void setMoMusics(ArrayList<Music> poMusics) {
		this.moMusics = poMusics;
	}
}
