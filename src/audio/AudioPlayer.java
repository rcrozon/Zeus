package audio;
import java.io.InputStream;
import java.util.Observable;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.Player;

public class AudioPlayer extends Observable{

    public final static int NOTSTARTED = 0;
    public final static int PLAYING = 1;
    public final static int PAUSED = 2;
    public final static int FINISHED = 3;
    public final static int STOPPED = 4;
    public final static int NEXT = 4;

    // the player actually doing all the work
    private final Player player;

    // locking object used to communicate with player thread
    private final Object playerLock = new Object();

    // status variable what player thread is doing/supposed to do
    private int playerStatus = NOTSTARTED;

    public AudioPlayer(final InputStream inputStream) throws JavaLayerException {
        this.player = new Player(inputStream);
    }

    public AudioPlayer(final InputStream inputStream, final AudioDevice audioDevice) throws JavaLayerException {
        this.player = new Player(inputStream, audioDevice);
    }

    /**
     * Starts playback (resumes if paused)
     */
    public void play() throws JavaLayerException {
        synchronized (playerLock) {
            switch (playerStatus) {
                case NOTSTARTED:
                	System.out.println("start");
                    final Runnable r = new Runnable() {
                        public void run() {
                            playInternal();
                        }
                    };
                    final Thread t = new Thread(r);
                    t.setDaemon(true);
                    t.setPriority(Thread.MAX_PRIORITY);
                    playerStatus = PLAYING;
                    t.start();
                    break;
                case PAUSED:
                    resume();
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Pauses playback. Returns true if new state is PAUSED.
     */
    public boolean pause() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = PAUSED;
            }
            return playerStatus == PAUSED;
        }
    }
    /**
     * Play the next music
     */
    public boolean playNext() {
        synchronized (playerLock) {
            if (playerStatus == PLAYING) {
                playerStatus = NEXT;
                setChanged();
                notifyObservers(NEXT);
                playerLock.notifyAll();
            }
            return playerStatus == PAUSED;
        }
    }
    
    /**
     * Resumes playback. Returns true if the new state is PLAYING.
     */
    public boolean resume() {
        synchronized (playerLock) {
            if (playerStatus == PAUSED) {
                playerStatus = PLAYING;
                playerLock.notifyAll();
            }
            return playerStatus == PLAYING;
        }
    }

    /**
     * Stops playback. If not playing, does nothing
     */
    public void stop() {
    	synchronized (playerLock) {
            playerStatus = FINISHED;
            setChanged();
            notifyObservers(STOPPED);
            playerLock.notifyAll();
        }
    }

    private void playInternal() {
        while (playerStatus != FINISHED) {
            try {
                if (!player.play(1)) {
                    break;
                }
            } catch (final JavaLayerException e) {
                break;
            }
            // check if paused or terminated
            synchronized (playerLock) {
                while (playerStatus == PAUSED) {
                    try {
                        playerLock.wait();
                    } catch (final InterruptedException e) {
                        // terminate player
                        break;
                    }
                }
            }
        }
        close();
    }

    /**
     * Closes the player, regardless of current state.
     */
    public void close() {
        synchronized (playerLock) {
            playerStatus = FINISHED;
            setChanged();
            notifyObservers(FINISHED);
        }
        try {
            player.close();
        } catch (Exception e) {}
    }
    
    public int getStatus(){
    	return playerStatus;
    }

}