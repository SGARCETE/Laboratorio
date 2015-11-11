package Interfaz.JDialogs;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Reportes.ReporteCompras;
import Reportes.ReportePedidos;
import com.toedter.calendar.JDateChooser;
import java.awt.Label;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Interfaz_Contabilidad {

	private JDialog frame;

	public Interfaz_Contabilidad() {
		initialize();
	}

	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 767, 396);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnGenerarContabilidad = new JButton("Generar Contabilidad");
		btnGenerarContabilidad.setHorizontalAlignment(SwingConstants.LEADING);
		btnGenerarContabilidad.setIcon(new ImageIcon(Interfaz_Contabilidad.class.getResource("/Recursos/IMG/Product-sale-report-icon24.png")));
		btnGenerarContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarContabilidadCompras();
				GenerarContabilidadPedidos();
		}
		});
		btnGenerarContabilidad.setBounds(434, 111, 178, 42);
		frame.getContentPane().add(btnGenerarContabilidad);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(673, 304, 68, 42);
		frame.getContentPane().add(btnSalir);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Selecciona Contabilidad", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(10, 22, 406, 252);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(102, 69, 130, 27);
		panel.add(dateChooser);
		
		Label labelPorDia = new Label("Por D\u00EDa :");
		labelPorDia.setBounds(10, 69, 78, 27);
		panel.add(labelPorDia);
		
		Label labelPorMes = new Label("Por Fechas :");
		labelPorMes.setBounds(10, 160, 86, 22);
		panel.add(labelPorMes);
		
		JDateChooser dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(254, 155, 130, 27);
		panel.add(dateChooser_1);
		
		JDateChooser dateChooser_2 = new JDateChooser();
		dateChooser_2.setBounds(102, 155, 130, 27);
		panel.add(dateChooser_2);
		
		Label labelHasta = new Label("Hasta");
		labelHasta.setBounds(296, 188, 62, 22);
		panel.add(labelHasta);
		
		Label labelDesde = new Label("Desde");
		labelDesde.setBounds(135, 188, 78, 27);
		panel.add(labelDesde);
	}
	private void GenerarContabilidadCompras() {
		ReporteCompras rc = new ReporteCompras();
		Integer fecha = (Integer) spinner.getValue();
		rc.Generar_Compras(fecha);
		}
	
	private void GenerarContabilidadPedidos() {
		ReportePedidos rp= new ReportePedidos();
		Integer fecha = (Integer) spinner.getValue();
		rp.Generar_Pedido(fecha);
	
	}
	
	public JDialog getDialog(){
		return frame;
	}
}

