package Interfaz.Swing_Extends;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTable_Cocina_vista_pedido extends JTable{
	private static final long serialVersionUID = -7728518054910903383L;

	public JTable_Cocina_vista_pedido(DefaultTableModel model){
		setModel(model);
		this.setRowHeight(20);
		this.setFont(new Font("Tahoma", Font.PLAIN, 18));
		this.setBackground(SystemColor.menu);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setRowHeight(25);
	}
}//---> FIN
