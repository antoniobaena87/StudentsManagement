package gestion_alumnos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

public class Utils {
	
	MainFrame mainFrame;
	
	protected static HashMap<String, Alumno> loadAlumnos(String filePath) {
		HashMap<String, Alumno> alumnos = new HashMap<>();
		
		// Try to load saved data, if it exists
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(filePath);
			ois = new ObjectInputStream(fis);
			alumnos = (HashMap<String, Alumno>)ois.readObject();
			System.out.println("Successfully loaded.");
		}catch(IOException ex) {
			
		}catch(ClassNotFoundException ex) {
			
		}finally {
			try{
				fis.close();
				ois.close();
			}catch(IOException|NullPointerException ex) {
				
			}
		}
		
		return alumnos;
	}
	
	/**
	 * 
	 * @param alumnos
	 * @return 1 if successful, -1 otherwise
	 */
	protected static int save(HashMap<String, Alumno> alumnos, String filePath) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(filePath);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(alumnos);
			System.out.println("Successfully saved.");
			
		}catch(IOException ex) {
			System.out.println(ex);
		}finally {
			try {
				fos.close();
				oos.close();
				return 1;
			}catch(IOException|NullPointerException ex) {
				
			}
		}
		
		return -1;
	}
}
