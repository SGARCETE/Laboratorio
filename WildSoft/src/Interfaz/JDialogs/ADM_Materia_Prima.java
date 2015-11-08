package Interfaz.JDialogs;

import javax.swing.JDialog;
import java.util.Date;
import java.util.HashMap;

import Negocio.Modelo.Cliente;
import Negocio.Modelo.Materia_Prima;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Materia_Prima;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Button;




public class ADM_Materia_Prima extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel= new JPanel();
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Materia_Prima SvMateria;
	private JTextField textNombre;
	private JTable table;
	private JComboBox<String> comboBoxCategoria;
	private ArrayList<String> listaCategorias; 
	private JScrollPane scrollPane;
	private JDateChooser dateChooser;
	private JDateChooser dateChooserNuevo;
	private String[] datoTabla;
	private JButton  btnEliminar;
	private JButton btnModificar;
	private JTextField textVencimiento;
	private JLabel lblVencimiento; 
	private JLabel  lblVencimientoAnterior;
	private JLabel lblVencimientoNuevo;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAgregar ;
	
	private HashMap<Integer, String> categorias;
	
	
	public ADM_Materia_Prima(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Administracion de Materia Prima");
		Principal = instancia_negocio;
		SvMateria = Principal.getSvMateriaPrima();
		
		table = new JTable();
		inicializarTabla();
		llenar_tabla(); 
		
		
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
		panel_1.setBounds(10, 11, 292, 263);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 44, 63, 14);
		panel_1.add(lblNombre);
		
		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 85, 63, 14);
		panel_1.add(lblCategoria);
		
		lblVencimiento = new JLabel("Vencimiento");
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
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(88, 194, 128, 59);
		panel_1.add(btnAgregar);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(111, 138, 160, 26);
		panel_1.add(dateChooser);
		
		lblVencimientoAnterior = new JLabel("Vencimiento anterior");
		lblVencimientoAnterior.setBounds(10, 138, 127, 14);
		panel_1.add(lblVencimientoAnterior);
		
		textVencimiento = new JTextField();
		textVencimiento.setBounds(170, 132, 86, 26);
		panel_1.add(textVencimiento);
		textVencimiento.setColumns(10);
		
		lblVencimientoNuevo = new JLabel("Vencimiento nuevo");
		lblVencimientoNuevo.setBounds(10, 175, 128, 14);
		panel_1.add(lblVencimientoNuevo);
		
		 dateChooserNuevo = new JDateChooser();
		dateChooserNuevo.setBounds(132, 175, 150, 26);
		panel_1.add(dateChooserNuevo);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptarModificarMateria();
			}
		});
		btnAceptar.setBounds(36, 212, 86, 40);
		panel_1.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar_modificar_Materia();
			}
		});
		btnCancelar.setBounds(170, 213, 86, 40);
		panel_1.add(btnCancelar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarMateriaPrima();
			}
		});
		

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(910, 347, 97, 66);
		panel.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lista de Materias Primas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(301, 11, 706, 263);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 19, 686, 233);
		panel_2.add(scrollPane);
		scrollPane.setViewportView(table);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_MateriaPrima();
			}
		});
		btnModificar.setBounds(812, 347, 89, 66);
		panel.add(btnModificar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar_MateriaPrima();
			}
		});
		btnEliminar.setBounds(713, 345, 89, 68);
		panel.add(btnEliminar);
		
		Inicializar();
		
	}
	private void Inicializar() {
		
		lblVencimientoNuevo.setVisible(false);
		lblVencimientoAnterior.setVisible(false);
		textVencimiento.setVisible(false);
		dateChooserNuevo.setVisible(false);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		
		
		categorias = SvMateria.getCategorias();
		for (HashMap.Entry<Integer, String> entry : categorias.entrySet()) {
		    String value = entry.getValue();
		    comboBoxCategoria.addItem(value);
		    
		}
	}
/*	private void ComboCategoria(){
		comboBoxCategoria.addItem("Seleccione la categoría");

		listaCategorias= SvMateria.getCategoria_MP();
		
		for (int i = 0; i < listaCategorias.size(); i++) {
				comboBoxCategoria.addItem(listaCategorias.get(i));
		}
		
	} */
	
	private void agregarMateriaPrima()
	{
		Date fecha= dateChooser.getDate();
		
		// Recorro el mapa buscando la id del elemento seleccionado del combo
		Integer id = -1;
		for (HashMap.Entry<Integer, String> entry : categorias.entrySet()) {
			String value = entry.getValue();
			if(comboBoxCategoria.getSelectedItem().equals(value)){
				id = entry.getKey();;
			}			    
		}
	
		if(!textNombre.getText().equals(""))
		{
			if(comboBoxCategoria.getSelectedIndex()!=-1)
			{
				SvMateria.AgregarMAteriaPrima(new Materia_Prima(textNombre.getText(),fecha,id));
				inicializarTabla();
				llenar_tabla();
				JOptionPane.showMessageDialog(null, "Materia Prima agregada");	
			}
		}
	}
	public void inicializarTabla(){
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Nombre", "Vencimiento", "Categor\u00EDa"
			}
		));
	}
	
	private void llenar_tabla(){
		
		for(Materia_Prima materia: SvMateria.getCategoria())
			
		{
			String[] fila= new String[4];
			fila[0]= materia.getId().toString();
			fila[1]= materia.getNombre();
			fila[2]= materia.getFecha_vencimiento().toString();
			fila[3]= SvMateria.dameNombreCategoria(materia.getId());
			
			((DefaultTableModel) this.table.getModel()).addRow(fila);
		}
	}
	
	private String[] obtenerSeleccion() {
		
		int indice = table.getSelectedRow();
		String id =  (String) table.getModel().getValueAt(indice, 0);
		String nombre = (String) table.getModel().getValueAt(indice, 1);
		
		String categoria= (String) table.getModel().getValueAt(indice, 2);
		String fecha =  (String) table.getModel().getValueAt(indice, 3);
		
		String[] dato = { String.valueOf(indice), id, nombre,categoria,fecha };
		return dato;
	}
	protected void Eliminar_MateriaPrima() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar esta Materia Prima?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			 
			SvMateria.ELIMINAR__Materia_Prima(new Materia_Prima(Integer.parseInt(datoTabla[1]), datoTabla[2]));
			inicializarTabla();
			llenar_tabla();
		}
	}
	protected void Modificar_MateriaPrima() {
		lblVencimientoAnterior.setVisible(true);
		lblVencimientoNuevo.setVisible(true);
		textVencimiento.setVisible(true);
		dateChooserNuevo.setVisible(true);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		
		lblVencimiento.setVisible(false);
		dateChooser.setVisible(false);
		btnAgregar.setVisible(false);
		
		datoTabla = obtenerSeleccion();
		
		textNombre.setText(datoTabla[2]);
		comboBoxCategoria.setSelectedItem(datoTabla[4]);
		
		textVencimiento.setText(datoTabla[3]); 
		
		
		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
		
		}
	
	protected void Cancelar_modificar_Materia() {
		
		textNombre.setText("");
		comboBoxCategoria.setSelectedIndex(0);
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		
		btnAgregar.setVisible(true);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		lblVencimiento.setVisible(true);
		
		lblVencimientoAnterior.setVisible(false);
		lblVencimientoNuevo.setVisible(false);
		dateChooserNuevo.setVisible(false);
		dateChooser.setVisible(true);
		textVencimiento.setVisible(false);
		
	}
	protected void aceptarModificarMateria(){
		
		Date fecha= dateChooserNuevo.getDate();
		
		// Recorro el mapa buscando la id del elemento seleccionado del combo
		Integer id = -1;
		for (HashMap.Entry<Integer, String> entry : categorias.entrySet()) {
			String value = entry.getValue();
			if(comboBoxCategoria.getSelectedItem().equals(value)){
				id = entry.getKey();;
			}			    
		}
	
		if(!textNombre.getText().equals(""))
		{
			if(comboBoxCategoria.getSelectedIndex()!=-1)
			{
				guardarCambios(textNombre.getText(), fecha, id);
				SvMateria.modificarMateria(new Materia_Prima(Integer.parseInt(datoTabla[1]),textNombre.getText(), fecha, id)); 
			
				inicializarTabla();
				llenar_tabla();
				JOptionPane.showMessageDialog(null, "Materia Prima Modificada");	
			}
		}
		textNombre.setText("");
		lblVencimiento.setVisible(true);
		btnAgregar.setVisible(true);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		dateChooser.setVisible(true);
		
		lblVencimientoAnterior.setVisible(false);
		lblVencimientoNuevo.setVisible(false);
		dateChooserNuevo.setVisible(false);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		textVencimiento.setVisible(false);
		
	}
	protected void guardarCambios(String nombre, Date fecha, Integer categoria) {
		SvMateria.modificarMateria(new Materia_Prima(Integer.parseInt(datoTabla[1]),nombre, fecha, categoria));
	}
	
	
	
	
}
