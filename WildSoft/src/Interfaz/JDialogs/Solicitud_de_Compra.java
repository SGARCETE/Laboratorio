package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

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

@SuppressWarnings("serial")
public class Solicitud_de_Compra extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup selector = new ButtonGroup();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Solicitud_de_Compra dialog = new Solicitud_de_Compra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Solicitud_de_Compra() {
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
		rdbtnCombo.setBounds(38, 7, 147, 23);
		panel.add(rdbtnCombo);
		
		JRadioButton rdbtnSearch = new JRadioButton("Buscar elementos");
		selector.add(rdbtnSearch);
		rdbtnSearch.setBounds(233, 7, 191, 23);
		panel.add(rdbtnSearch);
		
		JLabel lblProveedor = new JLabel("Proveedor:");
		lblProveedor.setBounds(10, 94, 64, 25);
		contentPanel.add(lblProveedor);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(96, 94, 304, 25);
		contentPanel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(410, 59, 388, 23);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblCategoria = new JLabel("Categoria:");
		lblCategoria.setBounds(10, 59, 64, 22);
		contentPanel.add(lblCategoria);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(96, 59, 304, 25);
		contentPanel.add(comboBox_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(410, 93, 388, 25);
		contentPanel.add(textField_1);
		
		JLabel lblProducto = new JLabel("Producto:");
		lblProducto.setBounds(10, 128, 64, 28);
		contentPanel.add(lblProducto);
		
		JComboBox<String> comboBox_2 = new JComboBox<String>();
		comboBox_2.setBounds(96, 130, 304, 25);
		contentPanel.add(comboBox_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(410, 129, 388, 25);
		contentPanel.add(textField_2);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(880, 93, 57, 25);
		contentPanel.add(spinner);
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		lblCantidad.setBounds(808, 98, 67, 16);
		contentPanel.add(lblCantidad);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 167, 927, 211);
		contentPanel.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Categoria", "Nombre", "Cantidad"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
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
}
