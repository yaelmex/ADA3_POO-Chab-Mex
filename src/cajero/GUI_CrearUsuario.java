package cajero;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class GUI_CrearUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textContraseña;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_CrearUsuario frame = new GUI_CrearUsuario();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_CrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistro = new JLabel("Datos de registro");
		lblRegistro.setBounds(155, 86, 202, 29);
		lblRegistro.setFont(new Font("Inter", Font.BOLD, 24));
		contentPane.add(lblRegistro);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(s)");
		lblNewLabel_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1.setBounds(41, 150, 120, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido(s):");
		lblNewLabel_1_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(41, 207, 120, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contraseña:");
		lblNewLabel_1_1_1.setFont(new Font("Inter", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(41, 265, 120, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnRegistrar = new JButton();
		btnRegistrar.setIcon(new ImageIcon("Imagenes\\btnRegistraInicia.png"));
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intermedio Registrador = new Intermedio();
				String nombre = textNombre.getText() + " " + textApellidos.getText();
				String apellidos = textApellidos.getText();
				String contraseña = textContraseña.getText();
				String[] extraerApellidos = apellidos.split(" ");
				String usuario = nombre.substring(0,1).toUpperCase() + extraerApellidos[0].toUpperCase();
				Boolean verificar =Registrador.verificarVacio(nombre, usuario, contraseña);
				if(verificar == true) 
				{ JOptionPane.showMessageDialog(null, "Por favor rellene todos los campos");
				return;}
				Registrador.agregarUsuario(nombre, usuario, contraseña);
				JOptionPane.showMessageDialog(null, "¡Usuario añadido con éxito, empecemos!");
				GUI_InicioSesion newframe = new GUI_InicioSesion();
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();
				
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRegistrar.setBounds(116, 309, 279, 59);
		contentPane.add(btnRegistrar);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(167, 150, 291, 23);
		contentPane.add(textNombre);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(167, 207, 291, 23);
		contentPane.add(textApellidos);
		
		textContraseña = new JTextField();
		textContraseña.setColumns(10);
		textContraseña.setBounds(173, 265, 285, 23);
		contentPane.add(textContraseña);
		
		JLabel BannerRegistro = new JLabel("");
		BannerRegistro.setIcon(new ImageIcon("Imagenes\\BannerRegistro.png"));
		BannerRegistro.setFont(new Font("Tahoma", Font.BOLD, 15));
		BannerRegistro.setBounds(0, 0, 514, 58);
		contentPane.add(BannerRegistro);
	}
}