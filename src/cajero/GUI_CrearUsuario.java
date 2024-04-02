package cajero;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	Intermedio Registrador;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_CrearUsuario frame = new GUI_CrearUsuario();
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
	public GUI_CrearUsuario() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 424, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setBounds(133, 45, 116, 29);
		lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblRegistro);
		
		JLabel lblNewLabel_1 = new JLabel("Nombre(s)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1.setBounds(41, 113, 120, 20);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Apellido(s):");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(41, 176, 120, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Contraseña:");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel_1_1_1.setBounds(41, 242, 120, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		JButton btnRegistrar = new JButton("Registrar e Iniciar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = textNombre.getText() + " " + textApellidos.getText();
				String apellidos = textApellidos.getText();
				String contraseña = textContraseña.getText();
				String usuario = nombre.substring(0,1).toUpperCase() + apellidos.substring(0,3).toUpperCase();
				Registrador.agregarUsuario(nombre, usuario, contraseña);
				JOptionPane.showMessageDialog(null, "¡Usuario añadido con éxito, empecemos!");
				GUI_InicioSesion newframe = new GUI_InicioSesion();
				newframe.sesion = Registrador;
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();
				
			}
		});
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnRegistrar.setBounds(89, 299, 206, 29);
		contentPane.add(btnRegistrar);
		
		textNombre = new JTextField();
		textNombre.setColumns(10);
		textNombre.setBounds(173, 113, 176, 20);
		contentPane.add(textNombre);
		
		textApellidos = new JTextField();
		textApellidos.setColumns(10);
		textApellidos.setBounds(173, 176, 176, 20);
		contentPane.add(textApellidos);
		
		textContraseña = new JTextField();
		textContraseña.setColumns(10);
		textContraseña.setBounds(173, 242, 176, 20);
		contentPane.add(textContraseña);
	}
}
