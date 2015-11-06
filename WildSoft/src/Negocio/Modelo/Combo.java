package Negocio.Modelo;

import java.util.ArrayList;

public class Combo {
	
	private Integer id;
	private String nombre;
	private ArrayList<Producto> Lista_productos = new ArrayList<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<Producto> getLista_productos() {
		return Lista_productos;
	}
	public void setLista_productos(ArrayList<Producto> lista_productos) {
		Lista_productos = lista_productos;
	}
	
	public void agregar_un_producto(Producto PRODUCTO) {
		if(PRODUCTO!=null)
			Lista_productos.add(PRODUCTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
