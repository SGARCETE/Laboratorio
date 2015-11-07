package Persistencia.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import Negocio.Modelo.Proveedor;

public interface ProveedorDAO {
	
	ArrayList<Proveedor> get_Proveedor_Categoria(String categoria_MP);
	
	void AGREGAR_PROVEEDOR(Proveedor p);
	
	boolean ELIMINAR_Proveedor(Proveedor p);
	
	ArrayList<Proveedor> get_Proveedores();
	
	ArrayList<String> getCategoriasProveedor(String nombreProveedor);
	
	Proveedor obtenerProveedor(String nombre);

	public HashMap<Integer, String> obtenerCategorias();
}
