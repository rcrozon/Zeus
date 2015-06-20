package alarm;

import java.util.ArrayList;
import java.util.List;

import threads.AlarmsThread;

public class AlarmsRegister {
	
	public static List<AlarmClock> registeredAlarms = null;
	
	public static List<AlarmClock> getAlarmClocks(){
		if (registeredAlarms == null){
			registeredAlarms = new ArrayList<AlarmClock>();
		}
		return registeredAlarms;
	}
	
	public static void startAlarms(){
		for(AlarmClock alarmClock : registeredAlarms){
			AlarmsThread alarmThread = new AlarmsThread(alarmClock);
			alarmThread.start();
		}
	}
}
