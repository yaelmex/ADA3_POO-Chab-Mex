package cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class GUI_Abono extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtAbono;
	Intermedio metAbono = new Intermedio();
	String nombre = ""; String code = "";
 private JTextField txtUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Abono frame = new GUI_Abono();
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
	
	
	public GUI_Abono() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 454);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(52, 27, 83, 15);
		contentPane.add(lblUsuario);
		
		JLabel lblnombreDeUsuario = new JLabel("[Escriba nombre de usuario]");
		lblnombreDeUsuario.setText(metAbono.setNombre(GUI_InicioSesion.presentacion));
		lblnombreDeUsuario.setBounds(190, 26, 239, 15);
		contentPane.add(lblnombreDeUsuario);
		
		JLabel lblCodigo = new JLabel("Codigo");
		lblCodigo.setBounds(50, 51, 83, 15);
		contentPane.add(lblCodigo);
		
		JLabel lblCode = new JLabel("[Codigo asignado a usuario]");
		lblCode.setText(GUI_InicioSesion.presentacion);
		lblCode.setBounds(188, 51, 198, 15);
		contentPane.add(lblCode);
		
		JLabel lblAbonar = new JLabel("Monto de abono");
		lblAbonar.setBounds(50, 111, 84, 14);
		contentPane.add(lblAbonar);
		
		txtAbono = new JTextField();
		txtAbono.setBounds(186, 106, 214, 20);
		contentPane.add(txtAbono);
		txtAbono.setColumns(10);
		
		JButton btnAbonar = new JButton("Abonar");
		btnAbonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intermedio envAbono = new Intermedio();
				envAbono.addAbono(txtAbono.getText(), txtUser.getText());
			}
		});
		btnAbonar.setBounds(43, 197, 89, 23);
		contentPane.add(btnAbonar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Caja venCaja = new GUI_Caja();
				venCaja.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(187, 196, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblCuenAbono = new JLabel("Cuenta a abonar");
		lblCuenAbono.setBounds(50, 141, 84, 14);
		contentPane.add(lblCuenAbono);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(186, 138, 214, 20);
		contentPane.add(txtUser);
	}
}
