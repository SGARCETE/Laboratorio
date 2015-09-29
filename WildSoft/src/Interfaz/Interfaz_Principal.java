package Interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import Interfaz.Swing_Extends.JTable_Listado_Pedidos;
import Interfaz.Swing_Extends.Model_Listado_Pedidos;


public class Interfaz_Principal {

	private JFrame frmWildsoft;
	private JTextField textCliente;
	private JTextField textDomicilio;
	private JTextField textTelefono;
	private JTextField textDetalle;
	private JTable tabla_Pedido_Completo;
	private JTextField textValor;
	private JTextField textObservaciones;
	private JTextField textValorTotal;
	private JTable Tabla_Lista_pedidos;
	private JScrollPane scrollPane_Lista_Pedidos;


	
	/**
	 * Launch the application.		ESTO ACA ES TEMPORAL, ESTA MAL ACA
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
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
		frmWildsoft.setBounds(100, 100, 962, 663);
		frmWildsoft.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		tabbedPane.addTab("Nuevo pedido", null, panel, null);
		panel.setLayout(null);
		
		JPanel panelProductos = new JPanel();
		panelProductos.setBackground(new Color(240,240,240));
		panelProductos.setLayout(null);
		panelProductos.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Producto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelProductos.setBounds(0, 6, 940, 326);
		panel.add(panelProductos);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Tabla que muestra el pedido
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(336, 93, 566, 176);
		panelProductos.add(scrollPane);
		
		tabla_Pedido_Completo = new JTable();
		tabla_Pedido_Completo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		tabla_Pedido_Completo.setModel(new DefaultTableModel(
			new Object[][] {
				{"1", "2", "Pizza Napolitana", "$50,00", "$100,00", "Sin aceitunas"},
				{"2", "12", "Empanadas de carne", "$7,00", "$84,00", null},
				{null, "", null, null, null, null},
				{null, null, null, null, null, null},
				{null, null, null, null, null, null},
			},
			new String[] {
				"Nro", "Unidades", "Producto", "Importe c/u", "Importe", "Observacion"
			}
		));
		tabla_Pedido_Completo.getColumnModel().getColumn(0).setPreferredWidth(34);
		tabla_Pedido_Completo.getColumnModel().getColumn(1).setPreferredWidth(67);
		tabla_Pedido_Completo.getColumnModel().getColumn(2).setPreferredWidth(134);
		tabla_Pedido_Completo.getColumnModel().getColumn(5).setPreferredWidth(101);
		scrollPane.setViewportView(tabla_Pedido_Completo);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(336, 281, 69, 28);
		panelProductos.add(btnQuitar);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblIngreseLosProductos = new JLabel("Ingrese los productos que componen su pedido");
		lblIngreseLosProductos.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseLosProductos.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblIngreseLosProductos.setBounds(18, 20, 420, 25);
		panelProductos.add(lblIngreseLosProductos);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblProducto = new JLabel("Producto");
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblProducto.setBounds(18, 57, 110, 25);
		panelProductos.add(lblProducto);
		
		JComboBox<String> comboProducto = new JComboBox<String>();
		comboProducto.setModel(new DefaultComboBoxModel<String>(new String[] {"Pizzas", "Empanadas"}));
		comboProducto.setBounds(127, 57, 199, 25);
		panelProductos.add(comboProducto);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblVariedadgusto = new JLabel("Variedad/Gusto");
		lblVariedadgusto.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVariedadgusto.setBounds(18, 93, 110, 25);
		panelProductos.add(lblVariedadgusto);

		JComboBox<String> comboVariedadGusto = new JComboBox<String>();
		comboVariedadGusto.setModel(new DefaultComboBoxModel<String>(new String[] {"Napolitana", "Napolitana especial", "Muzzarella", "Jamon y palmito", "Queso y peperoni", "Hawaiana", "Jamon y Panceta", "Cuatro quesos", "Salmon Ahumado", "Cuatro estaciones", "Pizza Funghi", "Vegetariana"}));
		comboVariedadGusto.setBounds(127, 94, 199, 25);
		panelProductos.add(comboVariedadGusto);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblCantidad = new JLabel("Cantidad");
		lblCantidad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCantidad.setBounds(18, 130, 110, 25);
		panelProductos.add(lblCantidad);
		
		JSpinner spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(new Integer(3), null, null, new Integer(1)));
		spinnerCantidad.setBounds(125, 130, 39, 25);
		panelProductos.add(spinnerCantidad);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblValor = new JLabel("Valor c/u");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValor.setBounds(18, 167, 110, 25);
		panelProductos.add(lblValor);
		
		textValor = new JTextField();
		textValor.setText("$50,00");
		textValor.setEditable(false);
		textValor.setColumns(10);
		textValor.setBounds(125, 167, 199, 25);
		panelProductos.add(textValor);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblValorTotal = new JLabel("Valor total");
		lblValorTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblValorTotal.setBounds(18, 204, 110, 25);
		panelProductos.add(lblValorTotal);
		
		textValorTotal = new JTextField();
		textValorTotal.setText("$150,00");
		textValorTotal.setEditable(false);
		textValorTotal.setColumns(10);
		textValorTotal.setBounds(125, 204, 199, 25);
		panelProductos.add(textValorTotal);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblObservacion = new JLabel("Observaciones");
		lblObservacion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblObservacion.setBounds(18, 244, 110, 25);
		panelProductos.add(lblObservacion);
		
		textObservaciones = new JTextField();
		textObservaciones.setColumns(10);
		textObservaciones.setBounds(125, 244, 199, 25);
		panelProductos.add(textObservaciones);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnAgregar.setBounds(125, 281, 104, 28);
		panelProductos.add(btnAgregar);

		JLabel lblResumenDelPedido = new JLabel("Pedido completo");
		lblResumenDelPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenDelPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResumenDelPedido.setBounds(336, 62, 566, 25);
		panelProductos.add(lblResumenDelPedido);

		JPanel panel_5 = new JPanel();
		panel_5.setBackground(Color.WHITE);
		panel_5.setLayout(null);
		panel_5.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Importes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_5.setBounds(354, 344, 578, 204);
		panel.add(panel_5);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Panel de Delibery
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JPanel panelDelibery = new JPanel();
		panelDelibery.setBackground(Color.WHITE);
		panelDelibery.setBounds(6, 344, 336, 204);
		panel.add(panelDelibery);
		panelDelibery.setBorder(new TitledBorder(null, "Servicio delivery", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDelibery.setLayout(null);
		
		JCheckBox chckbxDelivery = new JCheckBox("Delivery");
		chckbxDelivery.setSelected(true);
		chckbxDelivery.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxDelivery.setBounds(16, 14, 97, 25);
		panelDelibery.add(chckbxDelivery);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblCliente = new JLabel("Cliente");
		lblCliente.setBounds(16, 46, 97, 25);
		panelDelibery.add(lblCliente);
		lblCliente.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textCliente = new JTextField();
		textCliente.setBounds(123, 46, 199, 25);
		panelDelibery.add(textCliente);
		textCliente.setColumns(10);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblDomicilio = new JLabel("Domicilio");
		lblDomicilio.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDomicilio.setBounds(16, 82, 97, 25);
		panelDelibery.add(lblDomicilio);
		
		textDomicilio = new JTextField();
		textDomicilio.setColumns(10);
		textDomicilio.setBounds(123, 82, 199, 25);
		panelDelibery.add(textDomicilio);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTelefono.setBounds(16, 118, 97, 25);
		panelDelibery.add(lblTelefono);

		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(123, 118, 199, 25);
		panelDelibery.add(textTelefono);
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDetalle.setBounds(16, 156, 97, 25);
		panelDelibery.add(lblDetalle);
		
		textDetalle = new JTextField();
		textDetalle.setColumns(10);
		textDetalle.setBounds(123, 156, 199, 25);
		panelDelibery.add(textDetalle);
		
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
		
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		//			Panel de combos 
		//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Combos", null, panel_2, null);
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
		frmWildsoft.setVisible(true);
		
		iniciarParametros();
		
	}



	private void iniciarParametros() {
		Tabla_Lista_pedidos = new JTable_Listado_Pedidos(new Model_Listado_Pedidos());
		scrollPane_Lista_Pedidos.setViewportView(Tabla_Lista_pedidos);
		
	}
	
}//---> FIN CLASE
