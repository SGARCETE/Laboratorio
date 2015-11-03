package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Cliente;
import Persistencia.DAO.ClienteDAO;
import Persistencia.DAOjdbcImpl.ClienteDAOjdbcImpl;

public class Servicio_Clientes {
	
	private ClienteDAO clienteDAO = new ClienteDAOjdbcImpl();
	private ArrayList<Object> Lista_Clientes_AutoCompleter = clienteDAO.getAutoCompleter_Clientes();

	@SuppressWarnings("unused")
	private boolean Nuevo_Cliente(Cliente c){
		if(c!=null)
			return clienteDAO.Nuevo_Cliente(c);
		return false;
	}
	
	
	public boolean guardar_nuevo_cliente (Cliente c)
	{
		if(c!=null)
			return clienteDAO.guardar_cliente(c);
		return false;
	}

	public ArrayList<Object> getLISTA_CLIENTES() {
		return Lista_Clientes_AutoCompleter;
	}
	public ArrayList<Cliente> get_Lista_Clientes()
	{
		return clienteDAO.getListaCliente();
	}

	public Cliente getCliente(String Nombre_Cliente) {
		return clienteDAO.getCliente(Nombre_Cliente);
	}
	
	public Cliente getCliente(Integer ID_CLIENTE){
		return clienteDAO.getCliente(ID_CLIENTE);
	}
	public boolean Eliminar_cliente(Cliente c)
	{
		return clienteDAO.Eliminar_Cliente(c);
	}
	public boolean Modificar_Cliente(Cliente c)
	{
		return clienteDAO.modificar_cliente(c);
	}
	
}//--> FIN CLASE
