package audio;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import javazoom.jl.decoder.JavaLayerException;
import tools.XMLCreator;

import components.Music;
import components.Playlist;


public class Player implements Observer{

	public static Music moMusicPlaying = null;
	public static AudioPlayer moAudioPlayer;
	public static Playlist moPlaylist = null;
	public static FileInputStream moInput = null;
	public static Player moPlayer = null;
	public static boolean bLoop = false;
	
	public static Player getInstance(){
		if (moPlayer == null){
			moPlayer = new Player();
		}
		return moPlayer;
	}
	
	public Player(){
		
		System.out.println("CREATION PLAYER");
		moPlaylist = new Playlist("playlist", XMLCreator.getPlaylistByName("Playlist2"));
		for(Music oM : moPlaylist.getMoMusics()){
			System.out.println(oM.getMsPath());
		}
		
		JFrame frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
	
	/**
 	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Player oPlayer = new Player();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.equals(AudioPlayer.STOPPED)){
			stopMusic();
		}else if (moPlaylist != null && moPlaylist.getMoMusics() != null && moPlaylist.getMoMusics().size() > 0){
			try{
				if (moPlaylist.getMoMusics().get(moPlaylist.getMoMusics().indexOf(moMusicPlaying)+1) != null){
					playMusic(moPlaylist.getMoMusics().get(moPlaylist.getMoMusics().indexOf(moMusicPlaying)+1));
				}else if (moPlaylist.getMoMusics().get(0) != null){
					playMusic(moPlaylist.getMoMusics().get(0));
				}
			}
			catch (IndexOutOfBoundsException e) {
				if (moPlaylist.getMoMusics().get(0) != null && bLoop){
					playMusic(moPlaylist.getMoMusics().get(0));
				}
			}
		}
	}

	public static void pauseMusic(){
		if (moAudioPlayer != null){
			moAudioPlayer.pause();
		}
	}

	public static void stopMusic(){
		if (moAudioPlayer != null){
			moAudioPlayer.stop();
		}
	}
	
	public static void playNext(){
		if (moAudioPlayer != null){
			moAudioPlayer.playNext();
		}
	}
	
	public static void resumeMusic(){
		if (moAudioPlayer != null){
			moAudioPlayer.resume();
		}
	}
	
	public static void playMusic(Music poMusic){
		try {
			moInput = new FileInputStream(poMusic.getMsPath());
			if (moAudioPlayer != null){
				moAudioPlayer.deleteObservers();
				moAudioPlayer.stop();
			}
			moMusicPlaying = poMusic;
			moAudioPlayer = new AudioPlayer(moInput);
	        moAudioPlayer.deleteObservers();
	        moAudioPlayer.addObserver(Player.getInstance());
	
	        moAudioPlayer.play();
	        
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	
	public static void setCurrentPlaylist(Playlist poPlaylist){
		moPlaylist = poPlaylist;
	}
}

