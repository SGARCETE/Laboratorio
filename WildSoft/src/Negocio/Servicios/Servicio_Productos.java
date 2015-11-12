package Negocio.Servicios;

import java.util.ArrayList;
import java.util.HashMap;

import Negocio.Modelo.Producto;
import Persistencia.DAO.ProductoDAO;
import Persistencia.DAOjdbcImpl.ProductoDAOjdbcImpl;

public class Servicio_Productos {
	private ProductoDAO productoDAO = new ProductoDAOjdbcImpl();
	private ArrayList<Producto> Lista_Variedades = new ArrayList<Producto>();
	private ArrayList<String> Lista_Productos = new ArrayList<String>();
	
	public Servicio_Productos() {		
		String[] Variedades = new String[] {"Napolitana", "Napolitana especial", "Muzzarella", "Jamon y palmito", "Queso y peperoni", "Hawaiana", "Jamon y Panceta", "Cuatro quesos", "Salmon Ahumado", "Cuatro estaciones", "Pizza Funghi", "Vegetariana"};
		for (int i = 0; i < Variedades.length; i++) {
			Producto p = new Producto();
			p.setPR_nombre(Variedades[i]);
			p.setPR_precio(50.0);
			p.setPR_tipo_producto(1);
			Lista_Variedades.add(p);
		}
	}
	
	public ArrayList<Producto> GET_PRODUCTOS() {
		return productoDAO.GET_PRODUCTOS();
	}

	public ArrayList<String> getTipo_Producto_STRING(Integer id){
		return productoDAO.getTipo_Producto_STRING(id);
	}
	
	public boolean guardar_nuevo_producto(Producto p){
		return productoDAO.AGREGAR_PRODUCTO(p);
	}
	
	public ArrayList<String> getLista_Productos(){
		Lista_Productos = productoDAO.getTipos_Producto();
		return Lista_Productos;
	}
	
	public ArrayList<Producto> getVariedad_del_Producto(String Tipo_Producto){
		return productoDAO.getVARIEDAD_DEL_PRODUCTO(Tipo_Producto);
	}
	
	public boolean eliminar_un_producto(Producto p){
		return productoDAO.ELIMINAR_PRODUCTO(p);
	}
	
	public boolean Modificar_Producto(Producto P){
		return productoDAO.Modificar_Producto(P);
	}
	
	public ArrayList<Integer> getTipo_Producto_INTEGER (String nombre){
		return productoDAO.getTipo_Producto_INTEGER(nombre);
	}
	
	public HashMap<Integer, String> getCategorias() {
		return productoDAO.obtenerCategorias();
	}

	public Integer getIdProducto(String nombreProducto) {
		return productoDAO.getIdProducto(nombreProducto);
	}
	
}// FIN CLASE
