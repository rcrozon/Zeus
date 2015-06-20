package alarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import audio.Player;
import components.Music;
import components.Playlist;

public class AlarmClock {

	private boolean isActivated = true;
	private Date 	date = null;
	private Playlist playlist = null;
	
	public AlarmClock(Date date){
		this.date = date;
	}
	
	public AlarmClock(Date date, Playlist playlist){
		this(date);
		this.playlist = playlist;
	}
	
	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public void play(){
//		if (playlist != null){
		Music music1 = new Music("C:\\Users\\romai_000\\Desktop\\Come With Me Now.mp3");
		Music music2 = new Music("C:\\Users\\romai_000\\Desktop\\Stay With Me.mp3");
		Music music3 = new Music("C:\\Users\\romai_000\\Desktop\\Iron.mp3");
		List<Music> musics = new ArrayList<>();
		musics.add(music1);
		musics.add(music2);
		musics.add(music3);
		
		playlist = new Playlist("playlist", musics);
		Player.setCurrentPlaylist(playlist);
		Player.playMusic(playlist.getMoMusics().get(0));
//		}
	}
}
