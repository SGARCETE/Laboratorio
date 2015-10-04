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

public class Interfaz_ABM_Pedido extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable Tabla_Pedido_Completo;
	private JScrollPane scrollPane_Pedido_Completo;
	private JTable table;
	private Servicio_Pedidos SvPedidos;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	/**
	 * Create the dialog.
	 * @param principal_neg_int 
	 */
	public Interfaz_ABM_Pedido(Principal_Negocio_Interfaz principal_neg_int) {
		SvPedidos = principal_neg_int.getSvPedidos();
		
		setTitle("ABM Pedido");
		setBounds(100, 100, 556, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.menu);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		scrollPane_Pedido_Completo = new JScrollPane();
		scrollPane_Pedido_Completo.setBounds(198, 74, 332, 224);
		contentPanel.add(scrollPane_Pedido_Completo);
		{
			table = new JTable();
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column"
				}
			) {
				Class[] columnTypes = new Class[] {
					Float.class, Float.class, Boolean.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			scrollPane_Pedido_Completo.setViewportView(table);
		}
		
		textField = new JTextField();
		textField.setBounds(10, 120, 86, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 176, 86, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 244, 86, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNumeroPedido = new JLabel("NUMERO PEDIDO");
		lblNumeroPedido.setBounds(10, 74, 101, 35);
		contentPanel.add(lblNumeroPedido);
		
		JLabel lblFecha = new JLabel("FECHA");
		lblFecha.setBounds(10, 140, 101, 35);
		contentPanel.add(lblFecha);
		
		JLabel lblCantidadProductos = new JLabel("CANTIDAD PRODUCTOS");
		lblCantidadProductos.setBounds(10, 198, 128, 35);
		contentPanel.add(lblCantidadProductos);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.menu);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setIcon(new ImageIcon(Interfaz_ABM_Pedido.class.getResource("/Recursos/IMG/sign-check-icon24.png")));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
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
		}
	}


	public void setPedido_a_modificar(Integer Numero_pedido_modificar) {
		// TODO Auto-generated method stub
		Tabla_Pedido_Completo = new JTable_Pedido_Completo(new Model_Pedido_Completo());
		scrollPane_Pedido_Completo.setViewportView(Tabla_Pedido_Completo);
		
	}


	public void setPedido_a_visualizar(Integer Numero_pedido_visualizar) {
		// TODO Auto-generated method stub
		
	}


	
	
	public void ver_pedido_por_numero(Integer NUMERO_PEDIDO) {
		Pedido Pedido_solicitado= SvPedidos.get_pedido(NUMERO_PEDIDO);
		textField.setText(Pedido_solicitado.getNumero_Pedido().toString());
		textField_1.setText(Pedido_solicitado.getESTADO().toString());
		textField_2.setText(Pedido_solicitado.getTotal().toString());
		
	}
	
}
