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
import java.util.Map.Entry;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;

public class ADM_Categorias extends JDialog {

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

	public ADM_Categorias(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Administracion de Categoria");
		Principal = instancia_negocio;
		SvMaterias = Principal.getSvMateriaPrima();
		SvProducto = Principal.getSvProductos();
		SvCategorias = Principal.getSvCategoria();

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

		JLabel lblIngreseNombre = new JLabel("Ingrese Nombre");
		lblIngreseNombre.setBounds(249, 97, 91, 40);
		panel.add(lblIngreseNombre);

		textNombre = new JTextField();
		textNombre.setBounds(233, 148, 126, 28);
		panel.add(textNombre);
		textNombre.setColumns(10);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(387, 38, 164, 259);
		panel.add(scrollPane_1);

		scrollPane_1.setViewportView(tableCategoriaMateria);

		btnAgregarProducto = new JButton("Agregar Producto");
		btnAgregarProducto.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/add-1-icon16.png")));
		btnAgregarProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCategoriaProducto();
			}
		});
		btnAgregarProducto.setBounds(35, 302, 164, 23);
		panel.add(btnAgregarProducto);

		button = new JButton("Salir");
		button.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		button.setBounds(613, 320, 97, 47);
		panel.add(button);

		btnAgregarMateria = new JButton("Agregar Materia");
		btnAgregarMateria.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/add-1-icon16.png")));
		btnAgregarMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AgregarCategoriaMateria();
			}
		});
		btnAgregarMateria.setBounds(387, 302, 164, 23);
		panel.add(btnAgregarMateria);

		btnEliminarCategoriaMateria = new JButton("Eliminar Materia");
		btnEliminarCategoriaMateria.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/delete-1-icon16.png")));
		btnEliminarCategoriaMateria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Eliminar_Categoria_Materia();
			}
		});
		btnEliminarCategoriaMateria.setBounds(387, 328, 164, 31);
		panel.add(btnEliminarCategoriaMateria);

		btnEliminarCategoriaProducto = new JButton(
				"Eliminar Producto");
		btnEliminarCategoriaProducto.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/delete-1-icon16.png")));
		btnEliminarCategoriaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar_Categoria_Producto();
			}
		});
		btnEliminarCategoriaProducto.setBounds(33, 331, 164, 28);
		panel.add(btnEliminarCategoriaProducto);

	}

	public void inicializar() {
		
		tableCategoriaProducto = new JTable();
		tableCategoriaMateria = new JTable();
		LlenarTablaProducto();
		LlenarTablaMaterias();
		
	}

	public void AgregarCategoriaMateria() {
		if (!textNombre.equals("")) {
			SvCategorias.guardarCategoriaMateria(new Categoria(textNombre
					.getText()));

			LlenarTablaMaterias();

			JOptionPane.showMessageDialog(null, "Categoria Materia agregada");

			textNombre.setText("");

		}
	}

	public void AgregarCategoriaProducto() {
		if (!textNombre.equals("")) {
			SvCategorias.guardaCategoriaProducto(new Categoria(textNombre
					.getText()));

			LlenarTablaProducto();

			JOptionPane.showMessageDialog(null, "Categoria Producto agregada");

			textNombre.setText("");

		}

	}

	public void LlenarTablaProducto() {

		setModelTabla(tableCategoriaProducto);
		
		categoriasProductos = SvProducto.getCategorias();

		for (Entry<Integer, String> entry : categoriasProductos.entrySet()) {

			String value = entry.getValue();
			String[] fila = new String[1];
			fila[0] = value;

			((DefaultTableModel) this.tableCategoriaProducto.getModel())
					.addRow(fila);
		}

	}

	public void LlenarTablaMaterias() {

		setModelTabla(tableCategoriaMateria);
		
		categoriasMaterias = SvMaterias.getCategorias();

		for (Entry<Integer, String> entry : categoriasMaterias.entrySet()) {

			String value = entry.getValue();
			String[] fila = new String[1];
			fila[0] = value;
			((DefaultTableModel) this.tableCategoriaMateria.getModel())
					.addRow(fila);
		}
	}

	protected void Eliminar_Categoria_Materia() {
		datoTabla = obtenerSeleccionMateria();
		int RESPUESTA = JOptionPane
				.showConfirmDialog(
						null,
						"¿Seguro que desea eliminar esta Categoria Materia?\nEstos cambios no se pueden deshacer!",
						"CONFIRMAR", JOptionPane.OK_CANCEL_OPTION);
		if (RESPUESTA == JOptionPane.OK_OPTION) {
			SvCategorias
					.eliminarCategoriaMateria((new Categoria(datoTabla[1])));
			LlenarTablaMaterias();
		}
	}

	private String[] obtenerSeleccionMateria() {
		int indice = tableCategoriaMateria.getSelectedRow();

		String nombre = (String) tableCategoriaMateria.getModel().getValueAt(
				indice, 0);

		String[] dato = { String.valueOf(indice), nombre };
		return dato;
	}

	private String[] obtenerSeleccionProducto() {

		int indice = tableCategoriaProducto.getSelectedRow();

		String nombre = (String) tableCategoriaProducto.getModel().getValueAt(
				indice, 0);

		String[] dato = { String.valueOf(indice), nombre };
		return dato;

	}
	

	protected void Eliminar_Categoria_Producto() {
		
		datoTabla = obtenerSeleccionProducto();
		int RESPUESTA = JOptionPane
				.showConfirmDialog(
						null,
						"¿Seguro que desea eliminar esta Categoria Producto ?\nEstos cambios no se pueden deshacer!",
						"CONFIRMAR", JOptionPane.OK_CANCEL_OPTION);
		if (RESPUESTA == JOptionPane.OK_OPTION) {
			SvCategorias
					.eliminarCategoriaProducto((new Categoria(datoTabla[1])));
			LlenarTablaProducto();
		}
	}
	
	private void setModelTabla(JTable jtable) {
		jtable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Nombre" }));
	}
}
