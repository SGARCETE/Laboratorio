package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;

public interface PedidoDAO {

	public boolean AGREGAR_PEDIDO(Pedido p);

	public ArrayList<Pedido> getLISTA_PEDIDOS();

	public boolean ELIMINAR_PEDIDO(Pedido p);

	public Pedido MODIFICAR_PEDIDO(Pedido p);

	public Pedido OBTENER_PEDIDO(Integer Numero_Pedido);

}
