package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Modelo.Materia_Prima;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Materia_Prima;
import Negocio.Servicios.Servicio_Pedidos;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
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
	private JButton btnAgregar;
	private JButton btnCategoriaProducto ;
	private JButton btnCategoriaMateria;
	private JButton button;
	private HashMap<Integer, String> categoriasProductos;
	private HashMap<Integer, String> categoriasMaterias;
	
	private Servicio_Materia_Prima SvMaterias;
	
	public ADM_Categorias(Principal_Negocio_Interfaz instancia_negocio){
		setTitle("Administracion de Categoria");
		Principal = instancia_negocio;
		SvMaterias = Principal.getSvMateriaPrima();
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
		
		btnCategoriaProducto = new JButton("");
		btnCategoriaProducto.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/Actions-go-previous-icon32.png")));
		btnCategoriaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCategoriaProducto.setBounds(247, 83, 59, 47);
		panel.add(btnCategoriaProducto);
		
		btnCategoriaMateria = new JButton("");
		btnCategoriaMateria.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/Actions-go-next-icon32.png")));
		btnCategoriaMateria.setBounds(329, 83, 59, 47);
		panel.add(btnCategoriaMateria);
		
		JLabel lblSeleccioneTipoCategoria = new JLabel("Seleccione Tipo Categoria");
		lblSeleccioneTipoCategoria.setBounds(237, 32, 173, 40);
		panel.add(lblSeleccioneTipoCategoria);
		
		JLabel lblIngreseNombre = new JLabel("Ingrese Nombre");
		lblIngreseNombre.setBounds(278, 141, 91, 40);
		panel.add(lblIngreseNombre);
		
		textNombre = new JTextField();
		textNombre.setBounds(262, 179, 126, 28);
		panel.add(textNombre);
		textNombre.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(434, 32, 164, 259);
		panel.add(scrollPane_1);
		
		
		scrollPane_1.setViewportView(tableCategoriaMateria);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(280, 236, 89, 23);
		panel.add(btnAgregar);
		
		button = new JButton("Salir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(613, 312, 97, 47);
		panel.add(button);
		
		
	}
	public void inicializar(){
		inicializarTablas();
		
		
		
		
		LlenarTablas();
		
		
	}
	
	
	
	public void inicializarTablas(){
		tableCategoriaProducto = new JTable();
		tableCategoriaProducto.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		));
		
		tableCategoriaMateria = new JTable();
		tableCategoriaMateria.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre"
			}
		));
	}
	public void LlenarTablas(){
		
		categoriasMaterias = SvMaterias.getCategorias();
		
		System.out.println(categoriasMaterias.toString());
		
		for (HashMap.Entry<Integer, String> entry : categoriasMaterias.entrySet()) {
			
		    String value = entry.getValue();
		    String[] fila= new String[1];
			fila[0]= categoriasMaterias.get(value);
			System.out.println(value);
			Object[] dato = {fila[0]};
			((DefaultTableModel) this.tableCategoriaMateria.getModel()).addRow(dato);
		    
			tableCategoriaMateria.repaint();
		}
	}
	
}
