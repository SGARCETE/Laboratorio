package Reportes;

import java.util.List;

import Negocio.Modelo.Pedido;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class ReporteTicket {
	
	private JasperReport reporte;
	private JasperViewer reporteViewer;
	private JasperPrint reporteLleno;
	
	
	   public ReporteTicket(List<Pedido> pedido)
	    {
	    	try 
			{
				this.reporte = (JasperReport) JRLoader.loadObjectFromFile( "Reportes\\TicketOficial.jasper" );
				this.reporteLleno = JasperFillManager.fillReport(this.reporte, null, 
						new JRBeanCollectionDataSource(pedido));
			}
			catch( JRException ex ) 
			{
				ex.printStackTrace();
			}
	    }       
	    
	    public void mostrar()
		{
			this.reporteViewer = new JasperViewer(this.reporteLleno);
			this.reporteViewer.setVisible(true);
		}
	        

	
}
