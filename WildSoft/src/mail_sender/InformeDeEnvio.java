package mail_sender;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class InformeDeEnvio extends JDialog {
	private static final long serialVersionUID = -1920702195011036775L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblEnvioRealizado;
	private JLabel lblEnviando;
	private JLabel lblPreparandoDatos;
	private JLabel lblConectando;
	private JLabel prepIMG;
	private JLabel conIMG;
	private JLabel envIMG;
	private JLabel lbl_TITULO;
			
	public InformeDeEnvio() {
		setTitle("Estado del envio");
		setBounds(100, 100, 263, 271);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			lbl_TITULO = new JLabel("to:");
			lbl_TITULO.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_TITULO.setFont(new Font("Arial", Font.PLAIN, 14));
			lbl_TITULO.setBounds(10, 10, 227, 30);
			contentPanel.add(lbl_TITULO);
		}
		{
			lblPreparandoDatos = new JLabel("Preparacion de datos");
			lblPreparandoDatos.setFont(new Font("Arial", Font.PLAIN, 14));
			lblPreparandoDatos.setBounds(10, 46, 149, 30);
			contentPanel.add(lblPreparandoDatos);
		}
		{
			lblConectando = new JLabel("Conectar");
			lblConectando.setFont(new Font("Arial", Font.PLAIN, 14));
			lblConectando.setBounds(10, 82, 149, 30);
			contentPanel.add(lblConectando);
		}
		{
			lblEnviando = new JLabel("Enviar");
			lblEnviando.setFont(new Font("Arial", Font.PLAIN, 14));
			lblEnviando.setBounds(10, 118, 149, 30);
			contentPanel.add(lblEnviando);
		}
		{
			lblEnvioRealizado = new JLabel("");
			lblEnvioRealizado.setForeground(new Color(0, 153, 0));
			lblEnvioRealizado.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblEnvioRealizado.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnvioRealizado.setBounds(10, 153, 191, 30);
			contentPanel.add(lblEnvioRealizado);
		}
		{
			prepIMG = new JLabel("");
			prepIMG.setBounds(169, 46, 32, 32);
			contentPanel.add(prepIMG);
		}
		{
			conIMG = new JLabel("");
			conIMG.setBounds(169, 82, 32, 32);
			contentPanel.add(conIMG);
		}
		{
			envIMG = new JLabel("");
			envIMG.setBounds(169, 118, 32, 32);
			contentPanel.add(envIMG);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				JButton cancelButton = new JButton("Cerrar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	public void setTitulo(String T){
		lbl_TITULO.setText(T);
	}
	
	public void SetPreparando(){
		lblPreparandoDatos.setText("Preparando datos...");
	}
	public void SetConectando(){
		prepIMG.setIcon(new ImageIcon("recursos\\check-icon32.png"));
		lblConectando.setText("Conectando...");
	}
	public void SetEnviando(){
		conIMG.setIcon(new ImageIcon("recursos\\check-icon32.png"));
		lblEnviando.setText("Enviando...");
	}
	public void SetEnviado(boolean Enviado){
		if(Enviado){
			envIMG.setIcon(new ImageIcon("recursos\\check-icon32.png"));
			lblEnvioRealizado.setForeground(new Color(0, 153, 0));
			lblEnvioRealizado.setText("ENVIO REALIZADO CON EXITO!");
		}else{
			lblEnvioRealizado.setForeground(new Color(153, 0, 0));
			lblEnvioRealizado.setText("FALLO EL ENVIO");
		}
	}
}//--> FIN INFORMEDEENVIO
