package Negocio.Modelo;

public class Proveedor {

	private Integer id;
	private String nombre="";
	private String direccion="";
	private String telefono="";
	private String categoria_String="";
	private Integer categoria;
	
	public Proveedor(String nombre){
		this.nombre = nombre;
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
	public Integer getCategoria() {
		return categoria;
	}
	public void setCategoria(Integer categoria) {
		this.categoria = categoria;
	}
	
	
	
}
