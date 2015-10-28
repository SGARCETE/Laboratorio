package Interfaz.Swing_Extends;

import javax.swing.table.DefaultTableModel;

public class Model_Cocina_Detalles_Pedido extends DefaultTableModel{
	private static final long serialVersionUID = 873341231467491610L;

	public Model_Cocina_Detalles_Pedido(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { "Cantidad", "Tipo", "Producto", "Observacion"});
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Class<Object> getColumnClass(int columnIndex) {
		@SuppressWarnings("unchecked")
		Class<Object>[] columnTypes = new Class[] {Integer.class, String.class, String.class, String.class};
		return columnTypes[columnIndex];
	};

}//----> Fin

