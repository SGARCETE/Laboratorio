package Interfaz.Swing_Extends;

import java.awt.Font;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTable_Pedido_Completo extends JTable{
	private static final long serialVersionUID = -6274818637214625651L;
	private String FuenteTablas = "Arial Unicode MS"; //"Verdana";
	private int FuenteSize = 15;
	
	
	public JTable_Pedido_Completo(DefaultTableModel model){
		setModel(model);

		// Ordenar tabla 
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		this.getRowSorter().toggleSortOrder(0);	// Ordena por ingreso
		
		// Ocultar columna
		this.getColumnModel().getColumn(0).setMaxWidth(0);
		this.getColumnModel().getColumn(0).setMinWidth(0);
		this.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
		this.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
		
		// setar tamaños de las columnas
		this.getColumnModel().getColumn(1).setMinWidth(65);
		this.getColumnModel().getColumn(1).setMaxWidth(65);
		this.getColumnModel().getColumn(2).setMinWidth(90);
		this.getColumnModel().getColumn(3).setMinWidth(170);
		this.getColumnModel().getColumn(4).setMinWidth(60);
		this.getColumnModel().getColumn(5).setMinWidth(60);
		this.getColumnModel().getColumn(6).setMinWidth(100);
		
		// Centrar Celdas
		DefaultTableCellRenderer Centrar_Celda = new DefaultTableCellRenderer();
	    Centrar_Celda.setHorizontalAlignment(SwingConstants.CENTER);
	    this.getColumnModel().getColumn(1).setCellRenderer(Centrar_Celda);
		
	    // Fuente
	    this.setFont(new Font(FuenteTablas, Font.PLAIN, FuenteSize));
		
		// Altura de cada fila
		this.setRowHeight(25);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
}
