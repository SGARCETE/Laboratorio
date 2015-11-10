package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Combo;
import Negocio.Modelo.Producto;

public interface ComboDAO {
	
	ArrayList<Combo> getLista_Combos();
	
	ArrayList<Producto> getLista_Productos(Combo C);
	
	Integer get_id_combo(String nombre);
	
	ArrayList<Producto> getLista_Productos(String C);
	
	boolean ELIMINAR_Combo(Integer ID);
	

}
