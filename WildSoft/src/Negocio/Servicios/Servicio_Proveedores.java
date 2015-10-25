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
			//TODO
			return null;
		}

		/**
		 * Metodo que busca las categorias de un {@link Proveedor} dado
		 * @param nombreProveedor Es el nombre del proveedor del que se quiere saber las categorias
		 * @return Devuelve un ArrayList con los nombres de las categorias del proveedor
		 */
		public ArrayList<String> getCategoriasProveedor(String nombreProveedor) {
			// TODO
			return null;
		}
}
