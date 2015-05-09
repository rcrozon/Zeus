package zeus;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import utilEnum.ParamEnum;
import utilEnum.RequestEnum;
import configuration.Configuration;
import connection.Connection;
import connection.Request;

public class Zeus {

	public static void main(String[] args) {

		Configuration.initConfiguration();
		System.out.println(Configuration.getInstance().getDevice());
		
		Connection.startServerInstance();
		
		Request request = new Request();
		request.setRequestType(RequestEnum.GET_DEVICE);
		request.setSenderDevice(Configuration.getInstance().getDevice());
		
		Map<ParamEnum, Object> parameters = new HashMap<>();
		parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());

		request.setParameters(parameters);

		Connection.getClientInstance().sendSerializedObject(request);
		
		Request request2 = new Request();
		request2.setRequestType(RequestEnum.GET_ENTRY);
		
		Map<ParamEnum, Object> parameters2 = new HashMap<>();
		//TODO to change
		parameters2.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());
		
		parameters2.put(ParamEnum.ENTRY_PATH, "C:\\Users\\Romain\\Downloads");
		request2.setSenderDevice(Configuration.getInstance().getDevice());
		request2.setParameters(parameters2);

		Connection.getClientInstance().sendSerializedObject(request2);
	}
}
