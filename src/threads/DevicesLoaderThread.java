package threads;

import connection.Connection;
import connection.Request;

public class DevicesLoaderThread extends ConnectionThread {

	private Request request = null;

	public DevicesLoaderThread(){}

	public DevicesLoaderThread(Request request){
		this.request = request;
	}
	
	@Override
	public void run() {
		Connection.getClientInstance().sendSerializedObject(request);
	}
	
}
