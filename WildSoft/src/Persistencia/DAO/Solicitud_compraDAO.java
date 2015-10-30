package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;

public interface Solicitud_compraDAO {
	
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud);
	
	ArrayList<Solicitud_compra> getLISTA_SOLICITUDES();
	
	ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd);
	
	boolean MODIFICAR_ESTADO(Solicitud_compra sd, Integer numero);
	
	boolean MODIFICAR_Solicitud(Solicitud_compra sd);
	
	Proveedor getProveedor(Integer ID_Proveedor);
	
	
	
	int obtenerEstado(String estado);
	
	public boolean ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(Solicitud_compra sd);
	
	public boolean AGREGAR_MATERIA_PRIMA_SOLICITUD(Solicitud_compra sd);

	public void eliminarSolicitud(Solicitud_compra sc);

}
