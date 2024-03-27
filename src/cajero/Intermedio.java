package cajero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class Intermedio {
	BufferedWriter outbw;
    FileWriter outfw;
    
	public Intermedio() {
		outbw = null;
	    outfw = null;
	}
	
	public void agregarUsuario(String nombre, String usuario, String contraseña) {
		try {
			File file = new File("Usuarios.txt");
			if(!file.exists()) {
				file.createNewFile();
			}
			outfw = new FileWriter(file.getAbsoluteFile(), true);
			outbw = new BufferedWriter(outfw);
			String cadena = nombre + " " + usuario + " " + contraseña;
			outbw.write(cadena);
			outbw.newLine();
		} catch(IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if(outbw != null) {
					outbw.close();
				}
				
				if(outfw != null) {
					outfw.close();
				}
			} catch(IOException a) {
				a.printStackTrace();
			}
		}
	}
	
	
	public boolean buscarUsuario(String usuario, String contraseña) {
		try {
		    BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"));
		    String line = br.readLine();
		    
		    while(line != null) {
		    	 StringTokenizer partida = new StringTokenizer(line, " ");
		    	 while(partida.hasMoreElements()) {
		    		 String dato = partida.nextToken();
		    		 

		    		 if(dato.equals(usuario)) {
		    			 dato = partida.nextToken();
		    			 if(dato.equals(contraseña)) {
		    				 br.close();
		    				 return true;
		    			 }
		    		 } 
		    	 }
		    	 line = br.readLine();		    	
		    }
		    br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return false; 
	}
	   
}

