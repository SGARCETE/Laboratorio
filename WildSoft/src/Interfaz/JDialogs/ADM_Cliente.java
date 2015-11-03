package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Modelo.Cliente;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Clientes;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ADM_Cliente extends JDialog {

	
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel= new JPanel();
	
	private Principal_Negocio_Interfaz Principal;
	private Servicio_Clientes SvCliente;
	private JTextField textNombre;
	private JTable table;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textDetalle;
	private JLabel lblAviso;
	

	public ADM_Cliente(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("ABM Cliente");
		Principal = instancia_negocio;
		SvCliente = Principal.getSvClientes();
		
		setResizable(false);
		setBounds(100, 100, 1023, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Nuevo Cliente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 11, 299, 255);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 35, 63, 14);
		panel_1.add(lblNombre);
		
		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(10, 76, 63, 14);
		panel_1.add(lblDireccin);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(10, 112, 63, 14);
		panel_1.add(lblTelefono);
		
		textNombre = new JTextField();
		textNombre.setBounds(79, 28, 210, 28);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		JLabel lblDetalle = new JLabel("Detalle");
		lblDetalle.setBounds(10, 151, 63, 14);
		panel_1.add(lblDetalle);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar_cliente();
			}
		});
		btnAgregar.setBounds(108, 195, 89, 45);
		panel_1.add(btnAgregar);
		
		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(79, 69, 210, 28);
		panel_1.add(textDireccion);
		
		textTelefono = new JTextField();
		textTelefono.setColumns(10);
		textTelefono.setBounds(79, 105, 210, 28);
		panel_1.add(textTelefono);
		
		textDetalle = new JTextField();
		textDetalle.setColumns(10);
		textDetalle.setBounds(79, 144, 210, 28);
		panel_1.add(textDetalle);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lista de Clientes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(312, 11, 695, 255);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 643, 223);
		panel_2.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Direcci\u00F3n", "Telefono", "Detalle"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(102);
		table.getColumnModel().getColumn(1).setPreferredWidth(112);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEliminar.setBounds(668, 347, 111, 66);
		panel.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(789, 347, 111, 66);
		panel.add(btnModificar);
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(910, 347, 97, 66);
		panel.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(null, "Descripci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 272, 987, 64);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblAviso = new JLabel("");
		lblAviso.setBounds(283, 23, 492, 30);
		panel_3.add(lblAviso);
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("SansSerif", Font.BOLD, 15));
	}
	
	private void agregar_cliente(){
		if(!textNombre.getText().equals(""))
		{
			if(!textDireccion.getText().equals(""))
			{
				if(!textTelefono.getText().equals(""))
				{
					if(!textDetalle.getText().equals(""))
					{
						SvCliente.guardar_nuevo_cliente(new Cliente(textNombre.getText(), textDireccion.getText(), textTelefono.getText(), textDetalle.getText()));
						textNombre.setText("");
						textDireccion.setText("");
						textTelefono.setText("");
						textDetalle.setText("");
						lblAviso.setVisible(false);
						
						JOptionPane.showMessageDialog(null, "Cliente agregado con éxito");	
						
						
					}
					else {
					lblAviso.setText("Debes completar el campo 'Detalle' para continuar");
					lblAviso.setVisible(true);
					}
				}
				else {
					lblAviso.setText("Debes completar el campo 'Telefono' para continuar");
					lblAviso.setVisible(true);
					}
			}
			else {
				lblAviso.setText("Debes completar el campo 'Dirección' para continuar");
				lblAviso.setVisible(true);
				}
		}
		else {
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
			}
	}
}
