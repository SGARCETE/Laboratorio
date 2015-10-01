package Persistencia.DAOjdbcImpl;

import Negocio.Modelo.Cliente;
import Negocio.Modelo.Repartidor;
import Persistencia.DAO.RepartidorDAO;

public class RepartidorDAOjdbcImpl implements RepartidorDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	
	public boolean Nuevo_Repartidor(Repartidor R) {
		String SentenciaSQL = "INSERT INTO Repartidor(RE_nombre, RE_vehiculo) VALUES ("+
			"'"+	R.getNombre()			+"',"+
			"'"+	R.getVehiculo()		    +"')";
		return conex.Insertar(SentenciaSQL);
	}
	
	public boolean Eliminar_Repartidor(Repartidor R) {
		String SentenciaSQL = "DELETE from Repartidor R where R.RE_id =" + R.getID_Repartidor();
		return conex.Insertar(SentenciaSQL);
	}
	
	
	
	
	
	
	
	
	
}
