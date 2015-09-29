package Interfaz.Swing_Extends;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTable_Listado_Pedidos extends JTable{
	private static final long serialVersionUID = -3526713004625297701L;
	
	private String FuenteTablas = "Arial Unicode MS"; //"Verdana";
	private int FuenteSize = 15;
	
	public JTable_Listado_Pedidos(DefaultTableModel model){
		setModel(model);
		
		// Ordenar tabla por hora, por defecto
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		
		// Centrar Nro de chofer
		
		DefaultTableCellRenderer Centrar_Celda = new DefaultTableCellRenderer();
	    Centrar_Celda.setHorizontalAlignment(SwingConstants.CENTER);
	    
	    this.getColumnModel().getColumn(0).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(1).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(2).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(3).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(4).setCellRenderer(Centrar_Celda);
	    
	    this.setFont(new Font(FuenteTablas, Font.PLAIN, FuenteSize));
	    
//		//  
//		this.getColumnModel().getColumn(0).setMaxWidth(35);
//		this.getColumnModel().getColumn(0).setMinWidth(35);
//		// 
//		this.getColumnModel().getColumn(1).setMaxWidth(95);
//		this.getColumnModel().getColumn(1).setMinWidth(95);
//		// 
//		this.getColumnModel().getColumn(2).setMaxWidth(95);
//		this.getColumnModel().getColumn(2).setMinWidth(95);
//		// 
//		this.getColumnModel().getColumn(3).setMaxWidth(95);
//		this.getColumnModel().getColumn(3).setMinWidth(95);
//		// 
//		this.getColumnModel().getColumn(4).setMaxWidth(95);
//		this.getColumnModel().getColumn(4).setMinWidth(95);
//		// 
//		this.getColumnModel().getColumn(5).setPreferredWidth(70);
//		this.getColumnModel().getColumn(5).setMaxWidth(70);
//		this.getColumnModel().getColumn(5).setMinWidth(70);
//		// 
//		this.getColumnModel().getColumn(6).setPreferredWidth(70);
//		this.getColumnModel().getColumn(6).setMaxWidth(70);
//		this.getColumnModel().getColumn(6).setMinWidth(70);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		this.setRowHeight(25);
	}
	
		
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
		Component c = super.prepareRenderer(renderer, row, column);
		if (!isRowSelected(row)){
			c.setBackground(getBackground());
			/**	esta parte se puede modificar para poder cambiar los colores de la tabla dependiendo del dato que halla en la tabla**/
//			int modelRow = convertRowIndexToModel(row);
//			boolean Aviso = (Boolean)getModel().getValueAt(modelRow, 11);//10
//			boolean Cancelado = (Boolean)getModel().getValueAt(modelRow, 12);//11
//			if (Aviso && getModel().getValueAt(modelRow,1)!=null ) c.setBackground(new Color(153,255,153));//Color.GREEN );//240, 128, 128
//			if (Cancelado) c.setBackground(new Color(255,153,153));//Color.RED);
		}
		return c;
	}


	
}	//----> Fin
