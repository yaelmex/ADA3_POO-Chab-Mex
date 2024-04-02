package cajero;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI_InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textContraseña;
	private JLabel lblAgregarUsuario;
	Intermedio acceso = new Intermedio();
	public static String presentacion  =  "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_InicioSesion frame = new GUI_InicioSesion();
					frame.setLocationRelativeTo(null);
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
	public GUI_InicioSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 347);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblbienvenido = new JLabel("¡Bienvenido!");
		lblbienvenido.setBounds(189, 21, 167, 29);
		lblbienvenido.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblbienvenido);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblUsuario.setBounds(57, 92, 167, 29);
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblContrasea.setBounds(57, 143, 167, 29);
		contentPane.add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setBounds(247, 92, 215, 29);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContraseña = new JTextField();
		textContraseña.setColumns(10);
		textContraseña.setBounds(247, 143, 215, 29);
		contentPane.add(textContraseña);
		
		lblAgregarUsuario = new JLabel("Agregar Usuario Nuevo");
		lblAgregarUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				//Clave: 123Admin
				String clave = JOptionPane.showInputDialog("Para esto se necesitan permiso de administrador, ingrese la clave: ");
				if(clave.equals("123Admin")) {
					JOptionPane.showMessageDialog(null, "¡Acceso Correcto!", "Acceso", JOptionPane.INFORMATION_MESSAGE);
					GUI_CrearUsuario newframe = new GUI_CrearUsuario();
					newframe.Registrador = acceso;
					newframe.setLocationRelativeTo(null);
					newframe.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Clave de administrador incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		lblAgregarUsuario.setForeground(Color.BLUE);
		lblAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAgregarUsuario.setBounds(172, 266, 212, 21);
		contentPane.add(lblAgregarUsuario);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuario = textUsuario.getText();
				String contraseña = textContraseña.getText();
				boolean validador = acceso.buscarUsuario(usuario, contraseña);
				presentacion = textUsuario.getText();
				
				if(validador) {
					JOptionPane.showMessageDialog(null, "¡Acceso Correcto!");
					GUI_Caja newframe = new GUI_Caja();
					newframe.admin = acceso;
					newframe.setLocationRelativeTo(null);
					newframe.setVisible(true);
					dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIngresar.setBounds(209, 207, 123, 35);
		contentPane.add(btnIngresar);
	}
}
