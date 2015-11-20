package Reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

import Persistencia.Conector.ConectorMySQL;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteSolicitud {
	private JasperPrint jasperPrint = null;
	private InputStream inputStream = null;
	private Map<String, Object> parametros;
	
	
	public void Generar_Solicitud(Integer NUMERO_SOLICITUD){
		String JXML = "iReports\\Solicitud.jrxml";
		
		parametros = new HashMap<String, Object>();
		parametros.put("ID_SOLICITUD", NUMERO_SOLICITUD);
				
		try{
			inputStream= new FileInputStream(JXML);
			JasperDesign jasperDesing = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);
			
			ConectorMySQL con = new ConectorMySQL();
			con.connectToMySQL();
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.conexion);
			
			con.cerrarConexion();
		} 
		catch (JRException | FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al leer el fichero de carga jasper report "+e.getMessage());
		}	
	}
	
	
	public void MOSTRAR_REPORTE(){
		// MOSTRAR REPORTE
		JasperViewer view = new JasperViewer(jasperPrint,false); 
		view.setTitle("Solicitud De Compra");
		view.setVisible(true);
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	public void EXPORT_TO_PDF(String RUTA, String NOMBRE_ARCHIVO){
		try {
			JasperExportManager.exportReportToPdfFile(jasperPrint,  "d:\\"+ NOMBRE_ARCHIVO +".pdf");
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
	

}//----> FIN CLASE
