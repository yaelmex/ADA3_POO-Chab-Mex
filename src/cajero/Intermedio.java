package cajero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

	//RICARDO
	public void addAbono(String abono, String usuario) 

		{
		boolean Encontrado = false;
		try {
			// Lector de archivo
			String nombre = "";
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			String cadena = "";
			while ((linea = archivo.readLine())!=null) 
					{
				String[]parts = linea.split(" "); //Division de los datos en la linea en partes, el arreglo "parts" se usa en todo el método
				// El arreglo usa como parametro para llenar un espacio, las palabras separadas por un espacio vacio
				
				if(parts.length == 6) {
				 nombre = parts[4];
				} else if (parts.length ==5) {
					nombre = parts[3];
				}
				
				if(nombre.contains(usuario)) {
					Encontrado = true;
					if(parts.length == 6) { // Diferenciador, los nombres formados por 2 nombres y 2 apellidos ocupan 6 espacios
						String nueSaldo = sumaAbono( parts[5], abono);
						cadena += linea.replaceAll(parts[5], nueSaldo) + "\r\n"; // Remplazo del tercer espacio del arreglo "parts" con el saldo nuevo
						}
						else if(parts.length == 5) { // Diferenciador, los nombres formados por 2 nombres y 2 apellidos ocupan 6 espacios
						String nueSaldo = sumaAbono(parts[4], abono);
						cadena += linea.replaceAll(parts[4], nueSaldo) + "\r\n";	// Remplazo del tercer espacio del arreglo "parts" con el saldo nuevo
						}
				
					}
					 else {
						cadena += linea + "\r\n"; 
						
					 }
					} 
			archivo.close();
			
			if(Encontrado == false) {
				JOptionPane.showMessageDialog(null, "No se encontro el usuario");
				return;
			} else if (Encontrado == true) {
				JOptionPane.showMessageDialog(null, "Abono realizado con exito");
			}
				
			FileOutputStream fileout = new FileOutputStream("Cuentas.txt"); // Reescribir el documento Cuentas.txt con el saldo nuevo
			fileout.write(cadena.getBytes());
			fileout.close();
			
				} catch (IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();	
				} 
		}
	
	//RICARDO
	public String sumaAbono(String saldo, String abono) { //Metodo para sumar el abono y el saldo anterior, los datos se reciben del metodo addAbono
		Double valSaldo = Double.parseDouble(saldo);
		Double valAbono = Double.parseDouble(abono);
		Double valTotSaldo = valSaldo + valAbono;
		String totSaldo = String.valueOf(valTotSaldo);
		return totSaldo;
		
	}
	
	//YAEL
	public void agregarUsuario(String nombre, String usuario, String contraseña) {
		try {
			File file = new File("Usuarios.txt");//Verifica si el archivo existe o no para crearlo o solo modificarlo
			if(!file.exists()) {
				file.createNewFile();
			}
			outfw = new FileWriter(file.getAbsoluteFile(), true);
			outbw = new BufferedWriter(outfw);
			String cadena = nombre + " " + usuario + " " + contraseña;//Concatena al usuario y lo añade al archivo
			outbw.write("\r\n" + cadena);
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
	
	
	//YAEL
	public boolean buscarUsuario(String usuario, String contraseña) {
		try {
		    BufferedReader br = new BufferedReader(new FileReader("Usuarios.txt"));
		    String line = br.readLine();
		    
		    /*Mediante un Tokenizer se divide las líneas que se leen ignorando espacios y se validan recorriendo con un while
		     si existen el usuario y posterior si este contiene la contraseña ingresada*/
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
	
	//YAEL
	public String transferencia(String origen, String destino, float monto) {
		//Se usa un StringBuilder para modificar una cadena sin crear otro archivo y sobreescribir
		StringBuilder fileContent = new StringBuilder();
		try {
			//Se obtienen variables que sirven para comparaciones y modificaciones
			BufferedReader br = new BufferedReader(new FileReader("Cuentas.txt"));
			String linea = br.readLine();
			Float montoComparar = 0F;
			Float montoDestino = 0F;
			String actualizarOrigen = "";
			String actualizarDestino = "";
			
			//Mediante un while se extraen los datos necesarios de la cuenta origen y la de destino, N° de cuenta y saldo poseido
			while(linea != null) {
				if(linea.contains(origen)) {
					actualizarOrigen = linea;
					String[] extraido = linea.split(" ");
					montoComparar = Float.parseFloat(extraido[5]);
				}
				if(linea.contains(destino)) {
					actualizarDestino = linea;
					String[] extraido_destino = linea.split(" ");
					montoDestino = Float.parseFloat(extraido_destino[5]);
				}
				linea = br.readLine();
				
			}
			br.close();
			
			//Se verifica que la transferencia sea posible validando que la transferencia del origen no supere su saldo actual
			if(monto > montoComparar) {
				return "No posee fondos suficientes";
			} else {
				/*En caso de ser posible la transferencia se preparan los datos a sustituir haciendo las sumas y 
				restas correspondientes a los saldos, así como las sustituciones de cadenas a los mismos*/
				String [] dividirOrigen = actualizarOrigen.split(" ");
				String remplazarOrigen = String.valueOf(montoComparar - monto); 
				String OrigenFinal = actualizarOrigen.replace(dividirOrigen[5], remplazarOrigen);
				
				String [] dividirDestino = actualizarDestino.split(" ");
				String remplazarDestino = String.valueOf(montoDestino + monto); 
				String DestinoFinal = actualizarDestino.replace(dividirDestino[5], remplazarDestino);
				
				//Se inicializa otro lector para reiniciar las líneas
				BufferedReader in = new BufferedReader(new FileReader("Cuentas.txt"));
				//Se recorre el archivo en busca de las líneas a modificar y mediante las propiedades del builder se añaden
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
				//Finalmente lo escrito en el builder se le pasa al txt para su modificación
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
	} //---
	
	//RICARDO
	public String getCodigo(String txtNombre) {
		String codigo = "";
		try {	
			// Obtencion de codigo a traves del archivo Cuentas.txt
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			String nombre = "";
			while ((linea = archivo.readLine())!=null) 
					{
					String[]parts = linea.split(" "); //Division de los datos en la linea en partes
					if(parts.length == 6) { // Diferenciador, los nombres formados por 2 nombres y 2 apellidos ocupan 6 espacios
					nombre = parts[0] + " " + parts[1] + " " +  parts[2] + " " + parts[3];}
					else if(parts.length == 5) { // Diferenciador, los nombres formados por 1 nombre y 2 apellidos ocupan 5 espacios
					nombre = parts[0] + " " + parts[1] + " " +  parts[2];
					}
					
					// Establecimiento del codigo en funcion del caso
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
	
	//RICARDO
	public String getSaldo(String txtNombre) { // Obtencion del saldo por medio del archivo Cuentas.txt
		String saldo = "";
		try {	
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			String nombre = "";
			while ((linea = archivo.readLine())!=null) 
					{
					String[]parts = linea.split(" "); // Division de los datos en la linea en partes
					if(parts.length == 6) {  // Diferenciador, los nombres formados por 2 nombres y 2 apellidos ocupan 6 espacios
					nombre = parts[0] + " " + parts[1] + " " +  parts[2] + " " + parts[3];}
					else if(parts.length == 5) { // Diferenciador, los nombres formados por 1 nombre y 2 apellidos ocupan 5 espacios
					nombre = parts[0] + " " + parts[1] + " " +  parts[2];
					}
					
					// Establecimiento del saldo en funcion del caso
					if(txtNombre.equals(nombre) && parts.length == 6) {
							saldo = parts[5];
					} else if (txtNombre.equals(nombre) && parts.length == 5) {
							saldo = parts[4];
					}
					}
			archivo.close();	
			return saldo;
				} catch (IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();	
				} 
		return saldo;
	}
	
	//YAEL
	public void ImprimirBitacora(ArrayList<String> historial) {
		try {
			File file = new File("Bitacora.txt"); //Nuevamente se válida que exista o no el doc por si se hará otro cierre en otro día
			if(!file.exists()) {
				file.createNewFile();
			}
			outfw = new FileWriter(file.getAbsoluteFile(), true);
			outbw = new BufferedWriter(outfw);
			//El array list que se recibe se recorre y lo que contiene se escribe en el archivo txt
			for(int i = 0; i < historial.size(); i++) {
				outbw.write(historial.get(i).toString());
				outbw.newLine();
			}

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
	//RICARDO
	public String getHora() { // Metodo para obtener hora, este se muestra como un String en el label hora en GUI_Caja
		String hora = "";
		DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss"); // Establecimiento del formato para mostrar hora
		Date horaExacta = new Date(); // Inicializacion del objeto Date
		hora = dateFormat.format(horaExacta);
		return hora;
	}
	//RICARDO
	public Boolean verificarVacio(String nombre, String apellido, String contraseña) { // Metodo para GUI_InicioSesion
		// Evita la creacion de cuentas de usuario vacias
		boolean msg = false;
		if (nombre.equals("") || apellido.equals("") || contraseña.equals("")) { // Caso que uno de los campos este vacio
			msg = true;
		}
		return msg;
	}
	

}
