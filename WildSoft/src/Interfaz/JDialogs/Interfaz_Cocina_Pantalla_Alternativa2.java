package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class Interfaz_Cocina_Pantalla_Alternativa2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTable table_2;
	private JTable table_3;
	private JTable table_1;
	private JTable table_4;
	private JTable table_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");	// LookAndFeel por defecto: Nimbus
			Interfaz_Cocina_Pantalla_Alternativa2 dialog = new Interfaz_Cocina_Pantalla_Alternativa2();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Interfaz_Cocina_Pantalla_Alternativa2() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz_Cocina_Pantalla_Alternativa2.class.getResource("/Recursos/Pizza-icon16.png")));
		setTitle("Monitor Cocina");
		setBounds(100, 100, 983, 768);
		getContentPane().setLayout(new BorderLayout());
		
		
//		setAlwaysOnTop(true);
//		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
//		/*PARA SACARLE LOS BORDES:*/
//		this.setUndecorated(true);
//		/*PARA QUE SE ABRA LO MAS GRANDE POSIBLE */
//		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		
		
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel_11 = new JPanel();
		panel_11.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_11.setBackground(Color.WHITE);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(681, 40, 264, 195);
		
		table = new JTable();
		table.setFont(new Font("SansSerif", Font.PLAIN, 13));
		table.setRowHeight(18);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, "8", "Pizza", "Napolitana"},
				{null, "4", "Pizza", "Muzzarella"},
				{null, "2", "Pizza", "Jamon y Queso"},
				{null, "5", "Empanada", "Hawaiana"},
				{null, "48", "Empanada", "Humita"},
				{null, "36", "Empanada", "Carne"},
				{null, "10", "Empanada", "Pollo"},
			},
			new String[] {
				"ID", "Cantidad", "Tipo", "Producto"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setMinWidth(60);
		table.getColumnModel().getColumn(1).setMaxWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setMaxWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setMinWidth(90);
		table.getColumnModel().getColumn(3).setMaxWidth(200);
		scrollPane.setViewportView(table);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(SystemColor.textHighlight);
		
		JPanel panel_pedido_actual = new JPanel();
		panel_pedido_actual.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_pedido_actual.setBackground(Color.WHITE);
		
		JLabel lblPizza = new JLabel("Pedido");
		lblPizza.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_4 = new JLabel("12");
		label_4.setForeground(new Color(30, 144, 255));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel_pedido_actual = new GroupLayout(panel_pedido_actual);
		gl_panel_pedido_actual.setHorizontalGroup(
			gl_panel_pedido_actual.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pedido_actual.createSequentialGroup()
					.addGap(10)
					.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
		);
		gl_panel_pedido_actual.setVerticalGroup(
			gl_panel_pedido_actual.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_pedido_actual.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_pedido_actual.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 401, Short.MAX_VALUE))
		);
		
		table_3 = new JTable();
		table_3.setRowHeight(20);
		table_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table_3.setBackground(SystemColor.menu);
		table_3.setModel(new DefaultTableModel(
			new Object[][] {
				{"   Pizza"},
				{"2    Napolitana"},
				{"3    Muzzarella"},
				{"   Empanada"},
				{"5    Humita"},
				{"6    Carne"},
				{"7    Pollo"},
				{" [sin pasas de uva]"},
				{"9"},
				{"10"},
				{"11"},
				{"12"},
				{"13"},
				{"14"},
				{null},
				{null},
				{null},
				{null},
				{null},
				{"20"},
			},
			new String[] {
				""
			}
		));
		scrollPane_3.setColumnHeaderView(table_3);
		panel_pedido_actual.setLayout(gl_panel_pedido_actual);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		
		JLabel label_1 = new JLabel("Pedido");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_2 = new JLabel("13");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(30, 144, 255));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(10)
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
		);
		
		table_1 = new JTable();
		table_1.setRowHeight(20);
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_1.setBackground(SystemColor.menu);
		scrollPane_4.setColumnHeaderView(table_1);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(Color.WHITE);
		
		JLabel label_16 = new JLabel("Pedido");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_17 = new JLabel("14");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setForeground(new Color(30, 144, 255));
		label_17.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane_5 = new JScrollPane();
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(10)
							.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(scrollPane_5, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
		);
		
		table_4 = new JTable();
		table_4.setRowHeight(20);
		table_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_4.setBackground(SystemColor.menu);
		scrollPane_5.setColumnHeaderView(table_4);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(Color.WHITE);
		
		JLabel label_30 = new JLabel("Pedido");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_31 = new JLabel("15");
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setForeground(new Color(30, 144, 255));
		label_31.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane_6 = new JScrollPane();
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGap(10)
							.addComponent(label_30, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(label_31, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_30, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_31, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
		);
		
		table_5 = new JTable();
		table_5.setRowHeight(20);
		table_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		table_5.setBackground(SystemColor.menu);
		scrollPane_6.setColumnHeaderView(table_5);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBackground(Color.WHITE);
		
		JLabel label_44 = new JLabel("Pedido");
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_45 = new JLabel("16");
		label_45.setHorizontalAlignment(SwingConstants.CENTER);
		label_45.setForeground(new Color(30, 144, 255));
		label_45.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_9.createSequentialGroup()
							.addGap(11)
							.addComponent(label_44, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addGap(7)
							.addComponent(label_45, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addComponent(label_44, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_45, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE))
		);
		panel_9.setLayout(gl_panel_9);
		GroupLayout gl_panel_13 = new GroupLayout(panel_13);
		gl_panel_13.setHorizontalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_pedido_actual, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addGap(5)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 182, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 187, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(25))
		);
		gl_panel_13.setVerticalGroup(
			gl_panel_13.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addGroup(gl_panel_13.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addGap(5)
							.addComponent(panel_pedido_actual, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_4, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, 0, 0, Short.MAX_VALUE)))
					.addContainerGap())
		);
		panel_13.setLayout(gl_panel_13);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 983, Short.MAX_VALUE)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 961, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addComponent(panel_13, GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
		);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(14, 40, 655, 195);
		
		table_2 = new JTable();
		table_2.setRowHeight(25);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"4", "Pizza", "Napolitana", "Sin aceitunas"},
				{"1", "Pizza", "Muzzarella", null},
				{"1", "Empanada", "Hawaiana", "123456789012345678901234567890123"},
				{"12", "Empanada", "Humita", "1234567890123"},
				{"12", "Empanada", "Carne", "Sin pasas"},
				{"5", "Empanada", "Pollo", null},
			},
			new String[] {
				"Cantidad", "Tipo", "Producto", "Observacion"
			}
		));
		table_2.getColumnModel().getColumn(0).setPreferredWidth(55);
		table_2.getColumnModel().getColumn(0).setMinWidth(55);
		table_2.getColumnModel().getColumn(0).setMaxWidth(55);
		table_2.getColumnModel().getColumn(1).setPreferredWidth(280);
		table_2.getColumnModel().getColumn(1).setMinWidth(90);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(90);
		table_2.getColumnModel().getColumn(2).setMinWidth(90);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(500);
		table_2.getColumnModel().getColumn(3).setMinWidth(90);
		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
//		table_2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblPedido = new JLabel("Pedido 12 - Detalles");
		lblPedido.setBounds(18, 10, 467, 31);
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblResumenUltimos = new JLabel("Resumen productos pendientes");
		lblResumenUltimos.setBounds(681, 10, 264, 31);
		lblResumenUltimos.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenUltimos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_11.setLayout(null);
		panel_11.add(lblPedido);
		panel_11.add(scrollPane_2);
		panel_11.add(lblResumenUltimos);
		panel_11.add(scrollPane);
		contentPanel.setLayout(gl_contentPanel);
	}
}
