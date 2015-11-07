package Interfaz.JDialogs;

import javax.swing.JDialog;
import java.text.SimpleDateFormat;
import java.util.Date;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Materia_Prima;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Materia_Prima;
import net.sf.jasperreports.charts.util.SvgChartRendererFactory;

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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import javax.swing.table.DefaultTableModel;



public class ADM_Materia_Prima extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel= new JPanel();
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Materia_Prima SvMateria;
	private JTextField textNombre;
	private JTable table;
	private JComboBox<String> comboBoxCategoria;
	private ArrayList<String> listaCategorias;
	private JDateChooser dateChooser;

	
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
		
		JLabel lblVencimiento = new JLabel("Vencimiento");
		lblVencimiento.setBounds(10, 138, 80, 14);
		panel_1.add(lblVencimiento);
		
		textNombre = new JTextField();
		textNombre.setBounds(111, 32, 160, 26);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		comboBoxCategoria = new JComboBox<String>();
		comboBoxCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBoxCategoria.setBounds(111, 79, 160, 26);
		panel_1.add(comboBoxCategoria);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarMateriaPrima();
			}
		});
		btnAgregar.setBounds(83, 195, 89, 23);
		panel_1.add(btnAgregar);
		
		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("yyyy/mm/dd");
		dateChooser.setBounds(111, 132, 160, 31);
		panel_1.add(dateChooser);
		

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
		panel_3.setBounds(20, 277, 987, 64);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lista de Materias Primas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(301, 11, 706, 263);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 19, 686, 233);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Nombre", "Vencimiento", "Categor\u00EDa"
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(812, 347, 89, 66);
		panel.add(btnModificar);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(713, 345, 89, 68);
		panel.add(btnEliminar);
		
		Inicializar();
		
	}
	private void Inicializar() {
		   ComboCategoria();
	}
	private void ComboCategoria(){
		comboBoxCategoria.addItem("Seleccione la categoría");

		listaCategorias= SvMateria.getCategoria_MP();
		
		for (int i = 0; i < listaCategorias.size(); i++) {
				comboBoxCategoria.addItem(listaCategorias.get(i));
		}
	}
	
	private void agregarMateriaPrima()
	{
		Date fecha=dateChooser.getDate();
		
		
		
		
		if(!textNombre.getText().equals(""))
		{
			if(comboBoxCategoria.getSelectedIndex()!=-1)
			{
				SvMateria.AgregarMAteriaPrima(new Materia_Prima(textNombre.getText(),fecha,comboBoxCategoria.getSelectedIndex()));
				
			}
		}
	}
}
