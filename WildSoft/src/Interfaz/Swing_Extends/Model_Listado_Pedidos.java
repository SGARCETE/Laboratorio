package Interfaz.Swing_Extends;

import javax.swing.table.DefaultTableModel;

public class Model_Listado_Pedidos extends DefaultTableModel {

	private static final long serialVersionUID = 1L;

	public Model_Listado_Pedidos(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { "ID_Pedido","NºPedido", "Cliente", "Fecha", " Delivery", "Estado", "Total"});
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Class<Object> getColumnClass(int columnIndex) {
		@SuppressWarnings("unchecked")
		Class<Object>[] columnTypes = new Class[] {Integer.class, Integer.class, String.class, String.class, Boolean.class, String.class, String.class};
		return columnTypes[columnIndex];
	};

}//----> Fin
