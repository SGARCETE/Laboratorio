package Interfaz;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;
import com.toedter.calendar.JDateChooser;

import Interfaz.JDialogs.ADM_Categorias;
import Interfaz.JDialogs.ADM_Cliente;
import Interfaz.JDialogs.ADM_Materia_Prima;
import Interfaz.JDialogs.ADM_Repartidor;
import Interfaz.JDialogs.Interfaz_ABM_Pedido;
import Interfaz.JDialogs.Interfaz_ABM_Producto;
import Interfaz.JDialogs.Interfaz_Cocina_Pantalla;
import Interfaz.JDialogs.Interfaz_Combos;
import Interfaz.JDialogs.Interfaz_Contabilidad;
import Interfaz.JDialogs.Interfaz_Proveedores;
import Interfaz.JDialogs.Interfaz_Solicitud_Compra;
import Interfaz.JDialogs.Interfaz_Venta;
import Interfaz.Swing_Extends.JTable_Listado_Pedidos;
import Interfaz.Swing_Extends.JTable_Pedido_Completo;
import Interfaz.Swing_Extends.Model_Listado_Pedidos;
import Interfaz.Swing_Extends.Model_Pedido_Completo;
import MetAux.MetAux;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Entrega;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Modelo.Repartidor;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Clientes;
import Negocio.Servicios.Servicio_Pedidos;
import Negocio.Servicios.Servicio_Productos;
import Negocio.Servicios.Servicio_Repartidores;
import Negocio.Servicios.Servicio_entrega;
import Persistencia.Conector.Manejador_Base_Datos;
import Reportes.ReporteItinerario;
import Reportes.ReporteTicket_comboDespl;

public class Interfaz_Principal {

	public JFrame frmWildsoft;
	
	private JTabbedPane tabbedPane;
	
	private JScrollPane scrollPane_Lista_Pedidos;
	private JScrollPane scrollPane_Pedido_Completo;

	private JTable_Pedido_Completo Tabla_Pedido_Completo;
	private JTable_Listado_Pedidos Tabla_Lista_pedidos;
	private JTable tabla_Itinerario_Pedidos;
	private JTable tabla_Itinerario_con_pedidos;

	private JComboBox<String> comboBoxProducto;
	private JComboBox<String> comboBoxVariedad;
	private JComboBox<String> comboRepartidores;
	
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
	
	private TextAutoCompleter AutoCompleter_Cliente = new TextAutoCompleter(
			textCliente, new AutoCompleterCallback() {
				public void callback(Object selectedItem) {
					String Nombre_Cliente_seleccionado = ((String) selectedItem);
					Cliente C = sv_clientes.getCliente(Nombre_Cliente_seleccionado);
					Cargar_datos_Cliente(C);
				}
			});
	
	private ArrayList<Integer> pedidodItinerario = new ArrayList<Integer>();
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	
	private HashMap<String, Integer> listaRepartidores;

	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
	private SimpleDateFormat formato_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");

	private Servicio_Productos sv_productos;
	private Servicio_Clientes sv_clientes;
	private Servicio_Pedidos sv_pedidos;
	private Servicio_Repartidores sv_Repartidores;
	private Servicio_entrega sv_Entrega;

	private Pedido PEDIDO_ACTUAL = new Pedido();
	private Producto PRODUCTO_ACTUAL = new Producto();

	private Principal_Negocio_Interfaz Principal_neg_int;
	private JFrame frame_cocina;

	private Cliente CLIENTE_ACTUAL = null;
	private JDateChooser dateChooser_Fecha_mostrar;
	private JButton btn_fecha_anterior;
	private JButton btn_fecha_siguiente;
	private JToggleButton btnSoloPendientes;

	/**
	 * Create the application.
	 * 
	 * @param principal_Negocio_Interfaz
	 */
	public Interfaz_Principal(Principal_Negocio_Interfaz instancia_PNI) {
		Principal_neg_int = instancia_PNI;
		sv_productos = Principal_neg_int.getSvProductos();
		sv_clientes = Principal_neg_int.getSvClientes();
		sv_pedidos = Principal_neg_int.getSvPedidos();
		sv_Repartidores = Principal_neg_int.getSvRepartidores();
		sv_Entrega = Principal_neg_int.getSvEntrega();
		initialize();
		iniciarParametros();
	}

	@SuppressWarnings("serial")
	private void initialize() {
		frmWildsoft = new JFrame();
		frmWildsoft.setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz_Principal.class.getResource("/Recursos/Pizza-icon16.png")));
		frmWildsoft.setTitle("WildSoft");
		frmWildsoft.setBounds(100, 100, 1133, 720);
		frmWildsoft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frmWildsoft.setExtendedState(JFrame.MAXIMIZED_BOTH);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 14));
		GroupLayout groupLayout = new GroupLayout(frmWildsoft.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,GroupLayout.DEFAULT_SIZE, 946, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(tabbedPane,GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Panel de Nuevo Pedido
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panel_Nuevo_pedido = new JPanel();
		panel_Nuevo_pedido.setBackground(SystemColor.menu);
		tabbedPane.addTab("Nuevo pedido",new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/pizza-slice-icon32.png")),panel_Nuevo_pedido, null);

		JPanel panelProductos = new JPanel();
		panelProductos.setBackground(Color.WHITE);
		panelProductos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.CENTER,TitledBorder.TOP, null, null));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblIngreseLosProductos = new JLabel("Ingrese los productos que componen el pedido");
		lblIngreseLosProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosProductos.setFont(new Font("Tahoma", Font.BOLD, 16));

		JPanel panelAltaPedido = new JPanel();
		panelAltaPedido.setBackground(Color.WHITE);
		panelAltaPedido.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelAltaPedido.setBorder(new TitledBorder(null, "",TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelAltaPedido.setLayout(null);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setBounds(10, 12, 109, 28);
		panelAltaPedido.add(lblProducto);
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));

		comboBoxProducto = new JComboBox<String>();
		comboBoxProducto.setBackground(new Color(240, 255, 255));
		comboBoxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_De_Tipo_Producto();
			}
		});
		comboBoxProducto.setBounds(117, 12, 201, 28);
		panelAltaPedido.add(comboBoxProducto);

		comboBoxVariedad = new JComboBox<String>();
		comboBoxVariedad.setBackground(new Color(240, 255, 255));
		comboBoxVariedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccion_De_Variedad();
			}
		});
		comboBoxVariedad.setBounds(117, 49, 201, 28);
		panelAltaPedido.add(comboBoxVariedad);
		
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblVariedad = new JLabel("Variedad");
		lblVariedad.setBounds(10, 49, 109, 28);
		panelAltaPedido.add(lblVariedad);
		lblVariedad.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setBounds(10, 85, 109, 28);
		panelAltaPedido.add(lblCantidad);
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));

		spinnerCantidad = new JSpinner();
		spinnerCantidad.setFont(new Font("Tahoma", Font.PLAIN, 18));
		spinnerCantidad.setBackground(new Color(240, 255, 255));
		spinnerCantidad.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				Calcula_totales();
			}
		});
		spinnerCantidad.setBounds(117, 85, 59, 28);
		panelAltaPedido.add(spinnerCantidad);
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblValor = new JLabel("Valor c/u");
		lblValor.setBounds(10, 122, 109, 28);
		panelAltaPedido.add(lblValor);
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textValor = new JTextField();
		textValor.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textValor.setBackground(new Color(240, 255, 255));
		textValor.setHorizontalAlignment(SwingConstants.RIGHT);
		textValor.setText("$0,00");
		textValor.setBounds(117, 122, 199, 28);
		panelAltaPedido.add(textValor);
		textValor.setEditable(false);
		textValor.setColumns(10);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblValorTotal = new JLabel("Valor total");
		lblValorTotal.setBounds(10, 159, 109, 28);
		panelAltaPedido.add(lblValorTotal);
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblObservacion = new JLabel("Observaciones");
		lblObservacion.setBounds(10, 195, 109, 28);
		panelAltaPedido.add(lblObservacion);
		lblObservacion.setFont(new Font("Tahoma", Font.PLAIN, 16));

		textObservaciones = new JTextField();
		textObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textObservaciones.setBackground(new Color(240, 255, 255));
		textObservaciones.setBounds(117, 195, 199, 28);
		panelAltaPedido.add(textObservaciones);
		textObservaciones.setColumns(10);

		textValorTotal = new JTextField();
		textValorTotal.setFont(new Font("SansSerif", Font.PLAIN, 18));
		textValorTotal.setBackground(new Color(240, 255, 255));
		textValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textValorTotal.setText("$0,00");
		textValorTotal.setToolTipText("");
		textValorTotal.setBounds(117, 159, 199, 28);
		panelAltaPedido.add(textValorTotal);
		textValorTotal.setEditable(false);
		textValorTotal.setColumns(10);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(117, 230, 100, 30);
		panelAltaPedido.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/add-1-icon24.png")));

		JPanel panel_Resumen_Pedido = new JPanel();
		panel_Resumen_Pedido.setBackground(Color.WHITE);
		panel_Resumen_Pedido.setBorder(new TitledBorder(null, "",TitledBorder.CENTER, TitledBorder.TOP, null, null));

		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quitar_al_Pedido();
			}
		});
		btnQuitar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));

		scrollPane_Pedido_Completo = new JScrollPane();

		JLabel lblProductosQueComponen = new JLabel("Productos que componen el pedido");
		lblProductosQueComponen.setHorizontalAlignment(SwingConstants.CENTER);
		lblProductosQueComponen.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_panel_Resumen_Pedido = new GroupLayout(panel_Resumen_Pedido);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Resumen_Pedido.setHorizontalGroup(gl_panel_Resumen_Pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Resumen_Pedido.createSequentialGroup().addGap(8)
						.addGroup(gl_panel_Resumen_Pedido.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_Pedido_Completo, GroupLayout.DEFAULT_SIZE, 727,
										Short.MAX_VALUE)
						.addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addGap(8))
				.addGroup(Alignment.TRAILING,
						gl_panel_Resumen_Pedido.createSequentialGroup().addContainerGap()
								.addComponent(lblProductosQueComponen, GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
								.addContainerGap()));
		gl_panel_Resumen_Pedido.setVerticalGroup(gl_panel_Resumen_Pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Resumen_Pedido.createSequentialGroup().addGap(14)
						.addComponent(lblProductosQueComponen, GroupLayout.PREFERRED_SIZE, 25,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(scrollPane_Pedido_Completo, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
						.addGap(14).addComponent(btnQuitar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGap(1)));

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		panel_Resumen_Pedido.setLayout(gl_panel_Resumen_Pedido);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_al_Pedido();
			}
		});

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Panel de Delivery
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panelDelibery = new JPanel();
		panelDelibery.setBackground(Color.WHITE);
		panelDelibery.setBorder(new TitledBorder(null, "Servicio delivery",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDelibery.setLayout(null);

		chckbxDelivery = new JCheckBox("Con delivery");
		chckbxDelivery.setOpaque(false);
		chckbxDelivery.setBackground(SystemColor.menu);
		chckbxDelivery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDelivery.setBounds(16, 14, 110, 25);
		panelDelibery.add(chckbxDelivery);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDomicilio.setBounds(299, 46, 70, 25);
		panelDelibery.add(lblDomicilio);

		textDomicilio = new JTextField();
		textDomicilio.setEditable(false);
		textDomicilio.setBackground(new Color(240, 248, 255));
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(381, 46, 199, 30);
		panelDelibery.add(textDomicilio);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(16, 82, 70, 25);
		panelDelibery.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setEditable(false);
		textTelefono.setBackground(new Color(240, 248, 255));
		textTelefono.setColumns(10);
		textTelefono.setBounds(90, 82, 199, 30);
		panelDelibery.add(textTelefono);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetalle.setBounds(299, 82, 70, 25);
		panelDelibery.add(lblDetalle);

		textDetalle = new JTextField();
		textDetalle.setEditable(false);
		textDetalle.setBackground(new Color(240, 248, 255));
		textDetalle.setColumns(10);
		textDetalle.setBounds(381, 82, 199, 30);
		panelDelibery.add(textDetalle);

		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCliente.setBounds(16, 46, 70, 25);
		panelDelibery.add(lblCliente);
		textCliente.setBackground(new Color(240, 248, 255));
		textCliente.setColumns(10);
		textCliente.setBounds(90, 46, 199, 30);
		panelDelibery.add(textCliente);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Importes",TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
		btnNewButton.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Save-icon48.png")));
		btnNewButton.setBounds(174, 30, 129, 67);
		panel_3.add(btnNewButton);
		GroupLayout gl_panel_Nuevo_pedido = new GroupLayout(panel_Nuevo_pedido);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Nuevo_pedido.setHorizontalGroup(gl_panel_Nuevo_pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Nuevo_pedido.createSequentialGroup()
						.addGroup(gl_panel_Nuevo_pedido.createParallelGroup(Alignment.LEADING)
								.addComponent(panelProductos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
						.addGroup(gl_panel_Nuevo_pedido.createSequentialGroup().addContainerGap()
								.addComponent(panelDelibery, GroupLayout.PREFERRED_SIZE, 609,
										GroupLayout.PREFERRED_SIZE)
								.addGap(1).addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)))
						.addGap(1)));

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Nuevo_pedido.setVerticalGroup(gl_panel_Nuevo_pedido.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Nuevo_pedido.createSequentialGroup().addGap(6)
						.addComponent(panelProductos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGap(2)
						.addGroup(gl_panel_Nuevo_pedido.createParallelGroup(Alignment.LEADING, false)
								.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
										.addComponent(panelDelibery, GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))));
		
		JButton btnAgregarCLiente = new JButton("Agregar cliente nuevo");
		btnAgregarCLiente.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Check-3-icon16.png")));
		btnAgregarCLiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_nuevo_cliente();
			}
		});
		btnAgregarCLiente.setBounds(381, 15, 199, 25);
		panelDelibery.add(btnAgregarCLiente);
		
		JButton btnNewButton_1 = new JButton("Limpiar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CLIENTE_ACTUAL = null;
				textCliente.setText("");
				textDetalle.setText("");
				textTelefono.setText("");
				textDomicilio.setText("");
				
			}
		});
		btnNewButton_1.setToolTipText("Quita el cliente del pedido");
		btnNewButton_1.setBounds(199, 13, 90, 28);
		panelDelibery.add(btnNewButton_1);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		GroupLayout gl_panelProductos = new GroupLayout(panelProductos);
		gl_panelProductos.setHorizontalGroup(gl_panelProductos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProductos.createSequentialGroup().addGap(12)
						.addGroup(gl_panelProductos.createParallelGroup(Alignment.LEADING)
								.addComponent(lblIngreseLosProductos, GroupLayout.PREFERRED_SIZE, 404,
										GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panelProductos.createSequentialGroup()
								.addComponent(panelAltaPedido, GroupLayout.PREFERRED_SIZE, 331,
										GroupLayout.PREFERRED_SIZE)
								.addGap(5)
								.addComponent(panel_Resumen_Pedido, GroupLayout.DEFAULT_SIZE, 744, Short.MAX_VALUE)))
						.addGap(4)));
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panelProductos.setVerticalGroup(gl_panelProductos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProductos.createSequentialGroup().addGap(4)
						.addComponent(lblIngreseLosProductos, GroupLayout.PREFERRED_SIZE, 25,
								GroupLayout.PREFERRED_SIZE)
						.addGap(4)
						.addGroup(gl_panelProductos.createParallelGroup(Alignment.LEADING)
								.addComponent(panelAltaPedido, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
								.addComponent(panel_Resumen_Pedido, GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE))
						.addGap(4)));
		panelProductos.setLayout(gl_panelProductos);
		panel_Nuevo_pedido.setLayout(gl_panel_Nuevo_pedido);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Panel de listado de pedidos
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panel_Lista_de_pedidos = new JPanel();
		panel_Lista_de_pedidos.setBackground(Color.WHITE);
		tabbedPane.addTab("Listado de pedidos",new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Product-sale-report-icon32.png")),panel_Lista_de_pedidos, null);

		scrollPane_Lista_Pedidos = new JScrollPane();

		JLabel lblListadoDePedidos = new JLabel("Pedidos registrados");
		lblListadoDePedidos.setOpaque(true);
		lblListadoDePedidos.setBackground(SystemColor.menu);
		lblListadoDePedidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListadoDePedidos.setFont(new Font("SansSerif", Font.BOLD, 24));

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING,TitledBorder.TOP, null, null));
		panel.setBackground(Color.WHITE);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Modificar_Pedido_Seleccionado();
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		btnModificar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/edit-icon64.png")));
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
		panel.add(btnModificar);
		btnModificar.setBackground(Color.WHITE);

		JButton btnPreparado = new JButton("Preparado");
		btnPreparado.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/preparado64.png")));
		btnPreparado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Setear_como_Preparado();
			}
		});
		btnPreparado.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPreparado.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPreparado.setBackground(Color.WHITE);
		panel.add(btnPreparado);

		// ---------------------------
		// CAMBIAR ESTADOS
		// ---------------------------

		JButton btnCobrado = new JButton("Cobrado");
		btnCobrado.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Coin-us-dollar-icon64.png")));
		btnCobrado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Setear_como_cobrado();
			}
		});
		btnCobrado.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCobrado.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCobrado.setBackground(Color.WHITE);
		panel.add(btnCobrado);

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

		JButton btnComandaticket = new JButton("Ticket/Comanda");
		btnComandaticket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generar_Comanda();
			}
		});
		btnComandaticket.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Product-sale-report-icon64.png")));
		btnComandaticket.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnComandaticket.setHorizontalTextPosition(SwingConstants.CENTER);
		btnComandaticket.setBackground(Color.WHITE);
		panel.add(btnComandaticket);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(60, 179, 113));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setLayout(null);

		btn_fecha_anterior = new JButton("");
		btn_fecha_anterior.setBounds(257, 11, 50, 35);
		panel_2.add(btn_fecha_anterior);
		btn_fecha_anterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fecha_Anterior();
			}
		});
		btn_fecha_anterior.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Actions-go-previous-icon32.png")));

		dateChooser_Fecha_mostrar = new JDateChooser();
		dateChooser_Fecha_mostrar.setFont(new Font("Tahoma", Font.PLAIN, 21));
		dateChooser_Fecha_mostrar.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 16));
		dateChooser_Fecha_mostrar.setBounds(307, 11, 164, 35);
		panel_2.add(dateChooser_Fecha_mostrar);
		dateChooser_Fecha_mostrar.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		btn_fecha_siguiente = new JButton("");
		btn_fecha_siguiente.setBounds(471, 11, 50, 35);
		panel_2.add(btn_fecha_siguiente);
		btn_fecha_siguiente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Fecha_Siguiente();
			}
		});
		btn_fecha_siguiente.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Actions-go-next-icon32.png")));

		btnSoloPendientes = new JToggleButton("Solo pendientes");
		btnSoloPendientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Actualizar_Lista_pedidos();
			}
		});
		btnSoloPendientes.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Check-3-icon16.png")));
		btnSoloPendientes.setBounds(531, 11, 142, 35);
		panel_2.add(btnSoloPendientes);

		JLabel lbl_contador = new JLabel("");
		lbl_contador.setBackground(SystemColor.menu);
		lbl_contador.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_contador.setFont(new Font("SansSerif", Font.PLAIN, 24));
		GroupLayout gl_panel_Lista_de_pedidos = new GroupLayout(panel_Lista_de_pedidos);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Lista_de_pedidos.setHorizontalGroup(gl_panel_Lista_de_pedidos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Lista_de_pedidos.createSequentialGroup().addGap(6)
						.addGroup(gl_panel_Lista_de_pedidos.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Lista_de_pedidos.createSequentialGroup()
										.addComponent(lbl_contador, GroupLayout.PREFERRED_SIZE, 119,
												GroupLayout.PREFERRED_SIZE)
										.addGap(16).addComponent(lblListadoDePedidos, GroupLayout.DEFAULT_SIZE, 961,
												Short.MAX_VALUE))
						.addGroup(gl_panel_Lista_de_pedidos.createSequentialGroup()
								.addComponent(panel, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
								.addGap(8)
								.addGroup(gl_panel_Lista_de_pedidos.createParallelGroup(Alignment.LEADING)
										.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 961, Short.MAX_VALUE)
										.addComponent(scrollPane_Lista_Pedidos, GroupLayout.DEFAULT_SIZE, 961,
												Short.MAX_VALUE))))
						.addGap(15)));

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Lista_de_pedidos
				.setVerticalGroup(gl_panel_Lista_de_pedidos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_Lista_de_pedidos.createSequentialGroup().addGap(6)
								.addGroup(gl_panel_Lista_de_pedidos.createParallelGroup(Alignment.LEADING)
										.addComponent(lbl_contador, GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblListadoDePedidos, GroupLayout.PREFERRED_SIZE, 45,
												GroupLayout.PREFERRED_SIZE))
						.addGap(11)
						.addGroup(gl_panel_Lista_de_pedidos.createParallelGroup(Alignment.LEADING)
								.addComponent(panel, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
								.addGroup(gl_panel_Lista_de_pedidos.createSequentialGroup()
										.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 54,
												GroupLayout.PREFERRED_SIZE)
										.addGap(11).addComponent(scrollPane_Lista_Pedidos, GroupLayout.DEFAULT_SIZE,
												469, Short.MAX_VALUE)))
								.addGap(16)));

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		panel_Lista_de_pedidos.setLayout(gl_panel_Lista_de_pedidos);
		dateChooser_Fecha_mostrar.addPropertyChangeListener(new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent arg0) {
						Actualizar_Lista_pedidos();
					}
				});

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// PANEL ITINERARIO DE ENTREGA
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JPanel panel_Itinerario = new JPanel();
		panel_Itinerario.setBackground(Color.WHITE);
		tabbedPane.addTab("Itinerario de Entrega",new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Delivery-32.png")),panel_Itinerario, null);

		JScrollPane scrollPane = new JScrollPane();

		tabla_Itinerario_Pedidos = new JTable();
		tabla_Itinerario_Pedidos.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "", "Pedido N\u00B0","Cliente", "Direccion", "Telefono", "Precio" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class,String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		// Ocultar columna
		tabla_Itinerario_Pedidos.getColumnModel().getColumn(0).setMaxWidth(0);
		tabla_Itinerario_Pedidos.getColumnModel().getColumn(0).setMinWidth(0);
		tabla_Itinerario_Pedidos.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		tabla_Itinerario_Pedidos.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		tabla_Itinerario_Pedidos.setRowHeight(25);
		scrollPane.setViewportView(tabla_Itinerario_Pedidos);

		JScrollPane scrollPane_1 = new JScrollPane();

		tabla_Itinerario_con_pedidos = new JTable();
		tabla_Itinerario_con_pedidos.setRowHeight(25);
		tabla_Itinerario_con_pedidos.setModel(obtener_Model_itinerario());
		scrollPane_1.setViewportView(tabla_Itinerario_con_pedidos);

		JLabel lblListaDePedidos = new JLabel("Lista de Pedidos");
		lblListaDePedidos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblListaDePedidos.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblItinerarioDeEntrega = new JLabel("Itinerario de Entrega");
		lblItinerarioDeEntrega.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblItinerarioDeEntrega.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setLayout(null);

		JButton btnAgregar_1 = new JButton("Agregar");
		btnAgregar_1.setHorizontalTextPosition(SwingConstants.LEFT);
		btnAgregar_1.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Arrow-Next-4-icon32.png")));
		btnAgregar_1.setBounds(36, 11, 109, 35);
		panel_1.add(btnAgregar_1);

		JButton button_1 = new JButton("Quitar");
		button_1.setHorizontalTextPosition(SwingConstants.RIGHT);
		button_1.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Arrow-Back-4-icon32.png")));
		button_1.setBounds(36, 57, 109, 35);
		panel_1.add(button_1);

		JButton btnVaciar = new JButton("Vaciar");
		btnVaciar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/trash-icon32.png")));
		btnVaciar.setBounds(36, 103, 109, 35);
		panel_1.add(btnVaciar);

		JLabel lblRepartidor = new JLabel("Repartidor:");
		lblRepartidor.setBounds(10, 168, 163, 14);
		panel_1.add(lblRepartidor);
		lblRepartidor.setHorizontalAlignment(SwingConstants.CENTER);

		comboRepartidores = new JComboBox<String>();
		comboRepartidores.setBounds(10, 193, 163, 27);
		panel_1.add(comboRepartidores);

		JButton btnGenerarnItinerario = new JButton("Generar\n Itinerario");
		btnGenerarnItinerario.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/Product-sale-report-icon32.png")));
		btnGenerarnItinerario.setBounds(10, 231, 163, 45);
		panel_1.add(btnGenerarnItinerario);
		GroupLayout gl_panel_Itinerario = new GroupLayout(panel_Itinerario);

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Itinerario.setHorizontalGroup(gl_panel_Itinerario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Itinerario.createSequentialGroup().addGap(10)
						.addGroup(gl_panel_Itinerario.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblListaDePedidos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_Itinerario.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblItinerarioDeEntrega, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE))
						.addGap(10)));

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		gl_panel_Itinerario.setVerticalGroup(gl_panel_Itinerario.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Itinerario.createSequentialGroup().addGap(18)
						.addGroup(gl_panel_Itinerario.createParallelGroup(Alignment.LEADING)
								.addComponent(lblItinerarioDeEntrega, GroupLayout.PREFERRED_SIZE, 27,
										GroupLayout.PREFERRED_SIZE)
						.addComponent(lblListaDePedidos, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(
								ComponentPlacement.RELATED)
						.addGroup(gl_panel_Itinerario.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_Itinerario.createSequentialGroup()
										.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 627, Short.MAX_VALUE)
										.addContainerGap())
								.addGroup(Alignment.TRAILING,
										gl_panel_Itinerario.createSequentialGroup()
												.addGroup(gl_panel_Itinerario.createParallelGroup(Alignment.TRAILING)
														.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 627,
																Short.MAX_VALUE)
														.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 627,
																Short.MAX_VALUE))
												.addGap(11)))));

		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		panel_Itinerario.setLayout(gl_panel_Itinerario);
		btnGenerarnItinerario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generar_itinerario_entrega();
			}
		});
		
		btnVaciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vaciar_lista_itinerario();
			}
		});
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quitar_del_itinerario();
			}
		});
		
		btnAgregar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_al_itinerario();
			}
		});
		
		frmWildsoft.getContentPane().setLayout(groupLayout);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		frmWildsoft.setJMenuBar(menuBar);

		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);

		JMenuItem mntmRealizarBackup = new JMenuItem("Realizar Backup");
		mntmRealizarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String directorio = Consultar_directorio();
				if(!directorio.equals("")){
					Manejador_Base_Datos.backup(directorio);
				}
			}
		});
		mnArchivo.add(mntmRealizarBackup);

		JMenuItem mntmRestaurarBackup = new JMenuItem("Restaurar Backup");
		mntmRestaurarBackup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File archivo = importarDatos();
				if(!archivo.equals(null)){
					Manejador_Base_Datos.Restore(archivo);
				}
				ACTUALIZAR_TODO();
			}
		});
		mnArchivo.add(mntmRestaurarBackup);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frmWildsoft.dispose();
			}
		});
		mnArchivo.add(mntmSalir);

		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		// Repartidores

		JMenu mnRepartidor = new JMenu("Repartidores");
		menuBar.add(mnRepartidor);

		JMenuItem mntmADMRepartidores = new JMenuItem("Administrar Repartidores");
		mntmADMRepartidores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Abrir_Interfaz_ABM_Repartidor();
			}
		});
		mnRepartidor.add(mntmADMRepartidores);

		// Repartidores
		// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mntmAdmClientes = new JMenuItem("Administrar Clientes");
		mnClientes.add(mntmAdmClientes);
		mntmAdmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Abrir_Interfaz_ABM_Cliente();
			}
		});

		JMenu mnCombos = new JMenu("Combos");
		menuBar.add(mnCombos);

		JMenuItem mntmAbmCombos = new JMenuItem("Administrar Combos");
		mntmAbmCombos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Abrir_Interfaz_Combos();
			}
		});
		mnCombos.add(mntmAbmCombos);

		JMenu mnProductos = new JMenu("Productos");
		menuBar.add(mnProductos);

		JMenuItem mntmAbmProductos = new JMenuItem("Administrar Productos");
		mntmAbmProductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Abrir_Interfaz_ABM_Producto();
			}
		});
		mnProductos.add(mntmAbmProductos);

		JMenu mnProveedores = new JMenu("Proveedores");
		menuBar.add(mnProveedores);

		JMenu mnMateriasPrimas = new JMenu("Materias primas");
		mnProveedores.add(mnMateriasPrimas);

		JMenuItem mnCompraMP = new JMenuItem("Administrar Solicitud de Compra");
		mnCompraMP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInterfazBoton("sc");
			}
		});
		mnMateriasPrimas.add(mnCompraMP);

		JMenuItem mntmADMMateriaPrima = new JMenuItem("Administrar Materia Prima");
		mntmADMMateriaPrima.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Abrir_Interfaz_Materia();
			}
		});
		mnMateriasPrimas.add(mntmADMMateriaPrima);

		JMenuItem mnABMProveedores = new JMenuItem("Administrar Proveedores");
		mnABMProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abrirInterfazBoton("pr");
			}
		});
		mnProveedores.add(mnABMProveedores);

		JMenu mnReporteContabilidad = new JMenu("Reportes");
		menuBar.add(mnReporteContabilidad);

		JMenuItem mntmVerContabilidad = new JMenuItem("Mini-Contabilidad");
		mntmVerContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Interfaz_Contabilidad frame = new Interfaz_Contabilidad();
				frame.setVisible(true);
			}
		});
		mnReporteContabilidad.add(mntmVerContabilidad);

		JMenuItem mntmReporteVentas = new JMenuItem("Ventas");
		mntmReporteVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Interfaz_Venta frame = new Interfaz_Venta(Principal_neg_int);
				frame.setVisible(true);
			}
		});
		mnReporteContabilidad.add(mntmReporteVentas);

		JMenu mnCategoria = new JMenu("Categor\u00EDa");
		menuBar.add(mnCategoria);

		JMenuItem mntmADMCategoria = new JMenuItem("Administrar Categoria");
		mntmADMCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AbrirInterfazCategoria();
			}
		});
		mnCategoria.add(mntmADMCategoria);

	}// --> FIN INTERFAZ

	/**
	 * Inicia todos los parametros necesarios, como las tablas y los ComboBox
	 */
	private void iniciarParametros() {

		// Creacion de la tabla vacia de lista de pedidos
		Tabla_Lista_pedidos = new JTable_Listado_Pedidos(new Model_Listado_Pedidos());
		scrollPane_Lista_Pedidos.setViewportView(Tabla_Lista_pedidos);

		// Creacion de la tabla vacia con el contenido de un pedido
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);

		// Establece la fecha de HOY para mostrar los pedidos del dia
		dateChooser_Fecha_mostrar.setCalendar(new GregorianCalendar());

		// Llena el Autocomplete de clientes para poder cargarlos
		AutocompletarCliente();

		// LLENA EL COMBOBOX CON LOS TIPOS DE PRODUCTOS
		ArrayList<String> ListaProductos = sv_productos.getLista_Productos();
		comboBoxProducto.addItem("Seleccione el tipo de producto");
		for (int i = 0; i < ListaProductos.size(); i++) {
			comboBoxProducto.addItem(ListaProductos.get(i));
		}

		// LLENA EL COMBOBOX CON NOMBRE DE REPARTIDORES
		ArrayList<Repartidor> lista = sv_Repartidores.get_Repartidores();
		listaRepartidores = new HashMap<String, Integer>();
		comboRepartidores.addItem("Seleccione un repartidor");
		for (int i = 0; i < lista.size(); i++) {
			comboRepartidores.addItem(lista.get(i).getNombre());
			listaRepartidores.put(lista.get(i).getNombre(), lista.get(i).getID_Repartidor());
		}

		// INICIALIZA LA COCINA
		Generar_Monitor_cocina();

		// ACTUALIZA LISTA DE PEDIDOS, MONITOR COCINA, LISTA DE ITINERARIO DE
		// PEDIDOS
		ACTUALIZAR_TODO();
	}

	/**
	 * Llama a todos los metodos actualizar de cada pestaña
	 */
	private void ACTUALIZAR_TODO() {
		Actualizar_Lista_pedidos();
		ACTUALIZAR_MONITOR();
		Actualizar_itinerario_pedidos();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	/**
	 * Genera el monitor de la cocina
	 */
	private void Generar_Monitor_cocina() {
		/** MANEJA LAS PANTALLAS **/
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] gs = ge.getScreenDevices();
		Integer Pantalla_Numero = 0;
		for (int j = 0; j < gs.length; j++) {
			Pantalla_Numero = j;
		}
		GraphicsDevice gd = gs[Pantalla_Numero];

		frame_cocina = new JFrame(gd.getDefaultConfiguration());
		Integer X = frame_cocina.getX();
		Integer Y = frame_cocina.getY();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle R = new Rectangle(X, Y, screenSize.width, screenSize.height);

		frame_cocina = new Interfaz_Cocina_Pantalla(Principal_neg_int);
		frame_cocina.setBounds(R);
		frame_cocina.setVisible(true);
	}

	/**
	 * Calcula el total segun la cantidad de unidades del mismo producto y el total del pedido
	 */
	private void Calcula_totales() {
		if (PRODUCTO_ACTUAL != null && PEDIDO_ACTUAL.getLista_Productos() != null) {

			// CALCULA EL TOTAL POR LA CANTIDAD DE UNIDADES QUE LLEVA DEL MISMO
			// PRODUCTO
			Double Total_Mismo_Producto = PRODUCTO_ACTUAL.getPR_precio() * (Integer.parseInt(spinnerCantidad.getValue().toString()));
			textValorTotal.setText(formatoImporte.format(Total_Mismo_Producto));

			// CALCULA EL TOTAL DEL PEDIDO, recorre todos los productos del
			// pedido tomando su precio y lo acumula
			Double TOTAL_PEDIDO = 0.0;
			for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
				Double precio = PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_precio();
				int cantidad = PEDIDO_ACTUAL.getLista_Productos().get(i).getCantidad();
				TOTAL_PEDIDO += precio * cantidad;
			}
			PEDIDO_ACTUAL.setTotal(TOTAL_PEDIDO);
			textTotal_Pedido.setText(formatoImporte.format(TOTAL_PEDIDO));
		}
	}
	
	/**
	 * Se encarga de traer los productos del tipo seleccionado en el combo de Tipo de Producto
	 */
	private void Seleccion_De_Tipo_Producto() {
		if (!comboBoxProducto.getSelectedItem().toString().isEmpty()) {
			Lista_Variedades = sv_productos.getVariedad_del_Producto(comboBoxProducto.getSelectedItem().toString());
			comboBoxVariedad.removeAllItems();
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				comboBoxVariedad.addItem(Lista_Variedades.get(i).getPR_nombre());
			}
		}
	}
	
	/**
	 * Se encarga de traer el precio de la variedad de producto seleccionada
	 */
	private void Seleccion_De_Variedad() {
		if (comboBoxVariedad.getSelectedItem() != null && !comboBoxVariedad.getSelectedItem().toString().isEmpty()) {
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				if (Lista_Variedades.get(i).getPR_nombre().equals(comboBoxVariedad.getSelectedItem().toString()))
					PRODUCTO_ACTUAL = Lista_Variedades.get(i);
			}
			textValor.setText(formatoImporte.format(PRODUCTO_ACTUAL.getPR_precio()));
			Calcula_totales();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// LIMPIADOR DE CAMPOS
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	/**
	 * Resetea los campos del alta de pedido excepto la tabla
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

	/**
	 * Resetea la logica detras del pedido, el cliente seleccionado, la tabla y llama al metodo que limpia el formulario del pedido
	 */
	private void Limpiar_Todo() {
		Limpiar_Formulario_pedido();
		textCliente.setText("");
		textDetalle.setText("");
		textDomicilio.setText("");
		textTelefono.setText("");
		textTotal_Pedido.setText("");
		CLIENTE_ACTUAL = null;
		PEDIDO_ACTUAL = new Pedido();
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
		chckbxDelivery.setSelected(false);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// ABM Repartidor
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Abrir_Interfaz_ABM_Repartidor() {
		ADM_Repartidor frame = new ADM_Repartidor(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// ABM Client
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Abrir_Interfaz_ABM_Cliente() {
		ADM_Cliente frame = new ADM_Cliente(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
		AutocompletarCliente();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// ABM Client
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Abrir_Interfaz_ABM_Producto() {
		Interfaz_ABM_Producto frame = new Interfaz_ABM_Producto(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// ABM Combos
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Abrir_Interfaz_Combos() {
		Interfaz_Combos frame = new Interfaz_Combos(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// ABM Materia Prima
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Abrir_Interfaz_Materia() {
		ADM_Materia_Prima frame = new ADM_Materia_Prima(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	/**
	 * Metodo que se encarga de guardar el pedido en la base de datos
	 * @return Devuelve true si se guardo, caso contrario devuelve false
	 */
	private boolean Guardar_pedido() {
		if (!PEDIDO_ACTUAL.getLista_Productos().isEmpty()) {
			PEDIDO_ACTUAL.setFecha_Hora_Pedido(Calendar.getInstance().getTime());
			
			if(chckbxDelivery.isSelected()){
				if (CLIENTE_ACTUAL!= null && !CLIENTE_ACTUAL.getNombre().isEmpty() && !CLIENTE_ACTUAL.getDomicilio().isEmpty()) {
					PEDIDO_ACTUAL.setEs_Delivery(chckbxDelivery.isSelected());
					PEDIDO_ACTUAL.setCliente(CLIENTE_ACTUAL);
				} else {
					JOptionPane.showMessageDialog(null,"Para realizar un Delivery, debe seleccionar un cliente","Falta cliente",JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}
			if (CLIENTE_ACTUAL!= null && !CLIENTE_ACTUAL.getNombre().isEmpty() && !CLIENTE_ACTUAL.getDomicilio().isEmpty())
				PEDIDO_ACTUAL.setCliente(CLIENTE_ACTUAL);
			
			sv_pedidos.guardar_nuevo_pedido(PEDIDO_ACTUAL);
			ACTUALIZAR_TODO();
			Ticket_Comanda_nuevo_pedido();
			Limpiar_Todo();
		}
		return true;
	}
	
	/**
	 * Metodo que se encarga de abrir la interfaz que permite modificar el pedido seleccionado
	 */
	private void Modificar_Pedido_Seleccionado() {
		if (Tabla_Lista_pedidos.getSelectedRow() != -1) {
			Interfaz_ABM_Pedido frame = new Interfaz_ABM_Pedido(Principal_neg_int);
			Integer ID_PEDIDO_MODIFICAR = (Integer) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0);
			frame.setPedido_a_modificar(ID_PEDIDO_MODIFICAR);
			frame.setModal(true);
			frame.setVisible(true);
			ACTUALIZAR_TODO();
		}
	}
	
	/**
	 * Metodo que se encarga de dar de baja un pedido. La baja es logica
	 */
	private void Cancelar_Pedido_seleccionado() {
		if (Tabla_Lista_pedidos != null && Tabla_Lista_pedidos.getSelectedRow() != -1) {
			Integer Numero_pedido = (Integer) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0);
			Pedido P_cancelar = new Pedido();
			P_cancelar.setNumero_Pedido(Numero_pedido);
			String estado = (String) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 5);
			if (!estado.equals("Cobrado")) {
				sv_pedidos.eliminar_pedido(P_cancelar);
				ACTUALIZAR_TODO();
			}
		}
	}
	
	/**
	 * Se encarga de actualizar el JTable de pedidos
	 */
	private void Actualizar_Lista_pedidos() {
		if (dateChooser_Fecha_mostrar.getCalendar() != null) {
			ArrayList<Pedido> Lista_Pedidos = sv_pedidos.get_Pedidos(dateChooser_Fecha_mostrar.getCalendar());
			Model_Listado_Pedidos model = new Model_Listado_Pedidos();
			ArrayList<Pedido> Lista_Pedidos_Aux = new ArrayList<Pedido>();
			if (btnSoloPendientes.isSelected()) {
				for (int i = 0; i < Lista_Pedidos.size(); i++) {
					if (Lista_Pedidos.get(i).getESTADO().equals("Pendiente")) {
						Lista_Pedidos_Aux.add(Lista_Pedidos.get(i));
					}
				}
				Lista_Pedidos = Lista_Pedidos_Aux;
			}
			// Llena la tabla para mostrar los pedidos
			for (int i = 0; i < Lista_Pedidos.size(); i++) {
				Pedido PE = Lista_Pedidos.get(i);
				String Nombre_Cliente = "";
				if (PE.getCliente() != null) {
					Nombre_Cliente = PE.getCliente().getNombre();
				}
				model.addRow(new Object[] { PE.getNumero_Pedido(),PE.getID_DIARIO(), Nombre_Cliente,formato_ddMMyyyy.format(PE.getFecha_Hora_Pedido()),PE.getEs_Delivery(), PE.getESTADO(),formatoImporte.format(PE.getTotal()) });
			}
			Tabla_Lista_pedidos = new JTable_Listado_Pedidos(model);
			// Accion al darle doble click sobre un pedido
			Tabla_Lista_pedidos.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						Modificar_Pedido_Seleccionado();
					}
				}
			});
			scrollPane_Lista_Pedidos.setViewportView(Tabla_Lista_pedidos);
		}
	}

	/**
	 * Abre la interfaz del boton seleccionado: 
	 * @param nombre Es el identificador de la interfaz que se desea abrir
	 */
	private void abrirInterfazBoton(String nombre) {
		if (nombre.equals("sc")) {
			Interfaz_Solicitud_Compra frame = new Interfaz_Solicitud_Compra(Principal_neg_int);
			frame.setVisible(true);
		} else if (nombre.equals("pr")) {
			Interfaz_Proveedores frame = new Interfaz_Proveedores(Principal_neg_int);
			frame.setVisible(true);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// AGREGAR AL PEDIDO
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Toma los datos del panel "panelAltaPedido" y los inserta en la tabla de
	 * pedido general
	 */
	private void Agregar_al_Pedido() {
		if (comboBoxVariedad.getItemCount() != 0 && comboBoxProducto.getItemCount() != 0
				&& comboBoxProducto.getSelectedIndex() != 0) {
			// hacer que tome los datos del formulario de pedido y los agregue a
			// la tabla de pedidos LISTO
			String Tipo_producto = comboBoxProducto.getSelectedItem().toString();

			Integer Cantidad = Integer.parseInt(spinnerCantidad.getValue().toString());
			PRODUCTO_ACTUAL.setCantidad(Cantidad);

			String Variedad = comboBoxVariedad.getSelectedItem().toString();

			if (!Tipo_producto.isEmpty() && !Variedad.isEmpty() && Cantidad > 0) {

				PRODUCTO_ACTUAL.setPR_Observacion(textObservaciones.getText());
				PRODUCTO_ACTUAL.setPR_TIPO_PRODUCTO_STRING(Tipo_producto);

				/**
				 * Esto va a un objeto pedido, el cual se usara para guardar en
				 * la base de datos
				 **/

				ArrayList<Producto> productos = PEDIDO_ACTUAL.getLista_Productos();

				// Si se agrega el mismo producto otra vez, agrega la cantidad
				// al que ya estaba
				boolean productoNoEsta = true;
				for (int i = 0; i < productos.size(); i++) {
					if (productos.get(i).getPR_nombre().equals(PRODUCTO_ACTUAL.getPR_nombre())
							&& productos.get(i).getPR_precio().equals(PRODUCTO_ACTUAL.getPR_precio())) {
						int cantidad = productos.get(i).getCantidad();
						productos.get(i).setCantidad(cantidad + PRODUCTO_ACTUAL.getCantidad());
						productoNoEsta = false;
					}
				}
				if (productoNoEsta) {
					PEDIDO_ACTUAL.agregar_un_producto(PRODUCTO_ACTUAL);
				}

				/** Esto va para la parte visual **/
				Actualizar_Tabla_Productos_del_Pedido(PEDIDO_ACTUAL);

				/**
				 * Despues que se resetee el formulario de ingreso de pedido
				 **/
				Limpiar_Formulario_pedido();
				Calcula_totales();
			}

		}
	}

	private void Actualizar_Tabla_Productos_del_Pedido(Pedido P) {
		Model_Pedido_Completo model = new Model_Pedido_Completo();
		for (int i = 0; i < P.getLista_Productos().size(); i++) {
			Object[] datos = new Object[7];
			String ValorU = formatoImporte.format(P.getLista_Productos().get(i)
					.getPR_precio());
			String ValorT = formatoImporte.format(P.getLista_Productos().get(i)
					.getPR_precio()
					* P.getLista_Productos().get(i).getCantidad());
			datos[0] = i + 1;
			datos[1] = P.getLista_Productos().get(i).getCantidad();
			datos[2] = P.getLista_Productos().get(i)
					.getPR_TIPO_PRODUCTO_STRING();
			datos[3] = P.getLista_Productos().get(i).getPR_nombre();
			datos[4] = ValorU;
			datos[5] = ValorT;
			datos[6] = P.getLista_Productos().get(i).getPR_Observacion();
			model.addRow(datos);
		}
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(model);
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// QUITAR/ ELIMINAR/ CANCELAR DE UN PEDIDO
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * SI HAY UN ELEMENTO SELECCIONADO EN LA LISTA DE PRODUCTOS, DE UN PEDIDO,
	 * SE ELIMINARA ESE ELEMENTO DE LA LISTA
	 */
	private void Quitar_al_Pedido() {
		// LO QUITA DE LA LISTA DE PEDIDO
		if (!PEDIDO_ACTUAL.getLista_Productos().isEmpty()) {
			Integer cantidad = (Integer) Tabla_Pedido_Completo
					.getValueAt((Integer) Tabla_Pedido_Completo.getSelectedRow(), 1);
			String Variedad = (String) Tabla_Pedido_Completo
					.getValueAt((Integer) Tabla_Pedido_Completo.getSelectedRow(), 3);
			Integer removidos = 0;
			for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
				if (PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_nombre().equals(Variedad) && removidos < cantidad)
					PEDIDO_ACTUAL.getLista_Productos().remove(i);
				removidos++;
			}
		}
		// LO QUITA DE LA LISTA VISUAL
		if (Tabla_Pedido_Completo.getSelectedRow() != -1) {
			int indice_Seleccionado = Tabla_Pedido_Completo.getSelectedRow();
			DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();
			modelo.removeRow(indice_Seleccionado);
			Calcula_totales();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// CAMBIA ESTADO DEL PEDIDO //
	private void Setear_como_Preparado() {
		if (Tabla_Lista_pedidos.getSelectedRow() > -1) {
			Integer Numero_pedido = (Integer) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0);
			String Estado_pedido = (String) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 5);
			Pedido pedido = new Pedido();
			pedido.setNumero_Pedido(Numero_pedido);

			if (Estado_pedido.equals("Pendiente")) {
				sv_pedidos.modificar_estado(pedido, 2);
				ACTUALIZAR_TODO();
			}
		}
	}

	// CAMBIA ESTADO DEL PEDIDO //
	private void Setear_como_cobrado() {
		if (Tabla_Lista_pedidos.getSelectedRow() != -1) {
			Integer Numero_pedido = (Integer) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0);
			String Estado_pedido = (String) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 5);
			Pedido pedido = new Pedido();
			pedido.setNumero_Pedido(Numero_pedido);

			if (Estado_pedido.equals("Preparado") || (Estado_pedido.equals("Enviado"))) {
				sv_pedidos.modificar_estado(pedido, 4);
				ACTUALIZAR_TODO();
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cargar_datos_Cliente(Cliente c) {
		textDomicilio.setText("");
		textTelefono.setText("");
		textDetalle.setText("");
		if(c!=null){
			CLIENTE_ACTUAL = c;
			textDomicilio.setText(c.getDomicilio());
			textTelefono.setText(c.getTelefono_Fijo());
			textDetalle.setText(c.getDetalle());
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void AutocompletarCliente() {
		AutoCompleter_Cliente.removeAllItems();
		AutoCompleter_Cliente.setCaseSensitive(false);
		AutoCompleter_Cliente.setMode(0);
		AutoCompleter_Cliente.addItems(Principal_neg_int.getSvClientes().getLISTA_CLIENTES());
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void ACTUALIZAR_MONITOR() {
		ArrayList<Pedido> Lista_Productos_de_Hoy = sv_pedidos.getLISTA_PEDIDOS_COCINA(new GregorianCalendar());
		((Interfaz_Cocina_Pantalla) frame_cocina).Actualizar_monitor(Lista_Productos_de_Hoy);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Generar_Comanda() {
		if (Tabla_Lista_pedidos != null && Tabla_Lista_pedidos.getSelectedRow() != -1) {
			Integer NUMERO_PEDIDO = (Integer) Tabla_Lista_pedidos.getValueAt(Tabla_Lista_pedidos.getSelectedRow(), 0);
			Pedido Pedido_mostrar = sv_pedidos.OBTENER_PEDIDO_COMBOS_DESP(NUMERO_PEDIDO); 
			ReporteTicket_comboDespl RTCD = new ReporteTicket_comboDespl(Pedido_mostrar);
			RTCD.MOSTRAR_REPORTE();
			
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Fecha_Siguiente() {
		Calendar c = dateChooser_Fecha_mostrar.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, 1);
		dateChooser_Fecha_mostrar.setCalendar(c);
		ACTUALIZAR_TODO();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Fecha_Anterior() {
		Calendar c = dateChooser_Fecha_mostrar.getCalendar();
		c.add(Calendar.DAY_OF_MONTH, -1);
		dateChooser_Fecha_mostrar.setCalendar(c);
		ACTUALIZAR_TODO();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// METODOS ITINERARIO DE ENTREGA
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	@SuppressWarnings("serial")
	private DefaultTableModel obtener_Model_itinerario() {
		return new DefaultTableModel(new Object[][] {}, new String[] { "",
				"Pedido N°", "Cliente", "Direccion", "Telefono", "Precio" }) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, String.class,
					String.class, String.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Actualizar_itinerario_pedidos() {

		ArrayList<Pedido> lista = sv_pedidos.get_pedidos_preparados(Calendar
				.getInstance());
		DefaultTableModel modelo = obtener_Model_itinerario();

		for (int i = 0; i < lista.size(); i++) {
			if (!lista.get(i).getCliente().getNombre().equals("")) {
				String[] arreglo = {
						String.valueOf(lista.get(i).getNumero_Pedido()),
						String.valueOf(lista.get(i).getID_DIARIO()),
						lista.get(i).getCliente().getNombre(),
						lista.get(i).getCliente().getDomicilio(),
						lista.get(i).getCliente().getTelefono_Fijo(),
						String.valueOf(lista.get(i).getTotal()) };
				modelo.addRow(arreglo);
			}
		}

		tabla_Itinerario_Pedidos.setModel(modelo);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Vaciar_lista_itinerario() {
		pedidodItinerario = new ArrayList<Integer>();
		tabla_Itinerario_con_pedidos.setModel(obtener_Model_itinerario());
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Quitar_del_itinerario() {
		if (tabla_Itinerario_con_pedidos.getSelectedRow() > -1) {
			for (int i = 0; i < pedidodItinerario.size(); i++) {
				if (pedidodItinerario.get(i) == Integer.parseInt(
						(String) tabla_Itinerario_Pedidos.getValueAt(tabla_Itinerario_Pedidos.getSelectedRow(), 0))) {
					pedidodItinerario.remove(i);
				}
			}
			DefaultTableModel modelo = (DefaultTableModel) tabla_Itinerario_con_pedidos.getModel();
			modelo.removeRow(tabla_Itinerario_con_pedidos.getSelectedRow());
			tabla_Itinerario_con_pedidos.setModel(modelo);
		}
	}
	
	private void Agregar_nuevo_cliente() {
		ADM_Cliente frame = new ADM_Cliente(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Agregar_al_itinerario() {
		if (tabla_Itinerario_Pedidos.getSelectedRow() > -1) {
			if (!pedidodItinerario.contains(Integer.parseInt((String) tabla_Itinerario_Pedidos.getValueAt(tabla_Itinerario_Pedidos.getSelectedRow(), 0)))) {
				pedidodItinerario.add(Integer.parseInt((String) tabla_Itinerario_Pedidos.getValueAt(tabla_Itinerario_Pedidos.getSelectedRow(), 0)));
				DefaultTableModel modelo = (DefaultTableModel) tabla_Itinerario_con_pedidos.getModel();
				String[] arreglo = {
						(String) tabla_Itinerario_Pedidos.getValueAt(
								tabla_Itinerario_Pedidos.getSelectedRow(), 0),
						(String) tabla_Itinerario_Pedidos.getValueAt(
								tabla_Itinerario_Pedidos.getSelectedRow(), 1),
						(String) tabla_Itinerario_Pedidos.getValueAt(
								tabla_Itinerario_Pedidos.getSelectedRow(), 2),
						(String) tabla_Itinerario_Pedidos.getValueAt(
								tabla_Itinerario_Pedidos.getSelectedRow(), 3),
						(String) tabla_Itinerario_Pedidos.getValueAt(
								tabla_Itinerario_Pedidos.getSelectedRow(), 0) };
				modelo.addRow(arreglo);
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Generar_itinerario_entrega() {
		if (tabla_Itinerario_con_pedidos != null && tabla_Itinerario_con_pedidos.getRowCount() > 0) {
			if (comboRepartidores.getSelectedIndex() != 0) {
				Entrega entrega = new Entrega();
				entrega.setFecha_salida(MetAux.toDate(Calendar.getInstance()));
				entrega.setRepartidor(sv_Repartidores.getRepartidor(comboRepartidores.getSelectedItem().toString()));
				sv_Entrega.agregarEntrega(entrega);
				entrega.setId(sv_Entrega.obtenerIdUltimaEntrega());
				entrega.setLista_pedidos(new ArrayList<Pedido>());
				for (int i = 0; i < tabla_Itinerario_con_pedidos.getRowCount(); i++) {
					entrega.getLista_pedidos().add(sv_pedidos.get_pedido(Integer.parseInt((String) tabla_Itinerario_con_pedidos.getValueAt(i, 0))));
					Pedido pedido = sv_pedidos.get_pedido(Integer.parseInt((String) tabla_Itinerario_con_pedidos.getValueAt(i, 0)));
					sv_pedidos.modificar_estado(pedido, 3);
					ACTUALIZAR_TODO();
				}
				sv_Entrega.AGREGAR_PEDIDO(entrega);

				ReporteItinerario ri = new ReporteItinerario();
				ri.Generar_Itinerario(entrega.getId());

				tabla_Itinerario_con_pedidos.setModel(obtener_Model_itinerario());
			} else
				JOptionPane.showMessageDialog(null, "Para generar el itinerario debe seleccionar un repartidor","Falta elegir un repartidor", JOptionPane.WARNING_MESSAGE);
		} else
			JOptionPane.showMessageDialog(null, "Aun no hay pedidos con delivery preparados","Sin pedidos para delivery", JOptionPane.WARNING_MESSAGE);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void AbrirInterfazCategoria() {
		ADM_Categorias frame = new ADM_Categorias(Principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void Ticket_Comanda_nuevo_pedido() {

		Integer ID = sv_pedidos.Obtener_ID_Ultimo_Pedido();
		Integer indice = 0;
		// Busca en la tabla el indice que se modifico, Todo esto para que
		// seleccione el nuevo viaje en la tabla y se mueva el scroll
		for (int i = 0; i < Tabla_Lista_pedidos.getRowCount(); i++) {
			if ((Integer) Tabla_Lista_pedidos.getValueAt(i, 0) == ID)
				indice = i;
		}
		// Selecciona el registro modificado
		scrollPane_Lista_Pedidos.getVerticalScrollBar().setValue(scrollPane_Lista_Pedidos.getVerticalScrollBar().getMinimum());
		Tabla_Lista_pedidos.getSelectionModel().setSelectionInterval(indice, indice);
		Rectangle r = Tabla_Lista_pedidos.getCellRect(indice, 0, false);
		scrollPane_Lista_Pedidos.getViewport().scrollRectToVisible(r);
		Generar_Comanda();
	}
	
	/**
	 * Abre una ventana donde pide que se seleccione un directorio
	 * @return devuelve la ruta absoluta del directorio que se seleccionó
	 */
	private String Consultar_directorio() {
		JFileChooser chooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory().getAbsoluteFile());
		chooser.setDialogTitle("Seleccione ");
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile().getAbsolutePath();
		}
		return "";
	}
	
	public File importarDatos() {
		File ArchivoDeExportacion = null;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(".sql","sql");
		chooser.setFileFilter(filter);
		int resultado = chooser.showOpenDialog(null);
		if (resultado == JFileChooser.APPROVE_OPTION) {
			ArchivoDeExportacion = chooser.getSelectedFile();
		}
		return ArchivoDeExportacion;
	}
}// ---> FIN CLASE
