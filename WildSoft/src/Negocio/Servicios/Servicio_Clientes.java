package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Cliente;
import Persistencia.DAO.ClienteDAO;
import Persistencia.DAOjdbcImpl.ClienteDAOjdbcImpl;

public class Servicio_Clientes {
	
	private ClienteDAO clienteDAO = new ClienteDAOjdbcImpl();
	private Principal_Negocio_Interfaz Principal;
	
	public Servicio_Clientes(Principal_Negocio_Interfaz principal){
		Principal = principal;
	}
		
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean guardar_nuevo_cliente (Cliente c){
		if(c!=null){
			boolean salida = clienteDAO.guardar_cliente(c);
			Principal.Actualizar_autocomplete_clientes();
			return salida;
		}
		return false;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean Modificar_Cliente(Cliente c)	{
		if(c!=null){
			boolean salida =  clienteDAO.modificar_cliente(c);
			Principal.Actualizar_autocomplete_clientes();
			return salida;
		}
		return false;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean Eliminar_cliente(Cliente c){
		if(c!=null){
			boolean salida =  clienteDAO.Eliminar_Cliente(c);
			Principal.Actualizar_autocomplete_clientes();
			return salida;
		}
		return false;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Object> getLISTA_CLIENTES() {
		return clienteDAO.getAutoCompleter_Clientes();
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Cliente> get_Lista_Clientes(){
		return clienteDAO.getListaCliente();
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Cliente getCliente(String Nombre_Cliente) {
		return clienteDAO.getCliente(Nombre_Cliente);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Cliente getCliente(Integer ID_CLIENTE){
		return clienteDAO.getCliente(ID_CLIENTE);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
}//--> FIN CLASE
