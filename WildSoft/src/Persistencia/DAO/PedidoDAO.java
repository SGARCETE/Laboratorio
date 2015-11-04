package Persistencia.DAO;

import java.util.ArrayList;
import java.util.Calendar;

import Negocio.Modelo.Pedido;

public interface PedidoDAO {

	public boolean AGREGAR_PEDIDO(Pedido p);

	public ArrayList<Pedido> getLISTA_PEDIDOS(Calendar Fecha_mostrar);

	public boolean ELIMINAR_PEDIDO(Pedido p);
	
	public boolean MODIFICAR_ESTADO(Pedido p, Integer numero);
	
	public boolean MODIFICAR_PEDIDO(Pedido p);

	public Pedido OBTENER_PEDIDO(Integer Numero_Pedido);

	public Object[] getTODOS_LOS_ESTADOS();
	
	public boolean ELIMINAR_PRODUCTOS_DEL_PEDIDO(Pedido P);

	public boolean AGREGAR_PRODUCTO_PEDIDO(Pedido p);
	
	ArrayList<Pedido> getLISTA_PEDIDOS_PREPARADOS(Calendar Fecha_mostrar);
}
