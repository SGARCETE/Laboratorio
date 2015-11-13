package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import Interfaz.Swing_Extends.JTable_Cocina_Resumen_Productos_Pendientes;
import Interfaz.Swing_Extends.JTable_Cocina_vista_pedido;
import Interfaz.Swing_Extends.Model_Cocina_Resumen_Productos_Pendientes;
import Interfaz.Swing_Extends.Model_Cocina_vista_pedido;
import MetAux.MetAux;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Servicios.Principal_Negocio_Interfaz;

public class Interfaz_Cocina_Pantalla extends JFrame {
	private static final long serialVersionUID = 4440791632042457639L;
	
	private final JPanel contentPanel = new JPanel();
	private JScrollPane scrollPane_prioridad1;
	private JScrollPane scrollPane_prioridad2;
	private JScrollPane scrollPane_prioridad3;
	private JScrollPane scrollPane_prioridad4;
	private JTable_Cocina_vista_pedido table_prioridad1;
	private JTable_Cocina_vista_pedido table_prioridad2;
	private JTable_Cocina_vista_pedido table_prioridad3;
	private JTable_Cocina_vista_pedido table_prioridad4;
	private JTable[] Lista_Tablas = null; 
	private JLabel lbl_NroPedido_Prioridad1;
	private JLabel lbl_NroPedido_Prioridad2;
	private JLabel lbl_NroPedido_Prioridad3;
	private JLabel lbl_NroPedido_Prioridad4;
	private JLabel[] Lista_Label  = null; 
	private JScrollPane scrollPane_Resumen_Productos_Pendientes;
	private JTable table_Resumen_Productos_Pendientes;
	
	
	public Interfaz_Cocina_Pantalla(Principal_Negocio_Interfaz Principal_neg_int) {
		setMinimumSize(new Dimension(1024, 768));
		setIconImage(Toolkit.getDefaultToolkit().getImage(Interfaz_Cocina_Pantalla.class.getResource("/Recursos/Pizza-icon16.png")));
		setTitle("Monitor Cocina");
		setBounds(100, 100, 1024, 768);
		getContentPane().setLayout(new BorderLayout());
		
		

//		getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
//		/*PARA SACARLE LOS BORDES:*/
//		this.setUndecorated(true);
//		/*PARA QUE SE ABRA LO MAS GRANDE POSIBLE */
//		this.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds()); 
		
		
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JPanel panel_13 = new JPanel();
		panel_13.setBackground(SystemColor.textHighlight);
		
		JPanel panel_prioridad1 = new JPanel();
		panel_prioridad1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_prioridad1.setBackground(Color.WHITE);
		
		JLabel lblPizza = new JLabel("Pedido");
		lblPizza.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		lbl_NroPedido_Prioridad1 = new JLabel("12");
		lbl_NroPedido_Prioridad1.setForeground(new Color(30, 144, 255));
		lbl_NroPedido_Prioridad1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NroPedido_Prioridad1.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		scrollPane_prioridad1 = new JScrollPane();
		scrollPane_prioridad1.setBackground(Color.white);
		scrollPane_prioridad1.getViewport().setBackground(Color.WHITE);

		
		GroupLayout gl_panel_prioridad1 = new GroupLayout(panel_prioridad1);
		gl_panel_prioridad1.setHorizontalGroup(
			gl_panel_prioridad1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad1.createSequentialGroup()
					.addGap(10)
					.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbl_NroPedido_Prioridad1, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(scrollPane_prioridad1, GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
		);
		gl_panel_prioridad1.setVerticalGroup(
			gl_panel_prioridad1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad1.createSequentialGroup()
					.addGap(9)
					.addGroup(gl_panel_prioridad1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPizza, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_NroPedido_Prioridad1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_prioridad1, GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE))
		);
		


		
		panel_prioridad1.setLayout(gl_panel_prioridad1);
		
		JPanel panel_prioridad2 = new JPanel();
		panel_prioridad2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_prioridad2.setBackground(Color.WHITE);
		
		JLabel label_1 = new JLabel("Pedido");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		lbl_NroPedido_Prioridad2 = new JLabel("13");
		lbl_NroPedido_Prioridad2.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NroPedido_Prioridad2.setForeground(new Color(30, 144, 255));
		lbl_NroPedido_Prioridad2.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		scrollPane_prioridad2 = new JScrollPane();
		scrollPane_prioridad2.setBackground(Color.white);
		scrollPane_prioridad2.setOpaque(true);
		scrollPane_prioridad2.getViewport().setBackground(Color.WHITE);
		
		
		GroupLayout gl_panel_prioridad2 = new GroupLayout(panel_prioridad2);
		gl_panel_prioridad2.setHorizontalGroup(
			gl_panel_prioridad2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad2.createSequentialGroup()
					.addGap(10)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbl_NroPedido_Prioridad2, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addComponent(scrollPane_prioridad2, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
		);
		gl_panel_prioridad2.setVerticalGroup(
			gl_panel_prioridad2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad2.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_prioridad2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_NroPedido_Prioridad2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPane_prioridad2, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
		);
		

		panel_prioridad2.setLayout(gl_panel_prioridad2);
		
		JPanel panel_prioridad3 = new JPanel();
		panel_prioridad3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_prioridad3.setBackground(Color.WHITE);
		
		JLabel label_16 = new JLabel("Pedido");
		label_16.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		lbl_NroPedido_Prioridad3 = new JLabel("14");
		lbl_NroPedido_Prioridad3.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NroPedido_Prioridad3.setForeground(new Color(30, 144, 255));
		lbl_NroPedido_Prioridad3.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		scrollPane_prioridad3 = new JScrollPane();
		scrollPane_prioridad3.setBackground(Color.white);
		scrollPane_prioridad3.setOpaque(true);
		scrollPane_prioridad3.getViewport().setBackground(Color.WHITE);
		
		
		GroupLayout gl_panel_prioridad3 = new GroupLayout(panel_prioridad3);
		gl_panel_prioridad3.setHorizontalGroup(
			gl_panel_prioridad3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad3.createSequentialGroup()
					.addGap(10)
					.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbl_NroPedido_Prioridad3, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addComponent(scrollPane_prioridad3, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
		);
		gl_panel_prioridad3.setVerticalGroup(
			gl_panel_prioridad3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad3.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_prioridad3.createParallelGroup(Alignment.LEADING)
						.addComponent(label_16, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_NroPedido_Prioridad3, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPane_prioridad3, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
		);
		
	
		panel_prioridad3.setLayout(gl_panel_prioridad3);
		
		JPanel panel_prioridad4 = new JPanel();
		panel_prioridad4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_prioridad4.setBackground(Color.WHITE);
		
		JLabel label_30 = new JLabel("Pedido");
		label_30.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		lbl_NroPedido_Prioridad4 = new JLabel("15");
		lbl_NroPedido_Prioridad4.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_NroPedido_Prioridad4.setForeground(new Color(30, 144, 255));
		lbl_NroPedido_Prioridad4.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		scrollPane_prioridad4 = new JScrollPane();
		scrollPane_prioridad4.setBackground(Color.white); // TODO
		scrollPane_prioridad4.setOpaque(true);
		scrollPane_prioridad4.getViewport().setBackground(Color.WHITE);
		
		GroupLayout gl_panel_prioridad4 = new GroupLayout(panel_prioridad4);
		gl_panel_prioridad4.setHorizontalGroup(
			gl_panel_prioridad4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad4.createSequentialGroup()
					.addGap(10)
					.addComponent(label_30, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
					.addGap(7)
					.addComponent(lbl_NroPedido_Prioridad4, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
				.addComponent(scrollPane_prioridad4, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
		);
		gl_panel_prioridad4.setVerticalGroup(
			gl_panel_prioridad4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_prioridad4.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel_prioridad4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_30, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(lbl_NroPedido_Prioridad4, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(scrollPane_prioridad4, GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
		);
		

		panel_prioridad4.setLayout(gl_panel_prioridad4);
		
		JPanel panel_Resumen = new JPanel();
		panel_Resumen.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_Resumen.setBackground(Color.WHITE);
		
		scrollPane_Resumen_Productos_Pendientes = new JScrollPane();
		table_Resumen_Productos_Pendientes = new JTable_Cocina_Resumen_Productos_Pendientes(new Model_Cocina_Resumen_Productos_Pendientes());
		table_Resumen_Productos_Pendientes.setBackground(Color.white);
		table_Resumen_Productos_Pendientes.setOpaque(true);
		scrollPane_Resumen_Productos_Pendientes.setViewportView(table_Resumen_Productos_Pendientes);

		
		scrollPane_Resumen_Productos_Pendientes.setBackground(Color.white);
		scrollPane_Resumen_Productos_Pendientes.setOpaque(true);
		scrollPane_Resumen_Productos_Pendientes.getViewport().setBackground(Color.WHITE);
		
		
		JLabel lblResumenUltimos = new JLabel("Resumen");
		lblResumenUltimos.setHorizontalAlignment(SwingConstants.CENTER);
		lblResumenUltimos.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel_Resumen = new GroupLayout(panel_Resumen);
		gl_panel_Resumen.setHorizontalGroup(
			gl_panel_Resumen.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_Resumen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblResumenUltimos, GroupLayout.PREFERRED_SIZE, 179, Short.MAX_VALUE)
					.addGap(5))
				.addComponent(scrollPane_Resumen_Productos_Pendientes, GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
		);
		gl_panel_Resumen.setVerticalGroup(
			gl_panel_Resumen.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_Resumen.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblResumenUltimos, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_Resumen_Productos_Pendientes, GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE))
		);
		

		panel_Resumen.setLayout(gl_panel_Resumen);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_13, GroupLayout.PREFERRED_SIZE, 998, Short.MAX_VALUE)
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_13, GroupLayout.DEFAULT_SIZE, 719, Short.MAX_VALUE)
		);
		GroupLayout gl_panel_13 = new GroupLayout(panel_13);
		gl_panel_13.setHorizontalGroup(
			gl_panel_13.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addGap(5)
					.addComponent(panel_prioridad1, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_prioridad2, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_prioridad3, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(5)
					.addComponent(panel_prioridad4, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
					.addGap(4)
					.addComponent(panel_Resumen, GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
					.addGap(5))
		);
		gl_panel_13.setVerticalGroup(
			gl_panel_13.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_13.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_13.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_Resumen, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addComponent(panel_prioridad1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addComponent(panel_prioridad2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addComponent(panel_prioridad3, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE)
						.addComponent(panel_prioridad4, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 708, Short.MAX_VALUE))
					.addGap(6))
		);
		panel_13.setLayout(gl_panel_13);
		contentPanel.setLayout(gl_contentPanel);
		iniciarParametros();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void iniciarParametros() {
		
		table_prioridad1 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		table_prioridad2 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		table_prioridad3 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		table_prioridad4 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		
		scrollPane_prioridad1.setColumnHeaderView(table_prioridad1);
		scrollPane_prioridad2.setColumnHeaderView(table_prioridad2);
		scrollPane_prioridad3.setColumnHeaderView(table_prioridad3);
		scrollPane_prioridad4.setColumnHeaderView(table_prioridad4);
		
		Lista_Tablas = new JTable[]{table_prioridad1,table_prioridad2,table_prioridad3,table_prioridad4};
		Lista_Label  = new JLabel[]{lbl_NroPedido_Prioridad1, lbl_NroPedido_Prioridad2, lbl_NroPedido_Prioridad3, lbl_NroPedido_Prioridad4};
		
//		Hardcodear_Tablas();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void resetear_campos(){
		table_Resumen_Productos_Pendientes = new JTable_Cocina_Resumen_Productos_Pendientes(new Model_Cocina_Resumen_Productos_Pendientes());
		scrollPane_Resumen_Productos_Pendientes.setViewportView(table_Resumen_Productos_Pendientes);
				
		table_prioridad1 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		table_prioridad2 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		table_prioridad3 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		table_prioridad4 = new JTable_Cocina_vista_pedido(new Model_Cocina_vista_pedido());
		
		scrollPane_prioridad1.setViewportView(table_prioridad1);
		scrollPane_prioridad2.setViewportView(table_prioridad2);
		scrollPane_prioridad3.setViewportView(table_prioridad3);
		scrollPane_prioridad4.setViewportView(table_prioridad4);
		
		lbl_NroPedido_Prioridad1.setText("");
		lbl_NroPedido_Prioridad2.setText("");
		lbl_NroPedido_Prioridad3.setText("");
		lbl_NroPedido_Prioridad4.setText("");
		
		Lista_Tablas = new JTable[]{table_prioridad1,table_prioridad2,table_prioridad3,table_prioridad4};
		Lista_Label  = new JLabel[]{lbl_NroPedido_Prioridad1, lbl_NroPedido_Prioridad2, lbl_NroPedido_Prioridad3, lbl_NroPedido_Prioridad4};
		
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// HARDCODEADO
	@SuppressWarnings("unused")
	private void Hardcodear_Tablas(){
		Object [][] datos = new Object[][] {
			{"   Pizza"},
			{"2    Napolitana"},
			{"3    Muzzarella"},
			{"   Empanada"},
			{"5    Humita"},
			{"6    Carne"},
			{"7    Pollo"},
			{" >sin pasas de uva"},
			{"9    "},
			{"10   "},
			{"11   "},
			{"12   "},
			{"13   "},
			{"14   "},
			{"15   "},
			{"16   "},
			{"17   "},
			{"18   "},
			{"19   "},
			{"20   "}
		};
		
		DefaultTableModel model =  new DefaultTableModel(datos, new String[] {""} );
		
		table_prioridad1 = new JTable_Cocina_vista_pedido(model);
		table_prioridad2 = new JTable_Cocina_vista_pedido(model);
		table_prioridad3 = new JTable_Cocina_vista_pedido(model);
		table_prioridad4 = new JTable_Cocina_vista_pedido(model);
		scrollPane_prioridad1.setColumnHeaderView(table_prioridad1);
		scrollPane_prioridad2.setColumnHeaderView(table_prioridad2);
		scrollPane_prioridad3.setColumnHeaderView(table_prioridad3);
		scrollPane_prioridad4.setColumnHeaderView(table_prioridad4);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void Actualizar_monitor(ArrayList<Pedido> Lista_Pedidos){
		resetear_campos();
		ArrayList<Pedido> Lista_4_pedidos = new ArrayList<Pedido>();

		int cant_pedidos = 0;
		for (int j = 0; j < Lista_Pedidos.size(); j++) {
			if(Lista_Pedidos.get(j).getESTADO().equals("Pendiente") && cant_pedidos<4){
				Lista_4_pedidos.add(Lista_Pedidos.get(j));
				cant_pedidos++;
			}
		}
		
		if(Lista_4_pedidos!=null && Lista_4_pedidos.size()>0){
			for (int i = 0; i < Lista_4_pedidos.size(); i++) 
				Mostrar_pedido(Lista_4_pedidos.get(i), i);
			if(Lista_4_pedidos.size()>0 && Lista_Pedidos.size()>0){
				setResumen_Productos_Pendientes(Lista_Pedidos);
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
	// TODO BUG, AGREGA EL MISMO PRODUCTO EN OTRA FILA SI ESTA PERTENECE A OTRO PEDIDO 
	private void setResumen_Productos_Pendientes(ArrayList<Pedido> Lista_Pedidos){
		DefaultTableModel model = (DefaultTableModel) table_Resumen_Productos_Pendientes.getModel();
		for (int i = 0; i < Lista_Pedidos.size(); i++) {
			Pedido p = Lista_Pedidos.get(i);
			for (int j = 0; j < Lista_Pedidos.get(i).getLista_Productos().size(); j++) {
				Producto pr = p.getLista_Productos().get(j);
				if(pr.getPR_tipo_producto()!=3 )// SI NO ES UNA BEBIDA
					model.addRow(new Object[]{pr.getCantidad(), pr.getPR_TIPO_PRODUCTO_STRING(), pr.getPR_nombre()});	
			}
		}
		table_Resumen_Productos_Pendientes.setModel(model);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Mostrar_pedido(Pedido p, Integer Index){
		JTable JT = Lista_Tablas[Index];
		JLabel JL = Lista_Label[Index];
		DefaultTableModel model = (DefaultTableModel) JT.getModel();
		JL.setText(p.getID_DIARIO().toString());
		
		ArrayList<Producto> LISTA_PRODUCTOS_ORDENADA = MetAux.mergeSort(p.getLista_Productos());
		String TIPO_PR = "";
		String TIPO_PR_ACTUAL = ""; 
		
		for (int i = 0; i < LISTA_PRODUCTOS_ORDENADA.size(); i++) {
			Producto pr = LISTA_PRODUCTOS_ORDENADA.get(i);
			TIPO_PR_ACTUAL = pr.getPR_TIPO_PRODUCTO_STRING();
			
			if(!TIPO_PR_ACTUAL.equals(TIPO_PR) && pr.getPR_tipo_producto()!=3 ){// SI NO ES UNA BEBIDA (ID Producto bebida = 3)
				TIPO_PR = pr.getPR_TIPO_PRODUCTO_STRING();
				model.addRow(new Object[]{"   "+pr.getPR_TIPO_PRODUCTO_STRING()});
			}
			if(pr.getPR_tipo_producto()!=3 ){									// SI NO ES UNA BEBIDA  (ID Producto bebida = 3)
				model.addRow(new Object[]{pr.getCantidad() +"    "+pr.getPR_nombre()});
				if(!pr.getPR_Observacion().isEmpty() && !pr.getPR_Observacion().equals(" "))
					model.addRow(new Object[]{" >"+pr.getPR_Observacion()});
			}
			
		}
		JT.setModel(model);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
	
//	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//	public void Actualizar_monitor(ArrayList<Pedido> Lista_Pedidos){
//		resetear_campos();
//		ArrayList<Pedido> Lista_4_pedidos = new ArrayList<Pedido>();
//
//		int cant_pedidos = 0;
//		for (int j = 0; j < Lista_Pedidos.size(); j++) {
//			if(Lista_Pedidos.get(j).getESTADO().equals("Pendiente") && cant_pedidos<4){
//				Lista_4_pedidos.add(Lista_Pedidos.get(j));
//				cant_pedidos++;
//			}
//		}
//		
//		if(Lista_4_pedidos!=null && Lista_4_pedidos.size()>0){
//			for (int i = 0; i < Lista_4_pedidos.size(); i++) 
//				Mostrar_pedido(Lista_4_pedidos.get(i), i);
//			if(Lista_4_pedidos.size()>0 && Lista_Pedidos.size()>0){
//				setResumen_Productos_Pendientes(Lista_Pedidos);
//			}
//		}
//	}
//
//	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 
//	// TODO BUG, AGREGA EL MISMO PRODUCTO EN OTRA FILA SI ESTA PERTENECE A OTRO PEDIDO 
//	private void setResumen_Productos_Pendientes(ArrayList<Pedido> Lista_Pedidos){
//		DefaultTableModel model = (DefaultTableModel) table_Resumen_Productos_Pendientes.getModel();
//		for (int i = 0; i < Lista_Pedidos.size(); i++) {
//			Pedido p = Lista_Pedidos.get(i);
//			for (int j = 0; j < Lista_Pedidos.get(i).getLista_Productos().size(); j++) {
//				Producto pr = p.getLista_Productos().get(j);
//				if(pr.getPR_tipo_producto()!=3 )// SI NO ES UNA BEBIDA
//					model.addRow(new Object[]{pr.getCantidad(), pr.getPR_TIPO_PRODUCTO_STRING(), pr.getPR_nombre()});	
//			}
//		}
//		table_Resumen_Productos_Pendientes.setModel(model);
//	}
//	
//	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//	private void Mostrar_pedido(Pedido p, Integer Index){
//		JTable JT = Lista_Tablas[Index];
//		JLabel JL = Lista_Label[Index];
//		DefaultTableModel model = (DefaultTableModel) JT.getModel();
//		JL.setText(p.getID_DIARIO().toString());
//		
//		ArrayList<Producto> LISTA_PRODUCTOS_ORDENADA = MetAux.mergeSort(p.getLista_Productos());
//		String TIPO_PR = "";
//		String TIPO_PR_ACTUAL = ""; 
//		
//		for (int i = 0; i < LISTA_PRODUCTOS_ORDENADA.size(); i++) {
//			Producto pr = LISTA_PRODUCTOS_ORDENADA.get(i);
//			TIPO_PR_ACTUAL = pr.getPR_TIPO_PRODUCTO_STRING();
//			
//			if(!TIPO_PR_ACTUAL.equals(TIPO_PR) && pr.getPR_tipo_producto()!=3 ){// SI NO ES UNA BEBIDA (ID Producto bebida = 3)
//				TIPO_PR = pr.getPR_TIPO_PRODUCTO_STRING();
//				model.addRow(new Object[]{"   "+pr.getPR_TIPO_PRODUCTO_STRING()});
//			}
//			if(pr.getPR_tipo_producto()!=3 ){									// SI NO ES UNA BEBIDA  (ID Producto bebida = 3)
//				model.addRow(new Object[]{pr.getCantidad() +"    "+pr.getPR_nombre()});
//				if(!pr.getPR_Observacion().isEmpty() && !pr.getPR_Observacion().equals(" "))
//					model.addRow(new Object[]{" >"+pr.getPR_Observacion()});
//			}
//			
//		}
//		JT.setModel(model);
//	}
//
//	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	
}//--> FIN INTERFAZ
