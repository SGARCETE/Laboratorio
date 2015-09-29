package Interfaz.Swing_Extends;

import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class Model_Listado_Pedidos extends DefaultTableModel {
	private static final long serialVersionUID = -8126963003270511588L;

	public Model_Listado_Pedidos(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { "Nro Pedido", "Cliente", "Fecha", "Pedido para", "Delivery", "Estado", "Total"});
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Class<Object> getColumnClass(int columnIndex) {
		@SuppressWarnings("unchecked")
		Class<Object>[] columnTypes = new Class[] {Integer.class, String.class, Date.class, Date.class, Boolean.class, String.class, Double.class};
		return columnTypes[columnIndex];
	};
	
}//----> Fin
