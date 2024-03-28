package cajero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

public class Intermedio {
	BufferedWriter outbw;
    FileWriter outfw;
    
	public Intermedio() {
		outbw = null;
	    outfw = null;
	}

	

	public void sobrescribir(String abono, String usuario) 
		{
		
		try {
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			String cadena = "";
			while ((linea = archivo.readLine())!=null) 
					{
				String[]parts = linea.split(" "); //Division de los datos en la linea en partes
				String part1 = parts[0]; String part2 = parts[1]; String part3 = parts[2]; // Adicion a un arreglo temporal
					if(part1.contains(usuario)) {
						String nueSaldo = sumaAbono(part3, abono);
						cadena += linea.replaceAll(part3, nueSaldo) + "\r\n";	// Remplazo de la tercera linea con el saldo
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
}

