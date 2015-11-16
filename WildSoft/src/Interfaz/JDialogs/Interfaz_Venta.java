package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Productos;
import Reportes.ReporteMejoresClientes;
import Reportes.ReporteVentas;

public class Interfaz_Venta extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private Principal_Negocio_Interfaz Principal;
	private JDateChooser dateChooserHasta;
	private JDateChooser dateChooserDesde ;
	private JDateChooser dateChooserDia;
	private JRadioButton rdbtnProducto ;
	private JRadioButton rdbtnCliente ;
	private ButtonGroup grupo2 ;
	private JButton btnGenerarReporte ;
	private Servicio_Productos SvProductos;
	private JMonthChooser monthChooser ;
	private HashMap<Integer, String> categorias;
	private JComboBox<String> comboBox;
	private Servicio_Productos SvProducto;
	private JButton btnGenerarReporteProducto ;
	private JButton btnGenerarReporteFechas ;
	
	public Interfaz_Venta(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Reporte De Ventas");
		Principal = instancia_negocio;
		SvProductos= Principal.getSvProductos();
		SvProducto = Principal.getSvProductos();
		setResizable(false);
		setBounds(100, 100, 822, 496);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Reporte Mes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 248, 586, 73);
		panel.add(panel_2);
		panel_2.setLayout(null);
		 
		JLabel lblMes = new JLabel("Mes : ");
		lblMes.setBounds(24, 26, 53, 24);
		panel_2.add(lblMes);
		
		monthChooser = new JMonthChooser();
		monthChooser.setBounds(158, 26, 119, 20);
		panel_2.add(monthChooser);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Reporte Entre Fechas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 340, 701, 96);
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
		
		btnGenerarReporteFechas = new JButton("Generar Reporte Fechas");
		btnGenerarReporteFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarReporteFechas();
			}
		});
		btnGenerarReporteFechas.setBounds(458, 30, 186, 23);
		panel_3.add(btnGenerarReporteFechas);
		
		JButton button = new JButton("Salir");
		button.setBackground(Color.WHITE);
		button.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(725, 415, 81, 42);
		panel.add(button);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 164, 559, 73);
		panel.add(panel_1);
		panel_1.setBorder(new TitledBorder(null, "Reporte Dia", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		JLabel lblDia = new JLabel("Dia :");
		lblDia.setBounds(22, 26, 53, 24);
		panel_1.add(lblDia);
		
		dateChooserDia = new JDateChooser();
		dateChooserDia.setBounds(162, 23, 130, 27);
		panel_1.add(dateChooserDia);
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setBounds(345, 18, 173, 41);
		panel_1.add(btnGenerarReporte);
		btnGenerarReporte.setBackground(Color.WHITE);
		btnGenerarReporte.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/Product-sale-report-icon32.png")));
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerarReporte();
			}
		});
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(488, 27, 284, 86);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 32, 264, 27);
		panel_5.add(comboBox);
		
		new ButtonGroup();
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Tipo ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(243, 27, 211, 113);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		rdbtnProducto = new JRadioButton("Producto");
		rdbtnProducto.setOpaque(false);
		rdbtnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(true);
				btnGenerarReporteProducto.setEnabled(true);
			}
		});
		rdbtnProducto.setBounds(42, 28, 109, 23);
		panel_6.add(rdbtnProducto);
		
		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setOpaque(false);
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(false);
				btnGenerarReporteProducto.setEnabled(false);
				
			}
		});
		rdbtnCliente.setBounds(42, 70, 109, 23);
		panel_6.add(rdbtnCliente);
		
		grupo2 = new ButtonGroup();
		grupo2.add(rdbtnProducto);
		grupo2.add(rdbtnCliente);
		
		btnGenerarReporteProducto = new JButton("Generar Reporte Producto");
		btnGenerarReporteProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				GenerarReporteProducto();
			}
		});
		btnGenerarReporteProducto.setBounds(615, 145, 173, 53);
		panel.add(btnGenerarReporteProducto);
		
		inicializar();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void inicializar() {
	//	ComboProdutos();
		comboBox.setEnabled(false);
		monthChooser.setEnabled(false);
	//	dateChooserDesde.setEnabled(false);
	//	dateChooserHasta.setEnabled(false);
	//	rdbtnProducto.setEnabled(false);
	//	rdbtnCliente.setEnabled(false);
		
		
		comboBox.addItem("Seleccione Categoria");

		categorias = SvProducto.getProductoshas();
		for (Entry<Integer, String> entry : categorias.entrySet()) {
		    String value = entry.getValue();
		    comboBox.addItem(value);
		    
		}
	}
	
	//---------------- FIN!  -------------------------------------

	//---------------- LLena el combo box con los tipos productos  		
	public void ComboProdutos(){
		for (int j = 0; j < SvProductos.getLista_Productos().size(); j++) {
			comboBox.addItem(SvProductos.getLista_Productos().get(j));
			SvProductos.getLista_Productos();
		}
	}
	
	
	//---------------- Generar Reporte! ---------------------------	
	public void GenerarReporte(){
		if(rdbtnCliente.isSelected()){	// Esto esta comprobando el nombre del radio button, no si esta seleccionado ---> rdbtnCliente.getText().equals("Cliente")){
			Date f1 = dateChooserDia.getCalendar().getTime();
			Date f2= dateChooserDia.getCalendar().getTime();
			
			ReporteMejoresClientes rc= new ReporteMejoresClientes();
			rc.GenerarReporteMejoresClientes(f1, f2);
		}
	}
	
	public void GenerarReporteProducto(){
		
		Integer id = -1;
		for (Entry<Integer, String> entry : categorias.entrySet()) {
			String value = entry.getValue();
			if(comboBox.getSelectedItem().equals(value)){
				id = entry.getKey();;
			}			    
		}
		
		Calendar C = new GregorianCalendar();
		Date F1 = C.getTime();
		Date F2 = C.getTime();
		ReporteVentas rv= new ReporteVentas(id,F1,F2);
		rv.MOSTRAR_REPORTE();
	}
	public void GenerarReporteFechas(){
		if(rdbtnCliente.isSelected()){	// Esto esta comprobando el nombre del radio button, no si esta seleccionado ---> rdbtnCliente.getText().equals("Cliente")){
			Date f1 = dateChooserDesde.getCalendar().getTime();
			Date f2= dateChooserHasta.getCalendar().getTime();
			
			ReporteMejoresClientes rc= new ReporteMejoresClientes();
			rc.GenerarReporteMejoresClientes(f2, f1);
		}
		if(rdbtnProducto.isSelected()){	// Esto esta comprobando el nombre del radio button, no si esta seleccionado ---> rdbtnCliente.getText().equals("Cliente")){
			Date f1 = dateChooserDesde.getCalendar().getTime();
			Date f2= dateChooserHasta.getCalendar().getTime();
			
			ReporteMejoresClientes rc= new ReporteMejoresClientes();
			rc.GenerarReporteMejoresClientes(f2, f1);
		}
	}
	

	
	//----------------------- FIN! ------------------------------			
}
