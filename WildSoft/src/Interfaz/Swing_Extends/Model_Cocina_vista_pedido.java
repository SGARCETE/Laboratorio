package Interfaz.Swing_Extends;

import javax.swing.table.DefaultTableModel;

public class Model_Cocina_vista_pedido extends DefaultTableModel{
	private static final long serialVersionUID = -7487869527853632483L;


	public Model_Cocina_vista_pedido(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { ""});
	}


	public boolean isCellEditable(int row, int column) {
		return false;
	}
	

	@SuppressWarnings("unchecked")
	public Class<Object> getColumnClass(int columnIndex) {
		Class<Object>[] columnTypes = new Class[] {String.class};
		return columnTypes[columnIndex];
	};

}//----> Fin