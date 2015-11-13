package Interfaz.JDialogs;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
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

import MetAux.MetAux;
import Negocio.Modelo.Proveedor;
import Negocio.Servicios.Principal_Negocio_Interfaz;
import Negocio.Servicios.Servicio_Proveedores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Interfaz_Proveedores extends JDialog {

	private JPanel contentPanel = new JPanel();

	private Servicio_Proveedores SvProveedores;
	
	private JTable tableProveedores;
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
	private JLabel ID;
	
	private HashMap<Integer, String> categorias;
	
	private ArrayList<String> categoriasTabla;
	private boolean esModificacion;
	private JPanel panel_1;

	

	public Interfaz_Proveedores(Principal_Negocio_Interfaz instancia_negocio) {
		setTitle("Proveedores");
		SvProveedores = instancia_negocio.getSvProveedores();

		tableProveedores = new JTable();
		iniciarlizarTablaProveedor();

		completarTablaProveedores();

		setResizable(false);
		setBounds(100, 100, 1023, 513);
		getContentPane().setLayout(new BorderLayout());
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(60, 179, 113));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
						
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEliminar.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(btnEliminar);
		btnEliminar.setBackground(Color.WHITE);
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableProveedores.getSelectedRow() != -1) {
					eliminarProveedor();
				}

			}
		});
		btnEliminar.setIcon(new ImageIcon(ADM_Repartidor.class .getResource("/Recursos/IMG/delete-1-icon24.png")));

		btnModificar = new JButton("Modificar");
		btnModificar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnModificar.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(btnModificar);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableProveedores.getSelectedRow() != -1) {
					modificarProveedor();
				}
			}
		});
		btnModificar.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/edit-icon24.png")));
		
		JButton btnSalir = new JButton("    Salir    ");
		btnSalir.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSalir.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_1.add(btnSalir);
		btnSalir.setBackground(Color.WHITE);
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setIcon(new ImageIcon(ADM_Repartidor.class.getResource("/Recursos/IMG/User-Interface-Login-icon24.png")));
		
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(10, 11, 300, 213);
		getContentPane().add(panel);
		panel.setLayout(null);

		JPanel panelAltaModificacion = new JPanel();
		panelAltaModificacion.setBackground(Color.WHITE);
		panelAltaModificacion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Proveedores", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelAltaModificacion.setBounds(10, 11, 299, 402);
		panel.add(panelAltaModificacion);
		panelAltaModificacion.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(16, 28, 63, 28);
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
		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comprobarDatos()){
					
					Proveedor p = new Proveedor();
					p.setNombre(textNombre.getText());
					p.setDireccion(textDireccion.getText());
					p.setMail(textMail.getText());
					p.setTelefono(textTelefono.getText());
					ArrayList<Integer> lista = new ArrayList<Integer>();
					for (int i = 0; i < tablaCategorias.getRowCount(); i++) {
						lista.add(Integer.parseInt((String) tablaCategorias.getValueAt(i, 0)));
					}
					p.setCategoria(lista);
					
					if (esModificacion){
						p.setId(Integer.parseInt(ID.getText()));
						SvProveedores.modificarProveedor(p);					
					}else{
						SvProveedores.AGREGAR_PROVEEDOR(p);
					}
					camposHabilitados(false);
					resetearCampos();
					iniciarlizarTablaProveedor();
					completarTablaProveedores();
				}
			}
		});
		btnAceptar.setBounds(16, 351, 96, 28);
		panelAltaModificacion.add(btnAceptar);

		btnCancelar = new JButton("Cancelar");
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				camposHabilitados(false);
				resetearCampos();
			}
		});
		btnCancelar.setBounds(193, 351, 96, 28);
		panelAltaModificacion.add(btnCancelar);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n");
		lblDireccin.setBounds(16, 68, 63, 28);
		panelAltaModificacion.add(lblDireccin);

		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(16, 108, 63, 28);
		panelAltaModificacion.add(lblTelefono);

		JLabel lblMail = new JLabel("Mail");
		lblMail.setBounds(16, 148, 63, 28);
		panelAltaModificacion.add(lblMail);

		comboCategorias = new JComboBox<String>();
		comboCategorias.setBounds(16, 181, 273, 26);
		panelAltaModificacion.add(comboCategorias);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setIcon(new ImageIcon(Interfaz_Proveedores.class.getResource("/Recursos/IMG/add-1-icon16.png")));
		btnAgregar.setBackground(Color.WHITE);
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Recorro el mapa buscando la id del elemento seleccionado del combo
				Integer id = -1;
				for (Entry<Integer, String> entry : categorias.entrySet()) {
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
		btnAgregar.setBounds(16, 212, 96, 28);
		panelAltaModificacion.add(btnAgregar);

		btnQuitar = new JButton("Quitar");
		btnQuitar.setIcon(new ImageIcon(Interfaz_Proveedores.class.getResource("/Recursos/IMG/subtract-1-icon16.png")));
		btnQuitar.setBackground(Color.WHITE);
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
		btnQuitar.setBounds(181, 212, 108, 28);
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
		btnNuevoProveedor.setBackground(Color.WHITE);
		btnNuevoProveedor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				camposHabilitados(true);
			}
		});
		btnNuevoProveedor.setBounds(16, 351, 265, 28);
		panelAltaModificacion.add(btnNuevoProveedor);
		
		ID = new JLabel("");
		ID.setBounds(16, 6, 55, 16);
		panelAltaModificacion.add(ID);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		panel_2.setBorder(new TitledBorder(null, "Lista de Proveedores", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panel_2.setBounds(312, 11, 695, 345);
		panel.add(panel_2);
		panel_2.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 679, 318);
		panel_2.add(scrollPane);

		scrollPane.setViewportView(tableProveedores);

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
		for (Entry<Integer, String> entry : categorias.entrySet()) {
		    String value = entry.getValue();
		    comboCategorias.addItem(value);
		}
		camposHabilitados(false);
		ID.setVisible(false);
		MetAux.Limitar_caracteres(textNombre, 20);
		MetAux.Limitar_caracteres(textDireccion, 20);
		MetAux.Limitar_caracteres(textTelefono, 20);
		MetAux.Limitar_caracteres(textMail, 50);
		
		resetearLogica();
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

		tableProveedores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"N\u00B0", "Nombre", "Direcci\u00F3n", "Telefono", "Mail"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				true, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableProveedores.getColumnModel().getColumn(0).setPreferredWidth(0);
		tableProveedores.getColumnModel().getColumn(0).setMinWidth(0);
		tableProveedores.getColumnModel().getColumn(0).setMaxWidth(0);
		tableProveedores.getColumnModel().getColumn(1).setPreferredWidth(100);
		tableProveedores.getColumnModel().getColumn(1).setMinWidth(100);
		tableProveedores.getColumnModel().getColumn(1).setMaxWidth(200);
		tableProveedores.getColumnModel().getColumn(2).setPreferredWidth(100);
		tableProveedores.getColumnModel().getColumn(2).setMinWidth(100);
		tableProveedores.getColumnModel().getColumn(2).setMaxWidth(200);
		tableProveedores.getColumnModel().getColumn(3).setPreferredWidth(100);
		tableProveedores.getColumnModel().getColumn(3).setMinWidth(100);
		tableProveedores.getColumnModel().getColumn(3).setMaxWidth(200);
		tableProveedores.getColumnModel().getColumn(4).setPreferredWidth(100);
		tableProveedores.getColumnModel().getColumn(4).setMinWidth(100);
		tableProveedores.getColumnModel().getColumn(4).setMaxWidth(200);

	}

	private void completarTablaProveedores() {
		for (Proveedor proveedor : SvProveedores.getProveedores()) {
			String[] fila = new String[5];
			fila[0] = proveedor.getId().toString();
			fila[1] = proveedor.getNombre();
			fila[2] = proveedor.getDireccion();
			fila[3] = proveedor.getTelefono();
			fila[4] = proveedor.getMail();

			((DefaultTableModel) this.tableProveedores.getModel()).addRow(fila);
		}
	}

	private String[] obtenerSeleccion() {
		int indice = tableProveedores.getSelectedRow();
		String id = (String) tableProveedores.getModel().getValueAt(indice, 0);
		String nombre = (String) tableProveedores.getModel().getValueAt(indice, 1);
		String direccion = (String) tableProveedores.getModel().getValueAt(indice, 2);
		String telefono = (String) tableProveedores.getModel().getValueAt(indice, 3);
		String mail = (String) tableProveedores.getModel().getValueAt(indice, 4);

		String[] dato = { String.valueOf(indice), id, nombre, direccion, telefono, mail };
		return dato;
	}

	//TODO
	private void eliminarProveedor() {
		int RESPUESTA = JOptionPane.showConfirmDialog( null, "¿Seguro que desea eliminar este Proveedor?\nEstos cambios no se pueden deshacer!", "CONFIRMAR", JOptionPane.OK_CANCEL_OPTION);
		if (RESPUESTA == JOptionPane.OK_OPTION) {
			SvProveedores.eliminarProveedor(Integer.parseInt((String) tableProveedores.getValueAt(tableProveedores.getSelectedRow(), 0)));
			iniciarlizarTablaProveedor();
			completarTablaProveedores();
		}
	}

	//TODO
	private void modificarProveedor() {
		camposHabilitados(true);
		datoTabla = obtenerSeleccion();
		ID.setText(datoTabla[1]);
		textNombre.setText(datoTabla[2]);
		textDireccion.setText(datoTabla[3]);
		textTelefono.setText(datoTabla[4]);
		textMail.setText(datoTabla[5]);
		for (int i = 0; i < SvProveedores.getCategoriasProveedor(Integer.parseInt(datoTabla[1])).size(); i++) {
			DefaultTableModel modelo = (DefaultTableModel) tablaCategorias.getModel();
			String[] arreglo = {
					String.valueOf(SvProveedores.getCategoriasProveedor(Integer.parseInt(datoTabla[1])).get(i)),
					categorias.get(SvProveedores.getCategoriasProveedor(Integer.parseInt(datoTabla[1])).get(i))};
			modelo.addRow(arreglo);
			tablaCategorias.setModel(modelo);
			categoriasTabla.add(categorias.get(SvProveedores.getCategoriasProveedor(Integer.parseInt(datoTabla[1])).get(i)));
		}
		esModificacion = true;
	}

	private void resetearCampos() {
		textNombre.setText("");
		textDireccion.setText("");
		textTelefono.setText("");
		textMail.setText("");
		
		DefaultTableModel modelo = (DefaultTableModel) tablaCategorias.getModel();
		modelo.setRowCount(0);
		tablaCategorias.setModel(modelo);
		
		lblAviso.setText("");
		ID.setText("");
		
		resetearLogica();
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
        //pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        pat = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@[a-z0-9-]+(.[a-z0-9-]+)*(.[a-z]{2,4})$");
        mat = pat.matcher(correo);
        if (mat.find()) {
        	return true;
        }else{
            return false;
        }        
    }
	
	private void resetearLogica(){
		categoriasTabla = new ArrayList<String>();
		esModificacion = false;
	}
}
