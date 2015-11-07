package Interfaz.JDialogs;

import javax.swing.JDialog;


import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Materia_Prima;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.components.JSpinField;



public class ADM_Materia_Prima extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel= new JPanel();
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Materia_Prima SvMateria;
	private JTextField textNombre;
	
	public ADM_Materia_Prima(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("ABM Cliente");
		Principal = instancia_negocio;
		SvMateria = Principal.getSvMateriaPrima();
		
		
		setResizable(false);
		setBounds(100, 100, 1023, 453);
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
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nueva Materia Prima", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 281, 255);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 44, 63, 14);
		panel_1.add(lblNombre);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 85, 63, 14);
		panel_1.add(lblCategoria);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 126, 164, 26);
		panel_1.add(dateChooser);
		
		JLabel lblVencimiento = new JLabel("Vencimiento");
		lblVencimiento.setBounds(10, 138, 80, 14);
		panel_1.add(lblVencimiento);
		
		textNombre = new JTextField();
		textNombre.setBounds(111, 32, 160, 26);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(111, 79, 160, 26);
		panel_1.add(comboBox);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(83, 195, 89, 23);
		panel_1.add(btnAgregar);
		

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(910, 347, 97, 66);
		panel.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Descripci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 272, 987, 64);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(301, 11, 706, 255);
		panel.add(panel_2);
		
		
	}
}
