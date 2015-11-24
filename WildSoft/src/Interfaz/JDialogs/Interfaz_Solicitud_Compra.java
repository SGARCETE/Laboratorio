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

import Negocio.Modelo.Solicitud_compra;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Solicitud_compra;

public class Interfaz_Solicitud_Compra extends JDialog {
	private static final long serialVersionUID = 7623860179392834538L;
	
	private final JPanel contentPanel = new JPanel();
	private JTable tabla_solicitudes_actuales;
	private Servicio_Solicitud_compra sv_solicitudCompra;
	private Principal_Negocio_Interfaz principal_neg_int;
	private NumberFormat formatoImporte = NumberFormat.getCurrencyInstance();
	private JButton btnConsultar ;
	
	@SuppressWarnings("serial")
	public Interfaz_Solicitud_Compra(final Principal_Negocio_Interfaz prin_neg_int) {
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
		
		tabla_solicitudes_actuales = new JTable();
		tabla_solicitudes_actuales.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "Fecha", "Proveedor", "Precio", "Estado"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Integer.class, Date.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			@Override
			public boolean isCellEditable(int row, int col){
				return false;
			}
		});
		tabla_solicitudes_actuales.getColumnModel().getColumn(0).setResizable(false);
		tabla_solicitudes_actuales.getColumnModel().getColumn(0).setPreferredWidth(0);
		tabla_solicitudes_actuales.getColumnModel().getColumn(0).setMinWidth(0);
		tabla_solicitudes_actuales.getColumnModel().getColumn(0).setMaxWidth(0);
		tabla_solicitudes_actuales.getColumnModel().getColumn(1).setResizable(false);
		tabla_solicitudes_actuales.getColumnModel().getColumn(2).setResizable(false);
		tabla_solicitudes_actuales.getColumnModel().getColumn(3).setResizable(false);
		tabla_solicitudes_actuales.getColumnModel().getColumn(4).setResizable(false);
		tabla_solicitudes_actuales.setRowHeight(18);
		scrollPane.setViewportView(tabla_solicitudes_actuales);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/add-1-icon24.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Agregar_nueva_solicitud();
			}
		});
		btnAgregar.setBounds(10, 339, 100, 34);
		contentPanel.add(btnAgregar);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/subtract-1-icon24.png")));
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Eliminar_solicitud();
			}
		});
		btnBorrar.setBounds(115, 339, 100, 34);
		contentPanel.add(btnBorrar);
		
		JButton btnEditar = new JButton("Modificar");
		btnEditar.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/edit-icon16.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Editar_solicitud();
			}
		});
		btnEditar.setBounds(220, 339, 136, 34);
		contentPanel.add(btnEditar);
		
		JButton btnGenerarSolicitud = new JButton("Generar Solicitud");
		btnGenerarSolicitud.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/Product-sale-report-icon24.png")));
		btnGenerarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Generar_Solicitud();
			}
		});
		btnGenerarSolicitud.setBounds(508, 339, 160, 34);
		contentPanel.add(btnGenerarSolicitud);
		
		JButton btnEnviarSolicitud = new JButton("Enviar Solicitud");
		btnEnviarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Enviar_solicitud_a_proveedor();
			}
		});
		btnEnviarSolicitud.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/Mail-icon24.png")));
		btnEnviarSolicitud.setBounds(798, 339, 145, 34);
		contentPanel.add(btnEnviarSolicitud);
		
		JLabel lblSolicitudesGeneradas = new JLabel("Solicitudes de compra generadas");
		lblSolicitudesGeneradas.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblSolicitudesGeneradas.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolicitudesGeneradas.setBounds(10, 11, 933, 37);
		contentPanel.add(lblSolicitudesGeneradas);
		
		btnConsultar = new JButton("Consultar");
		btnConsultar.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/search-icon24.png")));
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Mostrar_solicitud();
			}
		});
		btnConsultar.setBounds(362, 339, 136, 34);
		contentPanel.add(btnConsultar);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(60, 179, 113));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton cancelButton = new JButton("Salir");
		cancelButton.setIcon(new ImageIcon(Interfaz_Solicitud_Compra.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
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
		llenarTabla();
		

	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Enviar_solicitud_a_proveedor() {
		String estado= (String) tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 4);
		if(tabla_solicitudes_actuales.getSelectedRow() > -1 && estado.equals("Pendiente")){
			Integer ID_SOLICITUD = ((Integer)tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 0));
			boolean EXITO_AL_ENVIAR = sv_solicitudCompra.Enviar_solicitud_a_proveedor(ID_SOLICITUD);
			if(EXITO_AL_ENVIAR){
				llenarTabla();
			}
		}

	}
	
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Editar_solicitud() {
		
		String estado= (String) tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 4);
		if(tabla_solicitudes_actuales.getRowCount()>0  && !estado.equals("Pagada") ) {
			
			Solicitud_compra sc = sv_solicitudCompra.OBTENER_SOLICITUD((Integer)tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 0));
			ADM_Solicitud_Compra frame = new ADM_Solicitud_Compra(principal_neg_int);
			frame.setSolicictud(sc);
			frame.setModal(true);
			frame.setVisible(true);
			llenarTabla();
		}
	}
	private void Mostrar_solicitud() {
		if(tabla_solicitudes_actuales.getRowCount()>0) {
			Solicitud_compra sc = sv_solicitudCompra.OBTENER_SOLICITUD((Integer)tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 0));
			Consultar_Solicitud_Compra frame = new Consultar_Solicitud_Compra(principal_neg_int);
			frame.setSolicictud(sc);
			frame.setModal(true);
			frame.setVisible(true);
			llenarTabla();
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Eliminar_solicitud() {
		int RESPUESTA = JOptionPane.showConfirmDialog(null,"¿Esta seguro que desea eliminar?","Eliminar solicitud",JOptionPane.OK_CANCEL_OPTION);
		if(RESPUESTA == JOptionPane.OK_OPTION){	
			String estado =  (String) tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 4);
			System.out.println(estado);
			if(tabla_solicitudes_actuales.getRowCount()>0 && !estado.equals("Pagada")){
				Integer id = (Integer) tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 0);
				sv_solicitudCompra.ELIMINAD_SOLICITUD_COMPRA(sv_solicitudCompra.OBTENER_SOLICITUD(id));
				llenarTabla();
			}
		}
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Agregar_nueva_solicitud() {
		ADM_Solicitud_Compra frame = new ADM_Solicitud_Compra(principal_neg_int);
		frame.setModal(true);
		frame.setVisible(true);
		llenarTabla();
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> METODOS >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void llenarTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tabla_solicitudes_actuales.getModel();
		modelo.setRowCount(0);
		ArrayList<Solicitud_compra> lista = sv_solicitudCompra.getLISTA_SOLICITUDES();
		for (int i = 0; i < lista.size(); i++) {
			Double precio = 0.0;
			if(lista.get(i).getPrecio()!=null && lista.get(i).getPrecio()!=0){
				precio = lista.get(i).getPrecio().doubleValue();
			}
			Object[] fila = {lista.get(i).getId(),
					lista.get(i).getFecha().getTime(),
					lista.get(i).getProveedor().getNombre(),
					formatoImporte.format(precio),
					lista.get(i).getEstado()};
			modelo.addRow(fila);
		}
		tabla_solicitudes_actuales.setModel(modelo);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	private void Generar_Solicitud() {
		if(tabla_solicitudes_actuales.getSelectedRow()!=-1){
			Integer NUMERO_SOLICITUD =  (Integer) tabla_solicitudes_actuales.getValueAt(tabla_solicitudes_actuales.getSelectedRow(), 0);
			sv_solicitudCompra.Ver_Reporte_solicitud_compra(NUMERO_SOLICITUD);
			
		}
	}
}//--> FIN
