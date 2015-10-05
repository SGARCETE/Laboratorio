package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;

public class Interfaz_ABM_Pedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable Tabla_Pedido_Completo;
	private JScrollPane scrollPane_Pedido_Completo;
	private Servicio_Pedidos SvPedidos;
	private JTextField textNroPedido;
	private JTextField textEstado;
	private JTextField textCliente;
	private JTextField textDetalle;
	private JTextField textFecha;
	private JTextField textTotal;
	private JTextField textDire;
	private JTextField textTelefono;
	
	/**
	 * Create the dialog.
	 * @param principal_neg_int 
	 */
	public Interfaz_ABM_Pedido(Principal_Negocio_Interfaz principal_neg_int) {
		SvPedidos = principal_neg_int.getSvPedidos();
		
		setTitle("ABM Pedido");
		setBounds(100, 100, 669, 428);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane_Pedido_Completo = new JScrollPane();
		scrollPane_Pedido_Completo.setBounds(286, 42, 355, 291);
		contentPanel.add(scrollPane_Pedido_Completo);
		
		textNroPedido = new JTextField();
		textNroPedido.setBounds(10, 68, 106, 30);
		contentPanel.add(textNroPedido);
		textNroPedido.setColumns(10);
		
		JLabel lblNumeroPedido = new JLabel("N\u00BA Pedido");
		lblNumeroPedido.setBounds(10, 42, 106, 25);
		contentPanel.add(lblNumeroPedido);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 101, 106, 25);
		contentPanel.add(lblEstado);
		
		textEstado = new JTextField();
		textEstado.setColumns(10);
		textEstado.setBounds(10, 127, 106, 30);
		contentPanel.add(textEstado);
		
		JLabel lblDireccion = new JLabel("Cliente");
		lblDireccion.setBounds(10, 218, 106, 25);
		contentPanel.add(lblDireccion);
		
		textCliente = new JTextField();
		textCliente.setColumns(10);
		textCliente.setBounds(10, 244, 106, 30);
		contentPanel.add(textCliente);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(10, 277, 106, 25);
		contentPanel.add(lblDetalle);
		
		textDetalle = new JTextField();
		textDetalle.setColumns(10);
		textDetalle.setBounds(10, 303, 106, 30);
		contentPanel.add(textDetalle);
		
		JLabel lblFecha = new JLabel("Fecha");
		lblFecha.setBounds(144, 42, 106, 25);
		contentPanel.add(lblFecha);
		
		textFecha = new JTextField();
		textFecha.setColumns(10);
		textFecha.setBounds(144, 68, 106, 30);
		contentPanel.add(textFecha);
		
		JLabel lblTotal = new JLabel("Total");
		lblTotal.setBounds(144, 101, 106, 25);
		contentPanel.add(lblTotal);
		
		textTotal = new JTextField();
		textTotal.setColumns(10);
		textTotal.setBounds(144, 127, 106, 30);
		contentPanel.add(textTotal);
		
		JLabel lblDireccion_1 = new JLabel("Direccion");
		lblDireccion_1.setBounds(144, 218, 106, 25);
		contentPanel.add(lblDireccion_1);
		
		textDire = new JTextField();
		textDire.setColumns(10);
		textDire.setBounds(144, 244, 106, 30);
		contentPanel.add(textDire);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(144, 277, 106, 25);
		contentPanel.add(lblTelefono);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(144, 303, 106, 30);
		contentPanel.add(textTelefono);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.menu);
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 185, 262, 145);
		contentPanel.add(panel);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(SystemColor.menu);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Salir");
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
		System.out.println(Numero_pedido_visualizar);
		Pedido p = SvPedidos.get_pedido(Numero_pedido_visualizar);
		// Datos del pedido
		textNroPedido.setText(p.getNumero_Pedido().toString());
		textEstado.setText(p.getESTADO());
		textTotal.setText(p.getTotal().toString());
		textFecha.setText(p.getFecha_Hora_Pedido().getTime()+"");
		textCliente.setText(p.getCliente().getNombre());
		textDetalle.setText(p.getCliente().getDetalle());
		
		textTelefono.setText(p.getCliente().getTelefono_Fijo());
		p.getCliente().getID_Cliente();
		p.getEs_Delivery();

		
		Model_Pedido_Completo model = new Model_Pedido_Completo();
		
		
		// Productos del pedido
		for (int i = 0; i < p.getLista_Productos().size(); i++) {
			p.getLista_Productos().get(i).getPR_id();
			p.getLista_Productos().get(i).getPR_nombre();
			p.getLista_Productos().get(i).getPR_Observacion();
			p.getLista_Productos().get(i).getPR_precio();
			p.getLista_Productos().get(i).getPR_tipo_producto();

			DefaultTableModel modelo = (DefaultTableModel) Tabla_Pedido_Completo.getModel();
			modelo.addRow(new Object[] { 
					modelo.getRowCount() + 1, 
					1, 
					p.getLista_Productos().get(i).getPR_tipo_producto(), 
					p.getLista_Productos().get(i).getPR_nombre(),
					p.getLista_Productos().get(i).getPR_precio(),
					p.getLista_Productos().get(i).getPR_precio(), 
					p.getLista_Productos().get(i).getPR_Observacion()
					}); 
			Tabla_Pedido_Completo.setModel(modelo); // Lo seteo en la tabla para que se vea

		}
		
		Tabla_Pedido_Completo.setModel(model);
	}


	
	

}
