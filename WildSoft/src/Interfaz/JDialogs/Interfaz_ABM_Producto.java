package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import MetAux.MetAux;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Productos;

public class Interfaz_ABM_Producto extends JDialog {

	
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel= new JPanel();
	
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Productos SvProducto;
	private JTextField textNombre;
	private JTable table;
	private JTextField textPrecio;
	private JLabel lblAviso;
	private String[] datoTabla;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAgregar;
	private JComboBox<String> comboTipo;
	private JPanel panel_4;
	private JPanel panel_3 ;

	public Interfaz_ABM_Producto(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("ABM Producto");
		Principal = instancia_negocio;
		
		Principal.getSvClientes();
		SvProducto= Principal.getSvProductos();
		
		table = new JTable();
		iniciarlizarTablaProducto();
		
		llenar_tabla();
		
		setResizable(false);
		setBounds(100, 100, 1023, 446);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panel_4 = new JPanel();
		panel_4.setBackground(new Color(60, 179, 113));
		getContentPane().add(panel_4, BorderLayout.SOUTH);
		panel_4.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		btnEliminar = new JButton("Eliminar");
		panel_4.add(btnEliminar);
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					Eliminar_producto();	
				}
				
			}
		});
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		
		
		btnModificar = new JButton("Modificar");
		panel_4.add(btnModificar);
		btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1)	{
					Modificar_Producto();
				}
				
			}
		});
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		
		JButton btnSalir = new JButton("    Salir    ");
		panel_4.add(btnSalir);
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nuevo Producto", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 299, 255);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 35, 76, 14);
		panel_1.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("Precio");
		lblDireccin.setBounds(10, 76, 76, 14);
		panel_1.add(lblDireccin);
		
		JLabel lblTelefono = new JLabel("Tipo producto");
		lblTelefono.setBounds(10, 109, 76, 25);
		panel_1.add(lblTelefono);
		
		textNombre = new JTextField();
		textNombre.setBounds(85, 28, 204, 28);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_ABM_Producto.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		btnAgregar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAgregar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar_producto();
			}
		});
		btnAgregar.setBounds(100, 180, 89, 49);
		panel_1.add(btnAgregar);
		
		textPrecio = new JTextField();
		textPrecio.setColumns(10);
		textPrecio.setBounds(85, 69, 204, 28);
		panel_1.add(textPrecio);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(Interfaz_ABM_Producto.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		btnAceptar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aceptar_modificar_Producto();
			}
		});
		btnAceptar.setBounds(27, 180, 89, 49);
		panel_1.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(Interfaz_ABM_Producto.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar_modificar_Cliente();
			}
		});
		btnCancelar.setBounds(169, 180, 89, 49);
		panel_1.add(btnCancelar);
		
		comboTipo = new  JComboBox<String>();
		comboTipo.setBounds(85, 109, 204, 25);
		panel_1.add(comboTipo);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Lista de Productos", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(312, 11, 695, 255);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 643, 223);
		panel_2.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Aviso", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_3.setBounds(10, 272, 997, 64);
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
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void InicializarParametros(){
		
		comboTipo.addItem("Selecciona la categoria");
		for (int i = 0; i < SvProducto.getLista_Productos().size(); i++) {
			comboTipo.addItem(SvProducto.getLista_Productos().get(i));
		}
		MetAux.SoloNumerosDecimales(textPrecio);
		panel_3.setVisible(false);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void agregar_producto(){
		
		if(!textNombre.getText().equals("")){
			if(comprobarPrecio()){
				if(comboTipo.getSelectedIndex()>0){
					Integer Tipo_producto_ID = SvProducto.getTipo_Producto_INTEGER(comboTipo.getSelectedItem().toString()).get(0);
					SvProducto.guardar_nuevo_producto(new Producto(null, textNombre.getText(), Tipo_producto_ID, Double.parseDouble(textPrecio.getText()), 0));
					
					textNombre.setText("");
					textPrecio.setText("");
//					textObservaci�n.setText("");
					lblAviso.setVisible(false);
					
					iniciarlizarTablaProducto();
					llenar_tabla();
					JOptionPane.showMessageDialog(null, "Producto agregado con �xito");	
				}else {
					lblAviso.setText("Debes completar el campo 'Tipo producto' para continuar");
					lblAviso.setVisible(true);
					panel_3.setVisible(true);
				}
			}else {
				lblAviso.setText("Debes completar el campo 'Precio' para continuar");
				lblAviso.setVisible(true);
				panel_3.setVisible(true);}
		}else {
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
			panel_3.setVisible(true);
		}
	}

	private boolean comprobarPrecio() {
		if(textPrecio.getText().isEmpty()){
			return false;
		}
		if(textPrecio.getText().equals("")){
			return false;
		}
		if ((Double.parseDouble(textPrecio.getText()) <= 0.0)){
			return false;
		}
		return true;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void iniciarlizarTablaProducto(){
		
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N�","Nombre", "Precio", "Tipo Producto", "Observaci�n"
			}
		));
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(4).setMaxWidth(0);
		table.getColumnModel().getColumn(4).setMinWidth(0);
		table.getColumnModel().getColumn(4).setPreferredWidth(0);
		
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void llenar_tabla(){
		for(Producto producto: SvProducto.GET_PRODUCTOS() )	{
			String[] fila= new String[5];
			fila[0]= producto.getPR_id().toString();
			fila[1]= producto.getPR_nombre();
			fila[2]= producto.getPR_precio().toString();
			fila[3]= SvProducto.getTipo_Producto_STRING(producto.getPR_tipo_producto()).get(0);
			fila[4]= producto.getPR_Observacion();
			
			
			((DefaultTableModel) this.table.getModel()).addRow(fila);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Eliminar_producto() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"�Seguro que desea eliminar este Producto?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvProducto.eliminar_un_producto(new Producto(Integer.parseInt(datoTabla[1])));
			iniciarlizarTablaProducto();
			llenar_tabla();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private String[] obtenerSeleccion() {
		int indice = table.getSelectedRow();
		String id = (String) table.getModel().getValueAt(indice, 0);
		String nombre = (String) table.getModel().getValueAt(indice, 1);
		String direccion = (String) table.getModel().getValueAt(indice, 2);
		String telefono = (String) table.getModel().getValueAt(indice, 3);
//		String detalle = (String) table.getModel().getValueAt(indice, 4);
		
		String[] dato = { String.valueOf(indice), id, nombre, direccion,telefono };
		return dato;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Modificar_Producto() {
		lblAviso.setVisible(false);
		datoTabla = obtenerSeleccion();
		textNombre.setText(datoTabla[2]);
		textPrecio.setText(datoTabla[3]);
		comboTipo.setSelectedItem(datoTabla[3]);
//		textObservaci�n.setText(datoTabla[5]);
		
		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
		btnAgregar.setVisible(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cancelar_modificar_Cliente() {
		textNombre.setText("");
		textPrecio.setText("");

		
		btnAceptar.setVisible(false);
		btnAgregar.setVisible(true);
		btnCancelar.setVisible(false);
		lblAviso.setVisible(false);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void guardarCambios(String nombre, String precio, String tipo) {
		Integer Tipo_producto = SvProducto.getTipo_Producto_INTEGER(tipo).get(0);
		SvProducto.Modificar_Producto(new Producto(Integer.parseInt(datoTabla[1]), null, nombre, Tipo_producto, Double.parseDouble(precio), 0));
		System.out.println("se modific� el producto");
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Aceptar_modificar_Producto() {
		if (!textNombre.getText().equals("")) {
			if (comprobarPrecio()) {
				if(!comboTipo.getSelectedItem().toString().equals("")){

					guardarCambios(textNombre.getText(), textPrecio.getText(), comboTipo.getSelectedItem().toString());
					textNombre.setText("");
					textPrecio.setText("");
					comboTipo.getSelectedItem().toString();
					
					iniciarlizarTablaProducto();
					llenar_tabla();
					lblAviso.setVisible(false);
					btnAceptar.setVisible(false);
					btnAgregar.setVisible(true);
					btnCancelar.setVisible(false);
					btnModificar.setVisible(true);
					btnEliminar.setVisible(true);

				}else{
					lblAviso.setText("Debes completar el campo 'Telefono' para continuar");
					lblAviso.setVisible(true);
				}
			
			}else{
				lblAviso.setText("Debes completar el campo 'Direcci�n' para continuar");
				lblAviso.setVisible(true);
			}
		} else{
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}// ---> FIN CLASE
