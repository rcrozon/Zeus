package tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import entries.Entry;

public abstract class Serializor {

	public static File serialize(Object poSerializableObject) {
		File file =  new File("./input.ser");
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 // ouverture d'un flux sur un fichier
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(file));
			// sérialization de l'objet
			oos.writeObject(poSerializableObject) ;
			oos.close();
		} catch (FileNotFoundException e) {
	    	System.out.println("filenotfound " + e.getMessage());
		} catch (IOException e) {
	    	System.out.println("io " + e.getMessage());
		}return file;
	}
	
	public static Object unserialize(File fFile) throws FileNotFoundException, IOException{
		 // ouverture d'un flux sur un fichier
		ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(fFile)) ;
		 // désérialization de l'objet
		Entry oEntry = null;
		try {
			oEntry = (Entry)ois.readObject();
			ois.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return oEntry;
	}

}
