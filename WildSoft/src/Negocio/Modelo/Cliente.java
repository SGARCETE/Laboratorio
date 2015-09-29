package Negocio.Modelo;

public class Cliente {
	private Integer ID_Cliente;
	private String  Nombre;
	private String  Apellido;
	private String  Domicilio;
	private Integer Telefono_Fijo;
	
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
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getDomicilio() {
		return Domicilio;
	}
	public void setDomicilio(String domicilio) {
		Domicilio = domicilio;
	}
	public Integer getTelefono_Fijo() {
		return Telefono_Fijo;
	}
	public void setTelefono_Fijo(Integer telefono_Fijo) {
		Telefono_Fijo = telefono_Fijo;
	}


	
} // FIN CLASE
