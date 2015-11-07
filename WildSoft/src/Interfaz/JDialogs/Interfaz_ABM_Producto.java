package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Clientes;
import Negocio.Servicios.Servicio_Productos;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;

public class Interfaz_ABM_Producto extends JDialog {

	
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel= new JPanel();
	
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Clientes SvCliente;
	private Servicio_Productos SvProducto;
	private JTextField textNombre;
	private JTable table;
	private JTextField textPrecio;
	private JTextField textObservación;
	private JLabel lblAviso;
	private String[] datoTabla;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JComboBox<String> comboTipo;

	public Interfaz_ABM_Producto(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("ABM Producto");
		Principal = instancia_negocio;
		
		SvCliente = Principal.getSvClientes();
		SvProducto= Principal.getSvProductos();
		
		table = new JTable();
		iniciarlizarTablaProducto();
		
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
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nuevo Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 299, 255);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 35, 63, 14);
		panel_1.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("Precio");
		lblDireccin.setBounds(10, 76, 63, 14);
		panel_1.add(lblDireccin);
		
		JLabel lblTelefono = new JLabel("Tipo producto");
		lblTelefono.setBounds(10, 112, 77, 14);
		panel_1.add(lblTelefono);
		
		textNombre = new JTextField();
		textNombre.setBounds(79, 28, 210, 28);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Observaci\u00F3n");
		lblDetalle.setBounds(10, 151, 63, 14);
		panel_1.add(lblDetalle);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar_producto();
			}
		});
		btnAgregar.setBounds(108, 195, 89, 45);
		panel_1.add(btnAgregar);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(79, 69, 210, 28);
		panel_1.add(textPrecio);
		
		textObservación = new JTextField();
		textObservación.setColumns(10);
		textObservación.setBounds(79, 144, 210, 28);
		panel_1.add(textObservación);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aceptar_modificar_Producto();
			}
		});
		btnAceptar.setBounds(29, 193, 96, 49);
		panel_1.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar_modificar_Cliente();
			}
		});
		btnCancelar.setBounds(171, 193, 96, 49);
		panel_1.add(btnCancelar);
		
		comboTipo = new  JComboBox();
		comboTipo.setBounds(79, 109, 210, 20);
		panel_1.add(comboTipo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lista de Clientes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(312, 11, 695, 255);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 643, 223);
		panel_2.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					Eliminar_producto();	
				}
				
			}
		});
		btnEliminar.setBounds(668, 347, 111, 66);
		panel.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)
				{
					Modificar_Producto();
				}
				
			}
		});
		btnModificar.setBounds(789, 347, 111, 66);
		panel.add(btnModificar);
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		
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
		
		lblAviso = new JLabel("");
		lblAviso.setBounds(283, 23, 492, 30);
		panel_3.add(lblAviso);
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		
		
		
		InicializarParametros();
	}
	
	
	private void InicializarParametros(){
		comboTipo.addItem("Selecciona la categoria");
		for (int i = 0; i < SvProducto.getLista_Productos().size(); i++) {
			comboTipo.addItem(SvProducto.getLista_Productos().get(i));
		}
	}
	
	private void agregar_producto(){
		
		if(!textNombre.getText().equals("")){
			if(!textPrecio.getText().equals("")){
				if(comboTipo.getSelectedIndex()>0){
					if(!textObservación.getText().equals("")){
						System.out.println(comboTipo.getSelectedItem().toString());
						Integer Tipo_producto_ID = SvProducto.getTipo_Producto_INTEGER(comboTipo.getSelectedItem().toString()).get(0);
						System.out.println(textNombre.getText());
						System.out.println(Tipo_producto_ID);
						System.out.println(textObservación.getText());
						System.out.println(Double.parseDouble(textPrecio.getText()));
						SvProducto.guardar_nuevo_producto(new Producto(null, textNombre.getText(), Tipo_producto_ID, textObservación.getText(), Double.parseDouble(textPrecio.getText()), 0));
						
						
						textNombre.setText("");
						textPrecio.setText("");
						textObservación.setText("");
						lblAviso.setVisible(false);
						
						iniciarlizarTablaProducto();
						llenar_tabla();
						JOptionPane.showMessageDialog(null, "Producto agregado con éxito");	
					}
					else {
					lblAviso.setText("Debes completar el campo 'Observación' para continuar");
					lblAviso.setVisible(true);}}
				else {
					lblAviso.setText("Debes completar el campo 'Precio' para continuar");
					lblAviso.setVisible(true);}}
			else {
				lblAviso.setText("Debes completar el campo 'Tipo producto' para continuar");
				lblAviso.setVisible(true);}}
		else {
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
			}}
	
	private void iniciarlizarTablaProducto()
	{
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N°","Nombre", "Precio", "Tipo Producto", "Observación"
			}
		));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(15);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(175);
		
		
	}
	
	private void llenar_tabla(){
		for(Producto producto: SvProducto.GET_PRODUCTOS() )
		{
			String[] fila= new String[5];
			fila[0]= producto.getPR_id().toString();
			fila[1]= producto.getPR_nombre();
			fila[2]= producto.getPR_precio().toString();
			fila[3]= SvProducto.getTipo_Producto_STRING(producto.getPR_tipo_producto()).get(0);
			fila[4]= producto.getPR_Observacion();
			
			
			((DefaultTableModel) this.table.getModel()).addRow(fila);
		}
	}
	
	protected void Eliminar_producto() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar este Producto?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvProducto.eliminar_un_producto(new Producto(Integer.parseInt(datoTabla[1])));
			iniciarlizarTablaProducto();
			llenar_tabla();
		}
	}
	private String[] obtenerSeleccion() {
		int indice = table.getSelectedRow();
		String id = (String) table.getModel().getValueAt(indice, 0);
		String nombre = (String) table.getModel().getValueAt(indice, 1);
		String direccion = (String) table.getModel().getValueAt(indice, 2);
		String telefono = (String) table.getModel().getValueAt(indice, 3);
		String detalle = (String) table.getModel().getValueAt(indice, 4);
		
		String[] dato = { String.valueOf(indice), id, nombre, direccion,telefono,detalle };
		return dato;
	}
	protected void Modificar_Producto() {
		lblAviso.setVisible(false);
		datoTabla = obtenerSeleccion();
		textNombre.setText(datoTabla[2]);
		textPrecio.setText(datoTabla[3]);
		comboTipo.setSelectedItem(datoTabla[3]);
		textObservación.setText(datoTabla[5]);
		
		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
		btnAgregar.setVisible(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		
		}
	protected void Cancelar_modificar_Cliente() {
		textNombre.setText("");
		textPrecio.setText("");
		textObservación.setText("");
		
		
		btnAceptar.setVisible(false);
		btnAgregar.setVisible(true);
		btnCancelar.setVisible(false);
		lblAviso.setVisible(false);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		
	}
	protected void guardarCambios(String nombre, String precio, String tipo, String observacion) {
		Integer Tipo_producto = SvProducto.getTipo_Producto_INTEGER(tipo).get(0);
		SvProducto.Modificar_Producto(new Producto(Integer.parseInt(datoTabla[1]), null, nombre, Tipo_producto, observacion, Double.parseDouble(precio), 0));
		System.out.println("se modificó el producto");
	}
	
	
	
	
	
	
	
	protected void Aceptar_modificar_Producto() {
		if (!textNombre.getText().equals("")) {
			if (!textPrecio.getText().equals("")) {
				if(comboTipo.getSelectedIndex()>0)
				{
					if(!textObservación.getText().equals(""))
					{
						guardarCambios(textNombre.getText(), textPrecio.getText(), comboTipo.getSelectedItem().toString(), textObservación.getText());
						textNombre.setText("");
						textPrecio.setText("");
						comboTipo.getSelectedItem().toString();
						textObservación.setText("");
						
						iniciarlizarTablaProducto();
						llenar_tabla();
						lblAviso.setVisible(false);
						btnAceptar.setVisible(false);
						btnAgregar.setVisible(true);
						btnCancelar.setVisible(false);
						btnModificar.setVisible(true);
						btnEliminar.setVisible(true);
					}
					else{
						lblAviso.setText("Debes completar el campo 'Detalle' para continuar");
						lblAviso.setVisible(true);
					}
						
				}else{
					lblAviso.setText("Debes completar el campo 'Telefono' para continuar");
					lblAviso.setVisible(true);
				}
			
			}else{
				lblAviso.setText("Debes completar el campo 'Dirección' para continuar");
				lblAviso.setVisible(true);
			}
		} else{
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
		}
	}
}
