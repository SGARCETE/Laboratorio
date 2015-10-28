package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Entrega;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Repartidor;

public interface EntregaDAO {

	
	ArrayList<Repartidor> getLISTA_REPARTIDORES();
	ArrayList<Pedido> getLISTA_PEDIDOS();
	ArrayList<Pedido> getENTREGA_PEDIDOS(Entrega ent);
	boolean AGREGAR_PEDIDO(Entrega e);
	boolean ELIMINAR_PEDIDOS_DE_LA_ENTREGA(Entrega e);
	boolean MODIFICAR_ENTREGA(Entrega e);
	
	
	
	
}
