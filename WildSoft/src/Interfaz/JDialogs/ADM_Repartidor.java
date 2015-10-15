package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Negocio.Modelo.Repartidor;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Repartidores;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

public class ADM_Repartidor extends JDialog {
	private static final long serialVersionUID = 5207847723773149517L;
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textVehiculo;

	private Servicio_Repartidores SvRepartidor;
	private Principal_Negocio_Interfaz Principal;
	private JTable table;
	private String[] datoTabla;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JLabel lblAviso;
	private JButton btnAgregar;
	private JButton btnAceptar;
	private JPanel panel_1;
	private JPanel panel_2;


	/**
	 * Create the dialog.
	 * @param Instancia_Negocio_principal 
	 */
	public ADM_Repartidor(Principal_Negocio_Interfaz Instancia_Negocio_principal) {
		setTitle("ABM Repartidores");
		Principal = Instancia_Negocio_principal;
		SvRepartidor = Principal.getSvRepartidores();
		
		setResizable(false);
		setBounds(100, 100, 574, 358);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		table = new JTable();
		inicializarTabla();

		// SE CREA COMPLETA LA TABAL CON LOS DATOS
		llenarTabla();
		
		JPanel panel = new JPanel();
		panel.setBounds(7, 198, 552, 53);
		panel.setBackground(Color.WHITE);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Descripcion", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPanel.add(panel);
		panel.setLayout(null);

		lblAviso = new JLabel("");
		lblAviso.setHorizontalAlignment(SwingConstants.CENTER);
		lblAviso.setBounds(16, 19, 506, 20);
		panel.add(lblAviso);
		lblAviso.setForeground(Color.RED);
		lblAviso.setFont(new Font("SansSerif", Font.BOLD, 15));
		
		panel_1 = new JPanel();
		panel_1.setBounds(7, 6, 235, 180);
		contentPanel.add(panel_1);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo Repartidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_1.setLayout(null);
		
		Label lblNombre = new Label("Nombre");
		lblNombre.setBounds(21, 39, 51, 25);
		panel_1.add(lblNombre);
				
		textNombre = new JTextField();
		textNombre.setBounds(78, 40, 137, 25);
		panel_1.add(textNombre);
		textNombre.setColumns(10);
		
		Label lblVehiculo = new Label("Veh\u00EDculo");
		lblVehiculo.setBounds(21, 82, 56, 25);
		panel_1.add(lblVehiculo);
								
		textVehiculo = new JTextField();
		textVehiculo.setBounds(78, 84, 137, 25);
		panel_1.add(textVehiculo);
		textVehiculo.setColumns(10);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(21, 133, 89, 30);
		panel_1.add(btnAceptar);
		btnAceptar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/Check-3-icon16.png")));
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(65, 133, 109, 30);
		panel_1.add(btnAgregar);
		btnAgregar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/add-1-icon16.png")));
				
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(126, 133, 89, 30);
		panel_1.add(btnCancelar);
		
		panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Repartidores existentes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBackground(Color.WHITE);
		panel_2.setBounds(254, 6, 305, 180);
		contentPanel.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 20, 287, 154);
		panel_2.add(scrollPane);
		

						
		scrollPane.setViewportView(table);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cancelar_modificar_repartidor();
			}
		});
		btnCancelar.setVisible(false);

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_repartidor();
			}
		});
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aceptar_modificar_Repartidor();
			}
		});
		btnAceptar.setVisible(false);
		lblAviso.setVisible(false);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(60, 179, 113));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
				
		btnEliminar = new JButton(" Eliminar ");
		btnEliminar.setBackground(Color.WHITE);
		buttonPane.add(btnEliminar);
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/delete-1-icon24.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow()!=-1){ 
					Eliminar_Repartidor();
				}
			}
		});

		btnModificar = new JButton("Modificar");
		btnModificar.setBackground(Color.WHITE);
		buttonPane.add(btnModificar);
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		
		JButton btnSalir = new JButton("    Salir     ");
		btnSalir.setBackground(Color.WHITE);
		buttonPane.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1){ 
					Modificar_Repartidor();
				}
			}
		});
	}

	protected void Aceptar_modificar_Repartidor() {
		if (!textNombre.getText().equals("")) {
			if (!textVehiculo.getText().equals("")) {
				guardarCambios(textNombre.getText(),textVehiculo.getText());
				textNombre.setText("");
				textVehiculo.setText("");
				inicializarTabla();
				llenarTabla();
				lblAviso.setVisible(false);
				btnAceptar.setVisible(false);
				btnAgregar.setVisible(true);
				btnCancelar.setVisible(false);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
				panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo Repartidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
			} else {
				lblAviso.setVisible(true);
			}
		} else {
			lblAviso.setVisible(true); 
		}
	}

	protected void Cancelar_modificar_repartidor() {
		textNombre.setText("");
		textVehiculo.setText("");
		btnAceptar.setVisible(false);
		btnAgregar.setVisible(true);
		btnCancelar.setVisible(false);
		lblAviso.setVisible(false);
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Nuevo Repartidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
	}

	protected void Agregar_repartidor() {
		if (!textNombre.getText().equals("")) {
			if (!textVehiculo.getText().equals("")) {
				SvRepartidor.guardar_nuevo_pedido(new Repartidor(textNombre.getText(), textVehiculo.getText()));
				textNombre.setText("");
				textVehiculo.setText("");
				inicializarTabla();
				llenarTabla();
				lblAviso.setVisible(false);
				btnAgregar.setVisible(true);
			} else {
				lblAviso.setText("Debe completar el campo 'Vehiculo' para continuar");
				lblAviso.setVisible(true);
			}
		} else {
			lblAviso.setText("Debe completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
		}
	}

	protected void Modificar_Repartidor() {
		lblAviso.setVisible(false);
		datoTabla = obtenerSeleccion();
		textNombre.setText(datoTabla[2]);
		textVehiculo.setText(datoTabla[3]);
		btnAgregar.setVisible(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		btnModificar.setEnabled(false); 
		btnEliminar.setEnabled(false);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Modificar Repartidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
	}

	protected void Eliminar_Repartidor() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Seguro que desea eliminar este registro?\nEstos cambios no se pueden deshacer!","CONFIRMAR",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION ){
			SvRepartidor.eliminar_Repartidor(new Repartidor(Integer.parseInt(datoTabla[1]), datoTabla[2], datoTabla[3]));
			inicializarTabla();
			llenarTabla();
		}
	}

	protected void guardarCambios(String nombre, String vehiculo) {
		SvRepartidor.Modificar_Repartidor(new Repartidor(Integer.parseInt(datoTabla[1]),nombre,vehiculo));
	}

	private void inicializarTabla() {
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Id", "Nombre", "Vehiculo" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
	}

	private void llenarTabla() {
		for (Repartidor repartidor : SvRepartidor.get_Repartidores()) {
			String[] fila = new String[3];
			fila[0] = repartidor.getID_Repartidor().toString();
			fila[1] = repartidor.getNombre();
			fila[2] = repartidor.getVehiculo();

			((DefaultTableModel) this.table.getModel()).addRow(fila);
		}
	}

	private String[] obtenerSeleccion() {
		int indice = table.getSelectedRow();
		String id = (String) table.getModel().getValueAt(indice, 0);
		String nombre = (String) table.getModel().getValueAt(indice, 1);
		String vehiculo = (String) table.getModel().getValueAt(indice, 2);
		String[] dato = { String.valueOf(indice), id, nombre, vehiculo };
		return dato;
	}
}
