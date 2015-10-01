package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Producto;
import Persistencia.DAO.ProductoDAO;
import Persistencia.DAOjdbcImpl.ProductoDAOjdbcImpl;

public class Servicio_Productos {
	private ProductoDAO productoDAO = new ProductoDAOjdbcImpl();
	
	public boolean guardar_nuevo_producto(Producto p){
		return productoDAO.AGREGAR_PRODUCTO(p);
	}
	
	public ArrayList<Producto> getVariedad_del_Producto(String Tipo_Producto){
		return productoDAO.getVARIEDAD_DEL_PRODUCTO(Tipo_Producto);
		
	}
	
	
	

}// FIN CLASE
