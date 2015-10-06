package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Interfaz.Swing_Extends.JTable_Listado_Pedidos;
import Interfaz.Swing_Extends.JTable_Pedido_Completo;
import Interfaz.Swing_Extends.Model_Pedido_Completo;
import Negocio.Modelo.Pedido;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Pedidos;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.JComboBox;
import javax.swing.JSpinner;

public class Interfaz_ABM_Pedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable Tabla_Pedido_Completo;
	private JScrollPane scrollPane_Pedido_Completo;
	private Servicio_Pedidos SvPedidos;
	private JTextField textCliente;
	private JTextField textDetalle;
	private JTextField textDire;
	private JTextField textTelefono;
	
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance(); /* Muestra un Double en formato Dinero. Ej: 50.5 => $50,50 */
	private SimpleDateFormat formato_ddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
	private JLabel label_NroPedido;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel label_Fecha;
	private JLabel label_ESTADO;
	private JLabel label_TOTAL;
	
	/**
	 * Create the dialog.
	 * @param principal_neg_int 
	 */
	public Interfaz_ABM_Pedido(Principal_Negocio_Interfaz principal_neg_int) {
		SvPedidos = principal_neg_int.getSvPedidos();
		
		setTitle("ABM Pedido");
		setBounds(100, 100, 1154, 490);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane_Pedido_Completo = new JScrollPane();
		scrollPane_Pedido_Completo.setBounds(349, 108, 469, 282);
		contentPanel.add(scrollPane_Pedido_Completo);
		
		
		JLabel lblNumeroPedido = new JLabel("N\u00BA Pedido");
		lblNumeroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroPedido.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblNumeroPedido.setBounds(6, 10, 124, 43);
		contentPanel.add(lblNumeroPedido);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(null, "Cliente", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel.setBounds(830, 108, 264, 199);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblDireccion = new JLabel("Cliente");
		lblDireccion.setBounds(22, 16, 106, 25);
		panel.add(lblDireccion);
		
		textCliente = new JTextField();
		textCliente.setBackground(new Color(240, 255, 240));
		textCliente.setBounds(22, 42, 224, 30);
		panel.add(textCliente);
		textCliente.setColumns(10);
		
		textDetalle = new JTextField();
		textDetalle.setBackground(new Color(240, 255, 240));
		textDetalle.setBounds(22, 148, 106, 30);
		panel.add(textDetalle);
		textDetalle.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(22, 122, 106, 25);
		panel.add(lblDetalle);
		
		JLabel lblDireccion_1 = new JLabel("Direccion");
		lblDireccion_1.setBounds(22, 72, 106, 25);
		panel.add(lblDireccion_1);
		
		textDire = new JTextField();
		textDire.setBackground(new Color(240, 255, 240));
		textDire.setBounds(22, 98, 224, 30);
		panel.add(textDire);
		textDire.setColumns(10);
		
		textTelefono = new JTextField();
		textTelefono.setBackground(new Color(240, 255, 240));
		textTelefono.setBounds(140, 148, 106, 30);
		panel.add(textTelefono);
		textTelefono.setColumns(10);
		
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(140, 122, 106, 25);
		panel.add(lblTelefono);
		
		label_NroPedido = new JLabel("");
		label_NroPedido.setHorizontalAlignment(SwingConstants.CENTER);
		label_NroPedido.setOpaque(true);
		label_NroPedido.setForeground(new Color(60, 179, 113));
		label_NroPedido.setBackground(new Color(240, 255, 240));
		label_NroPedido.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_NroPedido.setBounds(142, 11, 139, 42);
		contentPanel.add(label_NroPedido);
		
		label_Fecha = new JLabel("");
		label_Fecha.setOpaque(true);
		label_Fecha.setHorizontalAlignment(SwingConstants.CENTER);
		label_Fecha.setForeground(new Color(60, 179, 113));
		label_Fecha.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_Fecha.setBackground(new Color(240, 255, 240));
		label_Fecha.setBounds(474, 11, 167, 42);
		contentPanel.add(label_Fecha);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(6, 108, 331, 282);
		contentPanel.add(panel_1);
		
		JLabel label = new JLabel("Producto");
		label.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label.setBounds(10, 12, 109, 25);
		panel_1.add(label);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(240, 255, 255));
		comboBox.setBounds(117, 12, 201, 25);
		panel_1.add(comboBox);
		
		JComboBox<String> comboBox_1 = new JComboBox<String>();
		comboBox_1.setBackground(new Color(240, 255, 255));
		comboBox_1.setBounds(117, 49, 201, 25);
		panel_1.add(comboBox_1);
		
		JLabel label_2 = new JLabel("Variedad");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_2.setBounds(10, 49, 109, 25);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Cantidad");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(10, 85, 109, 25);
		panel_1.add(label_3);
		
		JSpinner spinner = new JSpinner();
		spinner.setBackground(new Color(240, 255, 255));
		spinner.setBounds(117, 85, 59, 25);
		panel_1.add(spinner);
		
		JLabel label_4 = new JLabel("Valor c/u     $");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_4.setBounds(10, 122, 109, 25);
		panel_1.add(label_4);
		
		textField = new JTextField();
		textField.setText("$0,00");
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBackground(new Color(240, 255, 255));
		textField.setBounds(117, 122, 199, 25);
		panel_1.add(textField);
		
		JLabel label_5 = new JLabel("Valor total   $");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_5.setBounds(10, 159, 109, 25);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("Observaciones");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_6.setBounds(10, 195, 109, 25);
		panel_1.add(label_6);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(240, 255, 255));
		textField_1.setBounds(117, 195, 199, 25);
		panel_1.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("");
		textField_2.setText("$0,00");
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		textField_2.setBackground(new Color(240, 255, 255));
		textField_2.setBounds(117, 159, 199, 25);
		panel_1.add(textField_2);
		
		JButton button = new JButton("Agregar");
		button.setBounds(117, 225, 100, 30);
		panel_1.add(button);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblFecha.setBounds(338, 11, 124, 43);
		contentPanel.add(lblFecha);
		
		JLabel lblNumero = new JLabel("Estado");
		lblNumero.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumero.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblNumero.setBounds(6, 65, 124, 30);
		contentPanel.add(lblNumero);
		
		label_ESTADO = new JLabel("");
		label_ESTADO.setOpaque(true);
		label_ESTADO.setHorizontalAlignment(SwingConstants.CENTER);
		label_ESTADO.setForeground(new Color(60, 179, 113));
		label_ESTADO.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_ESTADO.setBackground(new Color(240, 248, 255));
		label_ESTADO.setBounds(142, 66, 139, 30);
		contentPanel.add(label_ESTADO);
		
		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal_1.setFont(new Font("SansSerif", Font.PLAIN, 24));
		lblTotal_1.setBounds(338, 65, 124, 30);
		contentPanel.add(lblTotal_1);
		
		label_TOTAL = new JLabel("");
		label_TOTAL.setOpaque(true);
		label_TOTAL.setHorizontalAlignment(SwingConstants.CENTER);
		label_TOTAL.setForeground(new Color(60, 179, 113));
		label_TOTAL.setFont(new Font("SansSerif", Font.BOLD, 28));
		label_TOTAL.setBackground(new Color(240, 248, 255));
		label_TOTAL.setBounds(474, 66, 167, 30);
		contentPanel.add(label_TOTAL);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(60, 179, 113));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Guardar");
		okButton.setBackground(Color.WHITE);
		okButton.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Salir");
		cancelButton.setBackground(Color.WHITE);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		cancelButton.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}


	public void setPedido_a_modificar(Integer Numero_pedido_modificar) {
		// TODO Auto-generated method stub
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
		
	}


	public void setPedido_a_visualizar(Integer Numero_pedido_visualizar) {
		Pedido p = SvPedidos.get_pedido(Numero_pedido_visualizar);
		if(p!=null){
			// Datos del pedido
			label_NroPedido.setText(p.getNumero_Pedido().toString());
			label_ESTADO.setText(p.getESTADO());
			label_TOTAL.setText(formatoImporte.format(p.getTotal()));
			label_Fecha.setText(formato_ddMMyyyy.format(p.getFecha_Hora_Pedido().getTime()));
			
//			p.getEs_Delivery();
			if(p.getCliente()!=null){
				textCliente.setText(p.getCliente().getNombre());
				textDetalle.setText(p.getCliente().getDetalle());
				textTelefono.setText(p.getCliente().getTelefono_Fijo());
				p.getCliente().getID_Cliente();
			}
				
			
			Model_Pedido_Completo model = new Model_Pedido_Completo();
			System.out.println("SIZO LISTA PROD\n"+p.getLista_Productos().size());
			// Productos del pedido
			for (int i = 0; i < p.getLista_Productos().size(); i++) {
				p.getLista_Productos().get(i).getPR_id();
				p.getLista_Productos().get(i).getPR_nombre();
				p.getLista_Productos().get(i).getPR_Observacion();
				p.getLista_Productos().get(i).getPR_precio();
				p.getLista_Productos().get(i).getPR_tipo_producto();
				model.addRow(new Object[] { 
						model.getRowCount() + 1, 
						1, 
						p.getLista_Productos().get(i).getPR_TIPO_PRODUCTO_STRING(), 
						p.getLista_Productos().get(i).getPR_nombre(),
						p.getLista_Productos().get(i).getPR_precio(),
						p.getLista_Productos().get(i).getPR_precio(), 
						p.getLista_Productos().get(i).getPR_Observacion()
						}); 
//				Tabla_Pedido_Completo.setModel(modelo); // Lo seteo en la tabla para que se vea
	
			}
			
			Tabla_Pedido_Completo = new JTable_Listado_Pedidos(model);
			scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
		}
		else
			System.out.println("el pedido llego en null :(");
	}
}
