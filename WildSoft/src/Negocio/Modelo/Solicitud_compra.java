package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;

public class Solicitud_compra {

	private Integer id;
	private String estado;
	private Proveedor proveedor;
	private Date fecha;
	private Integer precio;
	
	ArrayList<Materia_Prima> lista_materia_prima;
	
	
	public Integer getPrecio() {
		return precio;
	}
	public void setPrecio(Integer precio) {
		this.precio = precio;
	}
	
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public ArrayList<Materia_Prima> getLista_materia_prima() {
		return lista_materia_prima;
	}
	public void setLista_materia_prima(ArrayList<Materia_Prima> lista_materia_prima) {
		this.lista_materia_prima = lista_materia_prima;
	}
	
	
	
	
}
