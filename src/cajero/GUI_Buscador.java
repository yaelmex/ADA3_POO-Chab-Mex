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
					String part1 = parts[0]; // Adicion a un arreglo temporal
					nombres.add(part1);
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
		setBounds(100, 100, 672, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		txtBuscar.setBounds(80, 44, 513, 34);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(222, 94, 158, 45);
		contentPane.add(btnBuscar);
		
		JLabel lblBusUsuario = new JLabel("Busqueda de Usuarios");
		lblBusUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblBusUsuario.setFont(new Font("Inter Black", Font.PLAIN, 20));
		lblBusUsuario.setBounds(176, 9, 268, 23);
		contentPane.add(lblBusUsuario);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(29, 187, 46, 14);
		contentPane.add(lblNombre);
		
		JLabel lblCodigo = new JLabel("Codigo de cuenta");
		lblCodigo.setBounds(28, 212, 105, 14);
		contentPane.add(lblCodigo);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(29, 242, 105, 14);
		contentPane.add(lblSaldo);
		
		JLabel lblMovimiento = new JLabel("Ultimo movimiento");
		lblMovimiento.setBounds(30, 268, 105, 14);
		contentPane.add(lblMovimiento);
		
		JLabel varNombre = new JLabel("");
		varNombre.setBounds(228, 187, 281, 14);
		contentPane.add(varNombre);
		
		JLabel varCodCuenta = new JLabel("");
		varCodCuenta.setBounds(226, 210, 281, 14);
		contentPane.add(varCodCuenta);
		
		JLabel varSaldo = new JLabel("");
		varSaldo.setBounds(226, 241, 281, 14);
		contentPane.add(varSaldo);
		
		JLabel varUltMov = new JLabel("");
		varUltMov.setBounds(227, 268, 281, 14);
		contentPane.add(varUltMov);
		
		CargarDatos();
		CargarSugerencias();

	}
}
