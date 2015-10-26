package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Proveedor;
import Persistencia.DAO.ProveedorDAO;
import Persistencia.DAOjdbcImpl.ProveedorDAOjdbcImpl;

public class Servicio_Proveedores {
		@SuppressWarnings("unused")
		private ProveedorDAO proveedorDAO = new ProveedorDAOjdbcImpl();
	
		/**
		 * Metodo que obtiene todos los proveedores de la base de datos
		 * @return Devuelve un ArrayList de {@link Proveedor}
		 */
		public ArrayList<Proveedor> getProveedores() {
			return proveedorDAO.get_Proveedores();
		}

		/**
		 * Metodo que busca las categorias de un {@link Proveedor} dado
		 * @param nombreProveedor Es el nombre del proveedor del que se quiere saber las categorias
		 * @return Devuelve un ArrayList con los nombres de las categorias del proveedor
		 */
		public ArrayList<String> getCategoriasProveedor(String nombreProveedor) {
			
			return proveedorDAO.getCategoriasProveedor(nombreProveedor);
		}
		
		
		
		// Retorna los proveedores que tienen  la categoria recibida.
		public ArrayList<Proveedor> get_Proveedor_Categoria(String categoria_MP) {
			return proveedorDAO.get_Proveedor_Categoria(categoria_MP);
			
		}
		
		public boolean ELIMINAR_Proveedor(Proveedor p){
			return proveedorDAO.ELIMINAR_Proveedor(p);
		}
		
		public boolean AGREGAR_PROVEEDOR(Proveedor p){
			return proveedorDAO.AGREGAR_PROVEEDOR(p);
		}
		
		
}


