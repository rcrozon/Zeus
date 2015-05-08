package connection;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import tools.Serializor;
import utilEnum.ParamEnum;
import constants.ConstantsConnection;

public class Client extends Connection {

	public static Client client;
	
	private Socket clientSocket;
	private FileInputStream fileInputStream;
	private BufferedInputStream bufferedInputStream;
	private OutputStream outputStream;

	@Override
	public void sendSerializedObject(Request request){
		File file = Serializor.serialize(request);
	    try {
	    	clientSocket = new Socket((String) request.getParameters().get(ParamEnum.RECIPIENT_IP_ADDRESS), ConstantsConnection.DEFAULT_PORT);

	    	byte[] mybytearray = new byte[(int) file.length()]; //create a byte array to file
	 
		    fileInputStream = new FileInputStream(file);
		    bufferedInputStream = new BufferedInputStream(fileInputStream);  
		    bufferedInputStream.read(mybytearray, 0, mybytearray.length); //read the file
		    outputStream = clientSocket.getOutputStream();

		    outputStream.write(mybytearray, 0, mybytearray.length); //write file to the output stream byte by byte
		    outputStream.flush();
		    bufferedInputStream.close();
		    outputStream.close();
		    clientSocket.close();
	    } catch (UnknownHostException e) {
	    	System.out.println("unknowHost " + e.getMessage());
	    } catch (IOException e) {
	    	System.out.println("io " + e.getMessage());
	    }
	}
}
