package Interfaz.JDialogs;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

public class Interfaz_Itinerario_entregas {

	private JFrame frame;
	private JTable tabla_pedidos;
	private JTable tabla_itinerario;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz_Itinerario_entregas window = new Interfaz_Itinerario_entregas();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz_Itinerario_entregas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAgergar = new JButton("Agergar");
		btnAgergar.setBounds(450, 106, 89, 23);
		frame.getContentPane().add(btnAgergar);
		
		JButton btnNewButton = new JButton("Quitar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(450, 177, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnQuitarTodo = new JButton("Quitar all");
		btnQuitarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnQuitarTodo.setBounds(450, 241, 89, 23);
		frame.getContentPane().add(btnQuitarTodo);
		
		JButton btnGenerarItinerario = new JButton("Generar Itinerario");
		btnGenerarItinerario.setBounds(556, 589, 140, 73);
		frame.getContentPane().add(btnGenerarItinerario);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(706, 589, 80, 73);
		frame.getContentPane().add(btnSalir);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 49, 430, 481);
		frame.getContentPane().add(scrollPane);
		
		tabla_pedidos = new JTable();
		tabla_pedidos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pedido", "Cliente","Direccion", "Fecha", "Delivery", "Estado", "Total"
			}
		));
		scrollPane.setViewportView(tabla_pedidos);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(549, 49, 449, 481);
		frame.getContentPane().add(scrollPane_1);
		
		tabla_itinerario = new JTable();
		tabla_itinerario.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"Pedido", "Cliente","Direccion", "Telefono", "Estado", "Precio"
				}
			));
		scrollPane_1.setViewportView(tabla_itinerario);
		
		JLabel lblListaDePedidos = new JLabel("Lista De Pedidos");
		lblListaDePedidos.setBounds(153, 11, 109, 27);
		frame.getContentPane().add(lblListaDePedidos);
		
		JLabel lblItinerarioDeEntrega = new JLabel("Itinerario De Entrega");
		lblItinerarioDeEntrega.setBounds(723, 17, 154, 14);
		frame.getContentPane().add(lblItinerarioDeEntrega);
	}
}
