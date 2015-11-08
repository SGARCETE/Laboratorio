package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Negocio.Modelo.Proveedor;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Proveedores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class Interfaz_Proveedores extends JDialog {

	private JPanel contentPanel = new JPanel();

	private Servicio_Proveedores SvProveedores;
	
	private JTable table;
	private JTable tablaCategorias;
	
	private JTextField textNombre;
	private JTextField textDireccion;
	private JTextField textTelefono;
	private JTextField textMail;
	
	private String[] datoTabla;
	
	private JButton btnEliminar;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JButton btnNuevoProveedor;
	private JButton btnAgregar;
	private JButton btnQuitar;
	
	private JComboBox<String> comboCategorias;
	
	private JScrollPane scrollPane_1;
	
	private JLabel lblAviso;
	
	private HashMap<Integer, String> categorias;
	
	private ArrayList<String> categoriasTabla;

	private boolean esModificacion;

	public Interfaz_Proveedores(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Proveedores");
		SvProveedores = instancia_negocio.getSvProveedores();

		table = new JTable();
		iniciarlizarTablaProveedor();

		completarTablaProveedores();

		setResizable(false);
		setBounds(100, 100, 1023, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelAltaModificacion = new JPanel();
		panelAltaModificacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proveedores", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAltaModificacion.setBounds(10, 11, 299, 402);
		panel.add(panelAltaModificacion);
		panelAltaModificacion.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(16, 35, 63, 14);
		panelAltaModificacion.add(lblNombre);

		textNombre = new JTextField();
		textNombre.setBounds(79, 28, 210, 28);
		panelAltaModificacion.add(textNombre);
		textNombre.setColumns(10);

		textDireccion = new JTextField();
		textDireccion.setColumns(10);
		textDireccion.setBounds(79, 68, 210, 28);
		panelAltaModificacion.add(textDireccion);

		textTelefono = new JTextField();
		textTelefono.addKeyListener(new KeyAdapter(){
		   public void keyTyped(KeyEvent e){
		      char caracter = e.getKeyChar();
		      if(((caracter < '0') || (caracter > '9'))
		    		  && (caracter != KeyEvent.VK_BACK_SPACE)
		    		  && (caracter != '(')
		    		  && (caracter != ')')
		    		  && (caracter != '+')
		    		  && (caracter != KeyEvent.VK_MINUS)
		    		  && (caracter != KeyEvent.VK_SPACE)
		    		  && (caracter != KeyEvent.VK_LEFT)
		    		  && (caracter != KeyEvent.VK_RIGHT)) {
		         e.consume();
		      }
		   }
		});
		textTelefono.setColumns(10);
		textTelefono.setBounds(79, 108, 210, 28);
		panelAltaModificacion.add(textTelefono);

		textMail = new JTextField();
		textMail.setColumns(10);
		textMail.setBounds(79, 148, 210, 28);
		panelAltaModificacion.add(textMail);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarDatos()){
					if (esModificacion){
						
					}else{
						Proveedor p = new Proveedor();
						p.setNombre(textNombre.getText());
						System.out.println(textNombre.getText());
						p.setDireccion(textDireccion.getText());
						System.out.println(textDireccion.getText());
						p.setMail(textMail.getText());
						System.out.println(textMail.getText());
						p.setTelefono(textTelefono.getText());
						System.out.println(textTelefono.getText());
						ArrayList<Integer> lista = new ArrayList<Integer>();
						for (int i = 0; i < tablaCategorias.getRowCount(); i++) {
							lista.add(Integer.parseInt((String) tablaCategorias.getValueAt(i, 0)));
						}
						p.setCategoria(lista);
						System.out.println(lista.size());
						SvProveedores.AGREGAR_PROVEEDOR(p);
					}
					camposHabilitados(false);
					resetearCampos();
				}
			}
		});
		btnAceptar.setBounds(16, 351, 96, 28);
		panelAltaModificacion.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposHabilitados(false);
				resetearCampos();
			}
		});
		btnCancelar.setBounds(193, 351, 96, 28);
		panelAltaModificacion.add(btnCancelar);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(16, 75, 63, 14);
		panelAltaModificacion.add(lblDireccin);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(16, 115, 63, 14);
		panelAltaModificacion.add(lblTelefono);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(16, 155, 63, 14);
		panelAltaModificacion.add(lblMail);

		comboCategorias = new JComboBox<String>();
		comboCategorias.setBounds(16, 181, 273, 26);
		panelAltaModificacion.add(comboCategorias);

		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recorro el mapa buscando la id del elemento seleccionado del combo
				Integer id = -1;
				for (HashMap.Entry<Integer, String> entry : categorias.entrySet()) {
					String value = entry.getValue();
					if(comboCategorias.getSelectedItem().equals(value)){
						id = entry.getKey();;
					}			    
				}
				
				// Al encontrarlo recorro la tabla de proveedores para comprobar que ya lo haya agregado
				boolean estaAgregado = false;
				for (int i = 0; i < categoriasTabla.size(); i++) {
					if(categoriasTabla.get(i).equals(comboCategorias.getSelectedItem())){
						estaAgregado = true;
					}
				}
				
				// Por ultimo si no esta en la tabla, agrego la id en la columna oculta y el nombre de la 
				if(!estaAgregado){
					categoriasTabla.add((String) comboCategorias.getSelectedItem());
					DefaultTableModel modelo = (DefaultTableModel) tablaCategorias.getModel();
					String[] arreglo = {String.valueOf(id), (String) comboCategorias.getSelectedItem()};
					modelo.addRow(arreglo);
					tablaCategorias.setModel(modelo);
				}
			}
		});
		btnAgregar.setBounds(16, 212, 90, 28);
		panelAltaModificacion.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		btnQuitar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tablaCategorias.getSelectedRow()!=-1){
					categoriasTabla.remove((String) tablaCategorias.getValueAt(tablaCategorias.getSelectedRow(), 1));
					DefaultTableModel modelo = (DefaultTableModel) tablaCategorias.getModel();
					modelo.removeRow(tablaCategorias.getSelectedRow());
					tablaCategorias.setModel(modelo);
				}
			}
		});
		btnQuitar.setBounds(199, 212, 90, 28);
		panelAltaModificacion.add(btnQuitar);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(16, 252, 265, 87);
		panelAltaModificacion.add(scrollPane_1);

		tablaCategorias = new JTable();
		tablaCategorias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Categorias"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablaCategorias.getColumnModel().getColumn(0).setPreferredWidth(0);
		tablaCategorias.getColumnModel().getColumn(0).setMinWidth(0);
		tablaCategorias.getColumnModel().getColumn(0).setMaxWidth(0);
		scrollPane_1.setViewportView(tablaCategorias);
		
		btnNuevoProveedor = new JButton("Nuevo Proveedor");
		btnNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				camposHabilitados(true);
			}
		});
		btnNuevoProveedor.setBounds(16, 351, 265, 28);
		panelAltaModificacion.add(btnNuevoProveedor);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(null, "Lista de Proveedores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(312, 11, 695, 345);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 679, 318);
		panel_2.add(scrollPane);

		scrollPane.setViewportView(table);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					eliminarProveedor();
				}

			}
		});
		btnEliminar.setBounds(668, 368, 111, 45);
		panel.add(btnEliminar);
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class .getResource("/Recursos/IMG/delete-1-icon24.png")));

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1) {
					modificarProveedor();
				}
			}
		});
		btnModificar.setBounds(789, 368, 111, 45);
		panel.add(btnModificar);
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setBounds(910, 368, 97, 45);
		panel.add(btnSalir);
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));

		lblAviso = new JLabel("");
		lblAviso.setForeground(Color.RED);
		lblAviso.setBounds(322, 378, 334, 24);
		panel.add(lblAviso);
		
		iniciarDatos();
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>       METODOS       >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>			
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private void iniciarDatos() {
		categorias = SvProveedores.getCategorias();
		for (HashMap.Entry<Integer, String> entry : categorias.entrySet()) {
		    String value = entry.getValue();
		    comboCategorias.addItem(value);
		}
		categoriasTabla = new ArrayList<String>();
		camposHabilitados(false);
		esModificacion = false;
	}
	
	private void camposHabilitados(boolean condicion){
		textNombre.setEnabled(condicion);
		textDireccion.setEnabled(condicion);
		textTelefono.setEnabled(condicion);
		textMail.setEnabled(condicion);
		comboCategorias.setEnabled(condicion);
		btnAgregar.setEnabled(condicion);
		btnQuitar.setEnabled(condicion);
		
		btnAceptar.setVisible(condicion);
		btnCancelar.setVisible(condicion);
		btnNuevoProveedor.setVisible(!condicion);
	}

	private void iniciarlizarTablaProveedor() {

		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"N\u00B0", "Nombre", "Direcci\u00F3n", "Telefono", "Mail" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMinWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		table.getColumnModel().getColumn(2).setMaxWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(3).setMinWidth(100);
		table.getColumnModel().getColumn(3).setMaxWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setMinWidth(100);
		table.getColumnModel().getColumn(4).setMaxWidth(200);

	}

	private void completarTablaProveedores() {
		for (Proveedor proveedor : SvProveedores.getProveedores()) {
			String[] fila = new String[5];
			fila[0] = proveedor.getId().toString();
			fila[1] = proveedor.getNombre();
			fila[2] = proveedor.getDireccion();
			fila[3] = proveedor.getTelefono();
			fila[4] = proveedor.getMail();

			((DefaultTableModel) this.table.getModel()).addRow(fila);
		}
	}

	private void eliminarProveedor() {
		datoTabla = obtenerSeleccion();
		int RESPUESTA = JOptionPane.showConfirmDialog( null, "¿Seguro que desea eliminar este Proveedor?\nEstos cambios no se pueden deshacer!", "CONFIRMAR", JOptionPane.OK_CANCEL_OPTION);
		if (RESPUESTA == JOptionPane.OK_OPTION) {
			//SvCliente.Eliminar_cliente(new Cliente(Integer.parseInt(datoTabla[1]), datoTabla[2], datoTabla[3], datoTabla[4], datoTabla[5])); TODO
			iniciarlizarTablaProveedor();
			completarTablaProveedores();
		}
	}

	private String[] obtenerSeleccion() {
		int indice = table.getSelectedRow();
		String id = (String) table.getModel().getValueAt(indice, 0);
		String nombre = (String) table.getModel().getValueAt(indice, 1);
		String direccion = (String) table.getModel().getValueAt(indice, 2);
		String telefono = (String) table.getModel().getValueAt(indice, 3);
		String detalle = (String) table.getModel().getValueAt(indice, 4);

		String[] dato = { String.valueOf(indice), id, nombre, direccion, telefono, detalle };
		return dato;
	}

	protected void modificarProveedor() {
		lblAviso.setVisible(false);
		datoTabla = obtenerSeleccion();
		textNombre.setText(datoTabla[2]);
		textDireccion.setText(datoTabla[3]);
		textTelefono.setText(datoTabla[4]);
		textMail.setText(datoTabla[5]);

		btnModificar.setVisible(false);
		btnEliminar.setVisible(false);
		btnAceptar.setVisible(true);
		btnCancelar.setVisible(true);

	}

	protected void resetearCampos() {
		textNombre.setText("");
		textDireccion.setText("");
		textTelefono.setText("");
		textMail.setText("");
		
		DefaultTableModel modelo = (DefaultTableModel) tablaCategorias.getModel();
		modelo.setRowCount(0);
		tablaCategorias.setModel(modelo);
		categoriasTabla.clear();
		
		lblAviso.setText("");
		
		esModificacion = false;
	}
	
	private boolean comprobarDatos() {
		if (textNombre.getText().equals("")) {
			lblAviso.setText("Debes completar el campo 'Nombre' para continuar");
			lblAviso.setVisible(true);
			return false;
		}else if (textDireccion.getText().equals("")) {
			lblAviso.setText("Debes completar el campo 'Dirección' para continuar");
			lblAviso.setVisible(true);
			return false;
		}else if (textTelefono.getText().equals("")) {
			lblAviso.setText("Debes completar el campo 'Telefono' para continuar");
			lblAviso.setVisible(true);
			return false;
		}else if (textMail.getText().equals("")) {
			lblAviso.setText("Debes completar el campo 'Mail' para continuar");
			lblAviso.setVisible(true);
			return false;
		}else if (!esEmail(textMail.getText())){
			lblAviso.setText("Debes introducir un mail válido para continuar");
			lblAviso.setVisible(true);
			return false;
		}else if (!(tablaCategorias.getRowCount()>0)){
			lblAviso.setText("Debes asignar al menos una categoria para continuar");
			lblAviso.setVisible(true);
			return false;
		}else{
			return true;
		}
	}
	
	private boolean esEmail(String correo) {
		Pattern pat = null;
        Matcher mat = null;        
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
        	return true;
        }else{
            return false;
        }        
    }
	
}
