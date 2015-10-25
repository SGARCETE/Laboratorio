package Negocio.Modelo;

import java.util.ArrayList;

public class Solicitud_compra {

	private Integer id;
	private String estado;
	private Proveedor proveedor;
	ArrayList<Materia_Prima> lista_materia_prima;
	
	
	
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
