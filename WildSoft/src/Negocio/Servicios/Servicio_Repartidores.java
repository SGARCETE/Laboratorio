package Negocio.Servicios;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;
import Negocio.Modelo.Repartidor;
import Persistencia.DAO.RepartidorDAO;
import Persistencia.DAOjdbcImpl.RepartidorDAOjdbcImpl;



public class Servicio_Repartidores {

	private RepartidorDAO repartidorDAO = new RepartidorDAOjdbcImpl();


	public boolean guardar_nuevo_pedido(Repartidor R){
		return repartidorDAO.Nuevo_Repartidor(R);
	}
	
	public ArrayList<Repartidor> get_Repartidores(){
		return repartidorDAO.getRepartidores();
	}
	
	public boolean eliminar_Repartidor(Repartidor R){
		return repartidorDAO.Eliminar_Repartidor(R);
	}






}
