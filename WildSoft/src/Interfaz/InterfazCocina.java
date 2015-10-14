package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class InterfazCocina extends JFrame {

	private JPanel contentPane;
	private JTable pedidosActuales;
	private JTable table_1;
	private JTextArea jTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCocina frame = new InterfazCocina();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazCocina() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 724, 424);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 12, 330, 361);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(12, 12, 306, 164);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPanePedidos.setBounds(12, 12, 282, 137);
		panel_2.add(scrollPanePedidos);
		
		pedidosActuales = new JTable();
		pedidosActuales.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(5), new Float(124.0f), Boolean.FALSE},
				{new Integer(4), new Float(1586.8f), Boolean.TRUE},
				{new Integer(3), new Float(348.0f), Boolean.TRUE},
				{new Integer(2), new Float(85.0f), null},
				{new Integer(1), new Float(215.0f), Boolean.FALSE},
			},
			new String[] {
				"Nro Pedido", "Precio", ""
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, Float.class, Boolean.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		pedidosActuales.getColumnModel().getColumn(0).setResizable(false);
		pedidosActuales.getColumnModel().getColumn(0).setPreferredWidth(80);
		pedidosActuales.getColumnModel().getColumn(0).setMaxWidth(80);
		pedidosActuales.getColumnModel().getColumn(1).setResizable(false);
		pedidosActuales.getColumnModel().getColumn(2).setResizable(false);
		pedidosActuales.getColumnModel().getColumn(2).setPreferredWidth(25);
		pedidosActuales.getColumnModel().getColumn(2).setMaxWidth(25);
		pedidosActuales.setBounds(12, 12, 282, 140);
		pedidosActuales.setRowHeight(23);
		
		scrollPanePedidos.setViewportView(pedidosActuales);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBounds(12, 188, 306, 164);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblProductosRestantes = new JLabel("Productos Restantes:");
		lblProductosRestantes.setBounds(12, 12, 182, 16);
		panel_3.add(lblProductosRestantes);
		
		JLabel lblPizzas = new JLabel("Pizzas:");
		lblPizzas.setBounds(12, 50, 55, 16);
		panel_3.add(lblPizzas);
		
		JLabel label = new JLabel("48");
		label.setBounds(139, 50, 55, 16);
		panel_3.add(label);
		
		JLabel lblEmpanadas = new JLabel("Empanadas:");
		lblEmpanadas.setBounds(12, 92, 88, 16);
		panel_3.add(lblEmpanadas);
		
		JLabel label_1 = new JLabel("128");
		label_1.setBounds(139, 92, 55, 16);
		panel_3.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(354, 12, 342, 361);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_4.setBounds(12, 12, 318, 163);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 294, 139);
		panel_4.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{"Pizza Napolitana", new Integer(3), Boolean.TRUE},
				{"Empanada de Carne", new Integer(48), null},
				{"Bebida CocaCola", new Integer(2), null},
			},
			new String[] {
				"Producto", "Cantidad", ""
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, Integer.class, Boolean.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_1.getColumnModel().getColumn(0).setResizable(false);
		table_1.getColumnModel().getColumn(1).setResizable(false);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_1.getColumnModel().getColumn(1).setMaxWidth(80);
		table_1.getColumnModel().getColumn(2).setResizable(false);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(25);
		table_1.getColumnModel().getColumn(2).setMinWidth(12);
		table_1.getColumnModel().getColumn(2).setMaxWidth(25);
		table_1.setBounds(12, 12, 294, 139);
		scrollPane.setViewportView(table_1);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBounds(12, 187, 318, 163);
		panel_1.add(panel_5);
		panel_5.setLayout(null);
		
		jTextArea = new JTextArea();
		jTextArea.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		jTextArea.setBounds(12, 12, 294, 139);
		panel_5.add(jTextArea);
		jTextArea.setColumns(10);
		jTextArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		jTextArea.setLineWrap(true);
		jTextArea.setWrapStyleWord(true);
	}
}
