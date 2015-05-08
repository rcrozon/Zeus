package connection;

import java.io.IOException;

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
}
