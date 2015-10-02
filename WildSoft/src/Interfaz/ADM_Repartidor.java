package Interfaz;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.security.auth.callback.TextOutputCallback;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Modelo.Repartidor;
import Persistencia.DAOjdbcImpl.RepartidorDAOjdbcImpl;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ADM_Repartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textVehiculo;
	private RepartidorDAOjdbcImpl repartidorDao = new RepartidorDAOjdbcImpl();
	private JTable table;

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
		setBounds(100, 100, 836, 378);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		Label label = new Label("Repartidor");
		label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		label.setBounds(147, 10, 138, 22);
		contentPanel.add(label);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(239, 50, 544, 231);
		contentPanel.add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Id", "Nombre", "Vehiculo" }));

		// SE CREA COMPLETA LA TABAL CON LOS DATOS
		llenarTabla();

		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane.setViewportView(table);

		JPanel panelNuevoRepartidor = new JPanel();
		panelNuevoRepartidor.setBounds(10, 50, 214, 152);
		contentPanel.add(panelNuevoRepartidor);
		panelNuevoRepartidor.setLayout(null);

		Label label_3 = new Label("Nuevo Repartidor");
		label_3.setBounds(10, 5, 98, 22);
		panelNuevoRepartidor.add(label_3);

		Label label_1 = new Label("Nombre");
		label_1.setBounds(10, 32, 51, 22);
		panelNuevoRepartidor.add(label_1);

		Label label_2 = new Label("Veh\u00EDculo");
		label_2.setBounds(10, 58, 56, 22);
		panelNuevoRepartidor.add(label_2);

		textNombre = new JTextField();
		textNombre.setBounds(67, 33, 137, 20);
		panelNuevoRepartidor.add(textNombre);
		textNombre.setColumns(10);

		textVehiculo = new JTextField();
		textVehiculo.setBounds(67, 60, 137, 20);
		panelNuevoRepartidor.add(textVehiculo);
		textVehiculo.setColumns(10);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(54, 118, 109, 23);
		panelNuevoRepartidor.add(btnAgregar);

		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!textNombre.equals("")) {
					if (!textVehiculo.equals("")) {
						repartidorDao.Nuevo_Repartidor(new Repartidor(textNombre.getText(), textVehiculo.getText()));
					}
				}
			}
		});

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(10, 118, 89, 23);
		panelNuevoRepartidor.add(btnAceptar);
		btnAceptar.setVisible(false);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(115, 118, 89, 23);
		panelNuevoRepartidor.add(btnCancelar);
		btnCancelar.setVisible(false);

		JButton btnModificar = new JButton("Modificar");
		btnModificar.setBounds(74, 258, 89, 23);
		contentPanel.add(btnModificar);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
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
}
