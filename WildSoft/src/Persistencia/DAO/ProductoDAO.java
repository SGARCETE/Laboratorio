package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Producto;

public interface ProductoDAO {

	public boolean AGREGAR_PRODUCTO(Producto p);

	public ArrayList<Producto> getVARIEDAD_DEL_PRODUCTO(String Tipo_Producto);

	public boolean ELIMINAR_PRODUCTO(Producto p);
	
	
	
}
