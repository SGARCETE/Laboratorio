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
import javax.swing.ImageIcon;
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
	private JButton btnCancelar;
	private JButton btnAgregarMateria;
	
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
		scrollPane.setBounds(40, 32, 164, 259);
		panel.add(scrollPane);
		
		
		scrollPane.setViewportView(tableCategoriaProducto);
		
		JLabel lblSeleccioneTipoCategoria = new JLabel("Seleccione Tipo");
		lblSeleccioneTipoCategoria.setBounds(297, 105, 97, 28);
		panel.add(lblSeleccioneTipoCategoria);
		
		JLabel lblIngreseNombre = new JLabel("Ingrese Nombre");
		lblIngreseNombre.setBounds(297, 26, 91, 40);
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
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(284, 225, 110, 35);
		panel.add(btnCancelar);
		
		btnAgregarMateria = new JButton("Agregar Como Categoria Materia");
		btnAgregarMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCategoriaMateria();
			}
		});
		btnAgregarMateria.setBounds(255, 179, 214, 23);
		panel.add(btnAgregarMateria);
		
		
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
	
}
