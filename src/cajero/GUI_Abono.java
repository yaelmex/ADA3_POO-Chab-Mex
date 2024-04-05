package cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

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
	
	
	public GUI_Abono() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 531, 454);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 244, 244));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCodigo = new JLabel("Usuario");
		lblCodigo.setFont(new Font("Inter", Font.PLAIN, 18));
		lblCodigo.setBounds(10, 92, 83, 15);
		contentPane.add(lblCodigo);
		
		JLabel lblCode = new JLabel("[Codigo asignado a usuario]");
		lblCode.setFont(new Font("Inter", Font.PLAIN, 18));
		lblCode.setText(GUI_InicioSesion.presentacion);
		lblCode.setBounds(167, 92, 227, 29);
		contentPane.add(lblCode);
		
		JLabel lblAbonar = new JLabel("Monto de abono");
		lblAbonar.setFont(new Font("Inter", Font.PLAIN, 18));
		lblAbonar.setBounds(10, 145, 151, 26);
		contentPane.add(lblAbonar);
		
		txtAbono = new JTextField();
		txtAbono.setBounds(167, 148, 302, 26);
		contentPane.add(txtAbono);
		txtAbono.setColumns(10);
		
		JButton btnAbonar = new JButton();
		btnAbonar.setIcon(new ImageIcon("Imagenes\\ImgbtnAbonar.png"));
		btnAbonar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Intermedio envAbono = new Intermedio();
					envAbono.addAbono(txtAbono.getText(), txtUser.getText()); // Llamado y envio de parametros a metodo addAbono de la clase Intermedio
					JOptionPane.showMessageDialog(null, "¡Abono realizado con éxito!");
					GUI_InicioSesion.bitacora.add(lblCode.getText() + " " + "hizo un abono de: " + txtAbono.getText() + " a " + txtUser.getText() + " en el horario " + envAbono.getHora() +
							" con fecha: " + GUI_InicioSesion.fecha); // Adicion de registro a bitacora
				} catch(NullPointerException a) {
					JOptionPane.showMessageDialog(null, "Proceso Interrumpido", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(NumberFormatException b) {
					JOptionPane.showMessageDialog(null, "No ingreses letras en el monto", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(ArithmeticException c) {
					JOptionPane.showMessageDialog(null, "Error al abonar", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				txtAbono.setText(null);
				txtUser.setText(null);
			
			}
		});
		btnAbonar.setBounds(23, 303, 195, 55);
		contentPane.add(btnAbonar);
		
		JButton btnCancelar = new JButton();
		btnCancelar.setIcon(new ImageIcon("Imagenes\\ImgbtnCancelar.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Caja venCaja = new GUI_Caja();
				venCaja.setLocationRelativeTo(null);
				venCaja.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setBounds(256, 303, 195, 55);
		contentPane.add(btnCancelar);
		
		JLabel lblCuenAbono = new JLabel("Cuenta a abonar");
		lblCuenAbono.setFont(new Font("Inter", Font.PLAIN, 18));
		lblCuenAbono.setBounds(10, 221, 151, 22);
		contentPane.add(lblCuenAbono);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBounds(167, 221, 302, 29);
		contentPane.add(txtUser);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imagenes\\ModAbono.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 514, 58);
		contentPane.add(lblNewLabel);
	}
}
