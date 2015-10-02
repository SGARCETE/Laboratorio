package Interfaz.Auxiliar;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Interfaz.ADM_Repartidor;
import Negocio.Modelo.Repartidor;
import Persistencia.DAOjdbcImpl.RepartidorDAOjdbcImpl;

@SuppressWarnings("serial")
public class eliminar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String[] dato;
	private RepartidorDAOjdbcImpl repartidorDao = new RepartidorDAOjdbcImpl();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			eliminar dialog = new eliminar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public eliminar() {
		setModal(true);
		setBounds(100, 100, 450, 187);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lbltexto = new JLabel("¿Seguro que desea eliminar este registro?");
		lbltexto.setHorizontalAlignment(SwingConstants.CENTER);
		lbltexto.setBounds(10, 11, 414, 52);
		contentPanel.add(lbltexto);
		{
			JLabel lbltexto2 = new JLabel("Estos cambios no se pueden deshacer");
			lbltexto2.setHorizontalAlignment(SwingConstants.CENTER);
			lbltexto2.setBounds(10, 53, 414, 33);
			contentPanel.add(lbltexto2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						repartidorDao.Eliminar_Repartidor(new Repartidor(Integer.parseInt(dato[1]), dato[2], dato[3]));
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void setDato(String[] datoTabla) {
		dato = datoTabla;
	}
}
