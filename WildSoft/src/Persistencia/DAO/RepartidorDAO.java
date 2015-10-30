package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Repartidor;

public interface RepartidorDAO {

	public boolean Nuevo_Repartidor(Repartidor R);
	
	public boolean Eliminar_Repartidor(Repartidor R);
	
	public ArrayList<Repartidor> getRepartidores();
	
	public boolean Modificar_Repartidor(Repartidor R);

	public Repartidor getRepartidor(String nombreRepartidor);
	
}
