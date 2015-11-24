package Negocio.Servicios;

import java.io.File;
import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;
import Persistencia.DAO.Solicitud_compraDAO;
import Persistencia.DAOjdbcImpl.Solicitud_compraDAOjdbc;
import Reportes.ReporteSolicitud;
import mail_sender.Email_Manager;

public class Servicio_Solicitud_compra {
	private Solicitud_compraDAO scDAO = new Solicitud_compraDAOjdbc();
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Solicitud_compra OBTENER_SOLICITUD(int ID_SOLICITUD){
		Solicitud_compra sc = scDAO.getSOLICITUD_COMPRA(ID_SOLICITUD);
		sc.setLista_materia_prima(scDAO.getLISTA_Materia_Prima(sc));
		return sc;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void AGREGAR_SOLICITUD_COMPRA(Solicitud_compra sc){
		int id = scDAO.AGREGAR_SOLICITUD(sc);
		sc.setId(id);
		scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
	}
		
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void ELIMINAD_SOLICITUD_COMPRA(Solicitud_compra sc){
		scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sc);
		scDAO.ELIMINAD_SOLICITUD(sc);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void MODIFICAR_SOLICITUD_COMPRA(Solicitud_compra sc){
		if(sc!=null && sc.getId()!=0){
			scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sc);
			scDAO.MODIFICAR_Solicitud(sc);
			scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
		}
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
		RS.EXPORT_TO_PDF("d:", "SOLICITUD_ENVIAR"+iD_SOLICITUD);
		File PDF_SOLICITUD = new File("d:\\SOLICITUD_ENVIAR"+iD_SOLICITUD+".pdf");
		Solicitud.setSolicitudPDF(PDF_SOLICITUD);
		if(Solicitud!=null){
			Exito =  EM.ENVIAR_SOLICITUD_DE_COMPRA(Solicitud);
			if(Exito)
				scDAO.Registrar_envio_solicitud(iD_SOLICITUD);
		}
		return Exito;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void Ver_Reporte_solicitud_compra(Integer NUMERO_SOLICITUD) {
		ReporteSolicitud RS = new ReporteSolicitud();
		RS.Generar_Solicitud(NUMERO_SOLICITUD);
		RS.MOSTRAR_REPORTE();
	}
	
	// REVISAR SI REALMENTE ES NECESARIO
	public Proveedor getProveedor(Integer ID_Proveedor){
		return scDAO.getProveedor(ID_Proveedor);
	}
	
	// REVISAR SI REALMENTE ES NECESARIO
	public int obtenerEstado(String estado) {
		return scDAO.obtener_ID_Estado_Solicitud(estado);
	}
	
}//---> FIN CLASE
