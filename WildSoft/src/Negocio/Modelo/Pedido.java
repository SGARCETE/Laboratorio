package Negocio.Modelo;

import java.util.ArrayList;
import java.util.Date;

//Como hacemos con el enum?
public class Pedido {
	//Agregar Observacion
	private Integer Numero_Pedido; 		// ID o Numero de pedido
	private Date 	Fecha_Hora_Pedido;		// Fecha y hora en la que se ingresa el pedido al sistema
	private ArrayList<Producto>	Lista_Productos = new ArrayList<Producto>();				// Lista de productos en el pedido
	private String ESTADO = "Pendiente";
	
	// En caso que sea delivery:
	private Boolean Es_Delivery = false;
	private Cliente cliente = null;
	private Double  Total = 0.0;
	private Date 	Hora_Entrega;			// Hora en la que se debe entregar el pedido
	private int idDiaria = 1;
	
	/** GETTERS AND SETTERS **/
	
	/**
	 * @return Devuelve la id de pedido
	 */
	public Integer getNumero_Pedido() {
		return Numero_Pedido;
	}
	public void setNumero_Pedido(Integer numero_Pedido) {
		Numero_Pedido = numero_Pedido;
	}
	public Date getFecha_Hora_Pedido() {
		return Fecha_Hora_Pedido;
	}	
	public void setFecha_Hora_Pedido(Date fecha_Hora_Pedido) {
		Fecha_Hora_Pedido = fecha_Hora_Pedido;
	}
	public ArrayList<Producto> getLista_Productos() {
		return Lista_Productos;
	}
	public void setLista_Productos(ArrayList<Producto> lista_Productos) {
		Lista_Productos = lista_Productos;
	}
	public Boolean getEs_Delivery() {
		return Es_Delivery;
	}
	public void setEs_Delivery(Boolean es_Delivery) {
		Es_Delivery = es_Delivery;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Date getHora_Entrega() {
		return Hora_Entrega;
	}
	public void setHora_Entrega(Date hora_Entrega) {
		Hora_Entrega = hora_Entrega;
	}
	public void agregar_un_producto(Producto PRODUCTO) {
		if(PRODUCTO!=null)
			Lista_Productos.add(PRODUCTO);
	}
	public void quitar_un_producto(Producto PRODUCTO) {
//		if(PRODUCTO!=null)
//			Lista_Productos.remove();
		System.out.println("Producto.quitar_un_producto NO ESTA IMPLEMENTADO" );
	}
	public Double getTotal() {
		return Total;
	}
	public void setTotal(Double total) {
		Total = total;
	}
	public String getESTADO() {
		return ESTADO;
	}
	public void setESTADO(String eSTADO) {
		ESTADO = eSTADO;
	}
	public int getIdDiaria() {
		return idDiaria ;
	}
	public void setIdDiaria(int idDiaria){
		this.idDiaria = idDiaria;
	}
	
	

	
}//--> FIN CLASE

