package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;
import Persistencia.DAO.PedidoDAO;
import Persistencia.DAOjdbcImpl.PedidoDAOjdbcImpl;

public class Servicio_Pedidos {
	
	private PedidoDAO pedidoDAO = new PedidoDAOjdbcImpl();
	
	public boolean guardar_nuevo_pedido(Pedido p){
		return pedidoDAO.AGREGAR_PEDIDO(p);
	}
	
	public ArrayList<Pedido> get_Pedidos(){
		return pedidoDAO.getLISTA_PEDIDOS();
	}
	
	public boolean eliminar_pedido(Pedido p){
		return pedidoDAO.ELIMINAR_PEDIDO(p);
	}
	
	public boolean modificar_pedido(Pedido p){
		return pedidoDAO.MODIFICAR_PEDIDO(p);
	}
	
	public Pedido get_pedido(Integer Numero_pedido){
		return pedidoDAO.OBTENER_PEDIDO(Numero_pedido);
	}

	public Object[] getTodos_los_estados() {
		return pedidoDAO.getTODOS_LOS_ESTADOS();
	}

}
