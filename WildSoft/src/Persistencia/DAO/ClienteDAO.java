package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Cliente;

public interface ClienteDAO {
	
	public boolean guardar_cliente(Cliente c);

	public boolean Eliminar_Cliente(Cliente c);

	public ArrayList<Object> getAutoCompleter_Clientes();
	
	public ArrayList<Cliente > getListaCliente();

	public Cliente getCliente(String nombre_Cliente);

	public Cliente getCliente(Integer ID_Cliente);
	
	public boolean modificar_cliente(Cliente c);

} //--> FIN CLASE
