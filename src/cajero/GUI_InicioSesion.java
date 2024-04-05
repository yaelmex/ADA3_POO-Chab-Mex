package cajero;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class GUI_InicioSesion extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JLabel lblAgregarUsuario;
	Intermedio acceso = new Intermedio();
	Intermedio sesion = new Intermedio();

	public static String presentacion  =  "";
	public static String fecha = "";
	private JPasswordField textContraseña;
	public static ArrayList<String> bitacora = new ArrayList<>();

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
					frame.setResizable(false);
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
		setBounds(100, 100, 561, 403);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		Calendar calendario = Calendar.getInstance();
		String year = String.valueOf( calendario.get(Calendar.YEAR));
		String dia =  String.valueOf(calendario.get(Calendar.DATE));
		String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
		fecha = dia+"/"+mes+"/"+year;

		JLabel lblbienvenido = new JLabel();
		lblbienvenido.setBounds(15, 20, 514, 58);
		ImageIcon img2 = new ImageIcon("Imagenes//BannerInicio.png");
		contentPane.setLayout(null);
		lblbienvenido.setIcon(img2);
		lblbienvenido.setFont(new Font("Tahoma", Font.BOLD, 24));
		contentPane.add(lblbienvenido);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setBounds(46, 102, 167, 29);
		lblUsuario.setFont(new Font("Inter", Font.BOLD, 25));
		contentPane.add(lblUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setForeground(new Color(255, 255, 255));
		lblContrasea.setBounds(46, 157, 200, 29);
		lblContrasea.setFont(new Font("Inter", Font.BOLD, 25));
		contentPane.add(lblContrasea);
		
		textUsuario = new JTextField();
		textUsuario.setFont(new Font("Inter", Font.PLAIN, 15));
		textUsuario.setBounds(252, 108, 215, 29);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		textContraseña = new JPasswordField();
		textContraseña.setEchoChar('\u25CF');
		textContraseña.setFont(new Font("Inter", Font.PLAIN, 15));
		textContraseña.setBounds(252, 161, 215, 29);
		contentPane.add(textContraseña);
		
		lblAgregarUsuario = new JLabel("Agregar Usuario Nuevo");
		lblAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		lblAgregarUsuario.setBounds(138, 304, 268, 29);
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
			@Override
			public void mouseEntered(MouseEvent e) {
				lblAgregarUsuario.setFont(new Font("Inter", Font.BOLD, 18));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblAgregarUsuario.setFont(new Font("Inter", Font.BOLD, 17));
			}
		});
		lblAgregarUsuario.setForeground(new Color(255, 255, 255));
		lblAgregarUsuario.setFont(new Font("Inter", Font.BOLD, 17));
		contentPane.add(lblAgregarUsuario);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setForeground(new Color(0, 0, 0));
		btnIngresar.setBackground(new Color(255, 255, 255));
		btnIngresar.setBounds(165, 224, 214, 61);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					String usuario = textUsuario.getText();
					String contraseña = textContraseña.getText();
					boolean validador = acceso.buscarUsuario(usuario, contraseña);
					presentacion = textUsuario.getText();
					
					if(validador) {
						JOptionPane.showMessageDialog(null, "¡Acceso Correcto!");
						bitacora.add(usuario + " " + "Inicio sesión" + " en el horario " + acceso.getHora() + " con fecha: " + fecha);

						GUI_Caja newframe = new GUI_Caja();
						newframe.setLocationRelativeTo(null);
						newframe.setVisible(true);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Usuario o Contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch(NullPointerException a) {
					JOptionPane.showMessageDialog(null, "No se he encontrado el archivo de Usuarios", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnIngresar.setFont(new Font("Arapey", Font.PLAIN, 48));
		contentPane.add(btnIngresar);
		
		JButton NotShowPass = new JButton("");
		NotShowPass.setBackground(new Color(255, 255, 255));
		JButton ShowPass = new JButton("");
		ShowPass.setForeground(new Color(255, 255, 255));
		ShowPass.setBackground(new Color(255, 255, 255));
		ImageIcon EyeOpen = new ImageIcon("Imagenes//EyeClosed.png");
		ShowPass.setIcon(EyeOpen);
		ShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ShowPass.setVisible(false);
					NotShowPass.setVisible(true);
					textContraseña.setEchoChar((char)0);
			}
		});
		ShowPass.setFont(new Font("Inter", Font.PLAIN, 15));
		ShowPass.setBounds(473, 161, 40, 29);
		contentPane.add(ShowPass);
		
		ImageIcon eyeClosed = new ImageIcon("Imagenes//EyeOpen.png");
		NotShowPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NotShowPass.setVisible(false);
				ShowPass.setVisible(true);
				textContraseña.setFont(new Font("Inter", Font.PLAIN, 15));
				textContraseña.setEchoChar('\u25CF');
			}
		});
		NotShowPass.setFont(new Font("Inter", Font.PLAIN, 15));
		NotShowPass.setIcon(eyeClosed);
		NotShowPass.setBounds(473, 161, 40, 29);
		contentPane.add(NotShowPass);
		
	}
}
