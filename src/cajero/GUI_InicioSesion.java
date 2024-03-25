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

public class GUI_InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblAgregarUsuario;
	Intermedio acceso = new Intermedio();

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
		setBounds(100, 100, 561, 312);
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
		
		textField = new JTextField();
		textField.setBounds(247, 92, 215, 29);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(247, 143, 215, 29);
		contentPane.add(textField_1);
		
		lblAgregarUsuario = new JLabel("Agregar Usuario Nuevo");
		lblAgregarUsuario.setForeground(Color.BLUE);
		lblAgregarUsuario.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblAgregarUsuario.setBounds(176, 214, 212, 21);
		contentPane.add(lblAgregarUsuario);
	}
}
