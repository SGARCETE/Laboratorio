package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Interfaz.Swing_Extends.JTable_Listado_Pedidos;
import Interfaz.Swing_Extends.JTable_Pedido_Completo;
import Interfaz.Swing_Extends.Model_Listado_Pedidos;
import Interfaz.Swing_Extends.Model_Pedido_Completo;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Servicio_Clientes;
import Negocio.Servicios.Servicio_Pedidos;
import Negocio.Servicios.Servicio_Productos;
import Negocio.Servicios.Servicio_Repartidores;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;

import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
//import com.mxrck.autocompleter.AutoCompleterCallback;
//import com.mxrck.autocompleter.TextAutoCompleter;

public class Interfaz_Principal {

	private JFrame frmWildsoft;
	private JScrollPane scrollPane_Lista_Pedidos;
	private JTable Tabla_Pedido_Completo;
	private JTable Tabla_Lista_pedidos;
	private JComboBox<String> comboBoxProducto;
	private JComboBox<String> comboBoxVariedad;
	private JCheckBox chckbxDelivery;
	private JSpinner spinnerCantidad;
	private JTextField textDomicilio;
	private JTextField textTelefono;
	private JTextField textDetalle;
	private JTextField textValor;
	private JTextField textObservaciones;
	private JTextField textValorTotal;
	private JTextField textCliente;
	private JTextField textProducto = new JTextField();		// se inicializa antes del AutoCompleter, sino tira excepcion
	private JTextField textVariedad = new JTextField();		// se inicializa antes del AutoCompleter, sino tira excepcion
	private TextAutoCompleter AutoCompleter_Producto = new TextAutoCompleter(textProducto, new AutoCompleterCallback() {
	    public void callback(Object selectedItem) { // Para saber que selecciono el usuario
//	    	<HACE ALGO SI TE ELIJO>			ejemplo: cargarClienteParaCargar(CN.getGestorClientes().getInfoCliente((String)selectedItem));
	    }
	});
	private TextAutoCompleter AutoCompleter_Variedad = new TextAutoCompleter(textVariedad, new AutoCompleterCallback() {
	    public void callback(Object selectedItem) { // Para saber que selecciono el usuario
//	    	<HACE ALGO SI TE ELIJO>			ejemplo: cargarClienteParaCargar(CN.getGestorClientes().getInfoCliente((String)selectedItem));
	    }
	});
	//	private TextAutoCompleter AutoCompleter_Nombre = new TextAutoCompleter(textNombre_Cliente);
	
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();	// Muestra un Double en formato Dinero. Ej: 50.5  => $50,50
	
	private Pedido PEDIDO_ACTUAL = new Pedido();				// Cuando creo un nuevo pedido lo voy llenando aca, cuando lo termino se resetea
	private Producto PRODUCTO_ACTUAL = new Producto();			// Cuando selecciono el producto, este va a saber la variedad, observacion, cantidad, total, cuando lo agrego a la tabla se resetea para ingresar otro
	private JTextField textTotal_Pedido;
	private JScrollPane scrollPane;
	
	// inicializador de servicios
	private Servicio_Productos sv_productos = new Servicio_Productos();
	private Servicio_Clientes sv_clientes = new Servicio_Clientes();
	private Servicio_Pedidos sv_pedidos = new Servicio_Pedidos();
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	
	/**
	 * Launch the application.		ESTO ACA ES TEMPORAL, ESTA MAL ACA, debe ir en Negocio.Servicios Ejecutar_WILDSOFT.java
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					
					// Look and feel por defecto: Nimbus
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
					Interfaz_Principal window = new Interfaz_Principal();
					window.frmWildsoft.setVisible(true);
				    SwingUtilities.updateComponentTreeUI(window.frmWildsoft);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public Interfaz_Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWildsoft = new JFrame();
		frmWildsoft.setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz_Principal.class.getResource("/Recursos/Pizza-icon16.png")));
		frmWildsoft.setTitle("WildSoft");
		frmWildsoft.setBounds(100, 100, 962, 550);
		frmWildsoft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.menu);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(frmWildsoft.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
		);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Panel de Nuevo Pedido
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		tabbedPane.addTab("Nuevo pedido", null, panel, null);
		panel.setLayout(null);
		
		JPanel panelProductos = new JPanel();
		panelProductos.setBackground(new Color(240,240,240));
		panelProductos.setLayout(null);
		panelProductos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Pedido", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelProductos.setBounds(0, 6, 940, 326);
		panel.add(panelProductos);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblIngreseLosProductos = new JLabel("Ingrese los productos que componen su pedido");
		lblIngreseLosProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosProductos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIngreseLosProductos.setBounds(18, 20, 420, 25);
		lblIngreseLosProductos.setBounds(18, 20, 404, 25);
		panelProductos.add(lblIngreseLosProductos);
		
		JPanel panelAltaPedido = new JPanel();
		panelAltaPedido.setBackground(SystemColor.menu);
		panelAltaPedido.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAltaPedido.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelAltaPedido.setBounds(18, 49, 331, 266);
		panelProductos.add(panelAltaPedido);
		panelAltaPedido.setLayout(null);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 12, 110, 25);
		panelAltaPedido.add(lblProducto);
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		

		textProducto.setBounds(119, 12, 98, 25);
		panelAltaPedido.add(textProducto);
		textProducto.setColumns(10);
		
		comboBoxProducto = new JComboBox<String>();
		comboBoxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_De_Producto();
				Seleccion_De_Tipo_Producto();
			}
		});
		comboBoxProducto.setBounds(218, 12, 100, 25);
		panelAltaPedido.add(comboBoxProducto);
		comboBoxProducto.setModel(new DefaultComboBoxModel<String>(new String[] {"Pizzas", "Empanadas", "Bebidas"}));
//		comboBoxProducto.setModel(new DefaultComboBoxModel<String>(new String[] {"Pizzas", "Empanadas", "Bebidas"}));
		
		comboBoxVariedad = new JComboBox<String>();
		comboBoxVariedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccion_De_Variedad();
			}
		});
		comboBoxVariedad.setBounds(218, 49, 100, 25);
		panelAltaPedido.add(comboBoxVariedad);
		comboBoxVariedad.setModel(new DefaultComboBoxModel<String>(new String[] {"Napolitana", "Napolitana especial", "Muzzarella", "Jamon y palmito", "Queso y peperoni", "Hawaiana", "Jamon y Panceta", "Cuatro quesos", "Salmon Ahumado", "Cuatro estaciones", "Pizza Funghi", "Vegetariana"}));
//		comboBoxVariedad.setModel(new DefaultComboBoxModel<String>(new String[] {"Napolitana", "Napolitana especial", "Muzzarella", "Jamon y palmito", "Queso y peperoni", "Hawaiana", "Jamon y Panceta", "Cuatro quesos", "Salmon Ahumado", "Cuatro estaciones", "Pizza Funghi", "Vegetariana"}));
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblVariedad = new JLabel("Variedad");
		lblVariedad.setBounds(10, 49, 110, 25);
		panelAltaPedido.add(lblVariedad);
		lblVariedad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 85, 110, 25);
		panelAltaPedido.add(lblCantidad);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Calcula_totales();
			}
		});
		spinnerCantidad.setBounds(117, 85, 59, 25);
		panelAltaPedido.add(spinnerCantidad);
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblValor = new JLabel("Valor c/u     $");
		lblValor.setBounds(10, 122, 110, 25);
		panelAltaPedido.add(lblValor);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textValor = new JTextField();
		textValor.setHorizontalAlignment(SwingConstants.RIGHT);
		textValor.setText("$0,00");
		textValor.setBounds(117, 122, 199, 25);
		panelAltaPedido.add(textValor);
		textValor.setEditable(false);
		textValor.setColumns(10);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblValorTotal = new JLabel("Valor total   $");
		lblValorTotal.setBounds(10, 159, 110, 25);
		panelAltaPedido.add(lblValorTotal);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblObservacion = new JLabel("Observaciones");
		lblObservacion.setBounds(10, 199, 110, 25);
		panelAltaPedido.add(lblObservacion);
		lblObservacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textObservaciones = new JTextField();
		textObservaciones.setBounds(117, 199, 199, 25);
		panelAltaPedido.add(textObservaciones);
		textObservaciones.setColumns(10);
		
		textValorTotal = new JTextField();
		textValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textValorTotal.setText("$0,00");
		textValorTotal.setToolTipText("");
		textValorTotal.setBounds(117, 159, 199, 25);
		panelAltaPedido.add(textValorTotal);
		textValorTotal.setEditable(false);
		textValorTotal.setColumns(10);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(117, 232, 100, 30);
		panelAltaPedido.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		
		textVariedad.setColumns(10);
		textVariedad.setBounds(119, 49, 98, 25);
		panelAltaPedido.add(textVariedad);
		
		JPanel panel_Resumen_Pedido = new JPanel();
		panel_Resumen_Pedido.setBackground(SystemColor.menu);
		panel_Resumen_Pedido.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Resumen_Pedido.setBorder(new TitledBorder(null, "", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_Resumen_Pedido.setBounds(354, 49, 576, 266);
		panelProductos.add(panel_Resumen_Pedido);
		panel_Resumen_Pedido.setLayout(null);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(10, 232, 100, 30);
		panel_Resumen_Pedido.add(btnQuitar);
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quitar_al_Pedido();
			}
		});
		btnQuitar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Tabla que muestra el pedido
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 42, 556, 176);
		panel_Resumen_Pedido.add(scrollPane);
		
		JLabel lblResumenDelPedido = new JLabel("Pedido completo");
		lblResumenDelPedido.setBounds(10, 11, 556, 25);
		panel_Resumen_Pedido.add(lblResumenDelPedido);
		lblResumenDelPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenDelPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.setEnabled(false);
		btnModificar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/edit-icon24.png")));
		btnModificar.setBounds(132, 232, 121, 30);
		panel_Resumen_Pedido.add(btnModificar);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_al_Pedido();
			}
		});
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Panel de Delibery
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JPanel panelDelibery = new JPanel();
		panelDelibery.setBackground(SystemColor.menu);
		panelDelibery.setBounds(6, 334, 654, 116);
		panelDelibery.setBounds(6, 334, 609, 116);
		panel.add(panelDelibery);
		panelDelibery.setBorder(new TitledBorder(null, "Servicio delivery", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDelibery.setLayout(null);
		
		chckbxDelivery = new JCheckBox("Delivery");
		chckbxDelivery = new JCheckBox("Con delivery");
		chckbxDelivery.setBackground(SystemColor.menu);
		chckbxDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servicio_Delivery();
			}
		});
		chckbxDelivery.setSelected(true);
		chckbxDelivery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDelivery.setBounds(16, 14, 97, 25);
		chckbxDelivery.setBounds(16, 14, 110, 25);
		panelDelibery.add(chckbxDelivery);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDomicilio.setBounds(332, 46, 97, 25);
		lblDomicilio.setBounds(299, 46, 70, 25);
		panelDelibery.add(lblDomicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(439, 46, 199, 25);
		textDomicilio.setBounds(381, 46, 199, 25);
		panelDelibery.add(textDomicilio);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(16, 82, 97, 25);
		lblTelefono.setBounds(16, 82, 70, 25);
		panelDelibery.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(123, 82, 199, 25);
		textTelefono.setBounds(90, 82, 199, 25);
		panelDelibery.add(textTelefono);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetalle.setBounds(332, 82, 97, 25);
		lblDetalle.setBounds(299, 82, 70, 25);
		panelDelibery.add(lblDetalle);
		
		textDetalle = new JTextField();
		textDetalle.setColumns(10);
		textDetalle.setBounds(439, 82, 199, 25);
		textDetalle.setBounds(381, 82, 199, 25);
		panelDelibery.add(textDetalle);
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCliente.setBounds(16, 46, 97, 25);
		lblCliente.setBounds(16, 46, 70, 25);
		panelDelibery.add(lblCliente);
		
		textCliente = new JTextField();
		textCliente.setColumns(10);
		textCliente.setBounds(123, 46, 199, 25);
		textCliente.setBounds(90, 46, 199, 25);
		panelDelibery.add(textCliente);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBorder(new TitledBorder(null, "Importes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_3.setBounds(670, 334, 261, 116);
		panel_3.setBounds(616, 334, 324, 116);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		textTotal_Pedido = new JTextField();
		textTotal_Pedido.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textTotal_Pedido.setHorizontalAlignment(SwingConstants.RIGHT);
		textTotal_Pedido.setText("$0,00");
		textTotal_Pedido.setBounds(17, 62, 145, 35);
		panel_3.add(textTotal_Pedido);
		textTotal_Pedido.setColumns(10);
		
		JLabel lblImporteTotal = new JLabel("Importe total");
		lblImporteTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblImporteTotal.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblImporteTotal.setBounds(44, 23, 177, 34);
		lblImporteTotal.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblImporteTotal.setBounds(17, 30, 145, 30);
		panel_3.add(lblImporteTotal);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guardar_pedido();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Save-icon48.png")));
		btnNewButton.setBounds(174, 30, 129, 67);
		panel_3.add(btnNewButton);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Panel de listado de pedidos
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Listado de pedidos", null, panel_1, null);
		
		scrollPane_Lista_Pedidos = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_Lista_Pedidos, GroupLayout.DEFAULT_SIZE, 936, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(94)
					.addComponent(scrollPane_Lista_Pedidos, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		panel_1.setLayout(gl_panel_1);
		frmWildsoft.getContentPane().setLayout(groupLayout);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Menu
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>		
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		frmWildsoft.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmRealizarBackup = new JMenuItem("Realizar Backup");
		mnArchivo.add(mntmRealizarBackup);
		
		JMenuItem mntmRestaurarBackup = new JMenuItem("Restaurar Backup");
		mnArchivo.add(mntmRestaurarBackup);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnPedidos = new JMenu("Pedidos");
		menuBar.add(mnPedidos);
		
		JMenuItem mntmNuevoPedido = new JMenuItem("Nuevo Pedido");
		mnPedidos.add(mntmNuevoPedido);
		
		JMenuItem mntmListaDePedidos = new JMenuItem("ADM pedidos");
		mnPedidos.add(mntmListaDePedidos);
		
		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);
		
		JMenuItem mntmNuevoCliente = new JMenuItem("Nuevo Cliente");
		mnClientes.add(mntmNuevoCliente);
		
		JMenuItem mntmAdmClientes = new JMenuItem("ADM Clientes");
		mnClientes.add(mntmAdmClientes);
		
		JMenu mnCombos = new JMenu("Combos");
		menuBar.add(mnCombos);
		
		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);
		
		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);
		
		JMenu mnMateriasPrimas = new JMenu("Materias primas");
		mnProveedores.add(mnMateriasPrimas);
		
		JMenu mnReportes = new JMenu("Reportes ventas");
		menuBar.add(mnReportes);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmAcercaDeWildsoft = new JMenuItem("Acerca de WildSoft");
		mnAyuda.add(mntmAcercaDeWildsoft);
//		frmWildsoft.setVisible(true);
		
		iniciarParametros();
		
	}//--> FIN INTERFAZ



	private void Seleccion_De_Producto() {
		if(!comboBoxProducto.getSelectedItem().toString().isEmpty()){
			textProducto.setText(comboBoxProducto.getSelectedItem().toString());
			Cargar_Variedades_del_producto(comboBoxProducto.getSelectedItem().toString());
		}
	}


	
	/**
	 * CARGA TODOS LOS DATOS NECESARIOS CUANDO INICIA LA INTERFAZ
	 */
	private void iniciarParametros() {
		Tabla_Lista_pedidos = new JTable_Listado_Pedidos(new Model_Listado_Pedidos());
		scrollPane_Lista_Pedidos.setViewportView(Tabla_Lista_pedidos);
		
		
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane.setViewportView(Tabla_Pedido_Completo);
		
		ArrayList<String> ListaProductos = sv_productos.getLista_Productos(); 
		for (int i = 0; i < ListaProductos.size(); i++) {
			comboBoxProducto.addItem(ListaProductos.get(i));
		}
		
		ArrayList<Producto> ListaVarie = sv_productos.getVariedad_del_Producto("PIZZA");
		for (int i = 0; i < ListaVarie.size(); i++) {
			comboBoxVariedad.addItem(ListaVarie.get(i).getPR_nombre());
		}
		
		//******		PRUEBA HARDCODEADO		*********//
		
		ArrayList<Object> L_Producto = new ArrayList<Object>();
		L_Producto.add("Pizza");
		L_Producto.add("Empanada");
		L_Producto.add("Bebida");
		agregar_elem_Autocompleter_Productos(L_Producto);
		
		// Variedad de pizza, cada producto tiene su variedad
		ArrayList<Object> L_Variedad = new ArrayList<Object>();
		L_Variedad.add("Napolitana");
		L_Variedad.add("Napolitana especial"); 
		L_Variedad.add("Muzzarella");
		L_Variedad.add("Jamon y palmito");
		L_Variedad.add("Queso y peperoni");
		L_Variedad.add("Hawaiana");
		L_Variedad.add("Jamon y Panceta");
		L_Variedad.add("Cuatro quesos");
		L_Variedad.add("Salmon Ahumado"); 
		L_Variedad.add("Cuatro estaciones");
		L_Variedad.add("Pizza Funghi");
		L_Variedad.add("Vegetariana");
		agregar_elem_Autocompleter_Variedad(L_Variedad);
		
		//******		FIN PRUEBA HARDCODE		*********//
		
	}
	/** Se limpia el formulario de pedido producto cuando ya se agrego al pedido 	 */
	
	
	/**
	 *  CALCULA EL TOTAL SEGUN LA CANTIDAD DE UNIDADES DEL MISMO PRODUCTO Y EL TOTAL DEL PEDIDO
	 */
	private void Calcula_totales() {
		if(PRODUCTO_ACTUAL!=null && PEDIDO_ACTUAL.getLista_Productos()!=null){
			// CALCULA EL TOTAL POR LA CANTIDAD DE UNIDADES QUE LLEVA DEL MISMO PRODUCTO
			textValorTotal.setText(formatoImporte.format(PRODUCTO_ACTUAL.getPR_precio() * Integer.parseInt(spinnerCantidad.getValue().toString())));
			// CALCULA EL TOTAL DEL PEDIDO
			Double TOTAL_PEDIDO = 0.0;
			for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
				TOTAL_PEDIDO += PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_precio();
			}
			textTotal_Pedido.setText(formatoImporte.format(TOTAL_PEDIDO));
		}
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	/**
	 * CUANDO SE SELECCIONA UN TIPO DE PRODUCTO [BEBIDA, EMPANADA, PIZZA] DEL COMBOBOX, ESTE DATO
	 * SE COPIA AL TEXTFIELD CORRESPONDIENTE, Y A CONTINUACION SE CARGAN LAS VARIEDADES DEL TIPO DE
	 * PRODUCTO SELECCIONADO:
	 * EJEMPLO: SI SE SELECCIONO BEBIDA, SE DEBE OBTENER LAS VARIEDADES DE BEBIDAS: COCACOLA, SPRITE, VINO Y SETEAR 
	 * EN EL COMBOBOX Y AUTOCOMPLETER DE VARIEDADES
	 */
	private void Seleccion_De_Tipo_Producto() {
		if(!comboBoxProducto.getSelectedItem().toString().isEmpty()){
			textProducto.setText(comboBoxProducto.getSelectedItem().toString());
			Cargar_Variedades_del_producto(comboBoxProducto.getSelectedItem().toString());
		}
	}

	/**
	 * 
	 * @param TIPO_PRODUCTO
	 */
	private void Cargar_Variedades_del_producto(String TIPO_PRODUCTO) {
		Lista_Variedades = sv_productos.getVariedad_del_Producto(TIPO_PRODUCTO);
		comboBoxVariedad.removeAllItems();
		for (int i = 0; i < Lista_Variedades.size(); i++) {
			comboBoxVariedad.addItem(Lista_Variedades.get(i).getPR_nombre());
		}
	}

	/**
	 * CUANDO SE SELECCIONA UNA VARIEDAD [COCACOLA, SPRITE, VINO] DEL COMBOBOX, ESTE DATO
	 * SE COPIA AL TEXTFIELD CORRESPONDIENTE, Y A CONTINUACION SE CARGA EL PRECIO DE LA VARIEDAD
	 * SELECCIONADA.
	 * EJEMPLO: SE SELECCIONA LA VARIEDAD COCACOLA [DE BEBIDAS], SE OBTIENE EL PRECIO DE DICHO PRODUCTO
	 * Y SE SETEA EN EL TEXTFIELD DEL PRECIO Y EN "PRODUCTO_ACTUAL"
	 */
	private void Seleccion_De_Variedad() {
		if(comboBoxVariedad.getSelectedItem()!=null && !comboBoxVariedad.getSelectedItem().toString().isEmpty()){
			textVariedad.setText(comboBoxVariedad.getSelectedItem().toString());
			Cargar_precio_del_producto(comboBoxVariedad.getSelectedItem().toString());
			Calcula_totales();
		}
	}
	
	/**
	 * 
	 * @param VARIEDAD
	 */
	private void Cargar_precio_del_producto(String VARIEDAD) {
		for (int i = 0; i < Lista_Variedades.size(); i++) {
			if(Lista_Variedades.get(i).getPR_nombre().equals(VARIEDAD))
				PRODUCTO_ACTUAL = Lista_Variedades.get(i);
		}	
		textValor.setText(formatoImporte.format(PRODUCTO_ACTUAL.getPR_precio()));
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			LIMPIADOR DE CAMPOS
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	/** SE LIMPIAN LOS DATOS DEL FORMULARIO DE ALTA DE PRODUCTO PARA PODER INGRESAR UNO NUEVO	 */
	private void Limpiar_Formulario_pedido(){
		PRODUCTO_ACTUAL = new Producto();	
		comboBoxProducto.setSelectedIndex(0);
		comboBoxVariedad.removeAllItems();
		textVariedad.setText("");
		textProducto.setText("");
		comboBoxProducto.setSelectedIndex(0);	
		textVariedad.setText("");
		comboBoxVariedad.setSelectedIndex(0);
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		textValor.setText("");
		textValorTotal.setText("");
		textObservaciones.setText("");
		
	}
	/**
	 * 
	 */
	private void Limpiar_Todo() {
		Limpiar_Formulario_pedido();
		PEDIDO_ACTUAL = new Pedido();
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane.setViewportView(Tabla_Pedido_Completo);		
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			AGREGAR
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	/**
	 * Toma los datos del panel "panelAltaPedido" y los inserta en la tabla de pedido general
	 */
	private void Agregar_al_Pedido() {
		// hacer que tome los datos del formulario de pedido y los agregue a la tabla de pedidos LISTO
		
	//	if( DATOS OBLIGATORIOS ESTAN COMPLETOS (!=null y a vacio), PUEDO GUARDARLO EN LA LISTA DEL PEDIDO){
		
			String producto = textProducto.getText();	// autocompleter  o search
	//		o
			String productoCB = comboBoxProducto.getSelectedItem().toString();			// o combo box
	
//		if( DATOS OBLIGATORIOS ESTAN COMPLETOS (!=null y a vacio), PUEDO GUARDARLO EN LA LISTA DEL PEDIDO){
			String Tipo_producto = textProducto.getText();	// autocompleter  o search
			//			String productoCB = comboBoxProducto.getSelectedItem().toString();			// o combo box
			String Variedad = textVariedad.getText();	// autocompleter o search
	//		o
			String VariedadCB = comboBoxVariedad.getSelectedItem().toString();	// o combo box
	
			//			String VariedadCB = comboBoxVariedad.getSelectedItem().toString();	// o combo box
			Integer cantidad = Integer.parseInt(spinnerCantidad.getValue().toString());
		if(!Tipo_producto.isEmpty() && !Variedad.isEmpty() && cantidad>0){
			Double ValorU = PRODUCTO_ACTUAL.getPR_precio();
			Double ValorT = PRODUCTO_ACTUAL.getPR_precio() * Integer.parseInt(spinnerCantidad.getValue().toString());

			PRODUCTO_ACTUAL.setPR_nombre(Variedad);
			String Observacion = textObservaciones.getText();
			
			/** Esto va para la parte visual	**/
			DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();	
			modelo.addRow(new Object[] { modelo.getRowCount()+1, cantidad, producto, Variedad, formatoImporte.format(ValorU), formatoImporte.format(ValorT), Observacion});	// "Nro", "Unidades", "Producto", "Importe c/u", "Importe", "Observacion"
			modelo.addRow(new Object[] { modelo.getRowCount()+1, cantidad, Tipo_producto, Variedad, formatoImporte.format(ValorU), formatoImporte.format(ValorT), Observacion});	// "Nro", "Unidades", "Producto", "Importe c/u", "Importe", "Observacion"
			Tabla_Pedido_Completo.setModel(modelo);	// Lo seteo en la tabla para que se vea
			
			
			/** Esto va a un objeto pedido, el cual se usara para guardar en la base de datos	**/
			PEDIDO_ACTUAL.agregar_un_producto(PRODUCTO_ACTUAL);
			
			for (int i = 0; i < cantidad; i++) 
				PEDIDO_ACTUAL.agregar_un_producto(PRODUCTO_ACTUAL);
						
			/** Despues que se resetee el formulario de ingreso de pedido**/
			Limpiar_Formulario_pedido();
		//}
		}
	}
	/**
	 * 
	 */
	private void Guardar_pedido() {
		if(!PEDIDO_ACTUAL.getLista_Productos().isEmpty()){
			//sv_pedidos.agregar_pedido(PEDIDO_ACTUAL);
			Limpiar_Todo();
			//TODO- actualizar Tabla_Lista_pedidos
		}
	}
	




	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			QUITAR/ ELIMINAR
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	/**
	 * SI HAY UN ELEMENTO SELECCIONADO EN LA LISTA DE PRODUCTOS, DE UN PEDIDO, SE ELIMINARA ESE ELEMENTO DE LA LISTA 
	 */
	private void Quitar_al_Pedido() {
		// LO QUITA DE LA LISTA DE PEDIDO
//		if(!PEDIDO_ACTUAL.getLista_Productos().isEmpty()){
//			for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
//				PEDIDO_ACTUAL.getLista_Productos().get(i).
//			}
//		}
		// LO QUITA DE LA LISTA VISUAL
		if(Tabla_Pedido_Completo.getSelectedRow()!=-1){		// -1 es cuando no se selecciono nada en la tabla, si es distinto, entonces es xq selecciono algo y se puede quitar
			int indice_Seleccionado = Tabla_Pedido_Completo.getSelectedRow();		// indice de la tabla, (No funciona si se ordenan los datos desde la tabla, ojo)
			DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();	
			modelo.removeRow(indice_Seleccionado);
		}
		
	}


	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	/**
	 * ACTIVA/DESACTIVA LAS OPCIONES DEL DELIVERY SI ES QUE SE SELECCIONA LA OPCION "DELIVERY" EN LA INTERFAZ
	 */
	private void Servicio_Delivery() {
		textCliente.setEnabled(chckbxDelivery.isSelected());
		textDomicilio.setEnabled(chckbxDelivery.isSelected());
		textTelefono.setEnabled(chckbxDelivery.isSelected());
		textDetalle.setEnabled(chckbxDelivery.isSelected());
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>	
	/**
	 * AGREGA ELEMENTOS (NOMBRES DE "TIPO DE PRODUCTOS") AL AUTOCOMPLETER DE "TIPO DE PRODUCTOS"
	 * @param L_producto	RECIBE UNA LISTA DE "TIPO DE PRODUCTOS" [EJEMPLO: EMAPANDAS, PIZZAS, BEBIDAS]
	 */
	public void agregar_elem_Autocompleter_Productos(ArrayList<Object> L_producto){
		AutoCompleter_Producto.removeAllItems();
		AutoCompleter_Producto.setCaseSensitive(false);
		AutoCompleter_Producto.setMode(0);
		AutoCompleter_Producto.addItems(L_producto);
	}
	
	/**
	 * AGREGA ELEMENTOS (NOMBRES DE "PRODUCTOS") AL AUTOCOMPLETER DE "PRODUCTOS"
	 * @param L_variedad	RECIBE UNA LISTA DE NOMBRE DE PRODUCTOS DE UN "TIPO DE PRODUCTO", [EJEMPLO: SI EL "TIPO DE PRODUCTO" ES BEBIDA, 
	 * ESTA LISTA DEBE CONTENER LOS NOMBRES DE LAS VARIEDADES DE LA "BEBIDA", EJEMPLO, COCACOLA, SPRITE, CERVEZA, MANAOS, ETC]
	 */
	public void agregar_elem_Autocompleter_Variedad(ArrayList<Object> L_variedad){
		AutoCompleter_Variedad.removeAllItems();
		AutoCompleter_Variedad.setCaseSensitive(false);
		AutoCompleter_Variedad.setMode(0);
		AutoCompleter_Variedad.addItems(L_variedad);
		
	}
}//---> FIN CLASE
