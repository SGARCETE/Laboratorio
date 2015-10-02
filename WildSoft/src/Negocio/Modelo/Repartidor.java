package Negocio.Modelo;

public class Repartidor {

	private Integer ID_Repartidor;
	private String Nombre;
	private String Vehiculo;

	public Repartidor(String nombre, String vehiculo) {
		Nombre = nombre;
		Vehiculo = vehiculo;
	}

	public Repartidor() {

	}

	/** GETTERS AND SETTERS **/
	public Integer getID_Repartidor() {
		return ID_Repartidor;
	}

	public void setID_Repartidor(Integer iD_Repartidor) {
		ID_Repartidor = iD_Repartidor;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getVehiculo() {
		return Vehiculo;
	}

	public void setVehiculo(String vehiculo) {
		Vehiculo = vehiculo;
	}

}
