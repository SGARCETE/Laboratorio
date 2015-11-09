package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Negocio.Servicios.Principal_Negocio_Interfaz;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JTable;

public class ADM_Categorias extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPanel = new JPanel();
	private Principal_Negocio_Interfaz Principal; 
	private JTextField textField;
	private JTable table;
	private JTable table_1;
	
	public ADM_Categorias(Principal_Negocio_Interfaz instancia_negocio){
		setTitle("Administracion de Categoria");
		Principal = instancia_negocio;
		
		setResizable(false);
		setBounds(100, 100, 726, 399);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 32, 164, 259);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCategoriaProducto = new JButton("");
		btnCategoriaProducto.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/Actions-go-previous-icon32.png")));
		btnCategoriaProducto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCategoriaProducto.setBounds(247, 83, 59, 47);
		panel.add(btnCategoriaProducto);
		
		JButton btnCategoriaMateria = new JButton("");
		btnCategoriaMateria.setIcon(new ImageIcon(ADM_Categorias.class.getResource("/Recursos/IMG/Actions-go-next-icon32.png")));
		btnCategoriaMateria.setBounds(329, 83, 59, 47);
		panel.add(btnCategoriaMateria);
		
		JLabel lblSeleccioneTipoCategoria = new JLabel("Seleccione Tipo Categoria");
		lblSeleccioneTipoCategoria.setBounds(237, 32, 173, 40);
		panel.add(lblSeleccioneTipoCategoria);
		
		JLabel lblIngreseNombre = new JLabel("Ingrese Nombre");
		lblIngreseNombre.setBounds(278, 141, 91, 40);
		panel.add(lblIngreseNombre);
		
		textField = new JTextField();
		textField.setBounds(262, 179, 126, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(434, 32, 164, 259);
		panel.add(scrollPane_1);
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(280, 236, 89, 23);
		panel.add(btnAgregar);
		
		
	}
}
