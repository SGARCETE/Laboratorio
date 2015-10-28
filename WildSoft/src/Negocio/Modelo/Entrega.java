package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Entrega {
	
	private Integer id;
	private Date fecha_salida;
	private Repartidor repartidor;
	private ArrayList<Pedido> lista_pedidos;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getFecha_salida() {
		return fecha_salida;
	}
	public void setFecha_salida(Date fecha_salida) {
		this.fecha_salida = fecha_salida;
	}
	public Repartidor getRepartidor() {
		return repartidor;
	}
	public void setRepartidor(Repartidor repartidor) {
		this.repartidor = repartidor;
	}

	public ArrayList<Pedido> getLista_pedidos() {
		return lista_pedidos;
	}

	public void setLista_pedidos(ArrayList<Pedido> lista_pedidos) {
		this.lista_pedidos = lista_pedidos;
	}
	
}
