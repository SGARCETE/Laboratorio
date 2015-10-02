package Interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Interfaz.Auxiliar.eliminar;
import Negocio.Modelo.Repartidor;
import Persistencia.DAOjdbcImpl.RepartidorDAOjdbcImpl;

@SuppressWarnings("serial")
public class ADM_Repartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textVehiculo;
	private RepartidorDAOjdbcImpl repartidorDao = new RepartidorDAOjdbcImpl();
	private JTable table;
	private String[] datoTabla;
	private JButton btnCancelar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JLabel lblAvisoError;
	private JButton btnAgregar;
	private JButton btnAceptar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ADM_Repartidor dialog = new ADM_Repartidor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ADM_Repartidor() {
		setResizable(false);
		setBounds(100, 100, 836, 378);
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
		lblNombre.setBounds(10, 32, 51, 22);
		panelNuevoRepartidor.add(lblNombre);

		Label lblVehiculo = new Label("Veh\u00EDculo");
		lblVehiculo.setBounds(10, 58, 56, 22);
		panelNuevoRepartidor.add(lblVehiculo);

		textNombre = new JTextField();
		textNombre.setBounds(67, 33, 137, 20);
		panelNuevoRepartidor.add(textNombre);
		textNombre.setColumns(10);

		textVehiculo = new JTextField();
		textVehiculo.setBounds(67, 60, 137, 20);
		panelNuevoRepartidor.add(textVehiculo);
		textVehiculo.setColumns(10);

		lblAvisoError = new JLabel(
				"Debe completar todos los campos para continuar");
		lblAvisoError.setForeground(Color.RED);
		lblAvisoError.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblAvisoError.setBounds(10, 293, 368, 39);
		contentPanel.add(lblAvisoError);
		lblAvisoError.setVisible(false);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(54, 118, 109, 23);
		panelNuevoRepartidor.add(btnAgregar);

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textNombre.getText().equals("")) {
					if (!textVehiculo.getText().equals("")) {
						repartidorDao.Nuevo_Repartidor(new Repartidor(textNombre.getText(), textVehiculo.getText()));
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
		});

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnAceptar.setBounds(10, 118, 89, 23);
		panelNuevoRepartidor.add(btnAceptar);
		btnAceptar.setVisible(false);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textNombre.setText("");
				textVehiculo.setText("");
				btnAceptar.setVisible(false);
				btnAgregar.setVisible(true);
				btnCancelar.setVisible(false);
				lblAvisoError.setVisible(false);
				btnModificar.setEnabled(true);
				btnEliminar.setEnabled(true);
			}
		});
		btnCancelar.setBounds(115, 118, 89, 23);
		panelNuevoRepartidor.add(btnCancelar);
		btnCancelar.setVisible(false);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
		});
		btnModificar.setBounds(490, 304, 89, 28);
		contentPanel.add(btnModificar);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(693, 304, 90, 28);
		contentPanel.add(btnSalir);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				datoTabla = obtenerSeleccion();
				eliminar frame = new eliminar();
				frame.setDato(datoTabla);
				frame.setVisible(true);
				frame.setModal(true);
				frame.setAlwaysOnTop(true);
				inicializarTabla();
				llenarTabla();
			}
		});
		btnEliminar.setBounds(591, 304, 90, 28);
		contentPanel.add(btnEliminar);
	}

	protected void guardarCambios(String nombre, String vehiculo) {
		
		repartidorDao.Modificar_Repartidor(new Repartidor(Integer.parseInt(datoTabla[1]),nombre,vehiculo));
	}

	private void inicializarTabla() {

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Id", "Nombre", "Vehiculo" }));

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
	}

	private void llenarTabla() {

		for (Repartidor repartidor : repartidorDao.getRepartidores()) {
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
