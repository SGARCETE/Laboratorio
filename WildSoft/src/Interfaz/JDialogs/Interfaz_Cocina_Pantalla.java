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

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Interfaz_Cocina_Pantalla extends JFrame {
	private static final long serialVersionUID = 4620505346691032916L;
	private JPanel contentPane;
	private JTable tablaPedidosActuales;
	private JTable tablaProductosPedido;
	private JTextArea areaComentarios;
	private Principal_Negocio_Interfaz instancia_de_Interfaz_Principal;
	private JLabel numeroPizzas;
	private JLabel numeroEmpanadas;
	private JLabel numeroBebidas;
	private JScrollPane scrollPanePedidos;
	private JScrollPane scrollPane;


	@SuppressWarnings("serial")
	public Interfaz_Cocina_Pantalla(Principal_Negocio_Interfaz principal_neg_int) {
		instancia_de_Interfaz_Principal = principal_neg_int;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 863, 529);
		
//		setAlwaysOnTop(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		/*PARA SACARLE LOS BORDES:*/
//		this.setUndecorated(true);
		/*PARA QUE SE ABRA LO MAS GRANDE POSIBLE */
//		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		
		scrollPanePedidos = new JScrollPane();
		scrollPanePedidos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		
		tablaPedidosActuales = new JTable();
		tablaPedidosActuales.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaPedidosActuales.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Nro Pedido", "Precio", ""
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
		tablaPedidosActuales.getColumnModel().getColumn(0).setResizable(false);
		tablaPedidosActuales.getColumnModel().getColumn(0).setPreferredWidth(80);
		tablaPedidosActuales.getColumnModel().getColumn(0).setMaxWidth(80);
		tablaPedidosActuales.getColumnModel().getColumn(1).setResizable(false);
		tablaPedidosActuales.getColumnModel().getColumn(2).setResizable(false);
		tablaPedidosActuales.getColumnModel().getColumn(2).setPreferredWidth(30);
		tablaPedidosActuales.getColumnModel().getColumn(2).setMinWidth(75);
		tablaPedidosActuales.getColumnModel().getColumn(2).setMaxWidth(30);
		tablaPedidosActuales.setBounds(12, 12, 282, 140);
		tablaPedidosActuales.setRowHeight(30);
		
		scrollPanePedidos.setViewportView(tablaPedidosActuales);
		
		JPanel panel_2 = new JPanel();
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBackground(SystemColor.menu);
		
		JLabel label_5 = new JLabel("Productos Restantes:");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		label_5.setBounds(12, 12, 182, 16);
		panel_2.add(label_5);
		
		JLabel labelPizzas = new JLabel("Pizzas:");
		labelPizzas.setBounds(12, 50, 100, 16);
		panel_2.add(labelPizzas);
		
		numeroPizzas = new JLabel("");
		numeroPizzas.setBounds(139, 50, 55, 16);
		panel_2.add(numeroPizzas);
		
		JLabel labelEmpanadas = new JLabel("Empanadas:");
		labelEmpanadas.setBounds(12, 77, 128, 16);
		panel_2.add(labelEmpanadas);
		
		numeroEmpanadas = new JLabel("");
		numeroEmpanadas.setBounds(139, 77, 55, 16);
		panel_2.add(numeroEmpanadas);
		
		JLabel labelBebidas = new JLabel("Bebidas:");
		labelBebidas.setBounds(12, 104, 100, 14);
		panel_2.add(labelBebidas);
		
		numeroBebidas = new JLabel("");
		numeroBebidas.setBounds(139, 104, 46, 14);
		panel_2.add(numeroBebidas);
		
		JTextArea areaComentarios = new JTextArea();
		areaComentarios.setBounds(12, 272, 417, 186);
		areaComentarios.setWrapStyleWord(true);
		areaComentarios.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		areaComentarios.setLineWrap(true);
		areaComentarios.setColumns(10);
		areaComentarios.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		
		scrollPane = new JScrollPane();
		
		tablaProductosPedido = new JTable();
		tablaProductosPedido.setRowHeight(30);
		tablaProductosPedido.setFont(new Font("Tahoma", Font.PLAIN, 16));
		tablaProductosPedido.setModel(new DefaultTableModel(
			new Object[][] {
				{"Pizza Napolitana", null, null},
				{"Empanada de Carne", null, null},
				{"Bebida CocaCola", null, null},
			},
			new String[] {
				"Producto", "Cantidad", ""
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
		tablaProductosPedido.getColumnModel().getColumn(0).setResizable(false);
		tablaProductosPedido.getColumnModel().getColumn(1).setResizable(false);
		tablaProductosPedido.getColumnModel().getColumn(1).setPreferredWidth(80);
		tablaProductosPedido.getColumnModel().getColumn(1).setMaxWidth(80);
		tablaProductosPedido.getColumnModel().getColumn(2).setResizable(false);
		tablaProductosPedido.getColumnModel().getColumn(2).setPreferredWidth(30);
		tablaProductosPedido.getColumnModel().getColumn(2).setMinWidth(75);
		tablaProductosPedido.getColumnModel().getColumn(2).setMaxWidth(30);
		tablaProductosPedido.setBounds(12, 12, 294, 139);
		scrollPane.setViewportView(tablaProductosPedido);
		
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setText("La pizza napolitana sin aceitunas y sin ajo. Ponerle mucho oregano");
		textArea.setLineWrap(true);
		textArea.setColumns(10);
		textArea.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
					.addGap(10))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPanePedidos, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE))
					.addGap(13))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(11)
					.addComponent(scrollPanePedidos, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
					.addGap(11)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
					.addGap(11))
		);
		
		panel.setLayout(gl_panel);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
					.addGap(10)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(6))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE))
					.addGap(3))
		);
		contentPane.setLayout(gl_contentPane);
		

	}
	
	public void Actualizar(List<String[]> pedidos,List<String[]> productos, int pizzas, int empanadas, int bebidas){
				
		
		DefaultTableModel model_pedidos =  (DefaultTableModel) tablaPedidosActuales.getModel();
		DefaultTableModel model_productos = (DefaultTableModel) tablaProductosPedido.getModel();
		
		model_pedidos.setRowCount(0);
		model_productos.setRowCount(0);

		int desde = 0;
		int hasta = 5;
		if(pedidos.size() < 5){
			hasta = pedidos.size();
		}
		while (desde < hasta){
			String[] arreglo = {pedidos.get(desde)[0],pedidos.get(desde)[1],pedidos.get(desde)[2]};
			model_pedidos.addRow(arreglo);
			desde++;
		}
		
		for(int j = 0; j < productos.size(); j++){
			if(productos.size() >= j){
				String[] arreglo = {productos.get(j)[0],productos.get(j)[1],productos.get(j)[2]};
				model_productos.addRow(arreglo);
//				tablaProductosPedido.setValueAt(productos.get(j)[0], j, 0);
//				tablaProductosPedido.setValueAt(productos.get(j)[1], j, 1);
//				tablaProductosPedido.setValueAt(productos.get(j)[2], j, 2);
			}
		}
		
		numeroBebidas.setText(String.valueOf(bebidas));
		numeroEmpanadas.setText(String.valueOf(empanadas));
		numeroPizzas.setText(String.valueOf(pizzas));
		
		tablaPedidosActuales.setModel(model_pedidos);
		
		tablaProductosPedido.setModel(model_productos);

		
		scrollPanePedidos.setViewportView(tablaPedidosActuales);
		scrollPane.setViewportView(tablaProductosPedido);
		
		
		
	}
}//--> FIN
