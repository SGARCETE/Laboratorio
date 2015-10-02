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
		return pedidoDAO.getPEDIDOS();
	}
	
	public boolean eliminar_pedido(Pedido p){
		return pedidoDAO.ELIMINAR_PEDIDO(p);
	}
	
	public Pedido modificar_pedido(Pedido p){
		return pedidoDAO.MODIFICAR_PEDIDO(p);
	}
	
	public Pedido get_pedido(Pedido p){
		return pedidoDAO.OBTENER_PEDIDO(p);
	}

}