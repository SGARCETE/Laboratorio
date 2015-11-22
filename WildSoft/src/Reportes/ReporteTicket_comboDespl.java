package Reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JOptionPane;

import Negocio.Modelo.Pedido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteTicket_comboDespl {
	
	private JasperPrint jasperPrint = null;
	private String JRXML = "iReports\\TicketOficial.jrxml";
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	public ReporteTicket_comboDespl(Pedido pedido){
		InputStream inputStream = null;

		// Es el que sabe como leer e ingresar los datos al reporte 
		Producto_DataSourcePedido datasource = new Producto_DataSourcePedido(); 
		
		// Agrego el dato que quiero mostrar
		datasource.addPedido(pedido);

		try{
			inputStream = new FileInputStream(JRXML);
		} catch(FileNotFoundException ex){
			JOptionPane.showMessageDialog(null, "Error al leer el fichero de carga jasper report "+ex.getMessage());
		}

		try{
			JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, datasource);
		} catch(JRException e){
			JOptionPane.showMessageDialog(null, "Error al cargar fichero jml jasper report " + e.getMessage());
		}		
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	public void MOSTRAR_REPORTE(){
		JasperViewer view = new JasperViewer(jasperPrint,false); 
		view.setTitle("Ticket y comanda vista previa");
		view.setVisible(true);
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	public void EXPORT_TO_PDF(String RUTA, String NOMBRE_ARCHIVO){
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint, RUTA + "\\"+ NOMBRE_ARCHIVO +".pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	
}//---> FIN CLASE
