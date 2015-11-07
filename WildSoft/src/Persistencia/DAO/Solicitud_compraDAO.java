package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;

public interface Solicitud_compraDAO {
	
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud);
	
	public ArrayList<Solicitud_compra> getLISTA_SOLICITUDES();
	
	public ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd);
	
	public boolean MODIFICAR_ESTADO(Solicitud_compra sd, Integer numero);
	
	public boolean MODIFICAR_Solicitud(Solicitud_compra sd);
	
	public Proveedor getProveedor(Integer ID_Proveedor);
	
	public int obtenerEstado(String estado);
	
	public boolean ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(Solicitud_compra sd);
	
	public boolean AGREGAR_MATERIA_PRIMA_SOLICITUD(Solicitud_compra sd);

	public void eliminarSolicitud(Solicitud_compra sc);

	public boolean Registrar_envio_solicitud(Integer id);

}
