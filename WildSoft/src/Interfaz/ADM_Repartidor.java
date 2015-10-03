package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
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
	private JLabel lblAvisoError;
	private JButton btnAgregar;
	private JButton btnAceptar;


	/**
	 * Create the dialog.
	 * @param Instancia_Negocio_principal 
	 */
	public ADM_Repartidor(Principal_Negocio_Interfaz Instancia_Negocio_principal) {
		setTitle("ABM Repartidores");
		Principal = Instancia_Negocio_principal;
		SvRepartidor = Principal.getSvRepartidores();
		
		setResizable(false);
		setBounds(100, 100, 813, 391);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		Label lblTitulo = new Label("Repartidor");
		lblTitulo.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		lblTitulo.setBounds(147, 10, 138, 22);
		contentPanel.add(lblTitulo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 50, 544, 231);
		contentPanel.add(scrollPane);

		table = new JTable();

		inicializarTabla();

		// SE CREA COMPLETA LA TABAL CON LOS DATOS
		llenarTabla();

		scrollPane.setViewportView(table);

		JPanel panelNuevoRepartidor = new JPanel();
		panelNuevoRepartidor.setBounds(10, 50, 214, 152);
		contentPanel.add(panelNuevoRepartidor);
		panelNuevoRepartidor.setLayout(null);

		Label lblNuevoRepartidor = new Label("Nuevo Repartidor");
		lblNuevoRepartidor.setBounds(10, 5, 98, 22);
		panelNuevoRepartidor.add(lblNuevoRepartidor);

		Label lblNombre = new Label("Nombre");
		lblNombre.setBounds(10, 32, 51, 25);
		panelNuevoRepartidor.add(lblNombre);

		Label lblVehiculo = new Label("Veh\u00EDculo");
		lblVehiculo.setBounds(10, 58, 56, 25);
		panelNuevoRepartidor.add(lblVehiculo);

		textNombre = new JTextField();
		textNombre.setBounds(67, 33, 137, 25);
		panelNuevoRepartidor.add(textNombre);
		textNombre.setColumns(10);

		textVehiculo = new JTextField();
		textVehiculo.setBounds(67, 60, 137, 25);
		panelNuevoRepartidor.add(textVehiculo);
		textVehiculo.setColumns(10);

		lblAvisoError = new JLabel("Debe completar todos los campos para continuar");
		lblAvisoError.setForeground(Color.RED);
		lblAvisoError.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAvisoError.setBounds(10, 293, 368, 39);
		contentPanel.add(lblAvisoError);
		lblAvisoError.setVisible(false);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/add-1-icon16.png")));
		btnAgregar.setBounds(54, 118, 109, 23);
		panelNuevoRepartidor.add(btnAgregar);

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textNombre.setText("");
				textVehiculo.setText("");
				Agregar_repartidor();
			}
		});

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/Check-3-icon16.png")));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Aceptar_modificar_Repartidor();
			}
		});
		btnAceptar.setBounds(10, 118, 89, 23);
		panelNuevoRepartidor.add(btnAceptar);
		btnAceptar.setVisible(false);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cancelar_modificar_repartidor();
			}
		});
		btnCancelar.setBounds(115, 118, 89, 23);
		panelNuevoRepartidor.add(btnCancelar);
		btnCancelar.setVisible(false);

		btnModificar = new JButton("Modificar");
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRow()!=-1){ 
					Modificar_Repartidor();
				}
			}
		});
		btnModificar.setBounds(492, 292, 80, 60);
		contentPanel.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(672, 292, 80, 60);
		contentPanel.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
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
		btnEliminar.setBounds(582, 292, 80, 60);
		contentPanel.add(btnEliminar);
	}

	protected void Aceptar_modificar_Repartidor() {
		if (!textNombre.getText().equals("")) {
			if (!textVehiculo.getText().equals("")) {
				guardarCambios(textNombre.getText(),textVehiculo.getText());
				inicializarTabla();
				llenarTabla();
				lblAvisoError.setVisible(false);
				btnAceptar.setVisible(false);
				btnAgregar.setVisible(true);
				btnCancelar.setVisible(false);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
			} else {
				lblAvisoError.setVisible(true);
			}
		} else {
			lblAvisoError.setVisible(true); 
		}
	}

	protected void Cancelar_modificar_repartidor() {
		textNombre.setText("");
		textVehiculo.setText("");
		btnAceptar.setVisible(false);
		btnAgregar.setVisible(true);
		btnCancelar.setVisible(false);
		lblAvisoError.setVisible(false);
		btnModificar.setEnabled(true);
		btnEliminar.setEnabled(true);
	}

	protected void Agregar_repartidor() {
		if (!textNombre.getText().equals("")) {
			if (!textVehiculo.getText().equals("")) {
				SvRepartidor.guardar_nuevo_pedido(new Repartidor(textNombre.getText(), textVehiculo.getText()));
				inicializarTabla();
				llenarTabla();
				lblAvisoError.setVisible(false);
				btnAgregar.setVisible(true);
			} else {
				lblAvisoError.setVisible(true);
			}
		} else {
			lblAvisoError.setVisible(true);
		}
	}

	protected void Modificar_Repartidor() {
		lblAvisoError.setVisible(false);
		datoTabla = obtenerSeleccion();
		textNombre.setText(datoTabla[2]);
		textVehiculo.setText(datoTabla[3]);
		btnAgregar.setVisible(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);
		btnModificar.setEnabled(false); 
		btnEliminar.setEnabled(false);
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
