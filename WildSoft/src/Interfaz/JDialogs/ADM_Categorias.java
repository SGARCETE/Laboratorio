package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Modelo.Categoria;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Categoria;
import Negocio.Servicios.Servicio_Materia_Prima;
import Negocio.Servicios.Servicio_Productos;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ADM_Categorias extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private Principal_Negocio_Interfaz Principal; 
	private JTextField textNombre;
	private JTable tableCategoriaProducto;
	private JTable tableCategoriaMateria;
	private JButton btnAgregarProducto;
	private JButton button;
	private HashMap<Integer, String> categoriasProductos;
	private HashMap<Integer, String> categoriasMaterias;

	
	private Servicio_Productos SvProducto;
	private Servicio_Materia_Prima SvMaterias;
	private Servicio_Categoria SvCategorias;
	private JButton btnAgregarMateria;
	private JButton btnEliminarCategoriaMateria;
	private String[] datoTabla;
	private JButton btnEliminarCategoriaProducto;
	
	public ADM_Categorias(Principal_Negocio_Interfaz instancia_negocio){
		setTitle("Administracion de Categoria");
		Principal = instancia_negocio;
		SvMaterias = Principal.getSvMateriaPrima();
		SvProducto= Principal.getSvProductos();
		SvCategorias= Principal.getSvCategoria();
		
		inicializar();
		
		
		setResizable(false);
		setBounds(100, 100, 726, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 32, 164, 259);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(tableCategoriaProducto);
		
		JLabel lblSeleccioneTipoCategoria = new JLabel("Seleccione Tipo");
		lblSeleccioneTipoCategoria.setBounds(297, 105, 97, 28);
		panel.add(lblSeleccioneTipoCategoria);
		
		JLabel lblIngreseNombre = new JLabel("Ingrese Nombre");
		lblIngreseNombre.setBounds(303, 26, 91, 40);
		panel.add(lblIngreseNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(288, 66, 126, 28);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(496, 32, 164, 259);
		panel.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(tableCategoriaMateria);
		
		btnAgregarProducto = new JButton("Agregar Como Tipo Producto");
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCategoriaProducto();
			}
		});
		btnAgregarProducto.setBounds(255, 145, 214, 23);
		panel.add(btnAgregarProducto);
		
		button = new JButton("Salir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(613, 312, 97, 47);
		panel.add(button);
		
		btnAgregarMateria = new JButton("Agregar Como Categoria Materia");
		btnAgregarMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCategoriaMateria();
			}
		});
		btnAgregarMateria.setBounds(255, 179, 214, 23);
		panel.add(btnAgregarMateria);
		
		btnEliminarCategoriaMateria = new JButton("Eliminar Categoria Materia");
		btnEliminarCategoriaMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar_Categoria_Materia();
			}
		});
		btnEliminarCategoriaMateria.setBounds(229, 271, 257, 35);
		panel.add(btnEliminarCategoriaMateria);
		
		btnEliminarCategoriaProducto = new JButton("Eliminar Categoria Producto");
		btnEliminarCategoriaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar_Categoria_Producto();
			}
		});
		btnEliminarCategoriaProducto.setBounds(229, 312, 257, 28);
		panel.add(btnEliminarCategoriaProducto);
		
		
	}
	public void inicializar(){
		inicializarTablaProducto();
		inicializarTablaMateria();
		LlenarTablaProducto();
		LlenarTablaMaterias();
		

		
		
	} 
	
	public void AgregarCategoriaMateria(){
		if(!textNombre.equals(""))
		{
			SvCategorias.guardarCategoriaMateria(new Categoria(textNombre.getText()));
		
			inicializarTablaMateria();
			LlenarTablaMaterias();
			
			JOptionPane.showMessageDialog(null, "Categoria Producto agregada");	
			
			textNombre.setText("");

		
		}
	}
	
	public void AgregarCategoriaProducto()
	{
		if(!textNombre.equals(""))
		{
			SvCategorias.guardaCategoriaProducto(new Categoria(textNombre.getText()));
			
			inicializarTablaProducto();
			LlenarTablaProducto();
			
			JOptionPane.showMessageDialog(null, "Categoria Producto agregada");	
			
			textNombre.setText("");

		
		}
		
	}
	public void inicializarTablaProducto(){
		tableCategoriaProducto = new JTable();
		tableCategoriaProducto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		));
	}
	public void inicializarTablaMateria(){
		tableCategoriaMateria = new JTable();
		tableCategoriaMateria.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		));
	}
	public void LlenarTablaProducto(){
		
		categoriasProductos = SvProducto.getCategorias();
		
		for (HashMap.Entry<Integer, String> entry : categoriasProductos.entrySet()) {
			
		    String value = entry.getValue();
		    String[] fila= new String[1];
		    fila[0]= value;
		    
			((DefaultTableModel) this.tableCategoriaProducto.getModel()).addRow(fila);
		}
		
	}
	public void LlenarTablaMaterias(){
		
		categoriasMaterias = SvMaterias.getCategorias();
		
		for (HashMap.Entry<Integer, String> entry : categoriasMaterias.entrySet()) {
			
		    String value = entry.getValue();
		    String[] fila= new String[1];
		    fila[0]= value;
			((DefaultTableModel) this.tableCategoriaMateria.getModel()).addRow(fila);
		}
	}
	protected void Eliminar_Categoria_Materia() {
		datoTabla = obtenerSeleccionMateria();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar este Tipo Producto ?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvCategorias.eliminarCategoriaMateria((new Categoria(datoTabla[1])));
			inicializarTablaMateria();
			LlenarTablaMaterias();
		}
	}
	private String[] obtenerSeleccionMateria() {
		int indice = tableCategoriaMateria.getSelectedRow();
		
		String nombre = (String) tableCategoriaMateria.getModel().getValueAt(indice, 0);
		
		String[] dato = { String.valueOf(indice),nombre};
		return dato;
	}
	private String[] obtenerSeleccionTipo(){
		
		int indice = tableCategoriaProducto.getSelectedRow();
		
		String nombre = (String) tableCategoriaProducto.getModel().getValueAt(indice, 0);
		
		String[] dato = { String.valueOf(indice),nombre};
		return dato;
		
	}

	
	
	protected void Eliminar_Categoria_Producto() {
		datoTabla = obtenerSeleccionTipo();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar esta Categoria ?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvCategorias.eliminarCategoriaProducto((new Categoria(datoTabla[1])));
			inicializarTablaMateria();
			LlenarTablaMaterias();
		}
	}
	
	
}
