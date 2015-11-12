package Persistencia.DAO;

import java.util.ArrayList;
import java.util.HashMap;

import Negocio.Modelo.Producto;

public interface ProductoDAO {

	public boolean AGREGAR_PRODUCTO(Producto p);

	public ArrayList<Producto> getVARIEDAD_DEL_PRODUCTO(String Tipo_Producto);

	public boolean ELIMINAR_PRODUCTO(Producto p);

	public ArrayList<String> getTipos_Producto();
	
	ArrayList<Producto> GET_PRODUCTOS();
	
	ArrayList<String> getTipo_Producto_STRING(Integer id);
	
	boolean Modificar_Producto(Producto P);
	
	ArrayList<Integer> getTipo_Producto_INTEGER (String nombre);

	public HashMap<Integer, String> obtenerCategorias();

	public Integer getIdProducto(String nombreProducto);
	
}
