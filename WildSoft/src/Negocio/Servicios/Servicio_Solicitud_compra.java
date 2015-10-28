package Negocio.Servicios;

import Negocio.Modelo.Solicitud_compra;
import Persistencia.DAO.Solicitud_compraDAO;
import Persistencia.DAOjdbcImpl.Solicitud_compraDAOjdbc;

public class Servicio_Solicitud_compra {
	
	private Solicitud_compraDAO scDAO = new Solicitud_compraDAOjdbc();
	
	public void agregarSolicitudCompra(Solicitud_compra sc){
		scDAO.AGREGAR_SOLICITUD(sc);
		scDAO.AGREGAR_MATERIA_PRIMA_SOLICITUD(sc);
	}

}
