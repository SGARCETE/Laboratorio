package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
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


@SuppressWarnings("serial")
public class Solicitud_de_Compra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup selector = new ButtonGroup();
	JComboBox<String> comboProveedor;
	JComboBox<String> comboCategorias;
	JComboBox<String> comboMateriaPrima;
	HashMap<String, Integer> ListaMateriaPrima = new HashMap<String, Integer>();
	private Servicio_Proveedores sv_proveedor;
	private Servicio_Materia_Prima sv_materiaPrima;
	private Servicio_Solicitud_compra sv_SolicitudCompra;
	private ArrayList<String> Lista_Categorias;
	private ArrayList<Materia_Prima> Lista_MateriasPrimas;
	private JTable tablaMateriasPrimas;
	private JTextField textField;
	private JSpinner spinnerCantidad;

	/**
	 * Create the dialog.
	 */
	public Solicitud_de_Compra(Principal_Negocio_Interfaz principal_neg_int) {
		
		sv_proveedor = principal_neg_int.getSvProveedores();
		sv_materiaPrima = principal_neg_int.getSvMateriaPrima();
		sv_SolicitudCompra = principal_neg_int.getSvSolicitudCompra();
		
		setBounds(100, 100, 963, 466);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 927, 37);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JRadioButton rdbtnCombo = new JRadioButton("Seleccionar por lista");
		selector.add(rdbtnCombo);
		rdbtnCombo.setBounds(6, 7, 147, 23);
		panel.add(rdbtnCombo);
		
		JRadioButton rdbtnSearch = new JRadioButton("Buscar elementos");
		selector.add(rdbtnSearch);
		rdbtnSearch.setBounds(165, 7, 191, 23);
		panel.add(rdbtnSearch);
		
		comboProveedor = new JComboBox<String>();
		comboProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Seleccion_Proveedor();
			}
		});
		comboProveedor.setBounds(617, 6, 304, 25);
		panel.add(comboProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(541, 6, 64, 25);
		panel.add(lblProveedor);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 61, 64, 22);
		contentPanel.add(lblCategoria);
		
		comboCategorias = new JComboBox<String>();
		comboCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Seleccion_Categoria();
			}
		});
		comboCategorias.setBounds(117, 60, 304, 25);
		contentPanel.add(comboCategorias);
		
		JLabel lblProducto = new JLabel("Materia Primas:");
		lblProducto.setBounds(10, 93, 92, 28);
		contentPanel.add(lblProducto);
		
		comboMateriaPrima = new JComboBox<String>();
		comboMateriaPrima.setBounds(117, 94, 304, 25);
		contentPanel.add(comboMateriaPrima);
		
		spinnerCantidad = new JSpinner();
		spinnerCantidad.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spinnerCantidad.setBackground(new Color(240, 255, 255));
		spinnerCantidad.setBounds(497, 77, 57, 25);
		contentPanel.add(spinnerCantidad);
		
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(432, 78, 67, 25);
		contentPanel.add(lblCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 130, 931, 218);
		contentPanel.add(scrollPane);
		
		tablaMateriasPrimas = new JTable();
		tablaMateriasPrimas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Categoria" , "Materia Prima", "Cantidad"
			}
		));
		scrollPane.setViewportView(tablaMateriasPrimas);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer posicionMateriaPrimaActual = ListaMateriaPrima.get(comboMateriaPrima.getSelectedItem().toString());
				if(posicionMateriaPrimaActual == null){
					String[] arreglo = { String.valueOf(tablaMateriasPrimas.getRowCount()+1), comboCategorias.getSelectedItem().toString(),
							comboMateriaPrima.getSelectedItem().toString(), String.valueOf(spinnerCantidad.getValue())};
					DefaultTableModel modelo = (DefaultTableModel) tablaMateriasPrimas.getModel();
					modelo.addRow(arreglo);
					tablaMateriasPrimas.setModel(modelo);
					ListaMateriaPrima.put(comboMateriaPrima.getSelectedItem().toString(), tablaMateriasPrimas.getRowCount()-1);
				}else{
					int cantidadSpinner = (int) spinnerCantidad.getValue();
					int cantidadTabla = Integer.parseInt((String) tablaMateriasPrimas.getValueAt(posicionMateriaPrimaActual, 3));
					int cantidadNueva = cantidadTabla + cantidadSpinner;
					tablaMateriasPrimas.setValueAt( String.valueOf(cantidadNueva), posicionMateriaPrimaActual, 3);
				}
				
			}});
		btnAgregar.setBounds(579, 73, 92, 32);
		contentPanel.add(btnAgregar);
		
		JLabel lblNewLabel = new JLabel("TOTAL     $");
		lblNewLabel.setVisible(false);
		lblNewLabel.setBounds(782, 359, 59, 25);
		contentPanel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setVisible(false);
		textField.setBounds(851, 359, 86, 28);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		btnQuitar.setBounds(675, 73, 97, 32);
		contentPanel.add(btnQuitar);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Agregar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						sv_SolicitudCompra.agregarSolicitudCompra(obtenerSolicitud());
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		inicializar();
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>> Metodos >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void inicializar(){
		Cargar_ComboBox_Todos_los_proveedores();
		
	}
	
	private void Cargar_ComboBox_Todos_los_proveedores() {
		ArrayList<Proveedor> ListaProveedor = sv_proveedor.getProveedores();
		comboProveedor.addItem("Seleccione el Proveedor");
		for (int i = 0; i < ListaProveedor.size(); i++) {
			comboProveedor.addItem(ListaProveedor.get(i).getNombre());
		}
	}

	private void Seleccion_Proveedor() {
		if (!comboProveedor.getSelectedItem().toString().isEmpty()) {
			Lista_Categorias = sv_proveedor.getCategoriasProveedor(comboProveedor.getSelectedItem().toString());
			System.out.println("Tamaño lista de "+ comboProveedor.getSelectedItem().toString()+" es de "+Lista_Categorias.size());// TODO
			
			comboCategorias.removeAllItems();
			for (int i = 0; i < Lista_Categorias.size(); i++) {
				comboCategorias.addItem(Lista_Categorias.get(i));
			}
		}
	}
	
	private void Seleccion_Categoria() {
		if (comboCategorias.getSelectedIndex()!=-1) {
			Lista_MateriasPrimas = sv_materiaPrima.getVARIEDAD_Materia_Prima(comboCategorias.getSelectedItem().toString());
			comboMateriaPrima.removeAllItems();
			for (int i = 0; i < Lista_MateriasPrimas.size(); i++) {
				comboMateriaPrima.addItem(Lista_MateriasPrimas.get(i).getNombre());
			}
		}
	}
	
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
			//mp.setCategoria(1); //TODO
			mp.setCategoria_string((String) tablaMateriasPrimas.getValueAt(i, 1));
			//mp.setFecha_vencimiento(fecha_vencimiento); TODO
			mp.setNombre((String) tablaMateriasPrimas.getValueAt(i, 2));
			mp.setCantidad(Integer.parseInt((String) tablaMateriasPrimas.getValueAt(i, 3))); 
			listaMateriaPrima.add(mp);
		}
		sc.setLista_materia_prima(listaMateriaPrima);
		return sc;
	}

}//---> FIN CLASE
