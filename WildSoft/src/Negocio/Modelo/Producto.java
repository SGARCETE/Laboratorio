package Negocio.Modelo;


public class Producto {
	
	private Integer	PR_id;
	private String	PR_nombre;
	private Double	PR_precio;
	private Integer	PR_tipo_producto;
	
	public Integer getPR_id() {
		return PR_id;
	}

	public void setPR_id(Integer pR_id) {
		PR_id = pR_id;
	}

	public String getPR_nombre() {
		return PR_nombre;
	}

	public void setPR_nombre(String pR_nombre) {
		PR_nombre = pR_nombre;
	}

	public Double getPR_precio() {
		return PR_precio;
	}

	public void setPR_precio(Double pR_precio) {
		PR_precio = pR_precio;
	}

	public Integer getPR_tipo_producto() {
		return PR_tipo_producto;
	}

	public void setPR_tipo_producto(Integer pR_tipo_producto) {
		PR_tipo_producto = pR_tipo_producto;
	}
}
