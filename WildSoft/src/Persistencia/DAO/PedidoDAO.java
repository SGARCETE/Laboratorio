package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;

public interface PedidoDAO {

	public boolean AGREGAR_PEDIDO(Pedido p);

	public ArrayList<Pedido> getPEDIDOS();

	public boolean ELIMINAR_PEDIDO(Pedido p);

	public Pedido MODIFICAR_PEDIDO(Pedido p);

	public Pedido OBTENER_PEDIDO(Pedido p);

}
