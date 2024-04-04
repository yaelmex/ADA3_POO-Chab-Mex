package cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class GUI_Transferencia extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textOrigen;
	private JTextField textDestino;
	private JTextField textMonto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Transferencia frame = new GUI_Transferencia();
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
	public GUI_Transferencia() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 528, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("[Aquí irá el banner de presentación]");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 514, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblCuentaOrigen = new JLabel("Cuenta Origen:");
		lblCuentaOrigen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCuentaOrigen.setBounds(22, 69, 149, 38);
		contentPane.add(lblCuentaOrigen);
		
		JLabel lblCuentaDestino = new JLabel("Cuenta Destino:");
		lblCuentaDestino.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCuentaDestino.setBounds(22, 135, 137, 38);
		contentPane.add(lblCuentaDestino);
		
		JLabel lblCuentaDestino_1 = new JLabel("Monto:");
		lblCuentaDestino_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCuentaDestino_1.setBounds(67, 204, 92, 38);
		contentPane.add(lblCuentaDestino_1);
		
		textOrigen = new JTextField();
		textOrigen.setBounds(199, 80, 227, 20);
		contentPane.add(textOrigen);
		textOrigen.setColumns(10);
		
		textDestino = new JTextField();
		textDestino.setColumns(10);
		textDestino.setBounds(199, 146, 227, 20);
		contentPane.add(textDestino);
		
		textMonto = new JTextField();
		textMonto.setColumns(10);
		textMonto.setBounds(199, 215, 227, 20);
		contentPane.add(textMonto);
		
		JButton btnTransferir = new JButton("Transferir");
		btnTransferir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Intermedio Transferencia = new Intermedio();
					String origen = textOrigen.getText();
					String destino = textDestino.getText();
					float monto = Float.parseFloat(textMonto.getText());
					String resul = Transferencia.transferencia(origen, destino, monto);
					JOptionPane.showMessageDialog(null, resul);
					GUI_InicioSesion.bitacora.add(GUI_InicioSesion.presentacion + " hizo una transferencia de la cuenta: " + origen +
							" por: " + textMonto.getText() + " hacia la cuenta: " + destino);
					GUI_Caja newframe = new GUI_Caja();
					newframe.setLocationRelativeTo(null);
					newframe.setVisible(true);
					dispose();
				} catch(NullPointerException a) {
					JOptionPane.showMessageDialog(null, "Proceso Interrumpido", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(NumberFormatException b) {
					JOptionPane.showMessageDialog(null, "No ingreses letras en el monto", "Error", JOptionPane.ERROR_MESSAGE);
				} catch(ArithmeticException c) {
					JOptionPane.showMessageDialog(null, "Error al transferir", "Error", JOptionPane.ERROR_MESSAGE);
				}
				

			}
		});
		btnTransferir.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTransferir.setBounds(184, 275, 120, 38);
		contentPane.add(btnTransferir);
	}
}