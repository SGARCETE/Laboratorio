package Interfaz;

import java.awt.EventQueue;
import java.util.List;

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
	private JTable tablaPedidosActuales;
	private JTable tablaProductosPedido;
	private JTextArea areaComentarios;
	private static InterfazCocina INSTANCE = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCocina frame = InterfazCocina.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	public static InterfazCocina getInstance() {
        if (INSTANCE == null){
        	INSTANCE = new InterfazCocina();
        }
        return INSTANCE;
    }
	

	/**
	 * Create the frame.
	 */
	private InterfazCocina() {
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
		
		JPanel panelPedidos = new JPanel();
		panelPedidos.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelPedidos.setBounds(12, 12, 306, 164);
		panel.add(panelPedidos);
		panelPedidos.setLayout(null);
		
		JScrollPane scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPanePedidos.setBounds(12, 12, 282, 137);
		panelPedidos.add(scrollPanePedidos);
		
		tablaPedidosActuales = new JTable();
		tablaPedidosActuales.setModel(new DefaultTableModel(
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
		tablaPedidosActuales.getColumnModel().getColumn(0).setResizable(false);
		tablaPedidosActuales.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaPedidosActuales.getColumnModel().getColumn(0).setMaxWidth(80);
		tablaPedidosActuales.getColumnModel().getColumn(1).setResizable(false);
		tablaPedidosActuales.getColumnModel().getColumn(2).setResizable(false);
		tablaPedidosActuales.getColumnModel().getColumn(2).setPreferredWidth(25);
		tablaPedidosActuales.getColumnModel().getColumn(2).setMaxWidth(25);
		tablaPedidosActuales.setBounds(12, 12, 282, 140);
		tablaPedidosActuales.setRowHeight(23);
		
		scrollPanePedidos.setViewportView(tablaPedidosActuales);
		
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
		
		JPanel panelProductosPedido = new JPanel();
		panelProductosPedido.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelProductosPedido.setBounds(12, 12, 318, 163);
		panel_1.add(panelProductosPedido);
		panelProductosPedido.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 294, 139);
		panelProductosPedido.add(scrollPane);
		
		tablaProductosPedido = new JTable();
		tablaProductosPedido.setModel(new DefaultTableModel(
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
		tablaProductosPedido.getColumnModel().getColumn(0).setResizable(false);
		tablaProductosPedido.getColumnModel().getColumn(1).setResizable(false);
		tablaProductosPedido.getColumnModel().getColumn(1).setPreferredWidth(80);
		tablaProductosPedido.getColumnModel().getColumn(1).setMaxWidth(80);
		tablaProductosPedido.getColumnModel().getColumn(2).setResizable(false);
		tablaProductosPedido.getColumnModel().getColumn(2).setPreferredWidth(25);
		tablaProductosPedido.getColumnModel().getColumn(2).setMinWidth(12);
		tablaProductosPedido.getColumnModel().getColumn(2).setMaxWidth(25);
		tablaProductosPedido.setBounds(12, 12, 294, 139);
		scrollPane.setViewportView(tablaProductosPedido);
		
		JPanel comentarios = new JPanel();
		comentarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		comentarios.setBounds(12, 187, 318, 163);
		panel_1.add(comentarios);
		comentarios.setLayout(null);
		
		areaComentarios = new JTextArea();
		areaComentarios.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		areaComentarios.setBounds(12, 12, 294, 139);
		comentarios.add(areaComentarios);
		areaComentarios.setColumns(10);
		areaComentarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		areaComentarios.setLineWrap(true);
		areaComentarios.setWrapStyleWord(true);
	}
	
	public void Actualizar(List<String[]> pedidos,List<String[]> productos){
		for(int i = 0; i < 5; i++){
			if(pedidos.size() >= i){
				tablaPedidosActuales.setValueAt(pedidos.get(i)[0], i, 0);
				tablaPedidosActuales.setValueAt(pedidos.get(i)[1], i, 1);
				tablaPedidosActuales.setValueAt(pedidos.get(i)[2], i, 2);
			}
		}
		
		for(int j = 0; j < 5; j++){
			if(productos.size() >= j){
				tablaProductosPedido.setValueAt(productos.get(j)[0], j, 0);
				tablaProductosPedido.setValueAt(productos.get(j)[1], j, 1);
				tablaProductosPedido.setValueAt(productos.get(j)[2], j, 2);
			}
		}
	}
}
