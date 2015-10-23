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

public class Interfaz_Cocina_Pantalla_Alternativa2 extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
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
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setMinWidth(150);
		table.getColumnModel().getColumn(2).setMaxWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(173);
		scrollPane.setViewportView(table);
		
		JPanel panel_13 = new JPanel();
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBackground(SystemColor.menu);
		
		JLabel lblPizza = new JLabel("Pedido");
		lblPizza.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_4 = new JLabel("12");
		label_4.setForeground(new Color(30, 144, 255));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JPanel panel_6 = new JPanel();
		panel_6.setBackground(new Color(240, 230, 140));
		panel_6.setLayout(null);
		
		JLabel lblPizza_1 = new JLabel("Pizzas:");
		lblPizza_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		lblPizza_1.setBounds(10, 11, 95, 27);
		panel_6.add(lblPizza_1);
		
		JLabel lblNapolitana = new JLabel("4 Napolitana");
		lblNapolitana.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNapolitana.setBounds(10, 49, 170, 27);
		panel_6.add(lblNapolitana);
		
		JLabel lblEmpHumita = new JLabel("12 Humita");
		lblEmpHumita.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblEmpHumita.setBounds(10, 197, 170, 27);
		panel_6.add(lblEmpHumita);
		
		JLabel lblMuzzarella = new JLabel("1 Muzzarella");
		lblMuzzarella.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblMuzzarella.setBounds(10, 87, 170, 27);
		panel_6.add(lblMuzzarella);
		
		JLabel lblCarne = new JLabel("12 Carne");
		lblCarne.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCarne.setBounds(10, 235, 170, 27);
		panel_6.add(lblCarne);
		
		JLabel lblHawaiana = new JLabel("1 Hawaiana");
		lblHawaiana.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblHawaiana.setBounds(10, 159, 170, 27);
		panel_6.add(lblHawaiana);
		
		JLabel lblPollo = new JLabel("5 Pollo");
		lblPollo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblPollo.setBounds(10, 273, 170, 27);
		panel_6.add(lblPollo);
		
		JLabel label = new JLabel("Empanadas:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label.setBounds(10, 121, 170, 27);
		panel_6.add(label);
		
		JLabel lblConDelivery = new JLabel("Con Delivery");
		lblConDelivery.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addComponent(lblConDelivery, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_6, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(lblConDelivery, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(panel_6, GroupLayout.PREFERRED_SIZE, 391, GroupLayout.PREFERRED_SIZE)
					.addGap(89))
		);
		panel.setLayout(gl_panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(SystemColor.menu);
		
		JLabel label_1 = new JLabel("Pedido");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_2 = new JLabel("13");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(30, 144, 255));
		label_2.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JPanel panel_7 = new JPanel();
		panel_7.setLayout(null);
		panel_7.setBackground(new Color(50, 205, 50));
		
		JLabel label_3 = new JLabel("Pizzas:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_3.setBounds(10, 11, 95, 27);
		panel_7.add(label_3);
		
		JLabel label_5 = new JLabel("4 Napolitana");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_5.setBounds(10, 49, 170, 27);
		panel_7.add(label_5);
		
		JLabel label_8 = new JLabel("12 Carne");
		label_8.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_8.setBounds(10, 163, 170, 27);
		panel_7.add(label_8);
		
		JLabel label_10 = new JLabel("1 Hawaiana");
		label_10.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_10.setBounds(10, 125, 170, 27);
		panel_7.add(label_10);
		
		JLabel label_15 = new JLabel("Empanadas:");
		label_15.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_15.setBounds(10, 87, 170, 27);
		panel_7.add(label_15);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, 389, GroupLayout.PREFERRED_SIZE)
					.addGap(91))
		);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBackground(SystemColor.menu);
		
		JLabel label_16 = new JLabel("Pedido");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_17 = new JLabel("14");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setForeground(new Color(30, 144, 255));
		label_17.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setBackground(new Color(50, 205, 50));
		
		JLabel label_18 = new JLabel("Pizzas:");
		label_18.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_18.setBounds(10, 11, 95, 27);
		panel_3.add(label_18);
		
		JLabel label_20 = new JLabel("12 Humita");
		label_20.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_20.setBounds(10, 163, 170, 27);
		panel_3.add(label_20);
		
		JLabel label_21 = new JLabel("1 Muzzarella");
		label_21.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_21.setBounds(10, 49, 170, 27);
		panel_3.add(label_21);
		
		JLabel label_22 = new JLabel("12 Carne");
		label_22.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_22.setBounds(10, 201, 170, 27);
		panel_3.add(label_22);
		
		JLabel label_24 = new JLabel("1 Hawaiana");
		label_24.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_24.setBounds(10, 125, 170, 27);
		panel_3.add(label_24);
		
		JLabel label_27 = new JLabel("5 Pollo");
		label_27.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_27.setBounds(10, 239, 170, 27);
		panel_3.add(label_27);
		
		JLabel label_29 = new JLabel("Empanadas:");
		label_29.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_29.setBounds(10, 87, 170, 27);
		panel_3.add(label_29);
		
		JLabel label_9 = new JLabel("Con Delivery");
		label_9.setFont(new Font("Tahoma", Font.PLAIN, 28));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_17, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(label_9, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(13)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE)
					.addGap(90))
		);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_4.setBackground(SystemColor.menu);
		
		JLabel label_30 = new JLabel("Pedido");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_31 = new JLabel("15");
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setForeground(new Color(30, 144, 255));
		label_31.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JPanel panel_8 = new JPanel();
		panel_8.setLayout(null);
		panel_8.setBackground(new Color(50, 205, 50));
		
		JLabel label_32 = new JLabel("Pizzas:");
		label_32.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_32.setBounds(10, 11, 95, 27);
		panel_8.add(label_32);
		
		JLabel lblHawaiana_1 = new JLabel("3 Ruccula");
		lblHawaiana_1.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblHawaiana_1.setBounds(10, 49, 170, 27);
		panel_8.add(lblHawaiana_1);
		
		JLabel label_34 = new JLabel("12 Humita");
		label_34.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_34.setBounds(10, 201, 170, 27);
		panel_8.add(label_34);
		
		JLabel label_35 = new JLabel("1 Muzzarella");
		label_35.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_35.setBounds(10, 87, 170, 27);
		panel_8.add(label_35);
		
		JLabel label_38 = new JLabel("1 Hawaiana");
		label_38.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_38.setBounds(10, 163, 170, 27);
		panel_8.add(label_38);
		
		JLabel label_43 = new JLabel("Empanadas:");
		label_43.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_43.setBounds(10, 125, 170, 27);
		panel_8.add(label_43);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(10)
					.addComponent(label_30, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(label_31, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_8, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_30, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_31, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(51)
					.addComponent(panel_8, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE)
					.addGap(88))
		);
		panel_4.setLayout(gl_panel_4);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_9.setBackground(SystemColor.menu);
		
		JLabel label_44 = new JLabel("Pedido");
		label_44.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		JLabel label_45 = new JLabel("16");
		label_45.setHorizontalAlignment(SwingConstants.CENTER);
		label_45.setForeground(new Color(30, 144, 255));
		label_45.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(null);
		panel_5.setBackground(new Color(50, 205, 50));
		
		JLabel label_6 = new JLabel("Pizzas:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_6.setBounds(10, 11, 95, 27);
		panel_5.add(label_6);
		
		JLabel label_7 = new JLabel("2 J y Q");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_7.setBounds(10, 49, 170, 27);
		panel_5.add(label_7);
		
		JLabel label_11 = new JLabel("12 Humita");
		label_11.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_11.setBounds(10, 201, 170, 27);
		panel_5.add(label_11);
		
		JLabel label_12 = new JLabel("1 Muzzarella");
		label_12.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_12.setBounds(10, 87, 170, 27);
		panel_5.add(label_12);
		
		JLabel label_13 = new JLabel("1 Hawaiana");
		label_13.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label_13.setBounds(10, 163, 170, 27);
		panel_5.add(label_13);
		
		JLabel label_14 = new JLabel("Empanadas:");
		label_14.setFont(new Font("Tahoma", Font.PLAIN, 28));
		label_14.setBounds(10, 125, 170, 27);
		panel_5.add(label_14);
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(label_44, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(label_45, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(8))
				.addGroup(gl_panel_9.createSequentialGroup()
					.addComponent(panel_5, GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
					.addGap(2))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.LEADING)
						.addComponent(label_44, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_45, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE))
		);
		panel_9.setLayout(gl_panel_9);
		GroupLayout gl_panel_13 = new GroupLayout(panel_13);
		gl_panel_13.setHorizontalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addGap(5)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_9, GroupLayout.PREFERRED_SIZE, 190, Short.MAX_VALUE)
					.addGap(8))
		);
		gl_panel_13.setVerticalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addGroup(gl_panel_13.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_9, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_4, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_2, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_1, 0, 0, Short.MAX_VALUE))
						.addGroup(Alignment.LEADING, gl_panel_13.createSequentialGroup()
							.addGap(5)
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 479, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_13.setLayout(gl_panel_13);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 951, Short.MAX_VALUE)
					.addGap(1))
				.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 957, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(6)
					.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_11, GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
					.addGap(0))
		);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		table_2 = new JTable();
		table_2.setRowHeight(25);
		table_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{"4", "Pizza", "Napolitana", "Sin aceitunas"},
				{"1", "Pizza", "Muzzarella", null},
				{"1", "Empanada", "Hawaiana", null},
				{"12", "Empanada", "Humita", ""},
				{"12", "Empanada", "Carne", "Sin pasas"},
				{"5", "Empanada", "Pollo", null},
			},
			new String[] {
				"Cantidad", "Tipo", "Producto", "Observacion"
			}
		));
		table_2.getColumnModel().getColumn(0).setMinWidth(75);
		table_2.getColumnModel().getColumn(0).setMaxWidth(75);
		scrollPane_2.setViewportView(table_2);
		
		JLabel lblPedido = new JLabel("Pedido 12 - Detalles");
		lblPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblPedido.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblResumenUltimos = new JLabel("Resumen ultimos 5 pedidos");
		lblResumenUltimos.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenUltimos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_11 = new GroupLayout(panel_11);
		gl_panel_11.setHorizontalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addGap(4)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addGap(4)
							.addComponent(lblPedido, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE))
						.addGroup(gl_panel_11.createSequentialGroup()
							.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
							.addGap(2)))
					.addGap(10)
					.addGroup(gl_panel_11.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_11.createSequentialGroup()
							.addComponent(lblResumenUltimos, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
							.addGap(4))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
					.addGap(4))
		);
		gl_panel_11.setVerticalGroup(
			gl_panel_11.createParallelGroup(Alignment.LEADING)
				.addComponent(lblPedido, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addGap(30)
					.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
				.addComponent(lblResumenUltimos, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
				.addGroup(gl_panel_11.createSequentialGroup()
					.addGap(30)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
		);
		panel_11.setLayout(gl_panel_11);
		contentPanel.setLayout(gl_contentPanel);
	}
}
