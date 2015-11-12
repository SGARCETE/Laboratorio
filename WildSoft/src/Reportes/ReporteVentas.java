//package Reportes;
//
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.Map;
//
//import Persistencia.Conector.ConectorMySQL;
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
//
//public class ReporteVentas {
//	private JasperPrint jasperPrint = null;
//	private InputStream inputStream = null;
//	private Map<String, Object> parametros;
//	
//	public ReporteVentas(Integer ID_categoria, Date F1, Date F2) {
//		String JXML = "src\\Reportes\\Ventas_productos.jrxml";
//		
//		parametros = new HashMap<String, Object>();
//		parametros.put("IDCategoria", ID_categoria);
//		parametros.put("Fecha_pedido1", F1);
//		parametros.put("Fecha_pedido2", F2);
//
//		try {
//			inputStream= new FileInputStream(JXML);
//	
//	
//			JasperDesign jasperDesing = JRXmlLoader.load(inputStream);
//			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);
//			
//			ConectorMySQL con = new ConectorMySQL();
//			con.connectToMySQL();
//			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.conexion);
//
//			con.cerrarConexion();
//		} catch (FileNotFoundException | JRException e) {
//			e.printStackTrace();
//		}
//	}
//	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//	
//	public void MOSTRAR_REPORTE(){
//		// MOSTRAR REPORTE
//		JasperViewer view = new JasperViewer(jasperPrint,false); 
//		view.setTitle("Solicitud De Compra");
//		view.setVisible(true);
//	}
//	
//	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//	public void EXPORT_TO_PDF(String RUTA, String NOMBRE_ARCHIVO){
//		try {
//			JasperExportManager.exportReportToPdfFile(jasperPrint, RUTA + "\\"+ NOMBRE_ARCHIVO +".pdf");
//		} catch (JRException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
//	
//	public static void main(String[] args) {
//		Calendar C = new GregorianCalendar();
//		Date F1 = C.getTime();
//		Date F2 = C.getTime();
//		ReporteVentas rv= new ReporteVentas(1,F1,F2);
//		rv.MOSTRAR_REPORTE();
//	}    
//}//----> FIN CLASE
