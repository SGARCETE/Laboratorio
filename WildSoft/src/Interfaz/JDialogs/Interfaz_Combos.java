package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import Negocio.Modelo.Combo;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Combos;

@SuppressWarnings("serial")
public class Interfaz_Combos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable tablaCombos;
	private Servicio_Combos svCombos;
	private Principal_Negocio_Interfaz principal_neg_int;
	
	public Interfaz_Combos(final Principal_Negocio_Interfaz prin_neg_int) {
		setTitle("Combos");
		setResizable(false);
		principal_neg_int = prin_neg_int;

		setBounds(100, 100, 969, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 44, 933, 284);
		contentPanel.add(scrollPane);

		tablaCombos = new JTable();
		tablaCombos.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Combo", "Precio"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaCombos.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaCombos.getColumnModel().getColumn(0).setMinWidth(0);
		tablaCombos.getColumnModel().getColumn(0).setMaxWidth(0);
		tablaCombos.getColumnModel().getColumn(1).setResizable(false);
		tablaCombos.getColumnModel().getColumn(2).setResizable(false);
		tablaCombos.getColumnModel().getColumn(2).setPreferredWidth(200);
		tablaCombos.getColumnModel().getColumn(2).setMinWidth(200);
		tablaCombos.getColumnModel().getColumn(2).setMaxWidth(200);
		tablaCombos.setRowHeight(18); 
		
		scrollPane.setViewportView(tablaCombos);

		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_nueva_solicitud();
			}
		});
		btnAgregar.setBounds(10, 333, 100, 39);
		contentPanel.add(btnAgregar);

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablaCombos.getSelectedRow()!=-1){
					eliminarCombo();
				}
			}
		});
		btnBorrar.setBounds(115, 333, 100, 39);
		contentPanel.add(btnBorrar);

		JButton btnEditar = new JButton("Consultar");
		btnEditar.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/search-icon24.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConsultarCombo();
			}
		});
		btnEditar.setBounds(220, 333, 110, 39);
		contentPanel.add(btnEditar);

		JLabel lblListado_Combos = new JLabel("Lista de combos");
		lblListado_Combos.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblListado_Combos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListado_Combos.setBounds(10, 0, 933, 44);
		contentPanel.add(lblListado_Combos);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(60, 179, 113));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Salir");
		cancelButton.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		cancelButton.setActionCommand("Cancelar");
		buttonPane.add(cancelButton);

		inicializar();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void inicializar() {
		svCombos = principal_neg_int.getSvCombos();
		llenarTabla();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void ConsultarCombo() {
		if (tablaCombos.getSelectedRow() != -1) {
			Integer id = Integer.parseInt((String) tablaCombos.getValueAt(tablaCombos.getSelectedRow(),0));
			Combo combo = svCombos.getCombo(id);
			Interfaz_ABM_Combos frame = new Interfaz_ABM_Combos(principal_neg_int);
			frame.setCombo(combo);
			frame.setModal(true);
			frame.setVisible(true);
			llenarTabla();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void eliminarCombo() {
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar?", "Eliminar combo",JOptionPane.OK_CANCEL_OPTION);
		if (RESPUESTA == JOptionPane.OK_OPTION) {
			if (tablaCombos.getRowCount() > 0) {
				Integer id = Integer.parseInt((String) tablaCombos.getValueAt(tablaCombos.getSelectedRow(), 0));
				svCombos.ELIMINAR_Combo(id);
				svCombos.ELIMINAR_Combo_de_pedido((String) tablaCombos.getValueAt(tablaCombos.getSelectedRow(), 1));
				llenarTabla();
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Agregar_nueva_solicitud() {
		Interfaz_ABM_Combos frame = new Interfaz_ABM_Combos(principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
		llenarTabla();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> METODOS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void llenarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tablaCombos.getModel();
		modelo.setRowCount(0);
		ArrayList<Combo> lista = svCombos.getLista_Combos();
		for (int i = 0; i < lista.size(); i++) {
			String[] fila = { String.valueOf(lista.get(i).getId()), lista.get(i).getNombre(), String.valueOf(lista.get(i).getPrecio()) };
			modelo.addRow(fila);
		}
		tablaCombos.setModel(modelo);
	}

}// --> FIN
