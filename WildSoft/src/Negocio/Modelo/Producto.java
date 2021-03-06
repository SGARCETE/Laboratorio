package Negocio.Modelo;


public class Producto {
	
	private Integer	PR_id = 0;
	private Integer	PR_tipo_producto = 0;
	private Double	PR_precio = 0.0;
	private String	PR_nombre = "";
	private String 	PR_Observacion = "";
	private String 	PR_TIPO_PRODUCTO_STRING ="";
	private int cantidad = 0;
	
	public Producto(){}
	
	public Producto(String Tipo_Producto, String Nombre, Integer Tipo_producto_ID, Double Precio, Integer Cantidad){
		PR_TIPO_PRODUCTO_STRING = Tipo_Producto;
		PR_tipo_producto = Tipo_producto_ID;
		PR_precio = Precio;
		PR_nombre = Nombre;
		cantidad = Cantidad;
	}
	
	public Producto(Integer id, String Tipo_Producto, String Nombre, Integer Tipo_producto_ID, Double Precio, Integer Cantidad){
		PR_TIPO_PRODUCTO_STRING = Tipo_Producto;
		PR_tipo_producto = Tipo_producto_ID;
		PR_precio = Precio;
		PR_nombre = Nombre;
		cantidad = Cantidad;
		PR_id= id;
	}
	
	public Producto(String Tipo_Producto, String Nombre, Integer Tipo_producto_ID, String Observacion, Double Precio, Integer Cantidad){
		PR_TIPO_PRODUCTO_STRING = Tipo_Producto;
		PR_tipo_producto = Tipo_producto_ID;
		PR_precio = Precio;
		PR_nombre = Nombre;
		PR_Observacion = Observacion;
		cantidad = Cantidad;
	}
	
	public Producto(Integer id, String Tipo_Producto, String Nombre, Integer Tipo_producto_ID, String Observacion, Double Precio, Integer Cantidad){
		PR_TIPO_PRODUCTO_STRING = Tipo_Producto;
		PR_tipo_producto = Tipo_producto_ID;
		PR_precio = Precio;
		PR_nombre = Nombre;
		PR_Observacion = Observacion;
		cantidad = Cantidad;
		PR_id= id;
	}
	
	
	public Producto(int id){
		PR_id=id;
		
	}
	

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

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

	public String getPR_Observacion() {
		return PR_Observacion;
	}

	public void setPR_Observacion(String pR_Observacion) {
		PR_Observacion = pR_Observacion;
	}

	public String getPR_TIPO_PRODUCTO_STRING() {
		return PR_TIPO_PRODUCTO_STRING;
	}

	public void setPR_TIPO_PRODUCTO_STRING(String pR_TIPO_PRODUCTO_STRING) {
		PR_TIPO_PRODUCTO_STRING = pR_TIPO_PRODUCTO_STRING;
	}

	@Override
	public boolean equals(Object P) {
		if(((Producto) P).getPR_id()==this.getPR_id() && ((Producto) P).getPR_tipo_producto()==this.getPR_tipo_producto())
			return true;
		return false;
	}


}
