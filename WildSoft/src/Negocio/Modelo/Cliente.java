package Negocio.Modelo;

public class Cliente {
	private Integer ID_Cliente = 0;
	private String  Nombre = "";
	private String  Direccion = "";
	private String  Detalle = "";
	private String  Telefono = "";
	
	public Cliente(){}
	
	public Cliente(String nombre_,String direccion_, String telefono_, String detalle_){
		this.Nombre = nombre_;
		this.Direccion= direccion_;
		this.Telefono= telefono_;
		this.Detalle= detalle_;
	}
	public Cliente(String nombre_)
	{
		this.Nombre=nombre_;
	}
	
	/** GETTERS AND SETTERS **/
	public Integer getID_Cliente() {
		return ID_Cliente;
	}
	public void setID_Cliente(Integer iD_Cliente) {
		ID_Cliente = iD_Cliente;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDomicilio() {
		return Direccion;
	}
	public void setDomicilio(String domicilio) {
		Direccion = domicilio;
	}
	public String getTelefono_Fijo() {
		return Telefono;
	}
	public void setTelefono_Fijo(String telefono_Fijo) {
		Telefono = telefono_Fijo;
	}
	
	public String getDetalle() {
		return Detalle;
	}
	public void setDetalle(String detalle) {
		Detalle = detalle;
	}



	
} // FIN CLASE
