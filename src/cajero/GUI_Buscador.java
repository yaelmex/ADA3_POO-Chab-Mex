package cajero;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.autocomplete.ObjectToStringConverter;

import javax.swing.JTextField;
import javax.swing.ListModel;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class GUI_Buscador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtBuscar;
	ArrayList<String> nombres = new ArrayList<>();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Buscador frame = new GUI_Buscador();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	private void CargarDatos() {
try {
			
			BufferedReader archivo = new BufferedReader (new FileReader ("Cuentas.txt"));
			String linea;
			while ((linea = archivo.readLine())!=null) 
					{
					String[]parts = linea.split(" "); //Division de los datos en la linea en partes
					if(parts.length == 6) {
					String part1 = parts[0] ; String part2 = parts[1] ; String part3 = parts[2] ; String part4 = parts[3];// Adicion a un arreglo temporal
					nombres.add(part1 + " " + part2 + " "  + part3  + " " + part4);
					} else if (parts.length == 5) {
						String part1 = parts[0] ; String part2 = parts[1] ; String part3 = parts[2];// Adicion a un arreglo temporal
						nombres.add(part1 + " " + part2 + " "  + part3);
					}
					}
			archivo.close();
			
				} catch (IOException error) {
					JOptionPane.showMessageDialog(null, error.getMessage());
					error.printStackTrace();	
				} 
		}

	private void CargarSugerencias() {
		JList listSugerencias = new JList(nombres.toArray(new String[nombres.size()]));
		AutoCompleteDecorator.decorate(listSugerencias, txtBuscar, ObjectToStringConverter.DEFAULT_IMPLEMENTATION);
	}

	
	public GUI_Buscador() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		txtBuscar.setBounds(16, 44, 513, 34);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblBusUsuario = new JLabel("Busqueda de Usuarios");
		lblBusUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusUsuario.setFont(new Font("Inter Black", Font.PLAIN, 20));
		lblBusUsuario.setBounds(128, 8, 268, 23);
		contentPane.add(lblBusUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(22, 174, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo de cuenta");
		lblCodigo.setBounds(23, 208, 105, 14);
		contentPane.add(lblCodigo);
		
		JLabel varNombre = new JLabel("");
		varNombre.setBounds(159, 172, 281, 14);
		contentPane.add(varNombre);
		
		JLabel varCodCuenta = new JLabel("");
		varCodCuenta.setBounds(163, 207, 281, 14);
		contentPane.add(varCodCuenta);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intermedio metodo = new Intermedio();
				varNombre.setText(txtBuscar.getText());
				String codigo = metodo.getCodigo(txtBuscar.getText());
				varCodCuenta.setText(codigo);
			}
		});
		btnBuscar.setBounds(161, 86, 158, 45);
		contentPane.add(btnBuscar);
		
		CargarDatos();
		CargarSugerencias();

	}
}
