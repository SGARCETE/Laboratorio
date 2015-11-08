package Negocio.Servicios;

import java.util.ArrayList;
import java.util.HashMap;

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
	public ArrayList<Materia_Prima> getCategoria(){
		return MateriaPrimaDAO.getCategoria();
	}
	
	
	public Integer obtenerId(String nombreMP){
		return MateriaPrimaDAO.obtenerId(nombreMP);
	}
	
	public Integer obtenerIdCategoria(String nombreCategoria){
		return MateriaPrimaDAO.obtenerIdCategoria(nombreCategoria);
	}
	public boolean AgregarMAteriaPrima(Materia_Prima m)
	{
		return MateriaPrimaDAO.AGREGAR_Materia_Prima(m);
	}
	public String dameNombreCategoria(Integer id)
	{
		return MateriaPrimaDAO.dameNombreCategoria(id);
	}
	public HashMap<Integer, String> getCategorias() {
		return MateriaPrimaDAO.obtenerCategorias();
	}
	public boolean modificarMateria(Materia_Prima m)
	{
		return MateriaPrimaDAO.modificarMateria(m);
	}
}
