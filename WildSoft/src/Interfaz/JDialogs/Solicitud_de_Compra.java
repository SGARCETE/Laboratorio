package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Negocio.Modelo.Proveedor;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Proveedores;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class Solicitud_de_Compra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup selector = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	JComboBox<String> comboProveedor;
	JComboBox<String> comboCategorias;
	private Servicio_Proveedores sv_proveedor;
	private ArrayList<String> Lista_Categorias;


	/**
	 * Create the dialog.
	 */
	public Solicitud_de_Compra(Principal_Negocio_Interfaz principal_neg_int) {
		
		sv_proveedor = principal_neg_int.getSvProveedores();
		
		inicializar();
		
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
				Seleccion_Categoria();
			}
		});
		comboProveedor.setBounds(617, 6, 304, 25);
		panel.add(comboProveedor);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(541, 6, 64, 25);
		panel.add(lblProveedor);
		
		textField = new JTextField();
		textField.setBounds(433, 60, 368, 23);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 61, 64, 22);
		contentPanel.add(lblCategoria);
		
		comboCategorias = new JComboBox<String>();
		comboCategorias.setBounds(117, 60, 304, 25);
		contentPanel.add(comboCategorias);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(433, 93, 368, 25);
		contentPanel.add(textField_1);
		
		JLabel lblProducto = new JLabel("Materia Primas:");
		lblProducto.setBounds(10, 93, 92, 28);
		contentPanel.add(lblProducto);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(117, 94, 304, 25);
		contentPanel.add(comboBox_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(880, 74, 57, 25);
		contentPanel.add(spinner);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(813, 79, 67, 16);
		contentPanel.add(lblCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 931, 253);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Categoria", "Nombre", "Cantidad"
			}
		));
		scrollPane.setColumnHeaderView(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>> Metodos >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	private void inicializar(){
		
		ArrayList<Proveedor> ListaProveedor = sv_proveedor.getProveedores();
		comboProveedor.addItem("Seleccione el Proveedor");
		for (int i = 0; i < ListaProveedor.size(); i++) {
			comboProveedor.addItem(ListaProveedor.get(i).getNombre());
		}
		
	}
	
	private void Seleccion_Categoria() {
		if (!comboProveedor.getSelectedItem().toString().isEmpty()) {
			Lista_Categorias = sv_proveedor.getCategoriasProveedor(comboProveedor.getSelectedItem().toString());
			comboCategorias.removeAllItems();
			for (int i = 0; i < Lista_Categorias.size(); i++) {
				comboCategorias.addItem(Lista_Categorias.get(i));
			}
		}
	}
}
