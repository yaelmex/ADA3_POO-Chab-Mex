package cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Color;

public class GUI_Caja extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Intermedio admin;
	
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
	public GUI_Caja() {
		setResizable(false);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 527, 547);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 244, 244));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblimagen = new JLabel("[Imagen]");
		lblimagen.setForeground(new Color(0, 0, 0));
		lblimagen.setBackground(new Color(255, 255, 255));
		lblimagen.setIcon(new ImageIcon("Imagenes\\Transferencia.png"));
		lblimagen.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen.setBounds(398, 164, 77, 38);
		contentPane.add(lblimagen);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("Imagenes\\BannerModulos.png"));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 514, 58);
		contentPane.add(lblNewLabel);
		
		JButton btnTransferencia = new JButton();
		btnTransferencia.setIcon(new ImageIcon("Imagenes\\btnTransferencia.png"));
		btnTransferencia.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTransferencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Transferencia newframe = new GUI_Transferencia();
				newframe.Transferencia = admin;
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();
			}
		});
		btnTransferencia.setBounds(36, 164, 330, 38);
		contentPane.add(btnTransferencia);
		
		JButton btnAbonoACuenta = new JButton();
		btnAbonoACuenta.setIcon(new ImageIcon("Imagenes\\btnAbono.png"));
		btnAbonoACuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Abono ventana = new GUI_Abono();
				ventana.setVisible(true);
				dispose();
			}
		});
		btnAbonoACuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAbonoACuenta.setBounds(36, 233, 330, 38);
		contentPane.add(btnAbonoACuenta);
		
		JButton btnBusquedaDeCuenta = new JButton();
		btnBusquedaDeCuenta.setIcon(new ImageIcon("Imagenes\\btnBuscar.png"));
		btnBusquedaDeCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Buscador ventana = new GUI_Buscador();
				ventana.setLocationRelativeTo(null);
				ventana.setVisible(true);
			}
		});
		btnBusquedaDeCuenta.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBusquedaDeCuenta.setBounds(36, 297, 330, 38);
		contentPane.add(btnBusquedaDeCuenta);
		
		JButton btnCierreDeCaja = new JButton();
		btnCierreDeCaja.setIcon(new ImageIcon("Imagenes\\btnCierreCaja.png"));
		btnCierreDeCaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Cierre newframe = new GUI_Cierre();
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();
			}
		});
		btnCierreDeCaja.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCierreDeCaja.setBounds(36, 365, 330, 38);
		contentPane.add(btnCierreDeCaja);
		
		JLabel lblimagen_1 = new JLabel("[Imagen]");
		lblimagen_1.setIcon(new ImageIcon("Imagenes\\Abono.png"));
		lblimagen_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen_1.setBounds(398, 233, 77, 38);
		contentPane.add(lblimagen_1);
		
		JLabel lblimagen_1_1 = new JLabel("[Imagen]");
		lblimagen_1_1.setIcon(new ImageIcon("Imagenes\\Buscar.png"));
		lblimagen_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen_1_1.setBounds(398, 297, 77, 38);
		contentPane.add(lblimagen_1_1);
		
		JLabel lblimagen_1_1_1 = new JLabel("[Imagen]");
		lblimagen_1_1_1.setIcon(new ImageIcon("Imagenes\\Cierre.png"));
		lblimagen_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblimagen_1_1_1.setBounds(398, 365, 77, 38);
		contentPane.add(lblimagen_1_1_1);
		
		JLabel lblPresentacion = new JLabel("");
		lblPresentacion.setForeground(new Color(0, 0, 0));
		lblPresentacion.setFont(new Font("Inter", Font.PLAIN, 18));
		lblPresentacion.setBounds(36, 64, 330, 38);
		contentPane.add(lblPresentacion);
		lblPresentacion.setText("Buen día " + GUI_InicioSesion.presentacion + " :)");
		
		JLabel lblFecha = new JLabel("");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setForeground(new Color(0, 0, 0));
		lblFecha.setFont(new Font("Inter", Font.PLAIN, 18));
		lblFecha.setBounds(36, 416, 330, 38);
		contentPane.add(lblFecha);
		Calendar calendario = Calendar.getInstance();
		String year = String.valueOf( calendario.get(Calendar.YEAR));
		String dia =  String.valueOf(calendario.get(Calendar.DATE));
		String mes = String.valueOf(calendario.get(Calendar.MONTH)+1);
		lblFecha.setText("Fecha de hoy: "+dia+"/"+mes+"/"+year);
		
		JLabel lblMovimientos = new JLabel("¿Que desea hacer hoy?");
		lblMovimientos.setForeground(new Color(0, 0, 0));
		lblMovimientos.setFont(new Font("Inter", Font.PLAIN, 25));
		lblMovimientos.setBounds(36, 96, 341, 38);
		contentPane.add(lblMovimientos);
		
		JLabel lblHora = new JLabel();
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblHora.setForeground(Color.BLACK);
		lblHora.setFont(new Font("Inter", Font.PLAIN, 18));
		lblHora.setBounds(36, 449, 330, 38);
		Intermedio hora = new Intermedio();
		lblHora.setText("Hora actual: " + hora.getHora());
		contentPane.add(lblHora);

	}
}
