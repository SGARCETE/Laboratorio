package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class Interfaz_Cocina_Pantalla_New extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Interfaz_Cocina_Pantalla_New dialog = new Interfaz_Cocina_Pantalla_New();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Interfaz_Cocina_Pantalla_New() {
		setBounds(100, 100, 991, 484);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(10, 11, 192, 390);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblPizzas = new JLabel("Pizzas:");
		lblPizzas.setBounds(10, 11, 172, 14);
		panel.add(lblPizzas);
		
		JLabel lblNapolitana = new JLabel("Muzzarella:");
		lblNapolitana.setBounds(20, 36, 162, 14);
		panel.add(lblNapolitana);
		
		JLabel lblJamonYQueso = new JLabel("Jamon y queso:");
		lblJamonYQueso.setBounds(20, 56, 162, 14);
		panel.add(lblJamonYQueso);
		
		JLabel lblCochina = new JLabel("Cochina:");
		lblCochina.setBounds(20, 96, 162, 14);
		panel.add(lblCochina);
		
		JLabel lblJamonYMorrones = new JLabel("Jamon y morrones:");
		lblJamonYMorrones.setBounds(20, 76, 162, 14);
		panel.add(lblJamonYMorrones);
		
		JLabel lblEmpanadas = new JLabel("Empanadas:");
		lblEmpanadas.setBounds(10, 121, 172, 14);
		panel.add(lblEmpanadas);
		
		JLabel lblNewLabel = new JLabel("Carne:");
		lblNewLabel.setBounds(20, 146, 162, 14);
		panel.add(lblNewLabel);
		
		JLabel lblVerdura = new JLabel("Verdura:");
		lblVerdura.setBounds(20, 166, 162, 14);
		panel.add(lblVerdura);
		
		JLabel lblHumita = new JLabel("Humita:");
		lblHumita.setBounds(20, 186, 162, 14);
		panel.add(lblHumita);
		
		JLabel lblPollo = new JLabel("Pollo:");
		lblPollo.setBounds(20, 206, 162, 14);
		panel.add(lblPollo);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBounds(212, 11, 753, 390);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class
			};
			@SuppressWarnings("unchecked")
			public Class<String> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.setBounds(10, 11, 733, 368);
		panel_1.add(table);
		
		table.setValueAt("Pedido 1", 0, 0);
		table.setValueAt("                          Pizza: Napolitana 8", 1, 0);
		table.setValueAt("                          Sin aceitunas", 2, 0);
		table.setValueAt("                          Empanadas: Carne 16", 3, 0);
		table.setValueAt(" ", 4, 0);
		table.setValueAt("Pedido 2", 5, 0);
		table.setValueAt("                          Empanadas: Humita 12", 6, 0);
		table.setValueAt("                          Empanadas: Pollo 36", 7, 0);
		table.setValueAt("                          Empanadas: Verduras 12", 8, 0);
		table.setValueAt(" ", 9, 0);
		table.setValueAt("Pedido 3", 10, 0);
		table.setValueAt("                          Pizza: Jamon y Morrones 3", 11, 0);
		table.setValueAt(" ", 12, 0);
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
