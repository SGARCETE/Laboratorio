package Interfaz.Swing_Extends;

import javax.swing.table.DefaultTableModel;

public class Model_Listado_Pedidos extends DefaultTableModel {
	private static final long serialVersionUID = -8126963003270511588L;


	public Model_Listado_Pedidos(){
		// Poner las columnas en el modelo de la tabla
		setColumnIdentifiers(new String[] { "Nro Pedido", "Cliente", "Fecha", " Delivery", "Estado", "Total"});
	}


	public boolean isCellEditable(int row, int column) {
		return false;
	}
	

	@SuppressWarnings("unchecked")
	public Class<Object> getColumnClass(int columnIndex) {
		Class<Object>[] columnTypes = new Class[] {Integer.class, String.class, String.class, Boolean.class , String.class, String.class};
		return columnTypes[columnIndex];
	};

}//----> Fin
