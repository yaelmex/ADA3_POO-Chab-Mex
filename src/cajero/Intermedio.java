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
	
	public String transferencia(String origen, String destino, float monto) {
		StringBuilder fileContent = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new FileReader("Cuentas.txt"));
			String linea = br.readLine();
			Float montoComparar = 0F;
			Float montoDestino = 0F;
			String actualizarOrigen = "";
			String actualizarDestino = "";
			
			while(linea != null) {
				if(linea.contains(origen)) {
					actualizarOrigen = linea;
					String[] extraido = linea.split(" ");
					montoComparar = Float.parseFloat(extraido[2]);
				}
				if(linea.contains(destino)) {
					actualizarDestino = linea;
					String[] extraido_destino = linea.split(" ");
					montoDestino = Float.parseFloat(extraido_destino[2]);
				}
				linea = br.readLine();
				
			}
			br.close();
			
			if(monto > montoComparar) {
				return "No posee fondos suficientes";
			} else {
				
				String [] dividirOrigen = actualizarOrigen.split(" ");
				String remplazarOrigen = String.valueOf(montoComparar - monto); 
				String OrigenFinal = actualizarOrigen.replace(dividirOrigen[2], remplazarOrigen);
				
				String [] dividirDestino = actualizarDestino.split(" ");
				String remplazarDestino = String.valueOf(montoDestino + monto); 
				String DestinoFinal = actualizarDestino.replace(dividirDestino[2], remplazarDestino);
				
				
				BufferedReader in = new BufferedReader(new FileReader("Cuentas.txt"));
				
				String lineaAct;
				while ((lineaAct = in.readLine()) != null) {
					
					if(lineaAct.contains(origen)) {
						fileContent.append(OrigenFinal).append(System.getProperty("line.separator"));
					} else if(lineaAct.contains(destino)) {
						fileContent.append(DestinoFinal).append(System.getProperty("line.separator"));
					} else {
						fileContent.append(lineaAct).append(System.getProperty("line.separator"));
					}
				}
				
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("Cuentas.txt"))) {
			            writer.write(fileContent.toString());
			            in.close();
			            writer.close();
						return "Transferencia Éxitosa";
			     } catch (IOException e) {
			            e.printStackTrace();
			     }	
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return null;	
	}
	   
}

