package Interfaz.Repartidor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Negocio.Modelo.Repartidor;
import Persistencia.DAOjdbcImpl.RepartidorDAOjdbcImpl;

@SuppressWarnings("serial")
public class Alta_Repartidor extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textNombre;
	private JTextField textVehiculo;
	private RepartidorDAOjdbcImpl repartidorDao = new RepartidorDAOjdbcImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Alta_Repartidor dialog = new Alta_Repartidor();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Alta_Repartidor() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		Label label = new Label("Repartidor");
		label.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 22));
		label.setBounds(147, 10, 138, 22);
		contentPanel.add(label);
		
		Label label_1 = new Label("Nombre");
		label_1.setBounds(10, 74, 44, 22);
		contentPanel.add(label_1);
		
		Label label_2 = new Label("Veh\u00EDculo");
		label_2.setBounds(10, 102, 57, 22);
		contentPanel.add(label_2);
		
		Label label_3 = new Label("Nuevo Repartidor");
		label_3.setBounds(32, 50, 98, 22);
		contentPanel.add(label_3);
		
		textNombre = new JTextField();
		textNombre.setBounds(70, 74, 88, 22);
		contentPanel.add(textNombre);
		textNombre.setColumns(10);
		
		textVehiculo = new JTextField();
		textVehiculo.setColumns(10);
		textVehiculo.setBounds(70, 102, 88, 22);
		contentPanel.add(textVehiculo);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textNombre.equals("") && textVehiculo.equals(""))){
					repartidorDao.Nuevo_Repartidor(new Repartidor(textNombre.getText(),textVehiculo.getText())); 
				}
			}
		});
		btnAgregar.setBounds(43, 133, 89, 23);
		contentPanel.add(btnAgregar);
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
}
