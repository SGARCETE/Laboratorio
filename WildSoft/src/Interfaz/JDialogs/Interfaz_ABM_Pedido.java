package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;

import Interfaz.Interfaz_Principal;
import Interfaz.Swing_Extends.JTable_Pedido_Completo;
import Interfaz.Swing_Extends.Model_Pedido_Completo;
import MetAux.MetAux;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Clientes;
import Negocio.Servicios.Servicio_Pedidos;
import Negocio.Servicios.Servicio_Productos;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


@SuppressWarnings("serial")
public class Interfaz_ABM_Pedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable Tabla_Pedido_Completo;
	private JScrollPane scrollPane_Pedido_Completo;
	private JTextField textValor;
	private JTextField textObservaciones;
	private JTextField textValorTotal;
	private JTextField textDetalle;
	private JTextField textDomicilio;
	private JTextField textTelefono;
	private JTextField textCliente = new JTextField();
	private TextAutoCompleter AutoCompleter_Cliente = new TextAutoCompleter(textCliente, new AutoCompleterCallback() {
		public void callback(Object selectedItem) { // Para saber que selecciono el usuario // <HACE ALGO SI TE ELIJO> ejemplo:
				String Nombre_Cliente_seleccionado = ((String)selectedItem);
				Cliente C = sv_clientes.getCliente(Nombre_Cliente_seleccionado);
				Cargar_datos_Cliente(C);
			}
		}
	);
	private JButton Modificar_Cantidad; 
	private JButton btnQuitar;
	private JLabel label_NroPedido;
	private JLabel label_Fecha;
	private JLabel label_ESTADO;
	private JLabel textTotal_Pedido;
	private JSpinner spinnerCantNueva;
	private JComboBox<String> comboBoxProducto;
	private JComboBox<String> comboBoxVariedad;
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	private JSpinner spinnerCantidad;
	
	private Producto PRODUCTO_ACTUAL = new Producto();
	private Pedido PEDIDO_ACTUAL = new Pedido();
	private Principal_Negocio_Interfaz Principal_neg_int;
	private Cliente CLIENTE_ACTUAL = null; 
	private Servicio_Productos svProductos;
	private Servicio_Pedidos SvPedidos;
	private Servicio_Clientes sv_clientes;
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(); /* Muestra un Double en formato Dinero. Ej: 50.5 => $50,50 */
	private SimpleDateFormat formato_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private JButton btn_Agregar;
	private JButton btnGuardarModificacionPedido;
	private JToggleButton tglbtnDelivery;
	
	public Interfaz_ABM_Pedido(Principal_Negocio_Interfaz principal_neg_int) {
		Principal_neg_int = principal_neg_int;
		sv_clientes = Principal_neg_int.getSvClientes();
		SvPedidos = Principal_neg_int.getSvPedidos();
		svProductos = Principal_neg_int.getSvProductos();
		SvPedidos.getTodos_los_estados();
		Principal_neg_int.getSvCombos(); 
		
		setTitle("ABM Pedido");
		setBounds(100, 100, 987, 630);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		scrollPane_Pedido_Completo = new JScrollPane();
		
		JLabel lblNumeroPedido = new JLabel("N\u00BA Pedido");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setFont(new Font("SansSerif", Font.PLAIN, 24));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Cliente", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setLayout(null);
		
		JLabel lblDireccion = new JLabel("Cliente");
		lblDireccion.setBounds(22, 16, 291, 25);
		panel.add(lblDireccion);
		
		textCliente.setBackground(new Color(240, 255, 240));
		textCliente.setBounds(22, 42, 222, 25);
		panel.add(textCliente);
		textCliente.setColumns(10);
		
		textDetalle = new JTextField();
		textDetalle.setBackground(new Color(240, 255, 240));
		textDetalle.setBounds(349, 42, 201, 25);
		panel.add(textDetalle);
		textDetalle.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Observacion");
		lblDetalle.setBounds(349, 16, 145, 25);
		panel.add(lblDetalle);
		
		JLabel lblDireccion_1 = new JLabel("Direcci\u00F3n");
		lblDireccion_1.setBounds(22, 72, 291, 25);
		panel.add(lblDireccion_1);
		
		textDomicilio = new JTextField();
		textDomicilio.setBackground(new Color(240, 255, 240));
		textDomicilio.setBounds(22, 98, 291, 25);
		panel.add(textDomicilio);
		textDomicilio.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBackground(new Color(240, 255, 240));
		textTelefono.setBounds(349, 98, 201, 25);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		
		JLabel lblTelefono = new JLabel("Tel\u00E9fono");
		lblTelefono.setBounds(349, 72, 145, 25);
		panel.add(lblTelefono);
		
		label_NroPedido = new JLabel("");
		label_NroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		label_NroPedido.setOpaque(true);
		label_NroPedido.setForeground(new Color(60, 179, 113));
		label_NroPedido.setBackground(new Color(240, 255, 240));
		label_NroPedido.setFont(new Font("SansSerif", Font.BOLD, 28));
		
		label_Fecha = new JLabel("");
		label_Fecha.setOpaque(true);
		label_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
		label_Fecha.setForeground(new Color(60, 179, 113));
		label_Fecha.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_Fecha.setBackground(new Color(240, 255, 240));
		
		JPanel panelPedido = new JPanel();
		panelPedido.setLayout(null);
		panelPedido.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPedido.setBackground(Color.WHITE);
		
		JLabel label = new JLabel("Producto");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 12, 109, 25);
		panelPedido.add(label);
		
		comboBoxProducto = new JComboBox<String>();
		comboBoxProducto.setBackground(new Color(240, 255, 255));
		comboBoxProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_De_Tipo_Producto();
			}
		});
		comboBoxProducto.setBounds(117, 12, 201, 25);
		panelPedido.add(comboBoxProducto);
		
		comboBoxVariedad = new JComboBox<String>();
		comboBoxVariedad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_De_Variedad();
			}
		});
		comboBoxVariedad.setBackground(new Color(240, 255, 255));
		comboBoxVariedad.setBounds(117, 49, 201, 25);
		panelPedido.add(comboBoxVariedad);
		
		JLabel label_2 = new JLabel("Variedad");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 49, 109, 25);
		panelPedido.add(label_2);
		
		JLabel label_3 = new JLabel("Cantidad");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 85, 109, 25);
		panelPedido.add(label_3);
		
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerCantidad.setBackground(new Color(240, 255, 255));
		spinnerCantidad.setBounds(117, 85, 59, 25);
		panelPedido.add(spinnerCantidad);
		
		JLabel label_4 = new JLabel("Valor c/u     $");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 122, 109, 25);
		panelPedido.add(label_4);
		
		textValor = new JTextField();
		textValor.setText("$0,00");
		textValor.setHorizontalAlignment(SwingConstants.RIGHT);
		textValor.setEditable(false);
		textValor.setColumns(10);
		textValor.setBackground(new Color(240, 255, 255));
		textValor.setBounds(117, 122, 199, 25);
		panelPedido.add(textValor);
		
		JLabel label_5 = new JLabel("Valor total   $");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 159, 109, 25);
		panelPedido.add(label_5);
		
		JLabel label_6 = new JLabel("Observaciones");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 195, 109, 25);
		panelPedido.add(label_6);
		
		textObservaciones = new JTextField();
		textObservaciones.setColumns(10);
		textObservaciones.setBackground(new Color(240, 255, 255));
		textObservaciones.setBounds(117, 195, 199, 25);
		panelPedido.add(textObservaciones);
		
		textValorTotal = new JTextField();
		textValorTotal.setToolTipText("");
		textValorTotal.setText("$0,00");
		textValorTotal.setHorizontalAlignment(SwingConstants.RIGHT);
		textValorTotal.setEditable(false);
		textValorTotal.setColumns(10);
		textValorTotal.setBackground(new Color(240, 255, 255));
		textValorTotal.setBounds(117, 159, 199, 25);
		panelPedido.add(textValorTotal);
		
		btn_Agregar = new JButton("Agregar");
		btn_Agregar.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/Check-3-icon16.png")));
		btn_Agregar.setBackground(Color.WHITE);
		btn_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_al_Pedido();
			}
		});
		btn_Agregar.setBounds(117, 225, 100, 30);
		panelPedido.add(btn_Agregar);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 24));
		
		JLabel lblNumero = new JLabel("Estado");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("SansSerif", Font.PLAIN, 24));
		
		label_ESTADO = new JLabel("");
		label_ESTADO.setOpaque(true);
		label_ESTADO.setHorizontalAlignment(SwingConstants.CENTER);
		label_ESTADO.setForeground(new Color(60, 179, 113));
		label_ESTADO.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_ESTADO.setBackground(new Color(240, 248, 255));
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		
		textTotal_Pedido = new JLabel("");
		textTotal_Pedido.setOpaque(true);
		textTotal_Pedido.setHorizontalAlignment(SwingConstants.CENTER);
		textTotal_Pedido.setForeground(new Color(60, 179, 113));
		textTotal_Pedido.setFont(new Font("SansSerif", Font.BOLD, 28));
		textTotal_Pedido.setBackground(new Color(240, 248, 255));
		
		JPanel panelModificacionPR = new JPanel();
		panelModificacionPR.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Producto", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelModificacionPR.setBackground(Color.WHITE);
		panelModificacionPR.setLayout(null);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(211, 50, 91, 38);
		panelModificacionPR.add(btnQuitar);
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quitar_al_Pedido();
			}
		});
		btnQuitar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		
		Modificar_Cantidad = new JButton("Modificar");
		Modificar_Cantidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Modificar_cantidad();
			}
		});
		Modificar_Cantidad.setBounds(94, 50, 107, 38);
		panelModificacionPR.add(Modificar_Cantidad);
		Modificar_Cantidad.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		
		spinnerCantNueva = new JSpinner();
		spinnerCantNueva.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		spinnerCantNueva.setBounds(21, 57, 55, 25);
		panelModificacionPR.add(spinnerCantNueva);
		
		tglbtnDelivery = new JToggleButton();
		tglbtnDelivery.setBackground(Color.WHITE);
		tglbtnDelivery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servicio_Delivery();				
			}
		});
		
		JLabel lblConDelivery = new JLabel("Delivery");
		lblConDelivery.setHorizontalAlignment(SwingConstants.CENTER);
		lblConDelivery.setFont(new Font("SansSerif", Font.PLAIN, 24));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(1)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNumeroPedido, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(label_NroPedido, GroupLayout.PREFERRED_SIZE, 149,
												GroupLayout.PREFERRED_SIZE)
										.addGap(47)
										.addComponent(lblFecha, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12).addComponent(label_Fecha, GroupLayout.PREFERRED_SIZE, 167,
												GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
										.addGap(12)
										.addComponent(label_ESTADO, GroupLayout.PREFERRED_SIZE, 149,
												GroupLayout.PREFERRED_SIZE)
										.addGap(47)
										.addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 124,
												GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(textTotal_Pedido, GroupLayout.PREFERRED_SIZE, 167,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(lblConDelivery, GroupLayout.PREFERRED_SIZE, 124,
										GroupLayout.PREFERRED_SIZE).addGap(10).addComponent(tglbtnDelivery,
												GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(panelPedido, GroupLayout.PREFERRED_SIZE, 331, GroupLayout.PREFERRED_SIZE)
								.addGap(11).addComponent(scrollPane_Pedido_Completo, GroupLayout.DEFAULT_SIZE, 613,
										Short.MAX_VALUE))
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addComponent(panelModificacionPR, GroupLayout.PREFERRED_SIZE, 331,
										GroupLayout.PREFERRED_SIZE)
								.addGap(8).addComponent(panel, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)))
						.addGap(5)));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(5)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumeroPedido, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(label_NroPedido,
								GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(lblFecha,
								GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(label_Fecha,
								GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)))
				.addGap(11)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNumero, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(label_ESTADO,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblTotal_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(textTotal_Pedido,
								GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
						.addComponent(lblConDelivery, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.addComponent(tglbtnDelivery, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE))
				.addGap(11)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelPedido, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addComponent(scrollPane_Pedido_Completo, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
				.addGap(11)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panelModificacionPR, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE))
				.addGap(7)));
		
		JButton btnQuitar_1 = new JButton("Quitar");
		btnQuitar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CLIENTE_ACTUAL = null;
				textCliente.setText("");
				textDetalle.setText("");
				textTelefono.setText("");
				textDomicilio.setText("");
			}
		});
		btnQuitar_1.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/subtract-1-icon16.png")));
		btnQuitar_1.setBounds(244, 42, 83, 25);
		panel.add(btnQuitar_1);
		contentPanel.setLayout(gl_contentPanel);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(60, 179, 113));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		btnGuardarModificacionPedido = new JButton("Guardar");
		btnGuardarModificacionPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guardar_pedido();
			}
		});
		btnGuardarModificacionPedido.setBackground(Color.WHITE);
		btnGuardarModificacionPedido.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		btnGuardarModificacionPedido.setActionCommand("OK");
		buttonPane.add(btnGuardarModificacionPedido);
		getRootPane().setDefaultButton(btnGuardarModificacionPedido);

		JButton cancelButton = new JButton("Salir");
		cancelButton.setBackground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);

		iniciarParametros();
	}
	
	private void Servicio_Delivery() {
		PEDIDO_ACTUAL.setEs_Delivery(tglbtnDelivery.isSelected());
		tglbtnDelivery.setText(PEDIDO_ACTUAL.getEs_Delivery() ? "SI" : "NO");
	}

	/** >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>**/
	private void iniciarParametros() {
		MetAux.Limitar_caracteres(textObservaciones, 20);
		Cargar_ComboBox_TipoProductos();
		AutocompletarCliente();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cargar_ComboBox_TipoProductos() {
		// Rellena el combobox de Tipos de productos
		ArrayList<String> ListaProductos = svProductos.getLista_Productos();
		comboBoxProducto.addItem("Seleccione el tipo de producto");
		for (int i = 0; i < ListaProductos.size(); i++) {
			comboBoxProducto.addItem(ListaProductos.get(i));
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Seleccion_De_Tipo_Producto() {
		if (!comboBoxProducto.getSelectedItem().toString().isEmpty()) {
			Lista_Variedades = svProductos.getVariedad_del_Producto(comboBoxProducto.getSelectedItem().toString());
			comboBoxVariedad.removeAllItems();
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				comboBoxVariedad.addItem(Lista_Variedades.get(i).getPR_nombre());
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Seleccion_De_Variedad() {
		if (comboBoxVariedad.getSelectedItem() != null && !comboBoxVariedad.getSelectedItem().toString().isEmpty()) {
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				if (Lista_Variedades.get(i).getPR_nombre().equals(comboBoxVariedad.getSelectedItem().toString()))
					PRODUCTO_ACTUAL = Lista_Variedades.get(i); // SETEO EL PRODUCTO SELECCIONADO
			}
			textValor.setText(formatoImporte.format(PRODUCTO_ACTUAL.getPR_precio()));
			Calcula_totales();
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/** SI HAY UN ELEMENTO SELECCIONADO EN LA LISTA DE PRODUCTOS, DE UN PEDIDO,
	 * SE ELIMINARA ESE ELEMENTO DE LA LISTA */
	private void Quitar_al_Pedido() {
		//		 LO QUITA DE LA LISTA DE PEDIDO
		if(!PEDIDO_ACTUAL.getLista_Productos().isEmpty()){
			Integer cantidad = (Integer) Tabla_Pedido_Completo.getValueAt((Integer)Tabla_Pedido_Completo.getSelectedRow(),1);
			String Variedad = (String) Tabla_Pedido_Completo.getValueAt((Integer)Tabla_Pedido_Completo.getSelectedRow(),3);
			Integer removidos = 0;
			for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
				 if(PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_nombre().equals(Variedad) && removidos<cantidad){
					PEDIDO_ACTUAL.getLista_Productos().remove(i);
				 	removidos++;
				 }
			} 
			
			// LO QUITA DE LA LISTA VISUAL
			if (Tabla_Pedido_Completo.getSelectedRow() != -1) { // -1 es cuando no se selecciono nada en la tabla, si es distinto, entonces es xq selecciono algo y se puede quitar
				int indice_Seleccionado = Tabla_Pedido_Completo.getSelectedRow();
				DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();
				modelo.removeRow(indice_Seleccionado);
				Calcula_totales();
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/** Toma los datos del panel "panelAltaPedido" y los inserta en la tabla de pedido general*/
	private void Agregar_al_Pedido() {
		if (comboBoxVariedad.getItemCount() != 0 && comboBoxProducto.getItemCount() != 0 && comboBoxProducto.getSelectedIndex()!=0) {
			// hacer que tome los datos del formulario de pedido y los agregue a
			// la tabla de pedidos LISTO
			String Tipo_producto = comboBoxProducto.getSelectedItem().toString();
			
				Integer Cantidad = Integer.parseInt(spinnerCantidad.getValue().toString());
				PRODUCTO_ACTUAL.setCantidad(Cantidad);
				
				String Variedad = comboBoxVariedad.getSelectedItem().toString();
				
					if (!Tipo_producto.isEmpty() && !Variedad.isEmpty() && Cantidad > 0) {
						
						PRODUCTO_ACTUAL.setPR_Observacion(textObservaciones.getText());
						PRODUCTO_ACTUAL.setPR_TIPO_PRODUCTO_STRING(Tipo_producto);
		
						/** Esto va a un objeto pedido, el cual se usara para guardar en la base de datos **/
						
						ArrayList<Producto> productos = PEDIDO_ACTUAL.getLista_Productos();
						
						// Si se agrega el mismo producto otra vez, agrega la cantidad al que ya estaba
						boolean productoNoEsta = true;
						for (int i = 0; i<productos.size(); i++ ) {
							if(productos.get(i).getPR_nombre().equals(PRODUCTO_ACTUAL.getPR_nombre()) && productos.get(i).getPR_precio().equals(PRODUCTO_ACTUAL.getPR_precio())){
								int cantidad  = productos.get(i).getCantidad();
								productos.get(i).setCantidad(cantidad + PRODUCTO_ACTUAL.getCantidad());
								productoNoEsta = false;
							}
						}
						if(productoNoEsta){
							PEDIDO_ACTUAL.agregar_un_producto(PRODUCTO_ACTUAL);
						}
		
						/** Esto va para la parte visual **/
						Actualizar_Tabla_Productos_del_Pedido(PEDIDO_ACTUAL);
						
						/** Despues que se resetee el formulario de ingreso de pedido **/
						Limpiar_Formulario_pedido();
						Calcula_totales();
						}
			
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Modificar_cantidad() {
		if(Tabla_Pedido_Completo.getSelectedRow()!=-1){
			int posicion = Tabla_Pedido_Completo.getSelectedRow();
			int cantidad = (int) spinnerCantNueva.getValue();
			PEDIDO_ACTUAL.getLista_Productos().get(posicion).setCantidad(cantidad);
			Actualizar_Tabla_Productos_del_Pedido(PEDIDO_ACTUAL);
			Calcula_totales();
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void setPedido_a_modificar(Integer Numero_pedido_modificar) {
		// OBTIENE EL PEDIDO CON EL ID GENERAL
		PEDIDO_ACTUAL = SvPedidos.get_pedido(Numero_pedido_modificar);
		
		// si el pedido no es nulo cargos los campos del pedido
		if(PEDIDO_ACTUAL!=null){
			// SE SETEA LOS DATOS PRINCIPALES DEL PEDIDO
			label_NroPedido.setText(PEDIDO_ACTUAL.getID_DIARIO().toString());
			label_ESTADO.setText(PEDIDO_ACTUAL.getESTADO());
			label_Fecha.setText(formato_ddMMyyyy.format(PEDIDO_ACTUAL.getFecha_Hora_Pedido().getTime()));
			textTotal_Pedido.setText(formatoImporte.format(PEDIDO_ACTUAL.getTotal()));		// ACTUALIZA EL TOTAL
			
			// SOLO SE PUEDE MODIFICAR SI EL ESTADO ES PENDIENTE, DE LO CONTRARIO SE DESHABILITAN LOS CAMPOS PARA MODIFICAR
			if (!PEDIDO_ACTUAL.getESTADO().equals("Pendiente")){
				textCliente.setEditable(false);
				textDomicilio.setEditable(false);
				textDetalle.setEditable(false);
				textTelefono.setEditable(false);
				textObservaciones.setEditable(false);
				
				comboBoxProducto.setEnabled(false);
				comboBoxVariedad.setEnabled(false);
			
				spinnerCantidad.setEnabled(false);
				spinnerCantNueva.setEnabled(false);
				btnGuardarModificacionPedido.setEnabled(false);
				Modificar_Cantidad.setEnabled(false);
				btnQuitar.setEnabled(false);
				btn_Agregar.setEnabled(false);
				tglbtnDelivery.setEnabled(false);
			}
		}
		
		// SE SETEA EL NOMBRE Y SE SELECCIONA EL TOOGLEBUTTON DEPENDIENDO SI ES DELIVERY O NO
		tglbtnDelivery.setText(PEDIDO_ACTUAL.getEs_Delivery() ? "SI" : "NO");
		tglbtnDelivery.setSelected(PEDIDO_ACTUAL.getEs_Delivery());
		
		// SE CARGAN LOS DATOS DEL CLIENTE SI ES QUE HAY UNO ASOCIADO
		Cargar_datos_Cliente(PEDIDO_ACTUAL.getCliente());
		
		// SE CARGAN LOS PRODUCTOS DEL PEDIDO EN LA TABLA
		Actualizar_Tabla_Productos_del_Pedido(PEDIDO_ACTUAL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private boolean Guardar_pedido() {
		// SI ES CON DELIVERY, CHEQUEO QUE EL NOMBRE DEL CLIENTE Y LA DIRECCION ESTEN, SINO NO LO GUARDO
		if(PEDIDO_ACTUAL.getEs_Delivery()){
			if(textCliente.getText().isEmpty() || textDomicilio.getText().isEmpty()){
				JOptionPane.showMessageDialog(this, "Un pedido con servicio de delivery debe tener \n al menos el nombre y la direccion del cliente", "Falta cliente", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		PEDIDO_ACTUAL.setCliente(CLIENTE_ACTUAL);
		
		// SI EL PEDIDO NO TIENE PRODUCTOS, NO SE PUEDE GUARDAR
		if(PEDIDO_ACTUAL.getLista_Productos().size()>0){
			SvPedidos.modificar_pedido(PEDIDO_ACTUAL);
			
			ArrayList<Producto> lista = PEDIDO_ACTUAL.getLista_Productos();
			
			SvPedidos.eliminar_producto_del_pedido(PEDIDO_ACTUAL);
			
			PEDIDO_ACTUAL.setLista_Productos(lista);
			
			SvPedidos.agregar_producto_al_pedido(PEDIDO_ACTUAL);
	
			dispose();
			
		}
		else
			JOptionPane.showMessageDialog(this, "El pedido debe tener al menos un producto", "Faltan productos", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Actualizar_Tabla_Productos_del_Pedido(Pedido P){
		Model_Pedido_Completo model = new Model_Pedido_Completo();

		for (int i = 0; i < P.getLista_Productos().size(); i++) {
			Object[] datos = new Object[7];
			String ValorU = formatoImporte.format(P.getLista_Productos().get(i).getPR_precio());
			String ValorT = formatoImporte.format(P.getLista_Productos().get(i).getPR_precio() * P.getLista_Productos().get(i).getCantidad());
			datos[0] = i+1;
			datos[1] = P.getLista_Productos().get(i).getCantidad();
			datos[2] = P.getLista_Productos().get(i).getPR_TIPO_PRODUCTO_STRING();
			datos[3] = P.getLista_Productos().get(i).getPR_nombre();
			datos[4] = ValorU;
			datos[5] = ValorT;
			datos[6] = P.getLista_Productos().get(i).getPR_Observacion();
			model.addRow( datos);
		}
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(model);
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Limpiar_Formulario_pedido() {
		PRODUCTO_ACTUAL = new Producto();
		comboBoxProducto.setSelectedIndex(0);
		comboBoxVariedad.removeAllItems();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		
		textValor.setText("");
		textValorTotal.setText("");
		textObservaciones.setText("");
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Calcula_totales() {
		if (PRODUCTO_ACTUAL != null	&& PEDIDO_ACTUAL.getLista_Productos() != null) {

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
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cargar_datos_Cliente(Cliente c) {
		if(c!=null){
			CLIENTE_ACTUAL = c;
			textCliente.setText(c.getNombre());
			textDomicilio.setText(c.getDomicilio());
			textTelefono.setText(c.getTelefono_Fijo());
			textDetalle.setText(c.getDetalle());
			PEDIDO_ACTUAL.setCliente(CLIENTE_ACTUAL);
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void AutocompletarCliente() {
		AutoCompleter_Cliente.removeAllItems();
		AutoCompleter_Cliente.setCaseSensitive(false);
		AutoCompleter_Cliente.setMode(0);
		AutoCompleter_Cliente.addItems(Principal_neg_int.getSvClientes().getLISTA_CLIENTES());
	}
}//---> FIN CLASE
