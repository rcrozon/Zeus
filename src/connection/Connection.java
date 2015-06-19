package connection;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import utilEnum.ParamEnum;
import utilEnum.RequestEnum;
import vocalAnalysis.VocalAnalyser;
import configuration.Configuration;
import constants.ConstantsConnection;
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
				if (entry.exists()){
					entry.setMvEntries(entry.listFiles());
				}
				parameters.put(ParamEnum.ENTRIES, entry);
				break;
			case GET_THUMBNAILS:
				response.setRequestType(RequestEnum.RETURN_THUMBNAILS);
				Entry entryThumbnails = new FolderEntry((String)request.getParameters().get(ParamEnum.ENTRY_PATH));
				if (entryThumbnails.listFiles() != null){
					entryThumbnails.setMvEntries(Entry.getEntries(entryThumbnails.listFiles()));
				}
				try {
					parameters.put(ParamEnum.THUMBNAILS, entryThumbnails.getThumbnails());
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case VOCAL_COMMAND:
				VocalAnalyser.analyseVocalRequest(request);
				response.setRequestType(RequestEnum.VOCAL_RESPONSE);
				parameters.put(ParamEnum.VOCAL_COMMAND, Configuration.getInstance().getDevice());
				break;		
			case RETURN_DEVICE: 
				System.out.println("Returned device " + request.getParameters().get(ParamEnum.DEVICE));
				return ConstantsConnection.EXIT_SUCCESS;
			case RETURN_ENTRY: 
				System.out.println("Returned entry " + request.getParameters().get(ParamEnum.ENTRIES));
				return ConstantsConnection.EXIT_SUCCESS;
			case RETURN_THUMBNAILS:
				System.out.println("Returned thumbnails " + request.getParameters().get(ParamEnum.THUMBNAILS));
				List<byte[]> picturesList = (List<byte[]>)request.getParameters().get(ParamEnum.THUMBNAILS);
				return ConstantsConnection.EXIT_SUCCESS;
			default:
				return ConstantsConnection.EXIT_FAILURE;
		}
		parameters.put(ParamEnum.RECIPIENT_IP_ADDRESS, request.getSenderDevice().getDeviceIpAddress());
		response.setSenderDevice(Configuration.getInstance().getDevice());
		response.setParameters(parameters);
		System.out.println("Response : " + response);
		Connection.getClientInstance().sendSerializedObject(response);
		return 0;
	}
}