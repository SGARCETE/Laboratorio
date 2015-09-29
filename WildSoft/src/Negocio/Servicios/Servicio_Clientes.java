package Negocio.Servicios;

import Negocio.Modelo.Cliente;
import Persistencia.DAO.ClienteDAO;
import Persistencia.DAOjdbcImpl.ClienteDAOjdbcImpl;

public class Servicio_Clientes {
	private ClienteDAO clienteDAO = new ClienteDAOjdbcImpl();
	

	private boolean Nuevo_Cliente(Cliente c){
		if(c!=null)
			return clienteDAO.Nuevo_Cliente(c);
		return false;
	}
	
	
	
}//--> FIN CLASE
