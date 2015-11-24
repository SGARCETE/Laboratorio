package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
	private ButtonGroup grupoTipo ;
	private JMonthChooser dateChooserMes ;
	private HashMap<Integer, String> categorias;
	private JComboBox<String> comboBoxCategoria;
	private Servicio_Productos SvProducto;
	private final ButtonGroup grupoPeriodo = new ButtonGroup();
	private SimpleDateFormat formato_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private JRadioButton rdbtnPorDia;
	private JRadioButton rdbtnPorSemana;
	private JRadioButton rdbtnPorMes;
	private JRadioButton rdbtnEntreFechas;
	private JDateChooser dateChooserSemana;
	private JPanel panel_9;
	
	public Interfaz_Venta(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Reporte De Ventas");
		Principal = instancia_negocio;
		SvProducto = Principal.getSvProductos();
		setResizable(false);
		setBounds(100, 100, 505, 574);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_5.setBounds(184, 6, 303, 86);
		panel.add(panel_5);
		panel_5.setLayout(null);
		
		comboBoxCategoria = new JComboBox<String>();
		comboBoxCategoria.setBounds(30, 32, 243, 27);
		panel_5.add(comboBoxCategoria);
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(Color.WHITE);
		panel_6.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Seleccione Tipo ", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_6.setBounds(10, 6, 162, 86);
		panel.add(panel_6);
		panel_6.setLayout(null);
		
		rdbtnProducto = new JRadioButton("Producto");
		rdbtnProducto.setSelected(true);
		rdbtnProducto.setOpaque(false);
		rdbtnProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxCategoria.setEnabled(true);
			}
		});
		rdbtnProducto.setBounds(20, 17, 109, 23);
		panel_6.add(rdbtnProducto);
		
		rdbtnCliente = new JRadioButton("Cliente");
		rdbtnCliente.setOpaque(false);
		rdbtnCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboBoxCategoria.setEnabled(false);
			}
		});
		rdbtnCliente.setBounds(20, 46, 109, 23);
		panel_6.add(rdbtnCliente);
		
		grupoTipo = new ButtonGroup();
		grupoTipo.add(rdbtnProducto);
		grupoTipo.add(rdbtnCliente);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new TitledBorder(null, "Periodo del reporte", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_4.setBackground(Color.WHITE);
		panel_4.setBounds(10, 104, 477, 285);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(16, 85, 440, 53);
		panel_4.add(panel_3);
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setLayout(null);
		
		rdbtnPorSemana = new JRadioButton("semana");
		rdbtnPorSemana.setOpaque(false);
		rdbtnPorSemana.setToolTipText("Su fecha m\u00E1s los 7 dias que le siguen");
		rdbtnPorSemana.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deshabilitar_lo_que_no_Sirve();
			}
		});
		rdbtnPorSemana.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnPorSemana.setBounds(10, 10, 115, 27);
		panel_3.add(rdbtnPorSemana);
		grupoPeriodo.add(rdbtnPorSemana);
		
		dateChooserSemana = new JDateChooser();
		dateChooserSemana.setToolTipText("");
		dateChooserSemana.setEnabled(false);
		dateChooserSemana.setBounds(133, 10, 130, 27);
		panel_3.add(dateChooserSemana);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(16, 150, 440, 53);
		panel_4.add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		
		dateChooserMes = new JMonthChooser();
		dateChooserMes.setEnabled(false);
		dateChooserMes.setBounds(133, 10, 115, 27);
		panel_1.add(dateChooserMes);
		
		rdbtnPorMes = new JRadioButton("mes");
		rdbtnPorMes.setOpaque(false);
		rdbtnPorMes.setToolTipText("del a\u00F1o actual");
		rdbtnPorMes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deshabilitar_lo_que_no_Sirve();
			}
		});
		rdbtnPorMes.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnPorMes.setBounds(10, 10, 115, 27);
		panel_1.add(rdbtnPorMes);
		grupoPeriodo.add(rdbtnPorMes);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(16, 215, 440, 53);
		panel_4.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		
		dateChooserHasta = new JDateChooser();
		dateChooserHasta.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (dateChooserDesde!=null && dateChooserDesde.getCalendar()!=null &&dateChooserDesde.getCalendar().compareTo(dateChooserHasta.getCalendar())>0) {
					dateChooserHasta.setCalendar(dateChooserDesde.getCalendar());
				}
			}
		});
		dateChooserHasta.setEnabled(false);
		dateChooserHasta.setBounds(300, 10, 130, 27);
		panel_2.add(dateChooserHasta);
		
		dateChooserDesde = new JDateChooser();
		dateChooserDesde.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent arg0) {
				dateChooserHasta.setCalendar(dateChooserDesde.getCalendar());
			}
		});
		dateChooserDesde.setEnabled(false);
		dateChooserDesde.setBounds(133, 10, 130, 27);
		panel_2.add(dateChooserDesde);
		
		JLabel lblHasta = new JLabel("al");
		lblHasta.setHorizontalAlignment(SwingConstants.CENTER);
		lblHasta.setBounds(265, 10, 34, 27);
		panel_2.add(lblHasta);
		

		
		rdbtnEntreFechas = new JRadioButton("entre fechas");
		rdbtnEntreFechas.setOpaque(false);
		rdbtnEntreFechas.setToolTipText("Fechas personalizadas");
		rdbtnEntreFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Deshabilitar_lo_que_no_Sirve();
			}
		});
		rdbtnEntreFechas.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnEntreFechas.setBounds(10, 10, 115, 27);
		panel_2.add(rdbtnEntreFechas);
		grupoPeriodo.add(rdbtnEntreFechas);
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(16, 20, 440, 53);
		panel_4.add(panel_8);
		panel_8.setLayout(null);
		panel_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_8.setBackground(Color.WHITE);
		
		rdbtnPorDia = new JRadioButton("d\u00EDa");
		rdbtnPorDia.setOpaque(false);
		rdbtnPorDia.setToolTipText("Solo de dia en particular");
		rdbtnPorDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Deshabilitar_lo_que_no_Sirve();
			}
		});
		rdbtnPorDia.setFont(new Font("SansSerif", Font.PLAIN, 16));
		rdbtnPorDia.setBounds(10, 10, 115, 27);
		panel_8.add(rdbtnPorDia);
		rdbtnPorDia.setSelected(true);
		grupoPeriodo.add(rdbtnPorDia);
		
		dateChooserDia = new JDateChooser();
		dateChooserDia.setBounds(133, 10, 130, 27);
		panel_8.add(dateChooserDia);
		
		panel_9 = new JPanel();
		panel_9.setBorder(new TitledBorder(null, "Generar", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_9.setBackground(Color.WHITE);
		panel_9.setBounds(10, 388, 477, 83);
		panel.add(panel_9);
		panel_9.setLayout(null);
		
		JButton btnPimba = new JButton("Generar reporte");
		btnPimba.setBackground(Color.WHITE);
		btnPimba.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/Product-sale-report-icon32.png")));
		btnPimba.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generar_Reporte_Generico();
			}
		});
		btnPimba.setBounds(145, 20, 179, 50);
		panel_9.add(btnPimba);
		
		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_7.setBackground(new Color(60, 179, 113));
		getContentPane().add(panel_7, BorderLayout.SOUTH);
		panel_7.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		JButton btnSalir = new JButton("    Cerrar    ");
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel_7.add(btnSalir);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setIcon(new ImageIcon(Interfaz_Venta.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		inicializar();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void inicializar() {
		dateChooserDia.setEnabled(true);
		
		// Fechas por defecto HOY
		Calendar HOY = new GregorianCalendar();
		dateChooserDia.setCalendar(HOY);
		dateChooserSemana.setCalendar(HOY);
		dateChooserMes.setMonth(HOY.get(Calendar.MONTH));
		dateChooserDesde.setCalendar(HOY);
		dateChooserHasta.setCalendar(HOY);
		
		
		comboBoxCategoria.addItem("Seleccione Categoria");

		categorias = SvProducto.getProductoshas();
		for (Entry<Integer, String> entry : categorias.entrySet()) {
		    String value = entry.getValue();
		    comboBoxCategoria.addItem(value);
		    
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Generar_Reporte_Generico() {
		
		// PREPARA LA FECHA
		Calendar F1 = null;
		Calendar F2 = null;
		
		if(rdbtnPorDia.isSelected()){
			F1 = dateChooserDia.getCalendar();
			F2 = dateChooserDia.getCalendar();
		}
				
		if(rdbtnPorSemana.isSelected()){
			
			Calendar C2 = getLunes(dateChooserSemana.getCalendar());
			Calendar C3 = getLunes(dateChooserSemana.getCalendar()); // Toma el lunes más cercano al dia seleccionado.
			C3.add(Calendar.DAY_OF_MONTH, 6);	// AL DIA SELECCIONADO SE LE SUMAN 6 DIAS (7 con el que se seleccionó)
			F1 = C2;
			System.out.println(formato_ddMMyyyy.format(C2.getTime()));
			System.out.println(formato_ddMMyyyy.format(C3.getTime()));
			F2 = C3;
		}
		
		if(rdbtnPorMes.isSelected()){
			// Primer dia del mes
			F1 = new GregorianCalendar();
			F1.set(Calendar.DAY_OF_MONTH, 1);
			F1.set(Calendar.MONTH, dateChooserMes.getMonth());
			// Ultimo dia del mes
			F2 = new GregorianCalendar();
			F2.set(Calendar.DAY_OF_MONTH, F2.getMaximum(Calendar.DAY_OF_MONTH));
			F2.set(Calendar.MONTH, dateChooserMes.getMonth());
		}
		
		if(rdbtnEntreFechas.isSelected()){
			F1 = dateChooserDesde.getCalendar();
			F2 = dateChooserHasta.getCalendar();
		}
		
		// GENERA EL REPORTE SEGUN LO QUE SE SELECCIONÓ, CLIENTES O PRODUCTOS
		if(rdbtnCliente.isSelected() && F1!=null && F2!=null){			// REPORTE CLIENTES
			ReporteMejoresClientes rc= new ReporteMejoresClientes();
			rc.GenerarReporteMejoresClientes(F1.getTime(), F2.getTime());
		}
		else if(rdbtnProducto.isSelected() && F1!=null && F2!=null){	// REPORTE VENTA TIPO DE PRODUCTO
			if(comboBoxCategoria.getSelectedIndex()!=0){
				Integer id = -1;
				for (Entry<Integer, String> entry : categorias.entrySet()) {
					String value = entry.getValue();
					if(comboBoxCategoria.getSelectedItem().equals(value)){
						id = entry.getKey();;
					}			    
				}
				ReporteVentas rv= new ReporteVentas(id,F1.getTime(),F2.getTime());
				rv.MOSTRAR_REPORTE();
			}
			else
				JOptionPane.showMessageDialog(null, "Debe seleccionar una Categoria", "Falta categoria", JOptionPane.WARNING_MESSAGE);
		}
		else
			JOptionPane.showMessageDialog(null, "Fecha incorrecta\nAsegurese de que las fechas esten correctas y no falte ninguna", "Fecha no valida", JOptionPane.WARNING_MESSAGE);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Deshabilitar_lo_que_no_Sirve() {
		dateChooserDia.setEnabled(false);
		dateChooserSemana.setEnabled(false);
		dateChooserMes.setEnabled(false);
		dateChooserDesde.setEnabled(false);	
		dateChooserHasta.setEnabled(false);
				
		if(rdbtnPorDia.isSelected()){
			dateChooserDia.setEnabled(true);
		}
				
		if(rdbtnPorSemana.isSelected()){
			dateChooserSemana.setEnabled(true);
			
		}
		
		if(rdbtnPorMes.isSelected()){
			dateChooserMes.setEnabled(true);
		}
		
		if(rdbtnEntreFechas.isSelected()){
			dateChooserDesde.setEnabled(true);	
			dateChooserHasta.setEnabled(true);
		}
		
	}
	
	private Calendar getLunes(Calendar C) {
		switch (C.get(Calendar.DAY_OF_WEEK)){
		case 1:
			C.add(Calendar.DAY_OF_MONTH, 0);
			return C;
		case 2:
			C.add(Calendar.DAY_OF_MONTH, -1);
			return C;
		case 3:
			C.add(Calendar.DAY_OF_MONTH, -2);
			return C;
		case 4:
			C.add(Calendar.DAY_OF_MONTH, -3);
			return C;
		case 5:
			C.add(Calendar.DAY_OF_MONTH, -4);
			return C;
		case 6:
			C.add(Calendar.DAY_OF_MONTH, -5);
			return C;
		case 7:
			C.add(Calendar.DAY_OF_MONTH, -6);
			return C;
		}
		return C;
		
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}//---> FIN CLASE
