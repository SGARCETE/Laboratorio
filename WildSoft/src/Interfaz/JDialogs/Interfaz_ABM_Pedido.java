package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Interfaz.Interfaz_Principal;
import Interfaz.Swing_Extends.JTable_Pedido_Completo;
import Interfaz.Swing_Extends.Model_Pedido_Completo;
import Negocio.Modelo.Cliente;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Clientes;
import Negocio.Servicios.Servicio_Pedidos;
import Negocio.Servicios.Servicio_Productos;

import com.mxrck.autocompleter.AutoCompleterCallback;
import com.mxrck.autocompleter.TextAutoCompleter;


@SuppressWarnings("serial")
public class Interfaz_ABM_Pedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable Tabla_Pedido_Completo;
	private JScrollPane scrollPane_Pedido_Completo;
	private Servicio_Productos svProductos;
	private Servicio_Pedidos SvPedidos;
	private Servicio_Clientes sv_clientes;
	private JTextField textDetalle;
	private JTextField textDire;
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
	private JComboBox<String> comboBoxProducto;
	private JComboBox<String> comboBoxVariedad;
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	private Object[] ESTADOS;
	private JSpinner spinnerCantidad;
	
	private Producto PRODUCTO_ACTUAL = new Producto();
	private Pedido PEDIDO_ACTUAL = new Pedido();
	private Principal_Negocio_Interfaz Principal_neg_int;
	@SuppressWarnings("unused")
	private Cliente CLIENTE_ACTUAL = null; 
	
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(); /* Muestra un Double en formato Dinero. Ej: 50.5 => $50,50 */
	private SimpleDateFormat formato_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private JLabel label_NroPedido;
	private JTextField textValor;
	private JTextField textObservaciones;
	private JTextField textValorTotal;
	private JLabel label_Fecha;
	private JLabel label_ESTADO;
	private JLabel textTotal_Pedido;

	private JButton guardar; 
	private JButton btnQuitar;
	private JSpinner spinnerCantNueva;
	/**
	 * Create the dialog.
	 * @param principal_neg_int 
	 */
	
	


	
	
	public Interfaz_ABM_Pedido(Principal_Negocio_Interfaz principal_neg_int) {
		Principal_neg_int = principal_neg_int;
		sv_clientes = Principal_neg_int.getSvClientes();
		SvPedidos = Principal_neg_int.getSvPedidos();
		svProductos = Principal_neg_int.getSvProductos();
		ESTADOS = SvPedidos.getTodos_los_estados();
		
		setTitle("ABM Pedido");
		setBounds(100, 100, 1179, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane_Pedido_Completo = new JScrollPane();
		scrollPane_Pedido_Completo.setBounds(349
				, 108, 469, 282);
		contentPanel.add(scrollPane_Pedido_Completo);
		
		JLabel lblNumeroPedido = new JLabel("N\u00BA Pedido");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblNumeroPedido.setBounds(6, 10, 124, 43);
		contentPanel.add(lblNumeroPedido);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(830, 108, 323, 185);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblDireccion = new JLabel("Cliente");
		lblDireccion.setBounds(22, 16, 106, 25);
		panel.add(lblDireccion);
		
		textCliente.setBackground(new Color(240, 255, 240));
		textCliente.setBounds(22, 42, 291, 25);
		panel.add(textCliente);
		textCliente.setColumns(10);
		
		textDetalle = new JTextField();
		textDetalle.setBackground(new Color(240, 255, 240));
		textDetalle.setBounds(22, 148, 145, 25);
		panel.add(textDetalle);
		textDetalle.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(22, 122, 106, 25);
		panel.add(lblDetalle);
		
		JLabel lblDireccion_1 = new JLabel("Direccion");
		lblDireccion_1.setBounds(22, 72, 106, 25);
		panel.add(lblDireccion_1);
		
		textDire = new JTextField();
		textDire.setBackground(new Color(240, 255, 240));
		textDire.setBounds(22, 98, 291, 25);
		panel.add(textDire);
		textDire.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBackground(new Color(240, 255, 240));
		textTelefono.setBounds(177, 148, 136, 25);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(177, 122, 106, 25);
		panel.add(lblTelefono);
		
		label_NroPedido = new JLabel("");
		label_NroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		label_NroPedido.setOpaque(true);
		label_NroPedido.setForeground(new Color(60, 179, 113));
		label_NroPedido.setBackground(new Color(240, 255, 240));
		label_NroPedido.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_NroPedido.setBounds(142, 11, 149, 42);
		contentPanel.add(label_NroPedido);
		
		label_Fecha = new JLabel("");
		label_Fecha.setOpaque(true);
		label_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
		label_Fecha.setForeground(new Color(60, 179, 113));
		label_Fecha.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_Fecha.setBackground(new Color(240, 255, 240));
		label_Fecha.setBounds(474, 11, 167, 42);
		contentPanel.add(label_Fecha);
		
		JPanel panelPedido = new JPanel();
		panelPedido.setLayout(null);
		panelPedido.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPedido.setBackground(Color.WHITE);
		panelPedido.setBounds(6, 108, 331, 282);
		contentPanel.add(panelPedido);
		
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
		
		JButton button = new JButton("Agregar");
		button.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/Check-3-icon16.png")));
		button.setBackground(Color.WHITE);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_al_Pedido();
			}
		});
		button.setBounds(117, 225, 100, 30);
		panelPedido.add(button);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblFecha.setBounds(338, 11, 124, 43);
		contentPanel.add(lblFecha);
		
		JLabel lblNumero = new JLabel("Estado");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblNumero.setBounds(6, 65, 124, 30);
		contentPanel.add(lblNumero);
		
		label_ESTADO = new JLabel("");
		label_ESTADO.setOpaque(true);
		label_ESTADO.setHorizontalAlignment(SwingConstants.CENTER);
		label_ESTADO.setForeground(new Color(60, 179, 113));
		label_ESTADO.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_ESTADO.setBackground(new Color(240, 248, 255));
		label_ESTADO.setBounds(142, 66, 149, 30);
		contentPanel.add(label_ESTADO);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblTotal_1.setBounds(338, 65, 124, 30);
		contentPanel.add(lblTotal_1);
		
		textTotal_Pedido = new JLabel("");
		textTotal_Pedido.setOpaque(true);
		textTotal_Pedido.setHorizontalAlignment(SwingConstants.CENTER);
		textTotal_Pedido.setForeground(new Color(60, 179, 113));
		textTotal_Pedido.setFont(new Font("SansSerif", Font.BOLD, 28));
		textTotal_Pedido.setBackground(new Color(240, 248, 255));
		textTotal_Pedido.setBounds(474, 66, 167, 30);
		contentPanel.add(textTotal_Pedido);
		
		JPanel panelModificacionPR = new JPanel();
		panelModificacionPR.setBorder(new TitledBorder(null, "Producto", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelModificacionPR.setBackground(Color.WHITE);
		panelModificacionPR.setBounds(828, 304, 325, 86);
		contentPanel.add(panelModificacionPR);
		panelModificacionPR.setLayout(null);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.setBounds(210, 24, 91, 38);
		panelModificacionPR.add(btnQuitar);
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quitar_al_Pedido();
			}
		});
		btnQuitar.setIcon(new ImageIcon(Interfaz_Principal.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		
		guardar = new JButton("Modificar");
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int posicion = Tabla_Pedido_Completo.getSelectedRow();
				int cantidad = (int) spinnerCantNueva.getValue();
				PEDIDO_ACTUAL.getLista_Productos().get(posicion).setCantidad(cantidad);
				Tabla_Pedido_Completo.setValueAt(cantidad, posicion, 1);
			}
		});
		guardar.setBounds(93, 24, 107, 38);
		panelModificacionPR.add(guardar);
		guardar.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		
		spinnerCantNueva = new JSpinner();
		spinnerCantNueva.setBounds(20, 31, 55, 25);
		panelModificacionPR.add(spinnerCantNueva);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(60, 179, 113));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Guardar");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SvPedidos.modificar_pedido(PEDIDO_ACTUAL);
				ArrayList<Producto> lista = PEDIDO_ACTUAL.getLista_Productos();
				SvPedidos.eliminar_producto_del_pedido(PEDIDO_ACTUAL);
				PEDIDO_ACTUAL.setLista_Productos(lista);
				SvPedidos.agregar_producto_al_pedido(PEDIDO_ACTUAL);
				//Principal_neg_int.getInstancia_Interfaz_Principal().actualizarCocina();
				// TODO
				dispose();
			}
		});
		okButton.setBackground(Color.WHITE);
		okButton.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

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


	private void Quitar_al_Pedido() {
//		 LO QUITA DE LA LISTA DE PEDIDO
		 if(!PEDIDO_ACTUAL.getLista_Productos().isEmpty()){
			 if (Tabla_Pedido_Completo.getSelectedRow() != -1){
				 String Variedad = (String) Tabla_Pedido_Completo.getValueAt((Integer)Tabla_Pedido_Completo.getSelectedRow(),3);
				 for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
						 if(PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_nombre().equals(Variedad)){
							PEDIDO_ACTUAL.getLista_Productos().remove(i);
						 }
					 } 
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


	private void iniciarParametros() {
		
	
			
		// Rellena el combobox de Tipos de productos
		ArrayList<String> ListaProductos = svProductos.getLista_Productos();
		
		comboBoxProducto.addItem("Seleccione el tipo de producto");
		for (int i = 0; i < ListaProductos.size(); i++) {
			comboBoxProducto.addItem(ListaProductos.get(i));
		}
		
		AutocompletarCliente();
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> MODIFICAR >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public void setPedido_a_modificar(Integer Numero_pedido_modificar) {
		// traigo el pedido con el id Numero_pedido_modificar
		PEDIDO_ACTUAL = SvPedidos.get_pedido(Numero_pedido_modificar);
		
		// si el pedido no es nulo cargos los campos del pedido
		if(PEDIDO_ACTUAL!=null){
			label_NroPedido.setText(PEDIDO_ACTUAL.getNumero_Pedido().toString());
			label_ESTADO.setText(PEDIDO_ACTUAL.getESTADO());
			textTotal_Pedido.setText(formatoImporte.format(PEDIDO_ACTUAL.getTotal()));
			label_Fecha.setText(formato_ddMMyyyy.format(PEDIDO_ACTUAL.getFecha_Hora_Pedido().getTime()));
			
			if (!label_ESTADO.getText().equals("Pendiente")){
				
				System.out.println(label_ESTADO.getText());
				textCliente.setEnabled(false);
				textDire.setEnabled(false);
				textDetalle.setEnabled(false);
				textTelefono.setEnabled(false);
				comboBoxProducto.setEnabled(false);
				comboBoxVariedad.setEnabled(false);
				spinnerCantidad.setEnabled(false);
				textValor.setEnabled(false);
				textValorTotal.setEnabled(false);
				textObservaciones.setEnabled(false);
				guardar.setEnabled(false);
				btnQuitar.setEnabled(false);
			}
			
			
		}
		
		// si el cliente no es nulo cargo los campos del cliente
		if(PEDIDO_ACTUAL.getCliente()!=null){
			textCliente.setText(PEDIDO_ACTUAL.getCliente().getNombre());
			textDetalle.setText(PEDIDO_ACTUAL.getCliente().getDetalle());
			textTelefono.setText(PEDIDO_ACTUAL.getCliente().getTelefono_Fijo());
			textDire.setText(PEDIDO_ACTUAL.getCliente().getDomicilio());
//			PEDIDO_ACTUAL.getCliente().getID_Cliente();
		}
		//AutocompletarCliente();
		
		
		
		Model_Pedido_Completo model = new Model_Pedido_Completo();
		System.out.println("SIZE LISTA PROD\n"+PEDIDO_ACTUAL.getLista_Productos().size());
		
		// Traigo todos los productos del pedido y lo pongo en la tabla
		for (int i = 0; i < PEDIDO_ACTUAL.getLista_Productos().size(); i++) {
			Object[] fila = new Object[7];
			fila[0] = model.getRowCount() + 1;
			fila[1] = PEDIDO_ACTUAL.getLista_Productos().get(i).getCantidad();
			fila[3] = PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_nombre();
			fila[2] = PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_TIPO_PRODUCTO_STRING();
			fila[4] = (PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_precio());
			fila[5] = (PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_precio()*(PEDIDO_ACTUAL.getLista_Productos().get(i).getCantidad())); 
			fila[6] = PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_Observacion();
			model.addRow(fila); 
		}
		
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(model);
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
		
		Tabla_Pedido_Completo.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent Mouse_evt) {
				if (Mouse_evt.getClickCount() == 2) {
					spinnerCantNueva.setValue((Tabla_Pedido_Completo.getValueAt(Tabla_Pedido_Completo.getSelectedRow(), 1)));
					//TODO
				}
			}
			});
	}

	
	private void Seleccion_De_Tipo_Producto() {
		if (!comboBoxProducto.getSelectedItem().toString().isEmpty()) {
			// Cargar_Variedades_del_producto(comboBoxProducto.getSelectedItem().toString());
			Lista_Variedades = svProductos.getVariedad_del_Producto(comboBoxProducto.getSelectedItem().toString());
			comboBoxVariedad.removeAllItems();
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				comboBoxVariedad.addItem(Lista_Variedades.get(i).getPR_nombre());
			}
		}
	}
	
	private void Agregar_al_Pedido() {
		if (comboBoxVariedad.getItemCount() != 0 && comboBoxProducto.getItemCount() != 0 && comboBoxProducto.getSelectedIndex()!=0) {
			// hacer que tome los datos del formulario de pedido y los agregue a
			// la tabla de pedidos LISTO
			
			Integer Cantidad = Integer.parseInt(spinnerCantidad.getValue().toString());
			PRODUCTO_ACTUAL.setCantidad(Cantidad);
			String Tipo_producto = comboBoxProducto.getSelectedItem().toString();
			String Variedad = comboBoxVariedad.getSelectedItem().toString();

			if (!Tipo_producto.isEmpty() && !Variedad.isEmpty() && Cantidad > 0) {
				Double ValorU = PRODUCTO_ACTUAL.getPR_precio();
				Double ValorT = PRODUCTO_ACTUAL.getPR_precio() * Integer.parseInt(spinnerCantidad.getValue().toString());
				String Observacion = textObservaciones.getText();
				PRODUCTO_ACTUAL.setPR_Observacion(Observacion);

				/**
				 * Esto va a un objeto pedido, el cual se usara para guardar en
				 * la base de datos
				 **/
				
				ArrayList<Producto> productos = PEDIDO_ACTUAL.getLista_Productos();
				
				boolean productoNoEsta = true;
				
				for (int i = 0; i<productos.size(); i++ ) {
					if(productos.get(i).getPR_nombre().equals(PRODUCTO_ACTUAL.getPR_nombre())){
						int cantidad  = productos.get(i).getCantidad();
						productos.get(i).setCantidad(cantidad + PRODUCTO_ACTUAL.getCantidad());
						productoNoEsta = false;
					}
				}
				
				if(productoNoEsta){
					PEDIDO_ACTUAL.agregar_un_producto(PRODUCTO_ACTUAL);
				}

				/** Esto va para la parte visual **/
				DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();				
				modelo.addRow(new Object[] { modelo.getRowCount() + 1,Cantidad, Tipo_producto, Variedad,formatoImporte.format(ValorU),formatoImporte.format(ValorT), Observacion }); 
				Tabla_Pedido_Completo.setModel(modelo); // Lo seteo en la tabla para que se vea

				/** Despues que se resetee el formulario de ingreso de pedido **/
				Limpiar_Formulario_pedido();
				Calcula_totales();
			}
		}
	}

	private void Limpiar_Formulario_pedido() {
		PRODUCTO_ACTUAL = new Producto();
		comboBoxProducto.setSelectedIndex(0);
		comboBoxVariedad.removeAllItems();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		textValor.setText("");
		textValorTotal.setText("");
		textObservaciones.setText("");
	}
	
	private void Seleccion_De_Variedad() {
		if (comboBoxVariedad.getSelectedItem() != null && !comboBoxVariedad.getSelectedItem().toString().isEmpty()) {
			// Cargar_precio_del_producto(comboBoxVariedad.getSelectedItem().toString());
			for (int i = 0; i < Lista_Variedades.size(); i++) {
				if (Lista_Variedades.get(i).getPR_nombre().equals(comboBoxVariedad.getSelectedItem().toString()))
					PRODUCTO_ACTUAL = Lista_Variedades.get(i); // SETEO EL PRODUCTO SELECCIONADO
			}
			textValor.setText(formatoImporte.format(PRODUCTO_ACTUAL.getPR_precio()));
			Calcula_totales();
		}
	}
	
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
				TOTAL_PEDIDO += PEDIDO_ACTUAL.getLista_Productos().get(i).getPR_precio();
			}
			PEDIDO_ACTUAL.setTotal(TOTAL_PEDIDO);
			textTotal_Pedido.setText(formatoImporte.format(TOTAL_PEDIDO));
		}
	}
	
	private void Cargar_datos_Cliente(Cliente c) {
		CLIENTE_ACTUAL = c;
		textDire.setText(c.getDomicilio());
		textTelefono.setText(c.getTelefono_Fijo());
		textDetalle.setText(c.getDetalle());
	}

	private void AutocompletarCliente() {
		AutoCompleter_Cliente.removeAllItems();
		AutoCompleter_Cliente.setCaseSensitive(false);
		AutoCompleter_Cliente.setMode(0);
		AutoCompleter_Cliente.addItems(Principal_neg_int.getSvClientes().getLISTA_CLIENTES());
	}

	
	
	
}
