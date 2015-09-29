package Persistencia.DAOjdbcImpl;

import Negocio.Modelo.Producto;
import Persistencia.DAO.ProductoDAO;

public class ProductoDAOjdbcImpl implements ProductoDAO {
	private ConectorMySQL conex = new ConectorMySQL();
	
	@Override
	public boolean AGREGAR_PRODUCTO(Producto p) {
		// TODO Auto-generated method stub
		return false;
	}

}
