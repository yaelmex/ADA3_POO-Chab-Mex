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
import java.time.LocalDate;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;

public class GUI_Caja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	//ramaCreada

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Caja frame = new GUI_Caja();
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
	public GUI_Caja() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 547);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("[Aquí irá el banner de presentación]");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 407, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnTransferencia = new JButton("Transferencia");
		btnTransferencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Transferencia newframe = new GUI_Transferencia();
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();
			}
		});
		btnTransferencia.setBounds(0, 138, 330, 38);
		contentPane.add(btnTransferencia);
		
		JButton btnAbonoACuenta = new JButton("Abono a Cuenta");
		btnAbonoACuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Abono ventana = new GUI_Abono();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
				dispose();
			}
		});
		btnAbonoACuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAbonoACuenta.setBounds(0, 187, 330, 38);
		contentPane.add(btnAbonoACuenta);
		
		JButton btnBusquedaDeCuenta = new JButton("Buscar Cuenta");
		btnBusquedaDeCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Buscador ventana = new GUI_Buscador();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
				dispose();
			}
		});
		btnBusquedaDeCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBusquedaDeCuenta.setBounds(0, 243, 330, 38);
		contentPane.add(btnBusquedaDeCuenta);
		
		JButton btnCierreDeCaja = new JButton("Cierre de Caja");
		btnCierreDeCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Cierre newframe = new GUI_Cierre();
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();		
			}
		});
		btnCierreDeCaja.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCierreDeCaja.setBounds(0, 297, 330, 38);
		contentPane.add(btnCierreDeCaja);
		
		JLabel lblimagen = new JLabel("[Imagen]");
		lblimagen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen.setBounds(330, 138, 77, 38);
		contentPane.add(lblimagen);
		
		JLabel lblimagen_1 = new JLabel("[Imagen]");
		lblimagen_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen_1.setBounds(330, 187, 77, 38);
		contentPane.add(lblimagen_1);
		
		JLabel lblimagen_1_1 = new JLabel("[Imagen]");
		lblimagen_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen_1_1.setBounds(330, 243, 77, 38);
		contentPane.add(lblimagen_1_1);
		
		JLabel lblimagen_1_1_1 = new JLabel("[Imagen]");
		lblimagen_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen_1_1_1.setBounds(330, 297, 77, 38);
		contentPane.add(lblimagen_1_1_1);
		
		JLabel lblPresentacion = new JLabel("");
		lblPresentacion.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblPresentacion.setBounds(10, 369, 320, 38);
		contentPane.add(lblPresentacion);
		lblPresentacion.setText("Buen día " + GUI_InicioSesion.presentacion + " :)");
		
		JLabel lblFecha = new JLabel("");
		lblFecha.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblFecha.setBounds(10, 418, 320, 38);
		contentPane.add(lblFecha);
		Calendar calendario = Calendar.getInstance();
		String year = String.valueOf( calendario.get(Calendar.YEAR));
		String dia =  String.valueOf(calendario.get(Calendar.DATE));
		String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
		lblFecha.setText("Fecha de hoy: "+dia+"/"+mes+"/"+year);
		
		JLabel lblMovimientos = new JLabel("Movimientos Disponibles");
		lblMovimientos.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblMovimientos.setBounds(87, 89, 212, 38);
		contentPane.add(lblMovimientos);

	}
}