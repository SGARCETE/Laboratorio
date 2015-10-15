package Interfaz.JDialogs;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.SystemColor;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Negocio.Servicios.Principal_Negocio_Interfaz;

public class Interfaz_Cocina_Pantalla extends JFrame {
	private static final long serialVersionUID = 4620505346691032916L;
	private JPanel contentPane;
	private JTable tablaPedidosActuales;
	private JTable tablaProductosPedido;
	private JTextArea areaComentarios;
	private Principal_Negocio_Interfaz instancia_de_Interfaz_Principal; 
	


	public Interfaz_Cocina_Pantalla(Principal_Negocio_Interfaz principal_neg_int) {
		instancia_de_Interfaz_Principal = principal_neg_int;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 521);
		
//		setAlwaysOnTop(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		/*PARA SACARLE LOS BORDES:*/
//		this.setUndecorated(true);
		/*PARA QUE SE ABRA LO MAS GRANDE POSIBLE */
		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 375, 471);
		contentPane.add(panel);
		
		JScrollPane scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setBounds(12, 11, 350, 246);
		scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		panel.add(scrollPanePedidos);
		
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
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(12, 268, 350, 192);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBackground(SystemColor.menu);
		
		JLabel label_5 = new JLabel("Productos Restantes:");
		label_5.setBounds(12, 12, 182, 16);
		panel_2.add(label_5);
		
		JLabel label_6 = new JLabel("Pizzas:");
		label_6.setBounds(12, 50, 55, 16);
		panel_2.add(label_6);
		
		JLabel label_7 = new JLabel("48");
		label_7.setBounds(139, 50, 55, 16);
		panel_2.add(label_7);
		
		JLabel label_8 = new JLabel("Empanadas:");
		label_8.setBounds(12, 92, 88, 16);
		panel_2.add(label_8);
		
		JLabel label_9 = new JLabel("128");
		label_9.setBounds(139, 92, 55, 16);
		panel_2.add(label_9);
		
		JTextArea areaComentarios = new JTextArea();
		areaComentarios.setBounds(12, 272, 417, 186);
		areaComentarios.setWrapStyleWord(true);
		areaComentarios.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		areaComentarios.setLineWrap(true);
		areaComentarios.setColumns(10);
		areaComentarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(395, 11, 441, 460);
		contentPane.add(panel_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 11, 419, 247);
		panel_1.add(scrollPane);
		
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
		
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		textArea.setLineWrap(true);
		textArea.setColumns(10);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		textArea.setBounds(12, 269, 419, 180);
		panel_1.add(textArea);
		

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
	
	
}//--> FIN
