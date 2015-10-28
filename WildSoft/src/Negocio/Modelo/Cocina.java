package Negocio.Modelo;

import java.util.ArrayList;

public class Cocina {

	private ArrayList<Pedido> lista_Pedidos = new ArrayList<Pedido>();
	private ArrayList<Producto> lista_Productos = new ArrayList<Producto>();
	private String detalle_pedido;
	
	
	public ArrayList<Pedido> getLista_Pedidos() {
		
		return lista_Pedidos;
		
	}
	public void setLista_Pedidos(ArrayList<Pedido> lista_Pedidos) {
		this.lista_Pedidos = lista_Pedidos;
	}
	public ArrayList<Producto> getLista_Productos() {
		return lista_Productos;
	}
	public void setLista_Productos(ArrayList<Producto> lista_Productos) {
		this.lista_Productos = lista_Productos;
	}
	public String getDetalle_pedido() {
		return detalle_pedido;
	}
	public void setDetalle_pedido(String detalle_pedido) {
		this.detalle_pedido = detalle_pedido;
	}
	
	
	
	
	
}
