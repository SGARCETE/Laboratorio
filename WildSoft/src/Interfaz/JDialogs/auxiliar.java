package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class auxiliar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;


	public auxiliar(int id) {
		setBounds(100, 100, 369, 170);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(120, 54, 188, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Al agregar el precio a esta solicitud cambiara el estado a \"Pagada\"");
		lblNewLabel.setBounds(10, 11, 414, 32);
		contentPanel.add(lblNewLabel);
		
		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(20, 57, 46, 14);
		contentPanel.add(lblTotal);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
