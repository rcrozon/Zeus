package threads;

import java.util.Date;

import alarm.AlarmClock;
import audio.AudioPlayer;
import audio.Player;

public class AlarmsThread extends Thread {

	private AlarmClock alarmClock = null;
	
	public AlarmsThread(AlarmClock alarmClock){
		this.alarmClock = alarmClock;
	}

	@Override
	public void run() {
		boolean isWaiting = true;
		while(isWaiting){
			Date currentDate = new Date();
			if (Math.abs(currentDate.getTime() - alarmClock.getDate().getTime()) < 60000){
				alarmClock.play();
				while(Player.getInstance().getAudioPlayer().getStatus() != AudioPlayer.FINISHED);
			}else{
				try {
					this.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
