package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Solicitud_compra;

@SuppressWarnings("serial")
public class auxiliar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private Servicio_Solicitud_compra sv_SolicitudCompra;


	public auxiliar(final Principal_Negocio_Interfaz principal_neg_int ,final int id) {
		
		sv_SolicitudCompra = principal_neg_int.getSvSolicitudCompra();
		
		setBounds(100, 100, 455, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(120, 54, 188, 33);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Al agregar el precio a esta solicitud cambiara el estado a \"Pagada\"");
		lblNewLabel.setBounds(10, 11, 414, 32);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(20, 57, 46, 30);
		contentPanel.add(lblTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Solicitud_compra sc = sv_SolicitudCompra.obtenerSolicitud(id);
//						Solicitud_compra sc = new Solicitud_compra();
//						sc.setId(id);
//						sv_SolicitudCompra.MODIFICAR_ESTADO(sc, 3);
//						sc.setPrecio(Integer.parseInt(textField.getText()));
//						sv_SolicitudCompra.MODIFICAR_Solicitud(sc);
						sv_SolicitudCompra.modificacionMAAAAAAAL(id, Integer.valueOf(textField.getText()));
						dispose();
						Interfaz_Solicitud_Compra frame = new Interfaz_Solicitud_Compra(principal_neg_int);
						frame.setVisible(true);
						frame.setModal(true);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
						Interfaz_Solicitud_Compra frame = new Interfaz_Solicitud_Compra(principal_neg_int);
						frame.setVisible(true);
						frame.setModal(true);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		textField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					getToolkit().beep();
					e.consume();
					}
				}
			});
		}
	
	
}
