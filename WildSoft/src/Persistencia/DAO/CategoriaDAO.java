package Persistencia.DAO;

import Negocio.Modelo.Categoria;


public interface CategoriaDAO {

	public boolean guardar_categoria_producto(Categoria c);

	public boolean guardar_categoria_materia(Categoria c);
	
}
