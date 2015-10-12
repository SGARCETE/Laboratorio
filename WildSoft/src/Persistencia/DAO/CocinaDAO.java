package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;

public interface CocinaDAO {

	public boolean VISUALIZAR_PEDIDOS(ArrayList<Pedido> listaPedidos);
	
	public ArrayList<Pedido> getLISTA_PEDIDOS();
	
}
