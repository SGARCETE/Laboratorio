package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

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
import javax.swing.table.DefaultTableModel;

import MetAux.MetAux;
import Negocio.Modelo.Combo;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Combos;
import Negocio.Servicios.Servicio_Productos;

@SuppressWarnings("serial")
public class Interfaz_ABM_Combos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	private JComboBox<String> comboCategorias;
	private JComboBox<String> comboProductos;
	
	private HashMap<String, Integer> MapaProductos = new HashMap<String, Integer>();
	
	private Servicio_Productos svProductos;
	private Servicio_Combos svCombos;
	
	private JTable tablaProductosCombo;
	
	private JTextField textCombo;
	private JTextField textNombre;
	
	private JSpinner spinnerCantidad;
	
	private JLabel lblCombo;
	private JLabel lblAviso;
	
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton okButton;
	private JButton cancelButton;

	private boolean esModificacion = false;

	private JLabel lblID;
	private JTextField textTotal;

	public Interfaz_ABM_Combos(Principal_Negocio_Interfaz principal_neg_int) {
		setTitle("Administracion de Combos");
		setResizable(false);

		principal_neg_int.getSvProveedores();
		principal_neg_int.getSvCombos();
		principal_neg_int.getSvMateriaPrima();
		principal_neg_int.getSvSolicitudCompra();
		svProductos = principal_neg_int.getSvProductos();
		svCombos = principal_neg_int.getSvCombos();
		setBounds(100, 100, 690, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 47, 81, 28);
		contentPanel.add(lblCategoria);

		comboCategorias = new JComboBox<String>();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				obtenerProductosCategoria();
			}
		});
		comboCategorias.setBounds(92, 47, 304, 28);
		contentPanel.add(comboCategorias);

		JLabel lblProducto = new JLabel("Producto :");
		lblProducto.setBounds(10, 86, 81, 28);
		contentPanel.add(lblProducto);

		comboProductos = new JComboBox<String>();
		comboProductos.setBounds(92, 86, 304, 28);
		contentPanel.add(comboProductos);

		spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerCantidad.setBackground(new Color(240, 255, 255));
		spinnerCantidad.setBounds(419, 65, 46, 28);
		contentPanel.add(spinnerCantidad);

		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(419, 8, 73, 28);
		contentPanel.add(lblCantidad);

		JScrollPane PanelCombos = new JScrollPane();
		PanelCombos.setBounds(10, 130, 661, 218);
		contentPanel.add(PanelCombos);

		tablaProductosCombo = new JTable();
		tablaProductosCombo.setModel(new DefaultTableModel(new Object[][] {},new String[] { "N\u00B0", "Categoria", "Producto","Cantidad" }) {
					
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class,String.class, String.class, String.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		});

		tablaProductosCombo.getColumnModel().getColumn(0).setMinWidth(60);
		tablaProductosCombo.getColumnModel().getColumn(0).setMaxWidth(60);
		tablaProductosCombo.getColumnModel().getColumn(3).setMinWidth(60);
		tablaProductosCombo.getColumnModel().getColumn(3).setMaxWidth(60);
		tablaProductosCombo.setRowHeight(18);

		PanelCombos.setViewportView(tablaProductosCombo);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				agregarProducto();
				calcularPrecio();
			}
		});
		btnAgregar.setBounds(512, 6, 135, 32);
		contentPanel.add(btnAgregar);

		lblCombo = new JLabel("Combo:");
		lblCombo.setHorizontalAlignment(SwingConstants.CENTER);
		lblCombo.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblCombo.setBounds(502, 359, 73, 28);
		contentPanel.add(lblCombo);

		textCombo = new JTextField();
		textCombo.setBounds(585, 359, 86, 28);
		contentPanel.add(textCombo);
		textCombo.setColumns(10);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				quitarProducto();
				calcularPrecio();
			}
		});
		btnQuitar.setBounds(512, 63, 135, 32);
		contentPanel.add(btnQuitar);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 8, 46, 28);
		contentPanel.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(92, 8, 304, 28);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(10, 359, 310, 28);
		contentPanel.add(lblAviso);
		
		lblID = new JLabel("");
		lblID.setVisible(false);
		lblID.setEnabled(false);
		lblID.setBounds(607, 54, 46, 14);
		contentPanel.add(lblID);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTotal.setBounds(330, 359, 66, 28);
		contentPanel.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setColumns(10);
		textTotal.setBounds(406, 359, 86, 28);
		contentPanel.add(textTotal);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBackground(new Color(60, 179, 113));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Guardar");
		okButton.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/Save-icon24.png")));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				guardarCombo();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Salir");
		cancelButton.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		
		buttonPane.add(cancelButton);

		inicializar();
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>  Metodos  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	private void calcularPrecio() {
		Double total = 0.0;
		for (HashMap.Entry<String, Integer> mapaProductos : MapaProductos.entrySet()) {
		    String key = mapaProductos.getKey();
		    Integer value = mapaProductos.getValue();
		    total = total + (svProductos.getProducto(key).getPR_precio()*value);
		}
		textTotal.setText(total.toString());
	}
	
	private void agregarProducto() {
		String productoSeleccionado = comboProductos.getSelectedItem().toString();
		if (!MapaProductos.containsKey(comboProductos.getSelectedItem().toString())) {
			String[] arreglo = {
					String.valueOf(tablaProductosCombo.getRowCount() + 1),
					comboCategorias.getSelectedItem().toString(),
					productoSeleccionado,
					String.valueOf(spinnerCantidad.getValue()) };
			DefaultTableModel modelo = (DefaultTableModel) tablaProductosCombo.getModel();
			modelo.addRow(arreglo);
			tablaProductosCombo.setModel(modelo);
			MapaProductos.put(productoSeleccionado,(Integer) spinnerCantidad.getValue());
			spinnerCantidad.setValue(1);
		} else {
			for (int i = 0; i < tablaProductosCombo.getRowCount(); i++) {
				if (tablaProductosCombo.getValueAt(i, 2).equals(productoSeleccionado)) {
					int cantidadSpinner = (int) spinnerCantidad.getValue();
					int cantidadTabla = Integer.parseInt((String) tablaProductosCombo.getValueAt(i, 3));
					int cantidadNueva = cantidadTabla + cantidadSpinner;
					tablaProductosCombo.setValueAt(String.valueOf(cantidadNueva), i, 3);
					spinnerCantidad.setValue(1);
					MapaProductos.put(productoSeleccionado, cantidadNueva);
				}
			}
		}
	}
	
	private void inicializar() {
		MetAux.Limitar_caracteres(textNombre, 20);
		llenar_categorias();
		MetAux.SoloNumerosDecimales(textCombo);
	}
	
	public void setCombo(Combo combo) {
		for (int i = 0; i < combo.getLista_productos().size(); i++) {
			MapaProductos.put(combo.getLista_productos().get(i).getPR_nombre(), combo.getLista_productos().get(i).getCantidad());
			String[] arreglo = {
					String.valueOf(tablaProductosCombo.getRowCount() + 1),
					combo.getLista_productos().get(i).getPR_TIPO_PRODUCTO_STRING(),
					combo.getLista_productos().get(i).getPR_nombre(),
					String.valueOf(combo.getLista_productos().get(i).getCantidad()) };
			DefaultTableModel modelo = (DefaultTableModel) tablaProductosCombo.getModel();
			modelo.addRow(arreglo);
			tablaProductosCombo.setModel(modelo);
		}
		Double PRECIO = 0.0;
		if (combo.getPrecio() != null && combo.getPrecio() > 0) {
			PRECIO = combo.getPrecio().doubleValue();
		}
		lblID.setText(String.valueOf(combo.getId()));
		textNombre.setText(combo.getNombre());
		esModificacion = true;
		textCombo.setText(String.valueOf(PRECIO));
		calcularPrecio();
	}
	
	private void llenar_categorias() {
		ArrayList<String> Lista_Categorias = svProductos.getLista_Productos();
		comboCategorias.removeAllItems();
		comboCategorias.addItem("Seleccione el tipo de producto");
		for (int i = 0; i < Lista_Categorias.size(); i++) {
			if(!Lista_Categorias.get(i).equals("Combo")){
				comboCategorias.addItem(Lista_Categorias.get(i));
			}
		}
	}
	
	private void obtenerProductosCategoria() {
		if (comboCategorias.getSelectedIndex() != -1) {
			ArrayList<Producto> Lista_productos = svProductos.getVariedad_del_Producto(comboCategorias.getSelectedItem().toString());
			comboProductos.removeAllItems();
			for (int i = 0; i < Lista_productos.size(); i++) {
				comboProductos.addItem(Lista_productos.get(i).getPR_nombre());
			}
		}
	}
	
	private void guardarCombo() {
		if(comprobarCampos()){
			if(esModificacion){
				svCombos.eliminarProductosCombo(obtenerCombo().getId());
				svCombos.modificarCombo(obtenerCombo());
				svCombos.guardarProductosCombo(obtenerCombo());
				dispose();
			}else{
				Combo combo = obtenerCombo();
				svCombos.agregarCombo(combo);
				combo.setId(svCombos.obtenerIdCombo(combo.getNombre()));
				svCombos.guardarProductosCombo(combo);
				dispose();
			}	
		}
	}

	private boolean comprobarCampos() {
		lblAviso.setText("");
		if(textNombre.getText().isEmpty()){
			lblAviso.setText("El campo 'Nombre' no debe quedar vacio");
			return false;
		}else if (!(tablaProductosCombo.getRowCount() > 0)) {
			lblAviso.setText("Debe agregar al menos un producto al combo");
			return false;
		}else if (textCombo.getText().isEmpty()){
			lblAviso.setText("Debe elegir un precio para el combo");
			return false;
		}
		return true;
	}

	private void quitarProducto() {
		int filaSeleccionada = tablaProductosCombo.getSelectedRow();
		if (!(filaSeleccionada == -1)) {

			MapaProductos.remove(tablaProductosCombo.getValueAt(filaSeleccionada, 2));

			DefaultTableModel modelo = (DefaultTableModel) tablaProductosCombo.getModel();
			modelo.removeRow(filaSeleccionada);
			tablaProductosCombo.setModel(modelo);

			for (int i = 0; i < tablaProductosCombo.getRowCount(); i++) {
				tablaProductosCombo.setValueAt(i + 1, i, 0);
			}
		}
	}
	
	private Combo obtenerCombo() {
		Combo combo = new Combo();
		if(!lblID.getText().isEmpty()){
			combo.setId(Integer.parseInt(lblID.getText()));
		}
		combo.setPrecio(Double.parseDouble(textCombo.getText()));
		combo.setNombre(textNombre.getText());
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		for (int i = 0; i < tablaProductosCombo.getRowCount(); i++) {
			Producto pr = new Producto();
			
			pr.setPR_id(svProductos.getProducto((String) tablaProductosCombo.getValueAt(i, 2)).getPR_id());
			pr.setPR_TIPO_PRODUCTO_STRING((String) tablaProductosCombo.getValueAt(i,1));
			pr.setPR_nombre((String) tablaProductosCombo.getValueAt(i, 2));
			pr.setCantidad(Integer.parseInt((String) tablaProductosCombo.getValueAt(i, 3)));

			listaProductos.add(pr);
		}
		combo.setLista_productos(listaProductos);

		return combo;
	}
}// ---> FIN CLASE
