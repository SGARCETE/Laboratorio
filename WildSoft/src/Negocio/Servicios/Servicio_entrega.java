package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Entrega;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Repartidor;
import Persistencia.DAOjdbcImpl.EntregaDAOjdbcImplement;

public class Servicio_entrega {
	
	private EntregaDAOjdbcImplement entregaDAO = new EntregaDAOjdbcImplement();
	
	ArrayList<Repartidor> getLISTA_REPARTIDORES(){
		return entregaDAO.getLISTA_REPARTIDORES();
		
		
	}
	
	ArrayList<Pedido> getLISTA_PEDIDOS(){
		return entregaDAO.getLISTA_PEDIDOS();
	    
	}
	
	
	ArrayList<Pedido> getENTREGA_PEDIDOS(Entrega ent){
		return entregaDAO.getENTREGA_PEDIDOS(ent);
	}
	
	boolean AGREGAR_PEDIDO(Entrega e){
		System.out.println("a");
		return entregaDAO.AGREGAR_PEDIDO(e);
	
	}
	
	boolean ELIMINAR_PEDIDOS_DE_LA_ENTREGA(Entrega e){
		return entregaDAO.ELIMINAR_PEDIDOS_DE_LA_ENTREGA(e);
		
	}
	
	boolean MODIFICAR_ENTREGA(Entrega e){
		return entregaDAO.MODIFICAR_ENTREGA(e);
		
	}
	
	

}
