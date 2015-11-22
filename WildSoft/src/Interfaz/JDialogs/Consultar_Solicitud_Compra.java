package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

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
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import MetAux.MetAux;
import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Materia_Prima;
import Negocio.Servicios.Servicio_Proveedores;
import Negocio.Servicios.Servicio_Solicitud_compra;

public class Consultar_Solicitud_Compra extends JDialog {
	
	private static final long serialVersionUID = 5649321082689428061L;
	private final JPanel contentPanel = new JPanel();
	public JComboBox<String> comboProveedor;
	public JComboBox<String> comboCategorias;
	public JComboBox<String> comboMateriaPrima;
	private HashMap<String, Integer> ListaMateriaPrima = new HashMap<String, Integer>();
	private Servicio_Proveedores sv_proveedor;
	private Servicio_Materia_Prima sv_materiaPrima;
	private Servicio_Solicitud_compra sv_SolicitudCompra;
	private ArrayList<String> Lista_Categorias;
	private ArrayList<Materia_Prima> Lista_MateriasPrimas;
	private JTable tablaMateriasPrimas;
	private JTextField textTotal;
	private JSpinner spinnerCantidad;
	private JLabel lblTotal;
	private Principal_Negocio_Interfaz Principal_neg_int;
	private boolean esEdicion; 
	private JButton btnQuitar;
	private JButton btnAgregar;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel lblID;

	@SuppressWarnings("serial")
	public Consultar_Solicitud_Compra(Principal_Negocio_Interfaz principal_neg_int) {
		setTitle("Solicitud de compra");
		setResizable(false);
		Principal_neg_int = principal_neg_int;
		sv_proveedor = Principal_neg_int.getSvProveedores();
		sv_materiaPrima = Principal_neg_int.getSvMateriaPrima();
		sv_SolicitudCompra = Principal_neg_int.getSvSolicitudCompra();
		
		setBounds(100, 100, 690, 495);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 661, 37);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		comboProveedor = new JComboBox<String>();
		comboProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_Proveedor();
			}
		});
		comboProveedor.setBounds(191, 6, 304, 25);
		panel.add(comboProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(115, 6, 64, 25);
		panel.add(lblProveedor);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(20, 62, 86, 28);
		contentPanel.add(lblCategoria);
		
		comboCategorias = new JComboBox<String>();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccion_Categoria();
			}
		});
		comboCategorias.setBounds(111, 62, 285, 28);
		contentPanel.add(comboCategorias);
		
		JLabel lblProducto = new JLabel("Materia Primas:");
		lblProducto.setBounds(20, 96, 86, 28);
		contentPanel.add(lblProducto);
		
		comboMateriaPrima = new JComboBox<String>();
		comboMateriaPrima.setBounds(111, 96, 285, 28);
		contentPanel.add(comboMateriaPrima);
		
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
		
		tablaMateriasPrimas = new JTable();
		tablaMateriasPrimas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Categoria" , "Materia Prima", "Cantidad"
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

		tablaMateriasPrimas.getColumnModel().getColumn(0).setMinWidth(60);
		tablaMateriasPrimas.getColumnModel().getColumn(0).setMaxWidth(60);
		tablaMateriasPrimas.getColumnModel().getColumn(3).setMinWidth(60);
		tablaMateriasPrimas.getColumnModel().getColumn(3).setMaxWidth(60);
		tablaMateriasPrimas.setRowHeight(18);
		
		
		
		scrollPane.setViewportView(tablaMateriasPrimas);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.setIcon(new ImageIcon(ADM_Solicitud_Compra.class.getResource("/Recursos/IMG/add-1-icon24.png")));
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
		textTotal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textTotal.setVisible(false);
		textTotal.setBounds(585, 359, 86, 28);
		contentPanel.add(textTotal);
		textTotal.setColumns(10);
		
		btnQuitar = new JButton("Quitar");
		btnQuitar.setBackground(Color.WHITE);
		btnQuitar.setIcon(new ImageIcon(ADM_Solicitud_Compra.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Quitar_Materia_prima();
			}
		});
		btnQuitar.setBounds(10, 351, 135, 32);
		contentPanel.add(btnQuitar);
		
		lblID = new JLabel("0.0");
		lblID.setVisible(false);
		lblID.setBounds(406, 69, 46, 14);
		contentPanel.add(lblID);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		buttonPane.setBackground(new Color(60, 179, 113));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		okButton = new JButton(" Guardar ");
		okButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		okButton.setHorizontalTextPosition(SwingConstants.CENTER);
		okButton.setBackground(Color.WHITE);
		okButton.setIcon(new ImageIcon(ADM_Solicitud_Compra.class.getResource("/Recursos/IMG/Save-icon24.png")));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Guardar_solicitud_compra();
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("    Salir     ");
		cancelButton.setVerticalTextPosition(SwingConstants.BOTTOM);
		cancelButton.setHorizontalTextPosition(SwingConstants.CENTER);
		cancelButton.setBackground(Color.WHITE);
		cancelButton.setIcon(new ImageIcon(ADM_Solicitud_Compra.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		inicializar();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Guardar_solicitud_compra() {
		if (esEdicion) {
			Solicitud_compra sc = obtenerSolicitud();
			sc.setId(Integer.valueOf(lblID.getText()));
			sv_SolicitudCompra.MODIFICAR_SOLICITUD_COMPRA(sc);
			dispose();
		} else {
			if (tablaMateriasPrimas.getRowCount() > 0) {
				sv_SolicitudCompra.AGREGAR_SOLICITUD_COMPRA(obtenerSolicitud());
				dispose();
			}
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Quitar_Materia_prima() {
		int filaSeleccionada = tablaMateriasPrimas.getSelectedRow();
		if(!(filaSeleccionada == -1)){
			
			ListaMateriaPrima.remove(tablaMateriasPrimas.getValueAt(filaSeleccionada, 2));
			
			DefaultTableModel modelo = (DefaultTableModel) tablaMateriasPrimas.getModel();
			modelo.removeRow(filaSeleccionada);
			tablaMateriasPrimas.setModel(modelo);
			
			for (int i = 0; i < tablaMateriasPrimas.getRowCount(); i++) {
				tablaMateriasPrimas.setValueAt(i+1, i, 0);
			}
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Agregar_Materia_prima() {
		Integer posicionMateriaPrimaActual = ListaMateriaPrima.get(comboMateriaPrima.getSelectedItem().toString());
		if(posicionMateriaPrimaActual == null){
			String[] arreglo = { String.valueOf(tablaMateriasPrimas.getRowCount()+1), comboCategorias.getSelectedItem().toString(),
					comboMateriaPrima.getSelectedItem().toString(), String.valueOf(spinnerCantidad.getValue())};
			DefaultTableModel modelo = (DefaultTableModel) tablaMateriasPrimas.getModel();
			modelo.addRow(arreglo);
			tablaMateriasPrimas.setModel(modelo);
			ListaMateriaPrima.put(comboMateriaPrima.getSelectedItem().toString(), tablaMateriasPrimas.getRowCount()-1);
			spinnerCantidad.setValue(1);
		}else{
			int cantidadSpinner = (int) spinnerCantidad.getValue();
			int cantidadTabla = Integer.parseInt((String) tablaMateriasPrimas.getValueAt(posicionMateriaPrimaActual, 3));
			int cantidadNueva = cantidadTabla + cantidadSpinner;
			tablaMateriasPrimas.setValueAt( String.valueOf(cantidadNueva), posicionMateriaPrimaActual, 3);
			spinnerCantidad.setValue(1);
		}
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>> Metodos >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void inicializar(){
		Cargar_ComboBox_Todos_los_proveedores();
		esEdicion = false;
		Solicitud_compra sc= obtenerSolicitud();
		if (sc.getEstado()== "Pagada"){
		 	comboProveedor.isEnabled();
			comboMateriaPrima.isEnabled();
			comboCategorias.isEnabled();
			spinnerCantidad.isEnabled();
			btnAgregar.isEnabled();
			btnQuitar.isEnabled();
			okButton.isEnabled();
			
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cargar_ComboBox_Todos_los_proveedores() {
		ArrayList<Proveedor> ListaProveedor = sv_proveedor.getProveedores();
		comboProveedor.addItem("Seleccione el Proveedor");
		for (int i = 0; i < ListaProveedor.size(); i++) {
			comboProveedor.addItem(ListaProveedor.get(i).getNombre());
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Seleccion_Proveedor() {
		if (!comboProveedor.getSelectedItem().toString().isEmpty()) {
			if(tablaMateriasPrimas.getRowCount() > 0){
				int RESPUESTA = JOptionPane.showConfirmDialog(null,"Si cambia de proveedor perdera las materia primas cargadas \n ¿Desea continuar?","TITULO DE LA VENTANA",JOptionPane.OK_CANCEL_OPTION);
				if(RESPUESTA == JOptionPane.OK_OPTION){
					DefaultTableModel modelo = (DefaultTableModel) tablaMateriasPrimas.getModel();
					modelo.setRowCount(0);
					tablaMateriasPrimas.setModel(modelo);
					ListaMateriaPrima = new HashMap<String, Integer>();
				}
			}
			
			comboMateriaPrima.removeAllItems();
			Lista_Categorias = sv_proveedor.getCategoriasProveedor(comboProveedor.getSelectedItem().toString());
			
			comboCategorias.removeAllItems();
			for (int i = 0; i < Lista_Categorias.size(); i++) {
				comboCategorias.addItem(Lista_Categorias.get(i));
			}
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Seleccion_Categoria() {
		if (comboCategorias.getSelectedIndex()!=-1) {
			Lista_MateriasPrimas = sv_materiaPrima.getVARIEDAD_Materia_Prima(comboCategorias.getSelectedItem().toString());
			comboMateriaPrima.removeAllItems();
			for (int i = 0; i < Lista_MateriasPrimas.size(); i++) {
				comboMateriaPrima.addItem(Lista_MateriasPrimas.get(i).getNombre());
			}
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private Solicitud_compra obtenerSolicitud() {
		Solicitud_compra sc = new Solicitud_compra();
		sc.setEstado("Pendiente");
		sc.setFecha(MetAux.toDate(Calendar.getInstance()));
		Proveedor p = sv_proveedor.getProveedor(comboProveedor.getSelectedItem().toString());
		sc.setProveedor(p);
		ArrayList<Materia_Prima> listaMateriaPrima = new ArrayList<Materia_Prima>();
		for (int i = 0; i < tablaMateriasPrimas.getRowCount(); i++) {
			Materia_Prima mp = new Materia_Prima();
			mp.setId(sv_materiaPrima.obtenerId((String)tablaMateriasPrimas.getValueAt(i, 2)));
			mp.setCategoria_string((String) tablaMateriasPrimas.getValueAt(i, 1));
			mp.setNombre((String) tablaMateriasPrimas.getValueAt(i, 2));
			mp.setCantidad(Integer.parseInt((String) tablaMateriasPrimas.getValueAt(i, 3))); 
			listaMateriaPrima.add(mp);
			
			if (!textTotal.getText().isEmpty()){
				sc.setPrecio(Double.parseDouble(textTotal.getText()));}
		}
		sc.setLista_materia_prima(listaMateriaPrima);
		return sc;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void setSolicictud(Solicitud_compra sc) {
		comboProveedor.setSelectedItem(sc.getProveedor().getNombre());
		comboProveedor.setEnabled(false); 
		comboCategorias.setEnabled(false);
		comboMateriaPrima.setEnabled(false);
		btnQuitar.setEnabled(false);
		textTotal.setEnabled(false);
		spinnerCantidad.setEnabled(false);
		btnAgregar.setEnabled(false);
		
		for (int i = 0; i < sc.getLista_materia_prima().size(); i++) {
			String[] arreglo = { String.valueOf(tablaMateriasPrimas.getRowCount()+1),
					sc.getLista_materia_prima().get(i).getCategoria_string(),
					sc.getLista_materia_prima().get(i).getNombre(),
					String.valueOf(sc.getLista_materia_prima().get(i).getCantidad())};
			DefaultTableModel modelo = (DefaultTableModel) tablaMateriasPrimas.getModel();
			modelo.addRow(arreglo);
			tablaMateriasPrimas.setModel(modelo);
			ListaMateriaPrima.put(comboMateriaPrima.getSelectedItem().toString(), tablaMateriasPrimas.getRowCount()-1);
		}

		Double PRECIO = 0.0;
		if (sc.getPrecio()!=null && sc.getPrecio()>0) {
			PRECIO = sc.getPrecio().doubleValue();
		}
		lblTotal.setVisible(true);
		textTotal.setVisible(true);
		textTotal.setText(PRECIO.toString());
		cancelButton.setText("Salir");
		esEdicion = true;
		spinnerCantidad.setValue(1);
		lblID.setText(sc.getId().toString());
	}
}
