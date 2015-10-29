package Interfaz.Swing_Extends;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class JTable_Cocina_Resumen_Productos_Pendientes  extends JTable{
	private static final long serialVersionUID = 2595204525627006439L;
	
	public JTable_Cocina_Resumen_Productos_Pendientes(DefaultTableModel model){
		setModel(model);

		// Color del fondo de la tabla
		this.setBackground(Color.WHITE);
		
		// Ordenar tabla 
		RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
		this.setRowSorter(sorter);
		this.getRowSorter().toggleSortOrder(1); // ordena por tipo de pedido
		
		// Centrar Celdas
		DefaultTableCellRenderer Centrar_Celda = new DefaultTableCellRenderer();
	    Centrar_Celda.setHorizontalAlignment(SwingConstants.CENTER);
	    this.getColumnModel().getColumn(0).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(1).setCellRenderer(Centrar_Celda);
	    this.getColumnModel().getColumn(2).setCellRenderer(Centrar_Celda);
	    
		// setar tamaños de las columnas
		this.getColumnModel().getColumn(0).setPreferredWidth(60);
		this.getColumnModel().getColumn(0).setMinWidth(60);
		this.getColumnModel().getColumn(0).setMaxWidth(60);
		this.getColumnModel().getColumn(1).setPreferredWidth(150);
		this.getColumnModel().getColumn(1).setMinWidth(80);
		this.getColumnModel().getColumn(1).setMaxWidth(150);
		this.getColumnModel().getColumn(2).setPreferredWidth(99);
		this.getColumnModel().getColumn(2).setMinWidth(90);
	    
		// Fuente
		this.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		// Altura de cada fila
		this.setRowHeight(18);
		
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
	}
	
}//---> FIN
