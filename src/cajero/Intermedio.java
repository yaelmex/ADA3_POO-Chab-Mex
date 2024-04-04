package cajero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

public class Intermedio {
	BufferedWriter outbw;
    FileWriter outfw;
    public String code = "";
	ArrayList<String> nombres = new ArrayList<>();

	public Intermedio() {
		outbw = null;
	    outfw = null;
	}

	
	public void addAbono(String abono, String usuario) 
		{
		
		try {
			String nombre = "";
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			String cadena = "";
			while ((linea = archivo.readLine())!=null) 
					{
				String[]parts = linea.split(" "); //Division de los datos en la linea en partes
				
				if(parts.length == 6) {
				 nombre = parts[4];
				} else if (parts.length ==5) {
					nombre = parts[3];
				}
				
				if(nombre.contains(usuario)) {
					if(parts.length == 6) {
						String nueSaldo = sumaAbono( parts[5], abono);
						cadena += linea.replaceAll(parts[5], nueSaldo) + "\r\n";
						}// Remplazo de la tercera linea con el saldo
						else if(parts.length == 5) {
						String nueSaldo = sumaAbono(parts[4], abono);
						cadena += linea.replaceAll(parts[4], nueSaldo) + "\r\n";	// Remplazo de la tercera linea con el saldo
						}
						
					}
					else 
						cadena += linea + "\r\n";
					}
			archivo.close();
					
			FileOutputStream fileout = new FileOutputStream("Cuentas.txt"); // Reescribir el documento Cuentas.txt
			fileout.write(cadena.getBytes());
			fileout.close();
			
				} catch (IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();	
				} 
		}
	
	
	public String sumaAbono(String saldo, String abono) {
		Double valSaldo = Double.parseDouble(saldo);
		Double valAbono = Double.parseDouble(abono);
		Double valTotSaldo = valSaldo + valAbono;
		String totSaldo = String.valueOf(valTotSaldo);
		return totSaldo;
		
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
	
	public String setNombre(String code) {
		String nombreCom = " ";
try {	
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			while ((linea = archivo.readLine())!=null) 
					{
					String[]parts = linea.split(" "); //Division de los datos en la linea en partes
					String part1 = parts[0] ; String part2 = parts[1] ; String part3 = parts[2] ; String part4 = parts[3];// Adicion a un arreglo temporal
					String part5 = parts[4];
					if(code.equals(part5)) {
						nombreCom = part1 + " " + part2 + " " + part3 + " " + part4;
					}
					}
			archivo.close();
			return nombreCom;	
			
				} catch (IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();	
				} 
	return nombreCom;
	}
	
	public String getCodigo(String txtNombre) {
		String codigo = "";
		try {	
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			String nombre = "";
			while ((linea = archivo.readLine())!=null) 
					{
					String[]parts = linea.split(" "); //Division de los datos en la linea en partes
					if(parts.length == 6) {
					nombre = parts[0] + " " + parts[1] + " " +  parts[2] + " " + parts[3];}
					else if(parts.length == 5) {
					nombre = parts[0] + " " + parts[1] + " " +  parts[2];
					}
					
					if(txtNombre.equals(nombre) && parts.length == 6) {
							codigo = parts[4];
					} else if (txtNombre.equals(nombre) && parts.length == 5) {
							codigo = parts[3];
					}
					}
			archivo.close();	
			return codigo;
				} catch (IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();	
				} 
		return codigo;
	}
}

