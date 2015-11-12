package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Combo;
import Negocio.Modelo.Producto;
import Persistencia.DAOjdbcImpl.ComboDAOjdbcImpl;


public class Servicio_Combos {

	private ComboDAOjdbcImpl comboDAO = new ComboDAOjdbcImpl();
	
	public ArrayList<Combo> getLista_Combos(){
		return comboDAO.getLista_Combos();
	}
	
	
	public ArrayList<Producto> getLista_Productos(Combo C){
		return comboDAO.getLista_Productos(C);
	}
	
	Integer Obtener_id_combo(String nombre){
		return comboDAO.get_id_combo(nombre);
	}
	
	
	public ArrayList<Producto> getLista_Productos(String C){
		return comboDAO.getLista_Productos(C);
	}
	
	public boolean ELIMINAR_Combo(Integer ID) {	
		return comboDAO.ELIMINAR_Combo(ID);
		}


	public Combo getCombo(Integer id) {
		return comboDAO.get_combo(id);
	}
	

	
}
