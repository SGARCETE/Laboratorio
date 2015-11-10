package Negocio.Servicios;


import Negocio.Modelo.Categoria;
import Persistencia.DAO.CategoriaDAO;
import Persistencia.DAOjdbcImpl.CategoriaDAOjdbcImpl;



public class Servicio_Categoria {

	private CategoriaDAO categoriaDAO = new CategoriaDAOjdbcImpl();
	
	public boolean guardaCategoriaProducto( Categoria c){
		if(c!=null)
		{
			return categoriaDAO.guardar_categoria_producto(c);
		}
		return false;
	}
	
	public boolean guardarCategoriaMateria(Categoria c){
		if(c!=null)
		{
			return categoriaDAO.guardar_categoria_materia(c);
		}
		return false;
	}

	public boolean eliminarCategoriaMateria(Categoria c){
		if(c!=null){
			return categoriaDAO.eliminar_categoria_materia(c);
			
		}
		return false;
	}
	
	public boolean eliminarCategoriaProducto(Categoria c){
		if(c!=null){
			return categoriaDAO.eliminar_categoria_producto(c);
			
		}
		return false;
	}
	
}
