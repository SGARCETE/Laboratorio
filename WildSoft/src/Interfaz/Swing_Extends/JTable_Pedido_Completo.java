package Interfaz.Swing_Extends;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTable_Pedido_Completo extends JTable{
	private static final long serialVersionUID = -6274818637214625651L;
	private String FuenteTablas = "Arial Unicode MS"; //"Verdana";
	private int FuenteSize = 15;
	
	
	public JTable_Pedido_Completo(DefaultTableModel model){
		setModel(model);
		this.setFont(new Font("Tahoma", Font.PLAIN, 12));
//		this.getColumnModel().getColumn(0).setPreferredWidth(0);
		this.getColumnModel().getColumn(0).setMaxWidth(0);
		this.getColumnModel().getColumn(0).setMinWidth(0);
//		this.getColumnModel().getColumn(0).setPreferredWidth(34);
		this.getColumnModel().getColumn(1).setPreferredWidth(75);
		this.getColumnModel().getColumn(2).setPreferredWidth(80);
		this.getColumnModel().getColumn(3).setPreferredWidth(170);
		this.getColumnModel().getColumn(4).setPreferredWidth(100);
		this.getColumnModel().getColumn(5).setPreferredWidth(100);
		this.getColumnModel().getColumn(6).setPreferredWidth(170);
		
		
	    this.setFont(new Font(FuenteTablas, Font.PLAIN, FuenteSize));
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		this.setRowHeight(25);
	}
	
}
