package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;
import Persistencia.DAOjdbcImpl.MateriaPrimaDAOjdbcImpl;

public class Servicio_Materia_Prima {

	private MateriaPrimaDAOjdbcImpl MateriaPrimaDAO = new MateriaPrimaDAOjdbcImpl();
	
	public ArrayList<Materia_Prima> getVARIEDAD_Materia_Prima(String Categoria_mp){
			return MateriaPrimaDAO.getVARIEDAD_Materia_Prima(Categoria_mp);
	}
 
	public boolean ELIMINAR__Materia_Prima(Materia_Prima m){
		return MateriaPrimaDAO.ELIMINAR__Materia_Prima(m);
	}
	 
	public ArrayList<String> getCategoria_MP(){
		return MateriaPrimaDAO.getCategoria_MP();
	}
	
	public Integer obtenerId(String nombreMP){
		return MateriaPrimaDAO.obtenerId(nombreMP);
	}
	
	public Integer obtenerIdCategoria(String nombreCategoria){
		return MateriaPrimaDAO.obtenerIdCategoria(nombreCategoria);
	}
}
