package Persistencia.DAOjdbcImpl;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;
import Persistencia.DAO.PedidoDAO;

public class PedidoDAOjdbcImpl implements PedidoDAO{
	private ConectorMySQL conex = new ConectorMySQL();

	@Override
	public boolean AGREGAR_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Pedido> getPEDIDOS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ELIMINAR_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pedido MODIFICAR_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pedido OBTENER_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
	
	
	
	
	
	
}
