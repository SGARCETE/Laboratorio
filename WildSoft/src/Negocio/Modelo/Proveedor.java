package Negocio.Modelo;

import java.util.ArrayList;

public class Proveedor {

	private Integer id;
	private String nombre = "";
	private String direccion = "";
	private String telefono = "";
	private ArrayList<Integer> categoria;
	private String mail = "";
	private boolean Vigente = true;// Si un proveedor se "elimino" de la lista de proveedores. se lo pone como false, porque no se pueden ELIMINAR proveedores, causaria problemas
	
	public Proveedor(){}
	
	public Proveedor(String nombre){
		this.nombre = nombre;
	}
	
	public Proveedor(String nombre, String direccion, String telefono, String mail) {
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mail = mail;
	}

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
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public ArrayList<Integer> getCategorias() {
		return categoria;
	}
	
	public void setCategoria(ArrayList<Integer> categoria) {
		this.categoria = categoria;
	}

	public String getMail(){
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}

	public boolean isVigente() {
		return Vigente;
	}

	public void setVigente(boolean vigente) {
		Vigente = vigente;
	}
}
