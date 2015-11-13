package Negocio.Modelo;
import java.util.Date;


public class Materia_Prima {

	private Integer id;
	private String nombre="";
	private Date fecha_vencimiento = null;
	private Integer categoria = 1;
	private String categoria_string = "";
	private String nombreCategoria; // SIN USO
	private int cantidad=0;
	
	public Materia_Prima(String nombre_,Date fecha,int categoria_ ) {
		this.nombre=nombre_;
		this.fecha_vencimiento= fecha;
		this.categoria=categoria_;
	}
	public Materia_Prima(){
		
	}
	
	public Materia_Prima(Integer id_, String nombre_)
	{
		this.id= id_;
		this.nombre= nombre_;
	}
	public Materia_Prima(String nombre_,Date fecha,String categoria_ ) {
		this.nombre=nombre_;
		this.fecha_vencimiento= fecha;
		this.categoria_string=categoria_;
	}
	public Materia_Prima(Integer id_,String nombre_,Date fecha,Integer categoria_ ) {
		this.id=id_;
		this.nombre=nombre_;
		this.fecha_vencimiento= fecha;
		this.categoria=categoria_;
	}
	
	
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
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
	
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public String getCategoria_string() {
		return categoria_string;
	}
	
	public void setCategoria_string(String categoria_string) {
		this.categoria_string = categoria_string;
	}
	
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	}
	
	public Integer getCategoria() {
		return categoria;
	}
	
	public String dameCagoria(Materia_Prima m)
	{
		
		return m.getCategoria_string();
	}
	public String getNombreCategoria() {
		return nombreCategoria;
	}
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}
	
	
}
