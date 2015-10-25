package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Proveedor;

public interface ProveedorDAO {
	
	ArrayList<Proveedor> get_Proveedor_Categoria(String categoria_MP);
	
	boolean AGREGAR_PROVEEDOR(Proveedor p);
	
	boolean ELIMINAR_Proveedor(Proveedor p);

}
