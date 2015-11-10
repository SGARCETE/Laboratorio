package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import MetAux.MetAux;
import Negocio.Modelo.Combo;
import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Producto;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Combos;
import Negocio.Servicios.Servicio_Materia_Prima;
import Negocio.Servicios.Servicio_Productos;
import Negocio.Servicios.Servicio_Proveedores;
import Negocio.Servicios.Servicio_Solicitud_compra;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class Interfaz_ABM_Combos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboProveedor;
	private JComboBox<String> comboCategorias;
	private JComboBox<String> comboProductos;
	private HashMap<String, Integer> ListaMateriaPrima = new HashMap<String, Integer>();
	private Servicio_Proveedores sv_proveedor;
	private Servicio_Combos sv_combos;
	private Servicio_Productos sv_productos;
	private Servicio_Materia_Prima sv_materiaPrima;
	private Servicio_Solicitud_compra sv_SolicitudCompra;
	private ArrayList<String> Lista_Categorias;
	private ArrayList<Producto> Lista_productos;
	private JTable tablaProductos;
	private JTextField textTotal;
	private JSpinner spinnerCantidad;
	private JLabel lblTotal;
	private Principal_Negocio_Interfaz Principal_neg_int;
	@SuppressWarnings("unused")
	private boolean esEdicion; 
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton okButton;
	private JButton cancelButton;
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();

	public Interfaz_ABM_Combos(Principal_Negocio_Interfaz principal_neg_int) {
		setTitle("Solicitud de compra");
		setResizable(false);
		
		sv_proveedor = principal_neg_int.getSvProveedores();
		sv_combos = principal_neg_int.getSvCombos();
		sv_materiaPrima = principal_neg_int.getSvMateriaPrima();
		sv_SolicitudCompra = principal_neg_int.getSvSolicitudCompra();
		sv_productos= principal_neg_int.getSvProductos();
		Principal_neg_int = principal_neg_int;
		setBounds(100, 100, 690, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 62, 81, 28);
		contentPanel.add(lblCategoria);
		
		comboCategorias = new JComboBox<String>();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccion_Categoria();
			}
		});
		comboCategorias.setBounds(92, 62, 304, 28);
		contentPanel.add(comboCategorias);
		
		JLabel lblProducto = new JLabel("Producto :");
		lblProducto.setBounds(10, 96, 81, 28);
		contentPanel.add(lblProducto);
		
		comboProductos = new JComboBox<String>();
		comboProductos.setBounds(92, 96, 304, 28);
		contentPanel.add(comboProductos);
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerCantidad.setBackground(new Color(240, 255, 255));
		spinnerCantidad.setBounds(469, 96, 57, 28);
		contentPanel.add(spinnerCantidad);
		
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(404, 96, 67, 28);
		contentPanel.add(lblCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 661, 218);
		contentPanel.add(scrollPane);
		
		tablaProductos = new JTable();
		tablaProductos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Categoria" , "Producto", "Cantidad"
			}
				) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, Integer.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		});

//		tablaMateriasPrimas.getColumnModel().getColumn(0).setResizable(false);
//		tablaMateriasPrimas.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaProductos.getColumnModel().getColumn(0).setMinWidth(60);
		tablaProductos.getColumnModel().getColumn(0).setMaxWidth(60);
		tablaProductos.getColumnModel().getColumn(3).setMinWidth(60);
		tablaProductos.getColumnModel().getColumn(3).setMaxWidth(60);
//		tablaMateriasPrimas.getColumnModel().getColumn(1).setResizable(false);
//		tablaMateriasPrimas.getColumnModel().getColumn(2).setResizable(false);
//		tablaMateriasPrimas.getColumnModel().getColumn(3).setResizable(false);
//		tablaMateriasPrimas.getColumnModel().getColumn(4).setResizable(false);
		tablaProductos.setRowHeight(18);
		
		
		
		scrollPane.setViewportView(tablaProductos);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_Materia_prima();
			}});
		btnAgregar.setBounds(536, 96, 135, 32);
		contentPanel.add(btnAgregar);
		
		lblTotal = new JLabel("TOTAL");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblTotal.setVisible(false);
		lblTotal.setBounds(502, 359, 73, 25);
		contentPanel.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setEditable(false);
		textTotal.setVisible(false);
		textTotal.setBounds(585, 359, 86, 28);
		contentPanel.add(textTotal);
		textTotal.setColumns(10);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quitar_Materia_prima();
			}
		});
		btnQuitar.setBounds(10, 351, 135, 32);
		contentPanel.add(btnQuitar);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBackground(new Color(60, 179, 113));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton("Guardar");
		okButton.setIcon(new ImageIcon(Interfaz_ABM_Combos.class.getResource("/Recursos/IMG/Save-icon24.png")));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guardar_solicitud_compra();
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
	
	private void Guardar_solicitud_compra() {
		if(tablaProductos.getRowCount()>0){
			
			sv_SolicitudCompra.AGREGAR_SOLICITUD_COMPRA(obtenerCombo());
			dispose();
		}
	}

	private void Quitar_Materia_prima() {
		int filaSeleccionada = tablaProductos.getSelectedRow();
		if(!(filaSeleccionada == -1)){
			
			ListaMateriaPrima.remove(tablaProductos.getValueAt(filaSeleccionada, 2));
			
			DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
			modelo.removeRow(filaSeleccionada);
			tablaProductos.setModel(modelo);
			
			for (int i = 0; i < tablaProductos.getRowCount(); i++) {
				tablaProductos.setValueAt(i+1, i, 0);
			}
		}
	}

	private void Agregar_Materia_prima() {
		Integer posicionMateriaPrimaActual = ListaMateriaPrima.get(comboProductos.getSelectedItem().toString());
		if(posicionMateriaPrimaActual == null){
			String[] arreglo = { String.valueOf(tablaProductos.getRowCount()+1), comboCategorias.getSelectedItem().toString(),
					comboProductos.getSelectedItem().toString(), String.valueOf(spinnerCantidad.getValue())};
			DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
			modelo.addRow(arreglo);
			tablaProductos.setModel(modelo);
			ListaMateriaPrima.put(comboProductos.getSelectedItem().toString(), tablaProductos.getRowCount()-1);
			spinnerCantidad.setValue(1);
		}else{
			int cantidadSpinner = (int) spinnerCantidad.getValue();
			int cantidadTabla = Integer.parseInt((String) tablaProductos.getValueAt(posicionMateriaPrimaActual, 3));
			int cantidadNueva = cantidadTabla + cantidadSpinner;
			tablaProductos.setValueAt( String.valueOf(cantidadNueva), posicionMateriaPrimaActual, 3);
			spinnerCantidad.setValue(1);
		}
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>> Metodos >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void inicializar(){
		llenar_categorias();
		esEdicion = false;
	}
	
	

	
	
	private void llenar_categorias(){
		Lista_Categorias = sv_productos.getLista_Productos();
		comboCategorias.removeAllItems();
		comboCategorias.addItem("Seleccione el tipo de producto");
		for (int i = 0; i < Lista_Categorias.size(); i++) {
				comboCategorias.addItem(Lista_Categorias.get(i));
					}
				}
	
	
	
		
		
	private void Seleccion_Categoria() {
		if (comboCategorias.getSelectedIndex()!=-1) {
			Lista_productos= sv_productos.getVariedad_del_Producto(comboCategorias.getSelectedItem().toString());
			comboProductos.removeAllItems();
			for (int i = 0; i < Lista_productos.size(); i++) {
				comboProductos.addItem(Lista_productos.get(i).getPR_nombre());
			}
		}
	}
	
	private Combo obtenerCombo() {
		Combo combo= new Combo();
		combo.setPrecio(Double.parseDouble(textTotal.getText()));
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		for (int i = 0; i < tablaProductos.getRowCount(); i++) {
			Producto pr = new Producto();
			pr.setPR_id(tablaProductos.getValueAt(i, 2));
			
			mp.setId(sv_materiaPrima.obtenerId((String)tablaProductos.getValueAt(i, 2)));
			//mp.setCategoria(1); //TODO
			mp.setCategoria_string((String) tablaProductos.getValueAt(i, 1));
			//mp.setFecha_vencimiento(fecha_vencimiento); TODO
			mp.setNombre((String) tablaProductos.getValueAt(i, 2));
			mp.setCantidad(Integer.parseInt((String) tablaProductos.getValueAt(i, 3))); 
			listaProductos.add(mp);
		}
		sc.setLista_materia_prima(listaProductos);
		return combo;
	}

	public void setSolicictud(Solicitud_compra sc) {
		comboProveedor.setSelectedItem(sc.getProveedor().getNombre());
		comboProveedor.setEnabled(false);
		comboCategorias.setEnabled(false);
		comboProductos.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnQuitar.setEnabled(false);
		for (int i = 0; i < sc.getLista_materia_prima().size(); i++) {
			ListaMateriaPrima.put(sc.getLista_materia_prima().get(i).getNombre(), sc.getLista_materia_prima().get(i).getCantidad());
			String[] arreglo = { String.valueOf(tablaProductos.getRowCount()+1),
					sc.getLista_materia_prima().get(i).getCategoria_string(),
					sc.getLista_materia_prima().get(i).getNombre(),
					String.valueOf(sc.getLista_materia_prima().get(i).getCantidad())};
			DefaultTableModel modelo = (DefaultTableModel) tablaProductos.getModel();
			modelo.addRow(arreglo);
			tablaProductos.setModel(modelo);
		}
		Double PRECIO = 0.0;
		if (sc.getPrecio()!=null && sc.getPrecio()>0) {
			PRECIO = sc.getPrecio().doubleValue();
		}
		lblTotal.setVisible(true);
		textTotal.setVisible(true);
		
		textTotal.setText(formatoImporte.format(PRECIO));
		okButton.setVisible(false);
		cancelButton.setText("Salir");
		spinnerCantidad.setEnabled(false);
		esEdicion = true;
	}

}//---> FIN CLASE
