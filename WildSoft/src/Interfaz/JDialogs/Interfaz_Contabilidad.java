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

public class Interfaz_Contabilidad {

	private JDialog frame;
	JSpinner spinner = new JSpinner();

	public Interfaz_Contabilidad() {
		initialize();
	}

	private void initialize() {
		frame = new JDialog();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblSeleccioneElMes = new JLabel("Seleccione el mes");
		lblSeleccioneElMes.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSeleccioneElMes.setBounds(27, 74, 151, 27);
		frame.getContentPane().add(lblSeleccioneElMes);
		
		JButton btnGenerarContabilidad = new JButton("Generar Contabilidad");
		btnGenerarContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GenerarContabilidadCompras();
				GenerarContabilidadPedidos();
			}
		});
		btnGenerarContabilidad.setBounds(203, 74, 175, 68);
		frame.getContentPane().add(btnGenerarContabilidad);
		
		
		spinner.setBounds(69, 112, 51, 32);
		frame.getContentPane().add(spinner);
		spinner.setModel(new SpinnerNumberModel(1, 1, 12, 1));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();
			}
		});
		btnSalir.setBounds(356, 208, 68, 42);
		frame.getContentPane().add(btnSalir);
		
		JLabel lblContabilidad = new JLabel("CONTABILIDAD");
		lblContabilidad.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblContabilidad.setBounds(130, 11, 168, 36);
		frame.getContentPane().add(lblContabilidad);
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

