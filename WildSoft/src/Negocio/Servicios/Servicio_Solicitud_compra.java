package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.Solicitud_compraDAO;
import Persistencia.DAOjdbcImpl.Solicitud_compraDAOjdbc;

public class Servicio_Solicitud_compra {
	
	private Solicitud_compraDAO scDAO = new Solicitud_compraDAOjdbc();
	
	public void agregarSolicitudCompra(Solicitud_compra sc){
		scDAO.AGREGAR_SOLICITUD(sc);
		scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
	}
	
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud){
		return scDAO.AGREGAR_SOLICITUD(solicitud);
	}
	
	public ArrayList<Solicitud_compra> getLISTA_SOLICITUDES(){
		return scDAO.getLISTA_SOLICITUDES();
	}
	
	public boolean MODIFICAR_ESTADO(Solicitud_compra sd, Integer numero){
		return scDAO.MODIFICAR_ESTADO(sd, numero);
		}
	
	public void MODIFICAR_Solicitud(Solicitud_compra sc){
		scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sc);
		scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
	}
	
	public Proveedor getProveedor(Integer ID_Proveedor){
		return scDAO.getProveedor(ID_Proveedor);
	}
	
	public ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd){
		return scDAO.getLISTA_Materia_Prima(sd);
	}
	
	public int obtenerEstado(String estado) {
		return scDAO.obtenerEstado(estado);
	}
	
	public Solicitud_compra obtenerSolicitud(int id){
		int index = scDAO.getLISTA_SOLICITUDES().indexOf(new Solicitud_compra(id));
		Solicitud_compra sc = scDAO.getLISTA_SOLICITUDES().get(index);
		sc.setLista_materia_prima(scDAO.getLISTA_Materia_Prima(sc));
		return sc;
	}
	
	public void eliminarSolicitudCompra(Solicitud_compra sc){
		scDAO.ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(sc);
		scDAO.eliminarSolicitud(sc);
	}

	
	
	//TODO
	/**
	 * ESTE METODO ESTA MAL ES SOLO PARA QUE ANDE DURANTE LA ENTREGA NO USAR
	 * @param id
	 */
	public void modificacionMAAAAAAAL(int id) {
		
		ConectorMySQL conex = new ConectorMySQL();
		
		conex.Insertar("UPDATE solicitud_compra set SD_estado=3, SD_precio=500 WHERE SD_id=" + id + ";");
		
	}
	

}
