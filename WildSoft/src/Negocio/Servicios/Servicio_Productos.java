package Negocio.Servicios;

import Negocio.Modelo.Producto;
import Persistencia.DAO.ProductoDAO;
import Persistencia.DAOjdbcImpl.ProductoDAOjdbcImpl;

public class Servicio_Productos {
	private ProductoDAO productoDAO = new ProductoDAOjdbcImpl();
	
	public boolean guardar_nuevo_producto(Producto p){
		return productoDAO.AGREGAR_PRODUCTO(p);
	}
	
	
	
	

}// FIN CLASE
