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

import MetAux.MetAux;

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
	private String[] datoTabla;
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnAgregar;

	public ADM_Cliente(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("ABM Cliente");
		Principal = instancia_negocio;
		SvCliente = Principal.getSvClientes();
		
		table = new JTable();
		iniciarlizarTablaCliente();
		
		llenar_tabla();
		
		setResizable(false);
		setBounds(100, 100, 1023, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
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
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregar_cliente();
			}
		});
		btnAgregar.setBounds(108, 193, 89, 49);
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
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aceptar_modificar_Cliente();
			}
		});
		btnAceptar.setBounds(29, 193, 96, 49);
		panel_1.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cancelar_modificar_Cliente();
			}
		});
		btnCancelar.setBounds(171, 193, 96, 49);
		panel_1.add(btnCancelar);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Lista de Clientes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(312, 11, 695, 255);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 643, 223);
		panel_2.add(scrollPane);
		
		
		scrollPane.setViewportView(table);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					Eliminar_cliente();	
				}
			}
		});
		btnEliminar.setBounds(668, 347, 111, 66);
		panel.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		
		
		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(Color.WHITE);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){
					Modificar_Cliente();
				}
				
			}
		});
		btnModificar.setBounds(789, 347, 111, 66);
		panel.add(btnModificar);
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(910, 347, 97, 66);
		panel.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel_3.setBorder(new TitledBorder(null, "Descripci\u00F3n", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_3.setBounds(20, 272, 987, 64);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		lblAviso = new JLabel("");
		lblAviso.setBounds(283, 23, 492, 30);
		panel_3.add(lblAviso);
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		btnAceptar.setVisible(false);
		btnCancelar.setVisible(false);
		
		iniciar_parametros();
	}

	private void iniciar_parametros() {
		MetAux.Limitar_caracteres(textDireccion,20);
		MetAux.Limitar_caracteres(textNombre,20);
		MetAux.Limitar_caracteres(textTelefono,20);
		MetAux.Limitar_caracteres(textDetalle,20);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void agregar_cliente(){
		
		if(!textNombre.getText().equals("")){
			if(!textDireccion.getText().equals("")){
				SvCliente.guardar_nuevo_cliente(new Cliente(textNombre.getText(), textDireccion.getText(), textTelefono.getText(), textDetalle.getText()));
				textNombre.setText("");
				textDireccion.setText("");
				textTelefono.setText("");
				textDetalle.setText("");
				lblAviso.setVisible(false);
				
				iniciarlizarTablaCliente();
				llenar_tabla();
				JOptionPane.showMessageDialog(null, "Cliente agregado con éxito");	
			}
			else {
				lblAviso.setText("Debes completar el campo 'Dirección' para continuar");
				lblAviso.setVisible(true);
			}
		}else {
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void iniciarlizarTablaCliente()	{
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N°","Nombre", "Direcci\u00F3n", "Telefono", "Detalle"
			}
		));
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void llenar_tabla(){
		for(Cliente cliente: SvCliente.get_Lista_Clientes()){
			String[] fila= new String[5];
			fila[0]= cliente.getID_Cliente().toString();
			fila[1]= cliente.getNombre();
			fila[2]= cliente.getDomicilio();
			fila[3]= cliente.getTelefono_Fijo();
			fila[4]= cliente.getDetalle();
			
			((DefaultTableModel) this.table.getModel()).addRow(fila);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Eliminar_cliente() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar este Cliente?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvCliente.Eliminar_cliente(new Cliente(Integer.parseInt(datoTabla[1]),datoTabla[2], datoTabla[3], datoTabla[4], datoTabla[5]));
			iniciarlizarTablaCliente();
			llenar_tabla();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private String[] obtenerSeleccion() {
		int indice = table.getSelectedRow();
		String id = (String) table.getModel().getValueAt(indice, 0);
		String nombre = (String) table.getModel().getValueAt(indice, 1);
		String direccion = (String) table.getModel().getValueAt(indice, 2);
		String telefono = (String) table.getModel().getValueAt(indice, 3);
		String detalle = (String) table.getModel().getValueAt(indice, 4);
		
		String[] dato = { String.valueOf(indice), id, nombre, direccion,telefono,detalle };
		return dato;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Modificar_Cliente() {
		lblAviso.setVisible(false);
		datoTabla = obtenerSeleccion();
		textNombre.setText(datoTabla[2]);
		textDireccion.setText(datoTabla[3]);
		textTelefono.setText(datoTabla[4]);
		textDetalle.setText(datoTabla[5]);
		
		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
		btnAgregar.setVisible(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Cancelar_modificar_Cliente() {
		textNombre.setText("");
		textDireccion.setText("");
		textTelefono.setText("");
		textDetalle.setText("");
		
		
		btnAceptar.setVisible(false);
		btnAgregar.setVisible(true);
		btnCancelar.setVisible(false);
		lblAviso.setVisible(false);
		btnModificar.setVisible(true);
		btnEliminar.setVisible(true);
		
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void guardarCambios(String nombre, String direccion, String telefono, String detalle) {
		SvCliente.Modificar_Cliente(new Cliente(Integer.parseInt(datoTabla[1]), nombre, direccion, telefono, detalle));
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Aceptar_modificar_Cliente() {
		if (!textNombre.getText().equals("")) {
			if (!textDireccion.getText().equals("")) {
				if(!textTelefono.getText().equals(""))
				{
					if(!textDetalle.getText().equals(""))
					{
						guardarCambios(textNombre.getText(), textDireccion.getText(), textTelefono.getText(), textDetalle.getText());
						textNombre.setText("");
						textDireccion.setText("");
						textTelefono.setText("");
						textDetalle.setText("");
						
						iniciarlizarTablaCliente();
						llenar_tabla();
						lblAviso.setVisible(false);
						btnAceptar.setVisible(false);
						btnAgregar.setVisible(true);
						btnCancelar.setVisible(false);
						btnModificar.setVisible(true);
						btnEliminar.setVisible(true);
					}
					else{
						lblAviso.setText("Debes completar el campo 'Detalle' para continuar");
						lblAviso.setVisible(true);
					}
						
				}else{
					lblAviso.setText("Debes completar el campo 'Telefono' para continuar");
					lblAviso.setVisible(true);
				}
			
			}else{
				lblAviso.setText("Debes completar el campo 'Dirección' para continuar");
				lblAviso.setVisible(true);
			}
		} else{
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}//---> FIN CLASE

