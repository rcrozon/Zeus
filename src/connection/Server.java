package connection;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import constants.ConstantsConnection;

public class Server {

	private ServerSocket serverSocket;
	
	public Server() {
		
		Thread threadServer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					serverSocket = new ServerSocket(ConstantsConnection.DEFAULT_PORT);
				
					while (true) {
						// On attend la connexion d'un client
						Socket socket = serverSocket.accept();
	
						Byte[] taillefichier;
						int lu;
						long taille = 0;
	
						// Création de l'entrée
						InputStream inpute = socket.getInputStream();
	
						OutputStream out = new FileOutputStream("tmp.txt");
	
						// Reçoit du client
						BufferedInputStream inBuffer = new BufferedInputStream(inpute);
	
						// Envoi vers le fichier
						BufferedOutputStream outBuffer = new BufferedOutputStream(out);
	
						lu = inBuffer.read();
	
						int compteur = 0;
	
						while (lu > -1) {
							outBuffer.write(lu);
							lu = inBuffer.read();
	
							compteur++;
						}
	
						outBuffer.write(lu);
	
						outBuffer.flush();
						outBuffer.close();
						inBuffer.close();
	
						out.flush();
						out.close();
						inpute.close();
						socket.close();
	
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		threadServer.start();
	}
}