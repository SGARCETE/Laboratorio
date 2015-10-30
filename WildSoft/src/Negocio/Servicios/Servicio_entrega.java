package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Entrega;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Repartidor;
import Persistencia.DAOjdbcImpl.EntregaDAOjdbcImplement;

public class Servicio_entrega {
	
	private EntregaDAOjdbcImplement entregaDAO = new EntregaDAOjdbcImplement();
	
	public ArrayList<Repartidor> getLISTA_REPARTIDORES(){
		return entregaDAO.getLISTA_REPARTIDORES();
	}
	
	public ArrayList<Pedido> getLISTA_PEDIDOS(){
		return entregaDAO.getLISTA_PEDIDOS();
	}
	
	public ArrayList<Pedido> getENTREGA_PEDIDOS(Entrega ent){
		return entregaDAO.getENTREGA_PEDIDOS(ent);
	}
	
	public boolean AGREGAR_PEDIDO(Entrega e){
		return entregaDAO.AGREGAR_PEDIDO(e);
	}
	
	public boolean ELIMINAR_PEDIDOS_DE_LA_ENTREGA(Entrega e){
		return entregaDAO.ELIMINAR_PEDIDOS_DE_LA_ENTREGA(e);
	}
	
	public boolean MODIFICAR_ENTREGA(Entrega e){
		return entregaDAO.MODIFICAR_ENTREGA(e);
	}
	
	public void agregarEntrega(Entrega e){
		entregaDAO.agregarEntrega(e);
	}
	
	public Integer obtenerIdUltimaEntrega(){
		return entregaDAO.obtenerUltimaEntrega();
	}
}
