package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Interfaz.Interfaz_Principal;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Cursor;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Interfaz_cocina extends JDialog {
	private static final long serialVersionUID = 4704325022653284338L;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaPedidosActuales;
	private JTable tablaProductosPedido;
	private JTextArea areaComentarios;
	private Interfaz_Principal instancia_de_Interfaz_Principal;
	
	
	
	public Interfaz_cocina(Interfaz_Principal inst_de_Interfaz_Principal) {
		setAlwaysOnTop(true);
		instancia_de_Interfaz_Principal = inst_de_Interfaz_Principal;
		
		setBounds(100, 100, 1001, 545);
		
//		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		
		/*PARA SACARLE LOS BORDES:*/
//		this.setUndecorated(true);
		/*PARA QUE SE ABRA LO MAS GRANDE POSIBLE */
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JLabel label = new JLabel("Productos Restantes:");
		label.setBounds(12, 12, 182, 16);
		panel_2.add(label);
		
		JLabel label_1 = new JLabel("Pizzas:");
		label_1.setBounds(12, 50, 55, 16);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("48");
		label_2.setBounds(139, 50, 55, 16);
		panel_2.add(label_2);
		
		JLabel label_3 = new JLabel("Empanadas:");
		label_3.setBounds(12, 92, 88, 16);
		panel_2.add(label_3);
		
		JLabel label_4 = new JLabel("128");
		label_4.setBounds(139, 92, 55, 16);
		panel_2.add(label_4);
		
		JScrollPane scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		tablaPedidosActuales = new JTable();
		tablaPedidosActuales.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		tablaPedidosActuales.setRowHeight(30);
		
		scrollPanePedidos.setViewportView(tablaPedidosActuales);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		
		tablaProductosPedido = new JTable();
		tablaProductosPedido.setRowHeight(30);
		tablaProductosPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		
		areaComentarios = new JTextArea();
		areaComentarios.setWrapStyleWord(true);
		areaComentarios.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		areaComentarios.setLineWrap(true);
		areaComentarios.setColumns(10);
		areaComentarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
					.addGap(7)
					.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
					.addGap(6))
		);
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
							.addComponent(areaComentarios, GroupLayout.PREFERRED_SIZE, 473, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_3.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
							.addGap(10))))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(areaComentarios, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
						.addComponent(scrollPanePedidos, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE))
					.addGap(12))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPanePedidos, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		contentPanel.setLayout(gl_contentPanel);
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
