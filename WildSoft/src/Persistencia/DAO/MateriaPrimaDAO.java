package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Materia_Prima;

public interface MateriaPrimaDAO {

	 ArrayList<Materia_Prima> getVARIEDAD_Materia_Prima(String Categoria_mp);
	 
	 boolean ELIMINAR__Materia_Prima(Materia_Prima m);
	 
	 ArrayList<String> getCategoria_MP() ;
	
	 Integer obtenerIdCategoria(String nombreCategoria);
	
}
