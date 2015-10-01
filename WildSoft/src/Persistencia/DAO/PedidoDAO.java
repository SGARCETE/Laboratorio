package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;

public interface PedidoDAO {

	boolean AGREGAR_PEDIDO(Pedido p);

	ArrayList<Pedido> getPEDIDOS();

	boolean ELIMINAR_PEDIDO(Pedido p);

	Pedido MODIFICAR_PEDIDO(Pedido p);

	Pedido OBTENER_PEDIDO(Pedido p);

}
