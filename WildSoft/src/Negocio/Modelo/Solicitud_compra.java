package Negocio.Modelo;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Solicitud_compra {

	private Integer id;
	private String estado;
	private Proveedor proveedor;
	private Date fecha;
	private Integer precio;
	private File solicitudPDF;
	
	private ArrayList<Materia_Prima> lista_materia_prima;

	public Solicitud_compra(int id) {
		this.id = id;
	}

	public Solicitud_compra() {
	}

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

	public void setLista_materia_prima(
			ArrayList<Materia_Prima> lista_materia_prima) {
		this.lista_materia_prima = lista_materia_prima;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Solicitud_compra) {
			Solicitud_compra sc = (Solicitud_compra) obj;
			if (this.id.equals(sc.id)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public File getSolicitudPDF() {
		return solicitudPDF;
	}

	public void setSolicitudPDF(File solicitudPDF) {
		this.solicitudPDF = solicitudPDF;
	}
}
