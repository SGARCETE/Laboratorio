package Reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import Persistencia.Conector.ConectorMySQL;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class ReporteContabilidad {
	
	
	public void Generar_Contabilidad(Date Fecha_1, Date Fecha_2){
		String JXML = "iReports\\Mini_contabilidad.jrxml";
		JasperPrint jasperPrint = null;
		InputStream inputStream = null;
		Map<String, Object> parametros;
		
		parametros = new HashMap<String, Object>();
		parametros.put("SD_fecha_1", Fecha_1);
		parametros.put("SD_fecha_2", Fecha_2);
		parametros.put("PD_fecha_pedido1", Fecha_1);
		parametros.put("PD_fecha_pedido2", Fecha_2);
		
		
		try	{
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
				
			// MOSTRAR REPORTE
			JasperViewer view = new JasperViewer(jasperPrint,false); 
			view.setTitle("Mini Contabilidad");
			view.setVisible(true);
	}   
}
