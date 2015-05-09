package connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import utilEnum.ParamEnum;
import utilEnum.RequestEnum;
import configuration.Configuration;
import entries.Entry;
import entries.FolderEntry;

public abstract class Connection {

	public static Server server;
	public static Client client;
	
	public static Server startServerInstance(){
		if (server == null){
			try {
				server = new Server();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return server;
	}

	public static Client getClientInstance(){
		if (client == null){
			client = new Client();
		}
		return client;
	}
	
	public abstract void sendSerializedObject(Request request) throws IOException ;
	
	public static int HandleRequest(Request request){
		Request response = new Request();
		Map<ParamEnum, Object> parameters = new HashMap<ParamEnum, Object>();
		switch (request.getRequestType()) {
			case GET_DEVICE:
				response.setRequestType(RequestEnum.RETURN_DEVICE);
				parameters.put(ParamEnum.DEVICE, Configuration.getInstance().getDevice());
				break;		
			case GET_ENTRY:
				response.setRequestType(RequestEnum.RETURN_ENTRY);
				Entry entry = new FolderEntry((String)request.getParameters().get(ParamEnum.ENTRY_PATH));
				entry.setMvEntries(entry.listFiles());
				parameters.put(ParamEnum.ENTRIES, entry);
				break;
			case RETURN_DEVICE: 
				System.out.println("Returned device " + request.getParameters().get(ParamEnum.DEVICE));
				return 0;
			case RETURN_ENTRY: 
				System.out.println("Returned entry " + request.getParameters().get(ParamEnum.ENTRIES));
				return 0;
			default:
				return -1;
		}
		parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, request.getSenderDevice().getDeviceIpAddress());
		response.setSenderDevice(Configuration.getInstance().getDevice());
		response.setParameters(parameters);
		System.out.println("Response : " + response);
		Connection.getClientInstance().sendSerializedObject(response);
		return 0;
	}
}