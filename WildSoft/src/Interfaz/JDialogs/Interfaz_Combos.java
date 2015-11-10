package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

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
import Negocio.Modelo.Solicitud_compra;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Combos;
import Negocio.Servicios.Servicio_Solicitud_compra;

public class Interfaz_Combos extends JDialog {
	private static final long serialVersionUID = 7623860179392834538L;
	
	private final JPanel contentPanel = new JPanel();
	private JTable tabla_combos_actuales;
	private Servicio_Solicitud_compra sv_solicitudCompra;
	private Servicio_Combos sv_combos;
	private Principal_Negocio_Interfaz principal_neg_int;
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
	
	public Interfaz_Combos(final Principal_Negocio_Interfaz prin_neg_int) {
		setTitle("Solicitudes de compra generadas");
		setResizable(false);
		principal_neg_int = prin_neg_int;
		
		
		setBounds(100, 100, 969, 455);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 59, 933, 274);
		contentPanel.add(scrollPane);
		
		tabla_combos_actuales = new JTable();
		tabla_combos_actuales.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nro", "Combo", "Precio"
			}
		) {
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tabla_combos_actuales.getColumnModel().getColumn(1).setResizable(false);
		tabla_combos_actuales.getColumnModel().getColumn(2).setResizable(false);
		tabla_combos_actuales.setRowHeight(18);
		scrollPane.setViewportView(tabla_combos_actuales);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_nueva_solicitud();
			}
		});
		btnAgregar.setBounds(10, 339, 100, 28);
		contentPanel.add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar_solicitud();
			}
		});
		btnBorrar.setBounds(115, 339, 100, 28);
		contentPanel.add(btnBorrar);
		
		JButton btnEditar = new JButton("Consultar");
		btnEditar.setIcon(new ImageIcon(Interfaz_Combos.class.getResource("/Recursos/IMG/search-icon24.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mostrar_solicitud();
			}
		});
		btnEditar.setBounds(220, 339, 110, 28);
		contentPanel.add(btnEditar);
		
		JLabel lblListado_Combos = new JLabel("Lista de combos");
		lblListado_Combos.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblListado_Combos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListado_Combos.setBounds(10, 11, 933, 37);
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
		sv_solicitudCompra = principal_neg_int.getSvSolicitudCompra();
		sv_combos = principal_neg_int.getSvCombos();
		llenarTabla();
	}
	
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Mostrar_solicitud() {
		if(tabla_combos_actuales.getRowCount()>0){
			Solicitud_compra sc = sv_solicitudCompra.OBTENER_SOLICITUD((Integer)tabla_combos_actuales.getValueAt(tabla_combos_actuales.getSelectedRow(), 0));
			Interfaz_ABM_Combos frame = new Interfaz_ABM_Combos(principal_neg_int);
			frame.setSolicictud(sc);
			frame.setModal(true);
			frame.setVisible(true);
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Eliminar_solicitud() {
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar?","Eliminar combo",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION){		
			if(tabla_combos_actuales.getRowCount()>0){
				Integer id = (Integer) tabla_combos_actuales.getValueAt(tabla_combos_actuales.getSelectedRow(), 0);
			    sv_combos.ELIMINAR_Combo(id);
				sv_solicitudCompra.ELIMINAD_SOLICITUD_COMPRA(sv_solicitudCompra.OBTENER_SOLICITUD(id));
				llenarTabla();
			}
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Agregar_nueva_solicitud() {
		Interfaz_ABM_Combos frame = new Interfaz_ABM_Combos(principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> METODOS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void llenarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tabla_combos_actuales.getModel();
		modelo.setRowCount(0);
		ArrayList<Combo> lista = sv_combos.getLista_Combos();
		for (int i = 0; i < lista.size(); i++) {
			Double precio = 0.0;
			if(lista.get(i).getPrecio()!=null && lista.get(i).getPrecio()!=0){
				precio = lista.get(i).getPrecio().doubleValue();
			}
			Object[] fila = {lista.get(i).getId(),
					lista.get(i).getNombre(),
					lista.get(i).getPrecio()};
			modelo.addRow(fila);
		}
		tabla_combos_actuales.setModel(modelo);
	}

}//--> FIN
