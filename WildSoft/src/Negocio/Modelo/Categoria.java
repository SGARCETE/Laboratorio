package Negocio.Modelo;

public class Categoria {
	Integer id;
	String nombre;
	
	public Categoria(){
		
	}
	public Categoria(Integer id_, String nombre_){
		this.id=id_;
		this.nombre=nombre_;
	}
	public Categoria( String nombre_){
		this.nombre=nombre_;
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
	
}
