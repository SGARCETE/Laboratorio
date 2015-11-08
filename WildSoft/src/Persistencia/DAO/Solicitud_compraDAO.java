package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;

public interface Solicitud_compraDAO {
	
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud);
	
	public ArrayList<Solicitud_compra> getLISTA_SOLICITUDES();
	
	public ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd);
	
	public boolean MODIFICAR_ESTADO(Integer ID_SOLICITUD, Integer ID_ESTADO_SOLICITUD);
	
	public boolean MODIFICAR_Solicitud(Solicitud_compra sd);
	
	public Proveedor getProveedor(Integer ID_Proveedor);
	
	public int obtener_ID_Estado_Solicitud(String estado);
	
	public boolean ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(Solicitud_compra sd);
	
	public boolean AGREGAR_MATERIA_PRIMA_SOLICITUD(Solicitud_compra sd);

	public void ELIMINAD_SOLICITUD(Solicitud_compra sc);

	public boolean Registrar_envio_solicitud(Integer id);

	public Solicitud_compra getSOLICITUD_COMPRA(Integer iD_SC);

}