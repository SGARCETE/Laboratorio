package Reportes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import Persistencia.Conector.ConectorMySQL;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteMejoresClientes {
	

	
	public void GenerarReporteMejoresClientes(Date F1, Date F2) {
		String JXML = "iReports\\Ventas_Clientes.jrxml";
		JasperPrint jasperPrint = null;
		InputStream inputStream = null;
		Map<String, Object> parametros;
		System.out.println(F1.getTime() + "    "+ F2.getTime());
		parametros = new HashMap<String, Object>();
		parametros.put("PD_fecha_pedido", F1);
		parametros.put("PD_fecha_pedido2", F2);

		try {
			inputStream= new FileInputStream(JXML);

			JasperDesign jasperDesing = JRXmlLoader.load(inputStream);
			JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesing);
			
			ConectorMySQL con = new ConectorMySQL();
			con.connectToMySQL();
			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, con.conexion);

			con.cerrarConexion();
			
		} catch (FileNotFoundException | JRException e) {
			e.printStackTrace();
		}

	
		// MOSTRAR REPORTE
		JasperViewer view = new JasperViewer(jasperPrint,false); 
		view.setTitle("Reporte Ventas Clientes");
		view.setVisible(true);

	}
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/

}//----> FIN CLASE
