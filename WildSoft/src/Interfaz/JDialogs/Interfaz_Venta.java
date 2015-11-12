package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Servicios.Principal_Negocio_Interfaz;
import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz_Venta extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private Principal_Negocio_Interfaz Principal;
	
	
	public Interfaz_Venta(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Reporte De Ventas");
		Principal = instancia_negocio;
		
		setResizable(false);
		setBounds(100, 100, 726, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(32, 22, 403, 73);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblDia = new JLabel("Dia :");
		lblDia.setBounds(22, 26, 53, 24);
		panel_1.add(lblDia);
		
		JDateChooser dateChooserDia = new JDateChooser();
		dateChooserDia.setBounds(162, 23, 130, 27);
		panel_1.add(dateChooserDia);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(30, 114, 405, 73);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMes = new JLabel("Mes : ");
		lblMes.setBounds(24, 26, 53, 24);
		panel_2.add(lblMes);
		
		JDateChooser dateChooserMes = new JDateChooser();
		dateChooserMes.setBounds(165, 26, 130, 27);
		panel_2.add(dateChooserMes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(30, 217, 405, 83);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblEntreFechas = new JLabel("Entre Fechas:");
		lblEntreFechas.setBounds(10, 29, 91, 24);
		panel_3.add(lblEntreFechas);
		
		JDateChooser dateChooserHasta = new JDateChooser();
		dateChooserHasta.setBounds(110, 26, 130, 27);
		panel_3.add(dateChooserHasta);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(153, 59, 53, 24);
		panel_3.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(293, 59, 53, 24);
		panel_3.add(lblHasta);
		
		JDateChooser dateChooserDesde = new JDateChooser();
		dateChooserDesde.setBounds(250, 26, 130, 27);
		panel_3.add(dateChooserDesde);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGenerarReporte.setBounds(479, 114, 124, 56);
		panel.add(btnGenerarReporte);
		
		JButton button = new JButton("Salir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(642, 318, 68, 42);
		panel.add(button);
		
		

	}
}
