package Persistencia.DAO;

import java.util.List;

public interface EstadoDAO {
	
	public String getEstado(int id);
	
	public int getEstado(String nombre);

	public List<String> getListaEstados();
}
