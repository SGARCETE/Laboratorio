package Negocio.Servicios;

import java.util.ArrayList;
import java.util.Calendar;

import Negocio.Modelo.Pedido;
import Persistencia.DAO.PedidoDAO;
import Persistencia.DAOjdbcImpl.PedidoDAOjdbcImpl;

public class Servicio_Pedidos {
	
	private PedidoDAO pedidoDAO = new PedidoDAOjdbcImpl();
	
	
	// OBTENER UN PEDIDO
	public Pedido get_pedido(Integer Numero_pedido){
		return pedidoDAO.OBTENER_PEDIDO(Numero_pedido);
	}
	
	// NUEVO PEDIDO
	public boolean guardar_nuevo_pedido(Pedido p){
		return pedidoDAO.AGREGAR_PEDIDO(p);
	}
	
	// ELIMINAR UN PEDIDO
	public boolean eliminar_pedido(Pedido p){
		return pedidoDAO.ELIMINAR_PEDIDO(p);
	}

	// MODIFICAR UN PEDIDO
	public boolean modificar_pedido(Pedido p){
		return pedidoDAO.MODIFICAR_PEDIDO(p);
	}
	
	// MODIFICAR ESTADO
	public boolean modificar_estado(Pedido p, Integer ID_ESTADO){
		return pedidoDAO.MODIFICAR_ESTADO(p, ID_ESTADO);
	}

	// OBTENER LISTA DE LOS PEDIDOS DE UNA DETERMINADA FECHA
	public ArrayList<Pedido> get_Pedidos(Calendar Fecha_mostrar){
		if(Fecha_mostrar!=null)
			return pedidoDAO.getLISTA_PEDIDOS(Fecha_mostrar);
		return null;
	}
	
	// OBTENER TODOS LOS ESTADOS DE UN PEDIDO
	public Object[] getTodos_los_estados() {
		return pedidoDAO.getTODOS_LOS_ESTADOS();
	}
	
	// ELIMINAR UN PRODUCTO DEL PEDIDO
	public boolean eliminar_producto_del_pedido(Pedido P){
		 return pedidoDAO.ELIMINAR_PRODUCTOS_DEL_PEDIDO(P);
	
	}
	
	// AGREGAR UN PRODUCTO AL PEDIDO
	public boolean agregar_producto_al_pedido(Pedido p){
		return pedidoDAO.AGREGAR_PRODUCTO_PEDIDO(p);
	}
	
	// OBTENER TODOS LOS PEDIDOS EN ESTADO "PREPARADO"
	public ArrayList<Pedido> get_pedidos_preparados(Calendar Fecha_mostrar){
		return pedidoDAO.getLISTA_PEDIDOS_PREPARADOS(Fecha_mostrar);
	}
	
	// OBTENER ID DEL ULTIMO PEDIDO
	public Integer Obtener_ID_Ultimo_Pedido(){
		return pedidoDAO.ObtenerUltimoPedido();
	}
	
	// OBTENER PEDIDOS TOMANDO LOS PRODUCTOS DE LOS COMBOS
	
//	public ArrayList<Producto> getLista_Productos_Cocina(Pedido P){
//		return pedidoDAO.getLista_Productos_Cocina(P);
//	}
	
	public ArrayList<Pedido> getLISTA_PEDIDOS_COCINA(Calendar Fecha_mostrar){
		return pedidoDAO.getLISTA_PEDIDOS_COCINA(Fecha_mostrar);
	}
	
	/** SOLO PARA TICKET*/
	public Pedido OBTENER_PEDIDO_COMBOS_DESP(Integer Numero_pedido){
		return pedidoDAO.OBTENER_PEDIDO_COMBOS_DESP(Numero_pedido);
	}
	

}
