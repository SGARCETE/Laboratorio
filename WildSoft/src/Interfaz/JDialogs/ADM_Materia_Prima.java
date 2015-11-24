package Interfaz.JDialogs;

import javax.swing.JDialog;
import java.util.HashMap;
import java.util.Map.Entry;

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
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import MetAux.MetAux;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class ADM_Materia_Prima extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel= new JPanel();
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Materia_Prima SvMateria;
	private JTextField textNombre;
	private JTable tableMateriasPrimas;
	private JComboBox<String> comboBoxCategoria;
	private JScrollPane scrollPane;
	private Object[] datoTabla;
	private JButton  btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAgregar ;
	private JLabel label;
	private HashMap<Integer, String> categorias;
	private JPanel panel_4;
	
	public ADM_Materia_Prima(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Administracion de Materia Prima");
		Principal = instancia_negocio;
		SvMateria = Principal.getSvMateriaPrima();
		
		tableMateriasPrimas = new JTable();
		inicializarTabla();
		llenar_tabla(); 
		
		
		setResizable(false);
		setBounds(100, 100, 1023, 453);
		getContentPane().setLayout(new BorderLayout());
		
		panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(new Color(60, 179, 113));
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnEliminar = new JButton(" Eliminar ");
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.setIcon(new ImageIcon(ADM_Materia_Prima.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel_4.add(btnEliminar);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar_MateriaPrima();
			}
		});
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setIcon(new ImageIcon(ADM_Materia_Prima.class.getResource("/Recursos/IMG/edit-icon24.png")));
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel_4.add(btnModificar);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_MateriaPrima();
			}
		});
		

		JButton btnSalir = new JButton("    Salir     ");
		btnSalir.setBackground(Color.WHITE);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel_4.add(btnSalir);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
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
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setBounds(88, 213, 128, 39);
		panel_1.add(btnAgregar);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				aceptarModificarMateria();
			}
		});
		btnAceptar.setBounds(36, 212, 86, 40);
		panel_1.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.WHITE);
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Lista de Materias Primas", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(301, 11, 706, 263);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 19, 686, 233);
		panel_2.add(scrollPane);
		scrollPane.setViewportView(tableMateriasPrimas);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setLayout(null);
		panel_3.setBorder(new TitledBorder(null, "Descripci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(10, 280, 987, 60);
		panel.add(panel_3);
		
		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setFont(new Font("SansSerif", Font.BOLD, 15));
		label.setBounds(283, 23, 492, 30);
		panel_3.add(label);
		
		Inicializar();
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Inicializar() {
		MetAux.Limitar_caracteres(textNombre, 20);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		label.setVisible(false);
		comboBoxCategoria.addItem("Seleccione Categoria");

		categorias = SvMateria.getCategorias();
		for (Entry<Integer, String> entry : categorias.entrySet()) {
		    String value = entry.getValue();
		    comboBoxCategoria.addItem(value);
		    
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void agregarMateriaPrima(){		
		// Recorro el mapa buscando la id del elemento seleccionado del combo
		Integer id = -1;
		for (Entry<Integer, String> entry : categorias.entrySet()) {
			String value = entry.getValue();
			if(comboBoxCategoria.getSelectedItem().equals(value)){
				id = entry.getKey();;
			}			    
		}
		if(!textNombre.getText().equals("")){
			if(comboBoxCategoria.getSelectedIndex()!=-1){
				
					label.setVisible(false);
					SvMateria.AgregarMAteriaPrima(new Materia_Prima(textNombre.getText(), id));
					inicializarTabla(); 
					llenar_tabla();
					JOptionPane.showMessageDialog(null, "Materia Prima agregada");	
					
					textNombre.setText("");
					
					
				}else {
					label.setText("Debes completar el 'Vencimiento' para continuar");
					label.setVisible(true);}
				
			}else {
				label.setText("Debes completar el combo 'Categoria' para continuar");
				label.setVisible(true);}
		}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void inicializarTabla(){
		tableMateriasPrimas.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {"N\u00B0", "Nombre", "Categor\u00EDa"}
		));
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void llenar_tabla(){
		
		for(Materia_Prima materia: SvMateria.getCategoria()){
			Object[] fila= new Object[3];
			fila[0]= materia.getId();
			fila[1]= materia.getNombre();
			fila[2]= SvMateria.dameNombreCategoria(materia.getId());
			
			((DefaultTableModel) this.tableMateriasPrimas.getModel()).addRow(fila);
			
			tableMateriasPrimas.getColumnModel().getColumn(0).setMaxWidth(0);
			tableMateriasPrimas.getColumnModel().getColumn(0).setMinWidth(0);
			tableMateriasPrimas.getColumnModel().getColumn(0).setPreferredWidth(0);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private Object[] obtenerSeleccion() {
		
		int indice = tableMateriasPrimas.getSelectedRow();
		Integer id =  (Integer) tableMateriasPrimas.getModel().getValueAt(indice, 0);
		String nombre = (String) tableMateriasPrimas.getModel().getValueAt(indice, 1);
		String categoria= (String) tableMateriasPrimas.getModel().getValueAt(indice, 2);
		
		Object[] dato = { String.valueOf(indice), id, nombre, categoria};
		return dato;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Eliminar_MateriaPrima() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar esta Materia Prima?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvMateria.ELIMINAR__Materia_Prima(new Materia_Prima((Integer)datoTabla[1], (String)datoTabla[2]));
			inicializarTabla();
			llenar_tabla();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Modificar_MateriaPrima() {
		
		datoTabla = obtenerSeleccion();
		
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		btnAgregar.setVisible(false);
		textNombre.setText((String)datoTabla[2]);
		comboBoxCategoria.setSelectedItem(datoTabla[3]);		
		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cancelar_modificar_Materia() {
		textNombre.setText("");
		comboBoxCategoria.setSelectedIndex(0);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		btnAgregar.setVisible(true);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void aceptarModificarMateria(){
		
		
		// Recorro el mapa buscando la id del elemento seleccionado del combo
		Integer id = -1;
		for (Entry<Integer, String> entry : categorias.entrySet()) {
			String value = entry.getValue();
			if(comboBoxCategoria.getSelectedItem().equals(value)){
				id = entry.getKey();;
			}			    
		}
	
		if(!textNombre.getText().equals("")){
			if(comboBoxCategoria.getSelectedIndex()!=-1){
				
					guardarCambios(textNombre.getText(),id );
					SvMateria.modificarMateria(new Materia_Prima((Integer)datoTabla[1],textNombre.getText(), id)); 
				
					inicializarTabla();
					llenar_tabla();
					JOptionPane.showMessageDialog(null, "Materia Prima Modificada");	
				
				
				
			}
		}
		textNombre.setText("");
		btnAgregar.setVisible(true);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void guardarCambios(String nombre, Integer categoria) {
		SvMateria.modificarMateria(new Materia_Prima((Integer)datoTabla[1],nombre, categoria));
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}