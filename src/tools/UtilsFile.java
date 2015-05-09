package tools;

import java.awt.Desktop;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import constants.ConstantsFile;
import entries.FileEntry;

public class UtilsFile {

	public static boolean isVideo(FileEntry fFile){	return isEntity(fFile, ConstantsFile.VIDEOS_EXTENSIONS); }
	public static boolean isAudio(FileEntry fFile){ return isEntity(fFile, ConstantsFile.AUDIOS_EXTENSIONS); }
	public static boolean isPicture(FileEntry fFile){ return isEntity(fFile, ConstantsFile.PICTURES_EXTENSIONS);}
	
	private static boolean isEntity(FileEntry fFile, String[] psExtensions){
		for (String sExtension : psExtensions){
			if (fFile.getAbsolutePath().contains(sExtension)){
				return true;
			}
		}
		return false;
	}
	
	public static void launchFile(File pfFile) throws IOException{
		// On vérifie que la classe Desktop soit bien supportée :
		if ( Desktop.isDesktopSupported() ) {
			// On récupère l'instance du desktop :
			Desktop desktop = Desktop.getDesktop();

			// On vérifie que la fonction open est bien supportée :
			if (desktop.isSupported(Desktop.Action.OPEN)) {
				// Et on lance l'application associé au fichier pour l'ouvrir :
				desktop.open(new File(pfFile.getAbsolutePath()));
			}
		}
	}
	
	/**
	 * Teste si le fichier est à ajouter ou non en fonction du filtre donné 
	 * @return 
	 */
	public static boolean isFileCorrect(FileEntry poFile, int piExtensionFilter){
		switch (piExtensionFilter) {
			case ConstantsFile.AUDIO_FILES_ONLY :
				return isAudio(poFile);
			case ConstantsFile.VIDEO_FILES_ONLY : 
				return isVideo(poFile); 
			case ConstantsFile.ALL_FILES : 
				return true; 
			default:
				return false;
		}
	}
	
	public static FileEntry getPictureFilePath(String psDirectoryPath){
		FileEntry fDirectory = new FileEntry(psDirectoryPath);
		System.out.println("DIRECTORY PATH " + psDirectoryPath);
		for (File fFile : fDirectory.listFiles()){
			FileEntry oFileEntry = new FileEntry(fFile.getAbsolutePath()); 
			if (isPicture(oFileEntry)){
				return oFileEntry;
			}
		}
		return null;
	}
	
}
