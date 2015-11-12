package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Productos;

import javax.swing.JLabel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class Interfaz_Venta extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private Principal_Negocio_Interfaz Principal;
	
	private JDateChooser dateChooserMes ;
	private JDateChooser dateChooserHasta;
	private JDateChooser dateChooserDesde ;
	private JDateChooser dateChooserDia;
	
	private JRadioButton rdbtnReporteDia;
	private JRadioButton rdbtnReporteMes;
	private JRadioButton rdbtnReporteEntreFechas;
	private ButtonGroup grupo1 ;
	
	private Servicio_Productos SvProductos;
	
	private JComboBox<Producto> comboBox;
	
	public Interfaz_Venta(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Reporte De Ventas");
		Principal = instancia_negocio;
		SvProductos= Principal.getSvProductos();
		
		setResizable(false);
		setBounds(100, 100, 822, 496);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Reporte Mes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 248, 405, 73);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblMes = new JLabel("Mes : ");
		lblMes.setBounds(24, 26, 53, 24);
		panel_2.add(lblMes);
		
		dateChooserMes = new JDateChooser();
		dateChooserMes.setBounds(165, 26, 130, 27);
		panel_2.add(dateChooserMes);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Reporte Entre Fechas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 340, 405, 96);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblEntreFechas = new JLabel("Entre Fechas:");
		lblEntreFechas.setBounds(10, 29, 91, 24);
		panel_3.add(lblEntreFechas);
		
		dateChooserHasta = new JDateChooser();
		dateChooserHasta.setBounds(110, 26, 130, 27);
		panel_3.add(dateChooserHasta);
		
		JLabel lblDesde = new JLabel("Desde");
		lblDesde.setBounds(153, 59, 53, 24);
		panel_3.add(lblDesde);
		
		JLabel lblHasta = new JLabel("Hasta");
		lblHasta.setBounds(293, 59, 53, 24);
		panel_3.add(lblHasta);
		
		dateChooserDesde = new JDateChooser();
		dateChooserDesde.setBounds(250, 26, 130, 27);
		panel_3.add(dateChooserDesde);
		
		JButton btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/Product-sale-report-icon32.png")));
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnGenerarReporte.setBounds(559, 298, 173, 56);
		panel.add(btnGenerarReporte);
		
		JButton button = new JButton("Salir");
		button.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(725, 415, 81, 42);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 164, 403, 73);
		panel.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Reporte Dia", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblDia = new JLabel("Dia :");
		lblDia.setBounds(22, 26, 53, 24);
		panel_1.add(lblDia);
		
		dateChooserDia = new JDateChooser();
		dateChooserDia.setBounds(162, 23, 130, 27);
		panel_1.add(dateChooserDia);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Reporte", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 27, 211, 113);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		rdbtnReporteDia = new JRadioButton("Reporte Por Dia");
		rdbtnReporteDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooserMes.setEnabled(false);
				dateChooserDesde.setEnabled(false);
				dateChooserHasta.setEnabled(false);
				dateChooserDia.setEnabled(true);
				
				dateChooserDia.setDate(null);
				dateChooserMes.setDate(null);
				dateChooserDesde.setDate(null);
				dateChooserHasta.setDate(null);;
				
			}
		});
		rdbtnReporteDia.setBounds(27, 19, 135, 23);
		panel_4.add(rdbtnReporteDia);
		
		rdbtnReporteMes = new JRadioButton("Reporte Por Mes");
		rdbtnReporteMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooserDia.setEnabled(false);
				dateChooserDesde.setEnabled(false);
				dateChooserHasta.setEnabled(false);
				dateChooserMes.setEnabled(true);
				
				dateChooserDia.setDate(null);
				dateChooserMes.setDate(null);
				dateChooserDesde.setDate(null);
				dateChooserHasta.setDate(null);;
			}
		});
		rdbtnReporteMes.setBounds(27, 45, 135, 23);
		panel_4.add(rdbtnReporteMes);
		
		rdbtnReporteEntreFechas = new JRadioButton("Reporte Entre Fechas");
		rdbtnReporteEntreFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooserDia.setEnabled(false);
				dateChooserMes.setEnabled(false);
				dateChooserDesde.setEnabled(true);
				dateChooserHasta.setEnabled(true);
				
				dateChooserDia.setDate(null);
				dateChooserMes.setDate(null);
				dateChooserDesde.setDate(null);
				dateChooserHasta.setDate(null);;
			}
		});
		rdbtnReporteEntreFechas.setBounds(27, 71, 148, 23);
		panel_4.add(rdbtnReporteEntreFechas);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(489, 38, 284, 86);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		comboBox = new JComboBox<Producto>();
		comboBox.setBounds(10, 32, 264, 27);
		panel_5.add(comboBox);
		
		grupo1 = new ButtonGroup();
		grupo1.add(rdbtnReporteDia);
		grupo1.add(rdbtnReporteMes);
		grupo1.add(rdbtnReporteEntreFechas);
		
		JCheckBox chckbxReporteCliente = new JCheckBox("Reporte Cliente");
		chckbxReporteCliente.setBounds(588, 160, 144, 23);
		panel.add(chckbxReporteCliente);
		
		
	}
}
