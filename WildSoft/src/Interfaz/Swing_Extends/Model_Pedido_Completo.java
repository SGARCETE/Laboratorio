package Interfaz.Swing_Extends;

import javax.swing.table.DefaultTableModel;

public class Model_Pedido_Completo extends DefaultTableModel{
	private static final long serialVersionUID = -3540115245570366100L;

	public Model_Pedido_Completo(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { "Nro", "Unidades", "Producto", "Importe c/u", "Importe", "Observacion"});
	}

	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
	public Class<Object> getColumnClass(int columnIndex) {
		@SuppressWarnings("unchecked")
		Class<Object>[] columnTypes = new Class[] {Integer.class, Integer.class, String.class, String.class, String.class, String.class};
		return columnTypes[columnIndex];
	};
	
}//----> Fin
