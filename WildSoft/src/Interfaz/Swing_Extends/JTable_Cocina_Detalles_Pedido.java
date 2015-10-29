package Interfaz.Swing_Extends;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTable_Cocina_Detalles_Pedido extends JTable{
	private static final long serialVersionUID = -2397803790822018009L;
	
	private String FuenteTablas = "Arial Unicode MS"; //"Verdana";
	private int FuenteSize = 18;
	
	public JTable_Cocina_Detalles_Pedido(DefaultTableModel model){
		setModel(model);

		// Ordenar tabla 
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		this.getRowSorter().toggleSortOrder(1);	// Ordena por tipo de pedido
		
		// Centrar Celdas
		DefaultTableCellRenderer Centrar_Celda = new DefaultTableCellRenderer();
	    Centrar_Celda.setHorizontalAlignment(SwingConstants.CENTER);
	    this.getColumnModel().getColumn(0).setCellRenderer(Centrar_Celda);
	    
		// setar tamaños de las columnas
	    this.getColumnModel().getColumn(0).setPreferredWidth(75);
		this.getColumnModel().getColumn(0).setMinWidth(75);
		this.getColumnModel().getColumn(0).setMaxWidth(100);
		this.getColumnModel().getColumn(1).setPreferredWidth(400);
		this.getColumnModel().getColumn(1).setMinWidth(90);
		this.getColumnModel().getColumn(2).setPreferredWidth(400);
		this.getColumnModel().getColumn(2).setMinWidth(90);
		this.getColumnModel().getColumn(3).setPreferredWidth(500);
		this.getColumnModel().getColumn(3).setMinWidth(90);

	    // Fuente
	    this.setFont(new Font(FuenteTablas, Font.PLAIN, FuenteSize));
	    
		// Altura de cada fila
		this.setRowHeight(25);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
}//---> FIN
