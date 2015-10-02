package Persistencia.DAO;

import java.util.ArrayList;

import Negocio.Modelo.Repartidor;

public interface RepartidorDAO {

	boolean Nuevo_Repartidor(Repartidor R);
	boolean Eliminar_Repartidor(Repartidor R);
	ArrayList<Repartidor> getRepartidores();
	void Modificar_Repartidor(Repartidor R);
	
}
