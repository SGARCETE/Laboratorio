package Interfaz.JDialogs;

import java.awt.Color;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import Reportes.ReporteContabilidad;


public class Interfaz_Contabilidad extends JDialog{

	private static final long serialVersionUID = 4413938744233164340L;
	private JDateChooser dateChooser_2;
	private JDateChooser dateChooser_1;
	private	JDateChooser dateChooser ;
	private ButtonGroup grupo1;
	private JRadioButton rdbtnReporteDia;
	private JRadioButton rdbtnReporteFechas ;
    public Interfaz_Contabilidad() {
		setTitle("Mini contabilidad");
		getContentPane().setBackground(Color.WHITE);
		initialize();
	}

	private void initialize() {

		setBounds(100, 100, 685, 311);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(Interfaz_Contabilidad.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(555, 219, 105, 42);
		getContentPane().add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Selecciona Contabilidad", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 22, 650, 181);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		Label labelPorMes = new Label("Por Fechas :");
		labelPorMes.setBounds(269, 82, 86, 22);
		panel.add(labelPorMes);
		
		dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(322, 110, 130, 32);
		panel.add(dateChooser_2);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(182, 110, 130, 32);
		panel.add(dateChooser_1);
		
		Label labelHasta = new Label("Hasta");
		labelHasta.setBounds(358, 138, 62, 22);
		panel.add(labelHasta);
		
		Label labelDesde = new Label("Desde");
		labelDesde.setBounds(213, 138, 78, 27);
		panel.add(labelDesde);
		
		// Establece la fecha de hoy por defecto
		Calendar C = new GregorianCalendar();
		dateChooser_1.setCalendar(C);
		dateChooser_2.setCalendar(C);
		
		dateChooser = new JDateChooser();
		dateChooser.setCalendar(new GregorianCalendar());
		dateChooser.setBounds(317, 33, 135, 32);
		panel.add(dateChooser);
		
		Label lblDelDia = new Label("Del Dia");
		lblDelDia.setBounds(250, 43, 62, 22);
		panel.add(lblDelDia);
		
		final JButton btnReporteDia = new JButton("Reporte Dia");
		btnReporteDia.setBounds(462, 33, 178, 32);
		panel.add(btnReporteDia);
		btnReporteDia.setIcon(new ImageIcon(Interfaz_Contabilidad.class.getResource("/Recursos/IMG/Product-sale-report-icon24.png")));
		
		final JButton btnGenerarContabilidad = new JButton("Generar Contabilidad");
		btnGenerarContabilidad.setBounds(462, 110, 178, 32);
		panel.add(btnGenerarContabilidad);
		btnGenerarContabilidad.setBackground(Color.WHITE);
		btnGenerarContabilidad.setHorizontalAlignment(SwingConstants.LEADING);
		btnGenerarContabilidad.setIcon(new ImageIcon(Interfaz_Contabilidad.class.getResource("/Recursos/IMG/Product-sale-report-icon24.png")));
		
		rdbtnReporteDia = new JRadioButton("Reporte Dia");
		rdbtnReporteDia.setOpaque(false);
		rdbtnReporteDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dateChooser.setEnabled(true);
				btnReporteDia.setEnabled(true);
				dateChooser_1.setEnabled(false);
				dateChooser_2.setEnabled(false);
				btnGenerarContabilidad.setEnabled(false);
			}
		});
		rdbtnReporteDia.setBounds(21, 33, 109, 32);
		panel.add(rdbtnReporteDia);
	
		rdbtnReporteFechas = new JRadioButton("Reporte Fechas");
		rdbtnReporteFechas.setOpaque(false);
		rdbtnReporteFechas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				dateChooser_1.setEnabled(true);
				dateChooser_2.setEnabled(true);
				btnGenerarContabilidad.setEnabled(true);
				dateChooser.setEnabled(false);
				btnReporteDia.setEnabled(false);
			}
		});
		rdbtnReporteFechas.setBounds(21, 110, 142, 32);
		panel.add(rdbtnReporteFechas);
		btnGenerarContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarMiniContabilidad();
		}
		});
		
		grupo1 = new ButtonGroup();
		grupo1.add(rdbtnReporteDia);
		grupo1.add(rdbtnReporteFechas);
		
		
		btnReporteDia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 GenerarMiniContabilidadDia();
			}
		});
	
	dateChooser.setEnabled(false);
	dateChooser_1.setEnabled(false);
	dateChooser_2.setEnabled(false);
	btnReporteDia.setEnabled(false);
	btnGenerarContabilidad.setEnabled(false);
	
	
	}
	
	
	
	private void GenerarMiniContabilidad() {
		if(dateChooser_1.getCalendar()!=null && dateChooser_2.getCalendar()!=null){
			Date Fecha1 = dateChooser_1.getCalendar().getTime();
			Date Fecha2 = dateChooser_2.getCalendar().getTime();

			ReporteContabilidad rc = new ReporteContabilidad();
			rc.Generar_Contabilidad(Fecha1, Fecha2);
		}
		
	}
	private void GenerarMiniContabilidadDia() {
		if(dateChooser.getCalendar()!=null){
			Date MismaFecha1 = dateChooser.getCalendar().getTime();
			
			ReporteContabilidad rc = new ReporteContabilidad();
			rc.Generar_Contabilidad(MismaFecha1, MismaFecha1);
		}
	
		
	}
	public void verificarRadio(){
		if(rdbtnReporteDia.isSelected())
		{
			dateChooser.setEnabled(true);
			rdbtnReporteDia.setEnabled(true);
		}
		else if(rdbtnReporteFechas.isSelected())
		{
			
		}
		
	}
	
}

