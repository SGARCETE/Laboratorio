package Interfaz.Swing_Extends;

import javax.swing.table.DefaultTableModel;

public class Model_Cocina_Resumen_Productos_Pendientes extends DefaultTableModel {
	private static final long serialVersionUID = -5317190394340878484L;
	

	public Model_Cocina_Resumen_Productos_Pendientes(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { "Cantidad", "Tipo", "Producto"});
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Class<Object> getColumnClass(int columnIndex) {
		@SuppressWarnings("unchecked")
		Class<Object>[] columnTypes = new Class[] {Integer.class, String.class, String.class};
		return columnTypes[columnIndex];
	};

}//----> Fin