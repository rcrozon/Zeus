package components;

import java.util.ArrayList;
import java.util.List;

public class Playlist extends Component {
	
	private static final long 	serialVersionUID = 1L;
	private List<Music> 	moMusics;
	
	public Playlist(String name, List<Music> poMusics){
		this.name = name;
		this.moMusics = poMusics;
	}
	

	/**
	 * @return the moEntry
	 */
	public List<Music> getMoMusics() {
		return moMusics;
	}

	/**
	 * @param moEntry the moEntry to set
	 */
	public void setMoMusics(List<Music> poMusics) {
		this.moMusics = poMusics;
	}
}
