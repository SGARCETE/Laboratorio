package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Cliente;

public interface ClienteDAO {

	public boolean Nuevo_Cliente(Cliente c);

	public boolean Eliminar_Cliente(Cliente c);

	public ArrayList<Object> getAutoCompleter_Clientes();

	public Cliente getCliente(String nombre_Cliente);

} //--> FIN CLASE
