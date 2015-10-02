package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Repartidor;

public interface RepartidorDAO {

	boolean Nuevo_repartidor(Repartidor R);
	boolean Eliminar_Repartidor(Repartidor R);
	ArrayList<Repartidor> getRepartidores();
	
}
