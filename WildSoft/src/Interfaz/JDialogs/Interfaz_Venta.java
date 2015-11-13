package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Productos;
import Reportes.ReporteMejoresClientes;
import java.util.Date;
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
import com.toedter.calendar.JMonthChooser;

public class Interfaz_Venta extends JDialog{
	
	
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private Principal_Negocio_Interfaz Principal;
	private JDateChooser dateChooserHasta;
	private JDateChooser dateChooserDesde ;
	private JDateChooser dateChooserDia;
	private JRadioButton rdbtnProducto ;
	private JRadioButton rdbtnCliente ;
	private JRadioButton rdbtnReporteDia;
	private JRadioButton rdbtnReporteMes;
	private JRadioButton rdbtnReporteEntreFechas;
	private ButtonGroup grupo1 ;
	private ButtonGroup grupo2 ;
	private JButton btnGenerarReporte ;
	private Servicio_Productos SvProductos;
	private JMonthChooser monthChooser ;
	
	private JComboBox<String> comboBox;
	
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
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Reporte Mes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(10, 248, 405, 73);
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
		
		btnGenerarReporte = new JButton("Generar Reporte");
		btnGenerarReporte.setBackground(Color.WHITE);
		btnGenerarReporte.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/Product-sale-report-icon32.png")));
		btnGenerarReporte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GenerarReporte();
			}
		});
		btnGenerarReporte.setBounds(538, 415, 173, 41);
		panel.add(btnGenerarReporte);
		
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
		panel_4.setBackground(Color.WHITE);
		panel_4.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Duracion", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_4.setBounds(10, 27, 211, 113);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		rdbtnReporteDia = new JRadioButton("Del D\u00EDa");
		rdbtnReporteDia.setOpaque(false);
		rdbtnReporteDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DejarDia();
			}
		});
		rdbtnReporteDia.setBounds(53, 19, 135, 23);
		panel_4.add(rdbtnReporteDia);
		
		rdbtnReporteMes = new JRadioButton("Del Mes");
		rdbtnReporteMes.setOpaque(false);
		rdbtnReporteMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DejarMes();
			}
		});
		rdbtnReporteMes.setBounds(53, 45, 135, 23);
		panel_4.add(rdbtnReporteMes);
		
		rdbtnReporteEntreFechas = new JRadioButton("Entre Fechas");
		rdbtnReporteEntreFechas.setOpaque(false);
		rdbtnReporteEntreFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DejarSoloEntreFechas();
			}
		});
		rdbtnReporteEntreFechas.setBounds(53, 71, 135, 23);
		panel_4.add(rdbtnReporteEntreFechas);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(488, 27, 284, 86);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 32, 264, 27);
		panel_5.add(comboBox);
		
		grupo1 = new ButtonGroup();
		grupo1.add(rdbtnReporteDia);
		grupo1.add(rdbtnReporteMes);
		grupo1.add(rdbtnReporteEntreFechas);
		
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
			}
		});
		rdbtnProducto.setBounds(42, 28, 109, 23);
		panel_6.add(rdbtnProducto);
		
		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setOpaque(false);
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBox.setEnabled(false);
				
			}
		});
		rdbtnCliente.setBounds(42, 70, 109, 23);
		panel_6.add(rdbtnCliente);
		
		grupo2 = new ButtonGroup();
		grupo2.add(rdbtnProducto);
		grupo2.add(rdbtnCliente);
		
		inicializar();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void inicializar() {
		ComboProdutos();
		comboBox.setEnabled(false);
		monthChooser.setEnabled(false);
		dateChooserDia.setEnabled(false);
		dateChooserDesde.setEnabled(false);
		dateChooserHasta.setEnabled(false);
		rdbtnProducto.setEnabled(false);
		rdbtnCliente.setEnabled(false);
	}
	
	//-------------------------------------------------------------	
	//---------------- Metodos que manejan las fechas  ------------
	//-------------------------------------------------------------	
	private void DejarDia(){
		monthChooser.setEnabled(false);
		dateChooserDesde.setEnabled(false);
		dateChooserHasta.setEnabled(false);
		dateChooserDia.setEnabled(true);
		dateChooserDia.setDate(null);
		monthChooser.setMonth(0);
		dateChooserDesde.setDate(null);
		dateChooserHasta.setDate(null);
		rdbtnProducto.setEnabled(true);
		rdbtnCliente.setEnabled(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void DejarSoloEntreFechas(){
		dateChooserDia.setEnabled(false);
		monthChooser.setEnabled(false);
		dateChooserDesde.setEnabled(true);
		dateChooserHasta.setEnabled(true);
		dateChooserDia.setDate(null);
		monthChooser.setMonth(0);
		dateChooserDesde.setDate(null);
		dateChooserHasta.setDate(null);
		rdbtnProducto.setEnabled(true);
		rdbtnCliente.setEnabled(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void DejarMes(){
		dateChooserDia.setEnabled(false);
		dateChooserDesde.setEnabled(false);
		dateChooserHasta.setEnabled(false);
		monthChooser.setEnabled(true);
		dateChooserDia.setDate(null);
		monthChooser.setMonth(0);
		dateChooserDesde.setDate(null);
		dateChooserHasta.setDate(null);
		rdbtnProducto.setEnabled(true);
		rdbtnCliente.setEnabled(true);
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
			Date f1 = dateChooserDesde.getCalendar().getTime();
			Date f2= dateChooserDesde.getCalendar().getTime();
			
			ReporteMejoresClientes rc= new ReporteMejoresClientes(f1, f2);
			rc.MOSTRAR_REPORTE();
		}
	}
	
	//----------------------- FIN! ------------------------------			
}
