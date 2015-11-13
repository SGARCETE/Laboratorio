package Interfaz.JDialogs;

import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.toedter.calendar.JDateChooser;

import Reportes.ReporteContabilidad;
import java.awt.Color;


public class Interfaz_Contabilidad extends JDialog{

	private static final long serialVersionUID = 4413938744233164340L
	
	;
	private JDateChooser dateChooser_2;
	private JDateChooser dateChooser_1;

	
	public Interfaz_Contabilidad() {
		setTitle("Mini contabilidad");
		getContentPane().setBackground(Color.WHITE);
		initialize();
	}

	private void initialize() {

		setBounds(100, 100, 767, 311);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnGenerarContabilidad = new JButton("Generar Contabilidad");
		btnGenerarContabilidad.setBackground(Color.WHITE);
		btnGenerarContabilidad.setHorizontalAlignment(SwingConstants.LEADING);
		btnGenerarContabilidad.setIcon(new ImageIcon(Interfaz_Contabilidad.class.getResource("/Recursos/IMG/Product-sale-report-icon24.png")));
		btnGenerarContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarMiniContabilidad();
		}
		});
		btnGenerarContabilidad.setBounds(502, 110, 178, 42);
		getContentPane().add(btnGenerarContabilidad);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnSalir.setBounds(673, 219, 68, 42);
		getContentPane().add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Selecciona Contabilidad", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(122, 22, 351, 181);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		Label labelPorMes = new Label("Por Mes :");
		labelPorMes.setBounds(159, 45, 86, 22);
		panel.add(labelPorMes);
		
		dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(197, 89, 130, 27);
		panel.add(dateChooser_2);
		
		dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(52, 89, 130, 27);
		panel.add(dateChooser_1);
		
		Label labelHasta = new Label("Hasta");
		labelHasta.setBounds(231, 122, 62, 22);
		panel.add(labelHasta);
		
		Label labelDesde = new Label("Desde");
		labelDesde.setBounds(97, 127, 78, 27);
		panel.add(labelDesde);
		
		// Establece la fecha de hoy por defecto
		Calendar C = new GregorianCalendar();
		dateChooser_1.setCalendar(C);
		dateChooser_2.setCalendar(C);
	}
	
	private void GenerarMiniContabilidad() {
		if(dateChooser_1.getCalendar()!=null && dateChooser_2.getCalendar()!=null){
			Date Fecha1 = dateChooser_1.getCalendar().getTime();
			Date Fecha2 = dateChooser_2.getCalendar().getTime();

			ReporteContabilidad rc = new ReporteContabilidad();
			rc.Generar_Contabilidad(Fecha1, Fecha2);
		}
		
	}
}

