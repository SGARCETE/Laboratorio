package Interfaz.Swing_Extends;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTable_Pedido_Completo extends JTable{
	private static final long serialVersionUID = -6274818637214625651L;

	public JTable_Pedido_Completo(DefaultTableModel model){
		setModel(model);
		this.setFont(new Font("Tahoma", Font.PLAIN, 12));
		this.getColumnModel().getColumn(0).setPreferredWidth(34);
		this.getColumnModel().getColumn(1).setPreferredWidth(67);
		this.getColumnModel().getColumn(2).setPreferredWidth(134);
		this.getColumnModel().getColumn(5).setPreferredWidth(101);
	}
	
}
