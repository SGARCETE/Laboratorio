package Negocio.Modelo;

import java.util.ArrayList;

public class Proveedor {

	private Integer id;
	private String nombre = "";
	private String direccion = "";
	private String telefono = "";
	private String categoria_String = "";
	private ArrayList<Integer> categoria;
	private String mail = "";
	
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
	public String getCategoria_String() {
		return categoria_String;
	}
	public void setCategoria_String(String categoria) {
		this.categoria_String = categoria;
	}
	public ArrayList<Integer> getCategoria() {
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
	
	
	
}
