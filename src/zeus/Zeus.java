package zeus;

import java.util.HashMap;
import java.util.Map;

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
		
		Map<ParamEnum, Object> parameters = new HashMap<>();

		parameters.put(ParamEnum.DEVICE, Configuration.getInstance().getDevice());
		parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, Configuration.getInstance().getDevice().getDeviceIpAddress());
		request.setParameters(parameters);
		
		Connection.getClientInstance().sendSerializedObject(request);
		
	}

}
