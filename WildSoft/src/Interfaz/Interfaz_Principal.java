package Interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;

import Interfaz.JDialogs.Interfaz_ABM_Pedido;
import Interfaz.Swing_Extends.JTable_Listado_Pedidos;
import Interfaz.Swing_Extends.JTable_Pedido_Completo;
import Interfaz.Swing_Extends.Model_Listado_Pedidos;
import Interfaz.Swing_Extends.Model_Pedido_Completo;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Clientes;
import Negocio.Servicios.Servicio_Pedidos;
import Negocio.Servicios.Servicio_Productos;

public class Interfaz_Principal {

	public JFrame frmWildsoft;
	// <COMPONENTES>
	private JTabbedPane tabbedPane;
	private JScrollPane scrollPane_Lista_Pedidos;
	private JScrollPane scrollPane_Pedido_Completo;
	private JTable_Pedido_Completo Tabla_Pedido_Completo;
	private JTable_Listado_Pedidos Tabla_Lista_pedidos;
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
	private JTextField textCliente = new JTextField();
	private JTextField textTotal_Pedido;

	private TextAutoCompleter AutoCompleter_Cliente = new TextAutoCompleter(textCliente, new AutoCompleterCallback() {
	@Override
	public void callback(Object selectedItem) { // Para saber que selecciono el usuario // <HACE ALGO SI TE ELIJO> ejemplo:
			String Nombre_Cliente_seleccionado = ((String)selectedItem);
			Cliente C = sv_clientes.getCliente(Nombre_Cliente_seleccionado);
			Cargar_datos_Cliente(C);
		}
	});

	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(); /* Muestra un Double en formato Dinero. Ej: 50.5 => $50,50 */
	private SimpleDateFormat formato_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	

	// <SERVICIOS> [solo los que voy a usar en esta clase, la instancia se le pide a Principal_Negocio_principal]
	private Servicio_Productos sv_productos;
	private Servicio_Clientes sv_clientes;
	private Servicio_Pedidos sv_pedidos;
	
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	private Pedido PEDIDO_ACTUAL = new Pedido(); 	   /* Cuando creo un nuevo pedido lo voy llenando aca, cuando lo termino se resetea */
	private Producto PRODUCTO_ACTUAL = new Producto(); /* Cuando selecciono el producto, este va a saber la variedad, observacion, cantidad, total, cuando lo agrego a la tabla se resetea para ingresar otro*/

	//<INSTANCIAS DE ESTA INTERFAZ Y DE PRINCIPAL>
	@SuppressWarnings("unused")
	private Interfaz_Principal Instancia_de_Interfaz_Principal;
	private Principal_Negocio_Interfaz Principal_neg_int; 

	/**
	 * Create the application.
	 * @param principal_Negocio_Interfaz 
	 */
	public Interfaz_Principal(Principal_Negocio_Interfaz iNSTANCIA_principal_Negocio_Interfaz) {
		Instancia_de_Interfaz_Principal = this; 					/* Esta instancia de Interfaz_principal sirve para poder pasarsela a otras ventanas externas y asi poder comunicarse*/
		Principal_neg_int = iNSTANCIA_principal_Negocio_Interfaz; 	/* ESTA CLASE ES NECESARIA PARA QUE TODA INTERFAZ PUEDA COMUNICARSE CON LA PARTE DE NEGOCIO*/
		sv_productos = Principal_neg_int.getSvProductos();			/* USAMOS LA INSTANCIA DE SERVICIO YA CREADA EN PRINCIPAL */
		sv_clientes  = Principal_neg_int.getSvClientes();			/* USAMOS LA INSTANCIA DE SERVICIO YA CREADA EN PRINCIPAL */
		sv_pedidos 	 = Principal_neg_int.getSvPedidos();			/* USAMOS LA INSTANCIA DE SERVICIO YA CREADA EN PRINCIPAL */
		initialize();												/* GENERA EL CONTENIDO DE LA INTERFAZ, LOS COMPONENTES */
		iniciarParametros();										/* INICIA LAS VARIABLES Y METODOS NECESARIOS PARA PODER EMPEZAR A OPERAR*/
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWildsoft = new JFrame();
		frmWildsoft.setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz_Principal.class.getResource("/Recursos/Pizza-icon16.png")));
		frmWildsoft.setTitle("WildSoft");
		frmWildsoft.setBounds(100, 100, 1031, 580);
		frmWildsoft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.menu);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(frmWildsoft.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(
				Alignment.LEADING).addComponent(tabbedPane,
				GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Panel de Nuevo Pedido
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panel_Nuevo_pedido = new JPanel();
		panel_Nuevo_pedido.setBackground(SystemColor.menu);
		tabbedPane.addTab("Nuevo pedido", null, panel_Nuevo_pedido, null);

		JPanel panelProductos = new JPanel();
		panelProductos.setBackground(new Color(240, 240, 240));
		panelProductos.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Pedido",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblIngreseLosProductos = new JLabel(
				"Ingrese los productos que componen su pedido");
		lblIngreseLosProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosProductos.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel panelAltaPedido = new JPanel();
		panelAltaPedido.setBackground(SystemColor.menu);
		panelAltaPedido.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAltaPedido.setBorder(new TitledBorder(null, "",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelAltaPedido.setLayout(null);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 12, 109, 25);
		panelAltaPedido.add(lblProducto);
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBoxProducto = new JComboBox<String>();
		comboBoxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_De_Tipo_Producto();
			}
		});
		comboBoxProducto.setBounds(117, 12, 201, 25);
		panelAltaPedido.add(comboBoxProducto);

		comboBoxVariedad = new JComboBox<String>();
		comboBoxVariedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccion_De_Variedad();
			}
		});
		comboBoxVariedad.setBounds(117, 49, 201, 25);
		panelAltaPedido.add(comboBoxVariedad);
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblVariedad = new JLabel("Variedad");
		lblVariedad.setBounds(10, 49, 109, 25);
		panelAltaPedido.add(lblVariedad);
		lblVariedad.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 85, 109, 25);
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

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblValor = new JLabel("Valor c/u     $");
		lblValor.setBounds(10, 122, 109, 25);
		panelAltaPedido.add(lblValor);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textValor = new JTextField();
		textValor.setHorizontalAlignment(SwingConstants.RIGHT);
		textValor.setText("$0,00");
		textValor.setBounds(117, 122, 199, 25);
		panelAltaPedido.add(textValor);
		textValor.setEditable(false);
		textValor.setColumns(10);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblValorTotal = new JLabel("Valor total   $");
		lblValorTotal.setBounds(10, 159, 109, 25);
		panelAltaPedido.add(lblValorTotal);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblObservacion = new JLabel("Observaciones");
		lblObservacion.setBounds(10, 195, 109, 25);
		panelAltaPedido.add(lblObservacion);
		lblObservacion.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textObservaciones = new JTextField();
		textObservaciones.setBounds(117, 195, 199, 25);
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

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(117, 225, 100, 30);
		panelAltaPedido.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(Interfaz_Principal.class
				.getResource("/Recursos/IMG/add-1-icon24.png")));

		JPanel panel_Resumen_Pedido = new JPanel();
		panel_Resumen_Pedido.setBackground(SystemColor.menu);
		panel_Resumen_Pedido.setBorder(new TitledBorder(null, "",TitledBorder.CENTER, TitledBorder.TOP, null, null));

		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quitar_al_Pedido();
			}
		});
		btnQuitar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Tabla que muestra el pedido
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		scrollPane_Pedido_Completo = new JScrollPane();

		JLabel lblResumenDelPedido = new JLabel("Pedido completo");
		lblResumenDelPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenDelPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_Resumen_Pedido = new GroupLayout(
				panel_Resumen_Pedido);
		gl_panel_Resumen_Pedido.setHorizontalGroup(
			gl_panel_Resumen_Pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Resumen_Pedido.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel_Resumen_Pedido.createParallelGroup(Alignment.LEADING)
						.addComponent(lblResumenDelPedido, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_Pedido_Completo, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
						.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addGap(8))
		);
		gl_panel_Resumen_Pedido.setVerticalGroup(
			gl_panel_Resumen_Pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Resumen_Pedido.createSequentialGroup()
					.addGap(9)
					.addComponent(lblResumenDelPedido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(scrollPane_Pedido_Completo, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
					.addGap(14)
					.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(1))
		);
		panel_Resumen_Pedido.setLayout(gl_panel_Resumen_Pedido);
		GroupLayout gl_panelProductos = new GroupLayout(panelProductos);
		gl_panelProductos
				.setHorizontalGroup(gl_panelProductos
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelProductos
										.createSequentialGroup()
										.addGap(12)
										.addGroup(
												gl_panelProductos
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblIngreseLosProductos,
																GroupLayout.PREFERRED_SIZE,
																404,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																gl_panelProductos
																		.createSequentialGroup()
																		.addComponent(
																				panelAltaPedido,
																				GroupLayout.PREFERRED_SIZE,
																				331,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(5)
																		.addComponent(
																				panel_Resumen_Pedido,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)))
										.addGap(4)));
		gl_panelProductos
				.setVerticalGroup(gl_panelProductos
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panelProductos
										.createSequentialGroup()
										.addGap(4)
										.addComponent(lblIngreseLosProductos,
												GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addGap(4)
										.addGroup(
												gl_panelProductos
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panelAltaPedido,
																GroupLayout.DEFAULT_SIZE,
																266,
																Short.MAX_VALUE)
														.addComponent(
																panel_Resumen_Pedido,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))
										.addGap(4)));
		panelProductos.setLayout(gl_panelProductos);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_al_Pedido();
			}
		});

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Panel de Delibery
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panelDelibery = new JPanel();
		panelDelibery.setBackground(SystemColor.menu);
		panelDelibery.setBorder(new TitledBorder(null, "Servicio delivery",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDelibery.setLayout(null);

		chckbxDelivery = new JCheckBox("Con delivery");
		chckbxDelivery.setBackground(SystemColor.menu);
		chckbxDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servicio_Delivery();
			}
		});
		chckbxDelivery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDelivery.setBounds(16, 14, 110, 25);
		panelDelibery.add(chckbxDelivery);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDomicilio.setBounds(299, 46, 70, 25);
		panelDelibery.add(lblDomicilio);

		textDomicilio = new JTextField();
		textDomicilio.setEnabled(false);
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(381, 46, 199, 30);
		panelDelibery.add(textDomicilio);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(16, 82, 70, 25);
		panelDelibery.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setEnabled(false);
		textTelefono.setColumns(10);
		textTelefono.setBounds(90, 82, 199, 30);
		panelDelibery.add(textTelefono);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetalle.setBounds(299, 82, 70, 25);
		panelDelibery.add(lblDetalle);

		textDetalle = new JTextField();
		textDetalle.setEnabled(false);
		textDetalle.setColumns(10);
		textDetalle.setBounds(381, 82, 199, 30);
		panelDelibery.add(textDetalle);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCliente.setBounds(16, 46, 70, 25);
		panelDelibery.add(lblCliente);
		textCliente.setEnabled(false);
		textCliente.setColumns(10);
		textCliente.setBounds(90, 46, 199, 30);
		panelDelibery.add(textCliente);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.menu);
		panel_3.setBorder(new TitledBorder(null, "Importes",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		lblImporteTotal.setBounds(17, 30, 145, 30);
		panel_3.add(lblImporteTotal);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guardar_pedido();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setIcon(new ImageIcon(Interfaz_Principal.class
				.getResource("/Recursos/IMG/Save-icon48.png")));
		btnNewButton.setBounds(174, 30, 129, 67);
		panel_3.add(btnNewButton);
		GroupLayout gl_panel_Nuevo_pedido = new GroupLayout(panel_Nuevo_pedido);
		gl_panel_Nuevo_pedido.setHorizontalGroup(gl_panel_Nuevo_pedido
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_panel_Nuevo_pedido
								.createSequentialGroup()
								.addComponent(panelProductos,
										GroupLayout.DEFAULT_SIZE, 940,
										Short.MAX_VALUE).addGap(1))
				.addGroup(
						gl_panel_Nuevo_pedido
								.createSequentialGroup()
								.addGap(6)
								.addComponent(panelDelibery,
										GroupLayout.PREFERRED_SIZE, 609,
										GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(panel_3,
										GroupLayout.DEFAULT_SIZE, 324,
										Short.MAX_VALUE).addGap(1)));
		gl_panel_Nuevo_pedido
				.setVerticalGroup(gl_panel_Nuevo_pedido
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_Nuevo_pedido
										.createSequentialGroup()
										.addGap(6)
										.addComponent(panelProductos,
												GroupLayout.DEFAULT_SIZE, 326,
												Short.MAX_VALUE)
										.addGap(2)
										.addGroup(
												gl_panel_Nuevo_pedido
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panelDelibery,
																GroupLayout.PREFERRED_SIZE,
																116,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																panel_3,
																GroupLayout.PREFERRED_SIZE,
																116,
																GroupLayout.PREFERRED_SIZE))
										.addGap(9)));
		panel_Nuevo_pedido.setLayout(gl_panel_Nuevo_pedido);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Panel de listado de pedidos
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panel_Lista_de_pedidos = new JPanel();
		panel_Lista_de_pedidos.setBackground(SystemColor.menu);
		tabbedPane.addTab("Listado de pedidos", null, panel_Lista_de_pedidos,
				null);

		scrollPane_Lista_Pedidos = new JScrollPane();

		JLabel lblListadoDePedidos = new JLabel("Listado de pedidos");
		lblListadoDePedidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDePedidos.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		panel.setBackground(SystemColor.menu);
		GroupLayout gl_panel_Lista_de_pedidos = new GroupLayout(
				panel_Lista_de_pedidos);
		gl_panel_Lista_de_pedidos
				.setHorizontalGroup(gl_panel_Lista_de_pedidos
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_Lista_de_pedidos
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_Lista_de_pedidos
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																lblListadoDePedidos,
																GroupLayout.DEFAULT_SIZE,
																971,
																Short.MAX_VALUE)
														.addGroup(
																gl_panel_Lista_de_pedidos
																		.createSequentialGroup()
																		.addComponent(
																				panel,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				scrollPane_Lista_Pedidos,
																				GroupLayout.DEFAULT_SIZE,
																				856,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		gl_panel_Lista_de_pedidos
				.setVerticalGroup(gl_panel_Lista_de_pedidos
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_Lista_de_pedidos
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblListadoDePedidos,
												GroupLayout.PREFERRED_SIZE, 25,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												ComponentPlacement.RELATED)
										.addGroup(
												gl_panel_Lista_de_pedidos
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																scrollPane_Lista_Pedidos,
																0, 0,
																Short.MAX_VALUE)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																418,
																Short.MAX_VALUE))
										.addGap(26)));

		JButton btnVer = new JButton("Ver");
		btnVer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Ver_Pedido_Seleccionado();
			}
		});

		JButton btnNuevo = new JButton("Nuevo");
		btnNuevo.setIcon(new ImageIcon(Interfaz_Principal.class
				.getResource("/Recursos/IMG/add-1-icon64.png")));
		btnNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		btnNuevo.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNuevo.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNuevo.setBackground(Color.WHITE);
		panel.add(btnNuevo);
		btnVer.setIcon(new ImageIcon(Interfaz_Principal.class
				.getResource("/Recursos/IMG/search-icon64.png")));
		btnVer.setHorizontalTextPosition(SwingConstants.CENTER);
		btnVer.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnVer);
		btnVer.setBackground(Color.WHITE);

		JButton btnModificar_1 = new JButton("Modificar");
		btnModificar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Pedido_Seleccionado();
			}
		});
		btnModificar_1.setIcon(new ImageIcon(Interfaz_Principal.class
				.getResource("/Recursos/IMG/edit-icon64.png")));
		btnModificar_1.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar_1.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnModificar_1);
		btnModificar_1.setBackground(Color.WHITE);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnCancelar);
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cancelar_Pedido_seleccionado();
			}
		});
		btnCancelar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/subtract-1-icon64.png")));

		panel_Lista_de_pedidos.setLayout(gl_panel_Lista_de_pedidos);
		frmWildsoft.getContentPane().setLayout(groupLayout);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Menu
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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
		
		// Repartidores	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JMenu mnRepartidor = new JMenu("Repartidores");
		menuBar.add(mnRepartidor);

		JMenuItem mntmADMRepartidores = new JMenuItem("Administracion de Repartidores");
		mntmADMRepartidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Abrir_Interfaz_ABM_Repartidor();
			}
		});
		mnRepartidor.add(mntmADMRepartidores);
		
		// Repartidores	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

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

	}// --> FIN INTERFAZ

	

	/**
	 * CARGA TODOS LOS DATOS NECESARIOS CUANDO INICIA LA INTERFAZ
	 */
	private void iniciarParametros() {
		// Creacion de la tabla vacia de lista de pedidos
		Tabla_Lista_pedidos = new JTable_Listado_Pedidos(new Model_Listado_Pedidos());
		scrollPane_Lista_Pedidos.setViewportView(Tabla_Lista_pedidos);

		// Creacion de la tabla vacia con el contenido de un pedido
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
		
		AutocompletarCliente();
		
		// Rellena el combobox de Tipos de productos
		ArrayList<String> ListaProductos = sv_productos.getLista_Productos();
		for (int i = 0; i < ListaProductos.size(); i++) {
			comboBoxProducto.addItem(ListaProductos.get(i));
		}
	}

	/***
	 * CALCULA EL TOTAL SEGUN LA CANTIDAD DE UNIDADES DEL MISMO PRODUCTO Y EL
	 * TOTAL DEL PEDIDO
	 */
	private void Calcula_totales() {
		if (PRODUCTO_ACTUAL != null
				&& PEDIDO_ACTUAL.getLista_Productos() != null) {

			// CALCULA EL TOTAL POR LA CANTIDAD DE UNIDADES QUE LLEVA DEL MISMO
			// PRODUCTO
			Double Total_Mismo_Producto = PRODUCTO_ACTUAL.getPR_precio()
					* (Integer.parseInt(spinnerCantidad.getValue().toString()));
			textValorTotal.setText(formatoImporte.format(Total_Mismo_Producto));

			// CALCULA EL TOTAL DEL PEDIDO, recorre todos los productos del
			// pedido tomando su precio y lo acumula
			Double TOTAL_PEDIDO = 0.0;
			for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
				TOTAL_PEDIDO += PEDIDO_ACTUAL.getLista_Productos().get(i)
						.getPR_precio();
			}
			PEDIDO_ACTUAL.setTotal(TOTAL_PEDIDO);
			textTotal_Pedido.setText(formatoImporte.format(TOTAL_PEDIDO));
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * CUANDO SE SELECCIONA UN TIPO DE PRODUCTO [BEBIDA, EMPANADA, PIZZA] DEL
	 * COMBOBOX, ESTE DATO SE COPIA AL TEXTFIELD CORRESPONDIENTE, Y A
	 * CONTINUACION SE CARGAN LAS VARIEDADES DEL TIPO DE PRODUCTO SELECCIONADO:
	 * EJEMPLO: SI SE SELECCIONO BEBIDA, SE DEBE OBTENER LAS VARIEDADES DE
	 * BEBIDAS: COCACOLA, SPRITE, VINO Y SETEAR EN EL COMBOBOX Y AUTOCOMPLETER
	 * DE VARIEDADES
	 */
	private void Seleccion_De_Tipo_Producto() {
		if (!comboBoxProducto.getSelectedItem().toString().isEmpty()) {
			// Cargar_Variedades_del_producto(comboBoxProducto.getSelectedItem().toString());
			Lista_Variedades = sv_productos.getVariedad_del_Producto(comboBoxProducto.getSelectedItem().toString());
			comboBoxVariedad.removeAllItems();
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				comboBoxVariedad.addItem(Lista_Variedades.get(i).getPR_nombre());
			}
		}
	}

	/**
	 * CUANDO SE SELECCIONA UNA VARIEDAD [COCACOLA, SPRITE, VINO] DEL COMBOBOX,
	 * ESTE DATO SE COPIA AL TEXTFIELD CORRESPONDIENTE, Y A CONTINUACION SE
	 * CARGA EL PRECIO DE LA VARIEDAD SELECCIONADA. EJEMPLO: SE SELECCIONA LA
	 * VARIEDAD COCACOLA [DE BEBIDAS], SE OBTIENE EL PRECIO DE DICHO PRODUCTO Y
	 * SE SETEA EN EL TEXTFIELD DEL PRECIO Y EN "PRODUCTO_ACTUAL"
	 */
	private void Seleccion_De_Variedad() {
		if (comboBoxVariedad.getSelectedItem() != null && !comboBoxVariedad.getSelectedItem().toString().isEmpty()) {
			// Cargar_precio_del_producto(comboBoxVariedad.getSelectedItem().toString());
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				if (Lista_Variedades.get(i).getPR_nombre()
						.equals(comboBoxVariedad.getSelectedItem().toString()))
					PRODUCTO_ACTUAL = Lista_Variedades.get(i); // SETEO EL PRODUCTO SELECCIONADO
			}
			textValor.setText(formatoImporte.format(PRODUCTO_ACTUAL
					.getPR_precio()));
			Calcula_totales();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// LIMPIADOR DE CAMPOS
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * SE LIMPIAN LOS DATOS DEL FORMULARIO DE ALTA DE PRODUCTO PARA PODER
	 * INGRESAR UNO NUEVO
	 */
	private void Limpiar_Formulario_pedido() {
		PRODUCTO_ACTUAL = new Producto();
		comboBoxProducto.setSelectedIndex(0);
		comboBoxVariedad.removeAllItems();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		textValor.setText("");
		textValorTotal.setText("");
		textObservaciones.setText("");
	}

	/**	 */
	private void Limpiar_Todo() {
		Limpiar_Formulario_pedido();
		PEDIDO_ACTUAL = new Pedido();
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(
				new Model_Pedido_Completo());
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// AGREGAR
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Toma los datos del panel "panelAltaPedido" y los inserta en la tabla de
	 * pedido general
	 */
	private void Agregar_al_Pedido() {
		if (comboBoxVariedad.getItemCount() != 0 && comboBoxProducto.getItemCount() != 0) {
			// hacer que tome los datos del formulario de pedido y los agregue a
			// la tabla de pedidos LISTO
			Integer Cantidad = Integer.parseInt(spinnerCantidad.getValue().toString());
			String Tipo_producto = comboBoxProducto.getSelectedItem().toString();
			String Variedad = comboBoxVariedad.getSelectedItem().toString();

			if (!Tipo_producto.isEmpty() && !Variedad.isEmpty() && Cantidad > 0) {
				Double ValorU = PRODUCTO_ACTUAL.getPR_precio();
				Double ValorT = PRODUCTO_ACTUAL.getPR_precio() * Integer.parseInt(spinnerCantidad.getValue().toString());
				String Observacion = textObservaciones.getText();

				/**
				 * Esto va a un objeto pedido, el cual se usara para guardar en
				 * la base de datos
				 **/
				for (int i = 0; i < Cantidad; i++)
					PEDIDO_ACTUAL.agregar_un_producto(PRODUCTO_ACTUAL);

				/** Esto va para la parte visual **/ //TODO
				DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();
				modelo.addRow(new Object[] { modelo.getRowCount() + 1,Cantidad, Tipo_producto, Variedad,formatoImporte.format(ValorU),formatoImporte.format(ValorT), Observacion }); 
				Tabla_Pedido_Completo.setModel(modelo); // Lo seteo en la tabla para que se vea

				/** Despues que se resetee el formulario de ingreso de pedido **/
				Limpiar_Formulario_pedido();
			}
		}
	}

	

	private void Agregar_a_lista_pedidos(Pedido PEDIDO) {
		/** Esto va para la parte visual **/
		DefaultTableModel modelo = (DefaultTableModel) Tabla_Lista_pedidos.getModel();
		modelo.addRow(new Object[] { 123, "Cliente", formato_ddMMyyyy.format(PEDIDO_ACTUAL.getFecha_Hora_Pedido()),"16/12/2015", (boolean) PEDIDO_ACTUAL.getEs_Delivery(), "PENDIENTE", formatoImporte.format(PEDIDO_ACTUAL.getTotal()) });
		Tabla_Lista_pedidos.setModel(modelo); // Lo seteo en la tabla para que se vea
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// QUITAR/ ELIMINAR/ CANCELAR
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * SI HAY UN ELEMENTO SELECCIONADO EN LA LISTA DE PRODUCTOS, DE UN PEDIDO,
	 * SE ELIMINARA ESE ELEMENTO DE LA LISTA
	 */
	private void Quitar_al_Pedido() {
//		 LO QUITA DE LA LISTA DE PEDIDO
		 if(!PEDIDO_ACTUAL.getLista_Productos().isEmpty()){
			 Integer cantidad = (Integer) Tabla_Pedido_Completo.getValueAt((Integer)Tabla_Pedido_Completo.getSelectedRow(),1);
			 String Variedad = (String) Tabla_Pedido_Completo.getValueAt((Integer)Tabla_Pedido_Completo.getSelectedRow(),3);
			 Integer removidos = 0;
//			 System.out.println("Antes "+PEDIDO_ACTUAL.getLista_Productos().size());
			 for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
				 if(PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_nombre().equals(Variedad) && removidos<cantidad)
//				 	System.out.println(PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_nombre());
					PEDIDO_ACTUAL.getLista_Productos().remove(i);
				 	removidos++;
				 } 
			 }
//		 	 System.out.println(PEDIDO_ACTUAL.getLista_Productos().size());
			// LO QUITA DE LA LISTA VISUAL
			if (Tabla_Pedido_Completo.getSelectedRow() != -1) { // -1 es cuando no se selecciono nada en la tabla, si es distinto, entonces es xq selecciono algo y se puede quitar
				int indice_Seleccionado = Tabla_Pedido_Completo.getSelectedRow(); 	// indice	 la tabla, (No funciona si se ordenan los datos
																					// desde la tabla, ojo)
				DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();
				modelo.removeRow(indice_Seleccionado);
				Calcula_totales();
			}
	}

	

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * ACTIVA/DESACTIVA LAS OPCIONES DEL DELIVERY SI ES QUE SE SELECCIONA LA
	 * OPCION "DELIVERY" EN LA INTERFAZ
	 */
	private void Servicio_Delivery() {
		textCliente.setEnabled(chckbxDelivery.isSelected());
		textDomicilio.setEnabled(chckbxDelivery.isSelected());
		textTelefono.setEnabled(chckbxDelivery.isSelected());
		textDetalle.setEnabled(chckbxDelivery.isSelected());
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//				ABM PEDIDO
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Abrir_Interfaz_ABM_Repartidor() {
		ADM_Repartidor frame = new ADM_Repartidor(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//				ABM PEDIDO
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * ALTA DE PEDIDO
	 */
	private void Guardar_pedido() {
		if (!PEDIDO_ACTUAL.getLista_Productos().isEmpty()) {
			PEDIDO_ACTUAL.setFecha_Hora_Pedido(Calendar.getInstance().getTime()); // inserta fecha y hora actual
			if (chckbxDelivery.isSelected()) {
				// agregar datos del pedido
				PEDIDO_ACTUAL.setEs_Delivery(chckbxDelivery.isSelected());
			}
			sv_pedidos.guardar_nuevo_pedido(PEDIDO_ACTUAL);
			// TODO- actualizar Tabla_Lista_pedidos
			Agregar_a_lista_pedidos(PEDIDO_ACTUAL);
			Limpiar_Todo();
		}
	}
	/**
	 * MODIFICACION DE PEDIDO
	 */
	private void Modificar_Pedido_Seleccionado() {
		Interfaz_ABM_Pedido frame = new Interfaz_ABM_Pedido(Principal_neg_int);
		
		frame.setModal(true);
		frame.setVisible(true);
	}
	/**
	 * VISUALIZACION DE PEDIDO
	 */
	private void Ver_Pedido_Seleccionado() {
		Interfaz_ABM_Pedido frame = new Interfaz_ABM_Pedido(Principal_neg_int);
		frame.setPedido_a_visualizar((Integer)Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0));
		frame.setModal(true);
		frame.setVisible(true);
	}
	/**
	 * BAJA DE PEDIDO (LO CANCELA, NO LO ELIMINA)
	 */
	private void Cancelar_Pedido_seleccionado() {
		if (Tabla_Lista_pedidos != null	&& Tabla_Lista_pedidos.getSelectedRow() != -1) {
			Integer Numero_pedido = (Integer) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0);
			Pedido P_cancelar = new Pedido();
			P_cancelar.setNumero_Pedido(Numero_pedido);
			// LO ELIMINA DE LA BASE DE DATOS
			//			sv_pedidos.eliminar_pedido(P_cancelar);
			
			// LO QUITA DE LA LISTA VISUAL
			if (Tabla_Lista_pedidos.getSelectedRow() != -1) { 
				int indice_Seleccionado = Tabla_Lista_pedidos.getSelectedRow(); 
				DefaultTableModel modelo = (DefaultTableModel) Tabla_Lista_pedidos.getModel();
				modelo.removeRow(indice_Seleccionado);
			}

		}
	}
	
	
	private void Cargar_datos_Cliente(Cliente c) {
		PEDIDO_ACTUAL.setCliente(c);
		textDomicilio.setText(c.getDomicilio());
		textTelefono.setText(c.getTelefono_Fijo());
		textDetalle.setText(c.getDetalle());
	}

	
	
	private void AutocompletarCliente() {
		AutoCompleter_Cliente.removeAllItems();
		AutoCompleter_Cliente.setCaseSensitive(false);
		AutoCompleter_Cliente.setMode(0);
		AutoCompleter_Cliente.addItems(Principal_neg_int.getSvClientes().getLISTA_CLIENTES());
	}
	
}// ---> FIN CLASE
