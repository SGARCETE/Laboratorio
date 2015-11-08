package Negocio.Servicios;

import java.io.File;
import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.Solicitud_compraDAO;
import Persistencia.DAOjdbcImpl.Solicitud_compraDAOjdbc;
import Reportes.ReporteSolicitud;
import mail_sender.Email_Manager;

public class Servicio_Solicitud_compra {
	private Solicitud_compraDAO scDAO = new Solicitud_compraDAOjdbc();
	
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**			ARREGLAR ESTO QUE ESTA PIDIENDO UAN LISTA PARA OBTENER SOLO UNA SOLICITUD  -TODO	***/
	public Solicitud_compra OBTENER_SOLICITUD(int ID_SOLICITUD){
		Solicitud_compra sc = scDAO.getSOLICITUD_COMPRA(ID_SOLICITUD);
		sc.setLista_materia_prima(scDAO.getLISTA_Materia_Prima(sc));
		return sc;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void AGREGAR_SOLICITUD_COMPRA(Solicitud_compra sc){
		scDAO.AGREGAR_SOLICITUD(sc);
		scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
	}
		
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void ELIMINAD_SOLICITUD_COMPRA(Solicitud_compra sc){
		scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sc);
		scDAO.ELIMINAD_SOLICITUD(sc);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean MODIFICAR_SOLICITUD_COMPRA(Solicitud_compra sc){
		if(sc!=null && sc.getId()!=0){
			scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sc);
			return scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
		}
		return false;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean MODIFICAR_ESTADO(Integer ID_SOLICITUD, Integer ID_ESTADO){
		return scDAO.MODIFICAR_ESTADO(ID_SOLICITUD, ID_ESTADO);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(Solicitud_compra sd){
		return scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sd);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud){
		return scDAO.AGREGAR_SOLICITUD(solicitud);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Solicitud_compra> getLISTA_SOLICITUDES(){
		return scDAO.getLISTA_SOLICITUDES();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd){
		return scDAO.getLISTA_Materia_Prima(sd);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean Enviar_solicitud_a_proveedor(Integer iD_SOLICITUD) {
		boolean Exito = false;
		Email_Manager EM = new Email_Manager();
		Solicitud_compra Solicitud = OBTENER_SOLICITUD(iD_SOLICITUD);
		
		// Genera archivo adjunto
		ReporteSolicitud RS = new ReporteSolicitud();
		RS.Generar_Solicitud(iD_SOLICITUD);
		RS.EXPORT_TO_PDF("", "SOLICITUD_ENVIAR"+iD_SOLICITUD);
//		RS.EXPORT_TO_PDF(System.getProperty("user.home") + "\\Desktop", "SOLICITUD_ENVIAR"+iD_SOLICITUD);
		
		File PDF_SOLICITUD = new File("\\SOLICITUD_ENVIAR"+iD_SOLICITUD+".pdf");
		
//		File PDF_SOLICITUD = new File(System.getProperty("user.home") + "\\Desktop\\SOLICITUD_ENVIAR"+iD_SOLICITUD+".pdf");
		Solicitud.setSolicitudPDF(PDF_SOLICITUD);
		
		if(Solicitud!=null){
			Exito =  EM.ENVIAR_SOLICITUD_DE_COMPRA(Solicitud);
			if(Exito)
				scDAO.Registrar_envio_solicitud(iD_SOLICITUD);
		}
		
//		PDF_SOLICITUD.delete();
		return Exito;
		

		
		

		
		
	
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public void Ver_Reporte_solicitud_compra(Integer NUMERO_SOLICITUD) {
		ReporteSolicitud RS = new ReporteSolicitud();
		RS.Generar_Solicitud(NUMERO_SOLICITUD);
		RS.MOSTRAR_REPORTE();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
//	public void solicitud_compra_PDF(Integer NUMERO_SOLICITUD) {
//		ReporteSolicitud RS = new ReporteSolicitud();
//		RS.Generar_Solicitud(NUMERO_SOLICITUD);
//		RS.EXPORT_TO_PDF(RUTA, NOMBRE_ARCHIVO);
//	}
	
	
	
	
	// REVISAR SI REALMENTE ES NECESARIO
	public Proveedor getProveedor(Integer ID_Proveedor){
		return scDAO.getProveedor(ID_Proveedor);
	}
	

	// REVISAR SI REALMENTE ES NECESARIO
	public int obtenerEstado(String estado) {
		return scDAO.obtener_ID_Estado_Solicitud(estado);
	}
	
	
	//TODO
	/**
	 * ESTE METODO ESTA MAL ES SOLO PARA QUE ANDE DURANTE LA ENTREGA, NO USAR!!!!!
	 */
	public void modificacionMAAAAAAAL(int id, int precio) {
		
		ConectorMySQL conex = new ConectorMySQL();
		
		conex.Insertar("UPDATE solicitud_compra set SD_estado=3, SD_precio=" + precio + " WHERE SD_id=" + id + ";");
		
	}

	





}//---> FIN CLASE
