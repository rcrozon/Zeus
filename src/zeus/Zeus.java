package zeus;

import configuration.Configuration;
import connection.Connection;

public class Zeus {

	public static void main(String[] args) {

		Configuration.initConfiguration();
		System.out.println(Configuration.getInstance().getDevice());
		
		Connection.startServerInstance();

//		Configuration.loadAvailableDevices();
//		String date = "20/06/2015 12:54:00";
//		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
//		try {
//			AlarmClock alarmClock = new AlarmClock(formater.parse(date));
//			AlarmClock alarmClock = new AlarmClock(new Date());
//			AlarmsRegister.getAlarmClocks().add(alarmClock);
//			AlarmsRegister.startAlarms();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		// REQUEST 1
//		Request request = new Request();
//		request.setRequestType(RequestEnum.GET_DEVICE);
//		request.setSenderDevice(Configuration.getInstance().getDevice());
//		
//		Map<ParamEnum, Object> parameters = new HashMap<>();
//		parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());
//
//		request.setParameters(parameters);
//
//		Connection.getClientInstance().sendSerializedObject(request);
		/*
		// REQUEST 2
		Request request2 = new Request();
		request2.setRequestType(RequestEnum.GET_ENTRY);
		
		Map<ParamEnum, Object> parameters2 = new HashMap<>();
		//TODO to change
		parameters2.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());
		
		parameters2.put(ParamEnum.ENTRY_PATH, "F:\\Series");
		request2.setSenderDevice(Configuration.getInstance().getDevice());
		request2.setParameters(parameters2);

		Connection.getClientInstance().sendSerializedObject(request2);

		// REQUEST 3
		Request request3 = new Request();
		request3.setRequestType(RequestEnum.GET_THUMBNAILS);
		
		Map<ParamEnum, Object> parameters3 = new HashMap<>();
		//TODO to change
		parameters3.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());
		
		parameters3.put(ParamEnum.ENTRY_PATH, "F:\\Series");
		request3.setSenderDevice(Configuration.getInstance().getDevice());
		request3.setParameters(parameters3);

		Connection.getClientInstance().sendSerializedObject(request3);

		Request request4 = new Request();
		request4.setRequestType(RequestEnum.VOCAL_COMMAND);
		
		Map<ParamEnum, Object> parameters4 = new HashMap<>();
		//TODO to change
		parameters4.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());
		
		parameters4.put(ParamEnum.VOCAL_COMMAND, "joue le morceau suivant");
		request4.setSenderDevice(Configuration.getInstance().getDevice());
		request4.setParameters(parameters4);

		Connection.getClientInstance().sendSerializedObject(request4);
		
		*/
	}
}
