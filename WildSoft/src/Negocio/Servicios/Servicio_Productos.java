package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Producto;
import Persistencia.DAO.ProductoDAO;
import Persistencia.DAOjdbcImpl.ProductoDAOjdbcImpl;

public class Servicio_Productos {
	private ProductoDAO productoDAO = new ProductoDAOjdbcImpl();
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	private ArrayList<String> Lista_Productos = new ArrayList<String>();

	
	
	public Servicio_Productos() {
		// Consulta los tipos de producto solo la primera vez a la BD
//		Lista_Variedades = productoDAO.getVARIEDAD_DEL_PRODUCTO("Pizza");
		// Consulta las variedades de producto solo la primera vez a la BD
//		Lista_Productos = productoDAO.getTipos_Producto();
		
		//** HARDCODEADO PARA QUE NO TRAIGA LOS DATOS DE LA BASE DE DATOS**//
		String[] Productos =  new String[] {"Pizza", "Empanada", "Bebida", "Combo"};
		Lista_Productos.add(Productos[0]);
		Lista_Productos.add(Productos[1]);
		Lista_Productos.add(Productos[2]);
		Lista_Productos.add(Productos[3]);
		
		String[] Variedades = new String[] {"Napolitana", "Napolitana especial", "Muzzarella", "Jamon y palmito", "Queso y peperoni", "Hawaiana", "Jamon y Panceta", "Cuatro quesos", "Salmon Ahumado", "Cuatro estaciones", "Pizza Funghi", "Vegetariana"};
		for (int i = 0; i < Variedades.length; i++) {
			Producto p = new Producto();
			p.setPR_nombre(Variedades[i]);
			p.setPR_precio(50.0);
			p.setPR_tipo_producto(1);
			Lista_Variedades.add(p);
		}
		
	}
	

	
	public boolean guardar_nuevo_producto(Producto p){
		return productoDAO.AGREGAR_PRODUCTO(p);
	}
	

	public ArrayList<String> getLista_Productos(){
		return Lista_Productos;
	}
	
	public ArrayList<Producto> getVariedad_del_Producto(String Tipo_Producto){
//		return Lista_Variedades;					//** HARDCODEADO PARA QUE NO TRAIGA LOS DATOS DE LA BASE DE DATOS**//
		return productoDAO.getVARIEDAD_DEL_PRODUCTO(Tipo_Producto);
	}
	
	public boolean eliminar_un_producto(Producto p){
		return productoDAO.ELIMINAR_PRODUCTO(p);
	}
}// FIN CLASE
