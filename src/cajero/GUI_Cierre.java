package cajero;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI_Cierre extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Cierre frame = new GUI_Cierre();
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
	public GUI_Cierre() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 319);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("[Aquí irá el banner de presentación]");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(0, 0, 514, 58);
		contentPane.add(lblNewLabel);
		
		JLabel lblCierreDeSucursal = new JLabel("Cierre de Sucursal Banco Capitalia");
		lblCierreDeSucursal.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblCierreDeSucursal.setBounds(116, 79, 277, 43);
		contentPane.add(lblCierreDeSucursal);
		
		JProgressBar progressProceso = new JProgressBar();
		progressProceso.setBounds(77, 135, 355, 22);
		contentPane.add(progressProceso);
		progressProceso.setStringPainted(true);
		
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Clave: 123Admin
				
				try {
					Intermedio cierre = new Intermedio();
					String clave = JOptionPane.showInputDialog("Para cerrar caja se necesitan permisos de administrador, ingrese la clave por favor: ");
					if(clave.equals("123Admin")) {
						
						Thread t1 = new Thread() {
							
							public void run() {
								
								while(progressProceso.getValue() <= 100) {
									progressProceso.setValue(progressProceso.getValue() + 10);
									if(progressProceso.getValue() == 100) {
										cierre.ImprimirBitacora(GUI_InicioSesion.bitacora);
										JOptionPane.showMessageDialog(null, "¡Bitácora generada con éxito!", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
										GUI_InicioSesion newframe = new GUI_InicioSesion();
										newframe.setLocationRelativeTo(null);
										newframe.setVisible(true);
										dispose();
										break;
									}	
									try {
										Thread.sleep(250);
									} catch(InterruptedException a) {
										a.printStackTrace();
									}
								}				
								
							}		
						};
						t1.start();	
						
					} else {
						JOptionPane.showMessageDialog(null, "Acceso Incorrecto", "Denegado", JOptionPane.ERROR_MESSAGE);
					}
				} catch(NullPointerException a) {
					JOptionPane.showMessageDialog(null, "Proceso Interrumpido", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
				

				
			}
		});
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCerrar.setBounds(116, 206, 120, 38);
		contentPane.add(btnCerrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUI_Caja newframe = new GUI_Caja();
				newframe.setLocationRelativeTo(null);
				newframe.setVisible(true);
				dispose();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelar.setBounds(273, 206, 120, 38);
		contentPane.add(btnCancelar);
	}
}
