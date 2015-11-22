package Reportes;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class Producto_DataSourcePedido implements JRDataSource{
	private ArrayList<Producto> lista_productos;
	private Pedido Pedido_mostrar;
	private int indiceParticipanteActual = -1;

	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@Override
	public boolean next() throws JRException{
		return ++indiceParticipanteActual < lista_productos.size();
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	public void addPedido(Pedido pedido){
		Pedido_mostrar = pedido;
		this.lista_productos = pedido.getLista_Productos();
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	@Override
	public Object getFieldValue(JRField jrf) throws JRException {
		Object valor = null;
		switch (jrf.getName()) {
			case "PD_id": 				valor = Pedido_mostrar.getNumero_Pedido(); 									break; // es al pedo
			case "PD_Delivery": 		valor = Pedido_mostrar.getEs_Delivery();									break;
			case "PD_numero": 			valor = Pedido_mostrar.getID_DIARIO(); 										break;
			case "PD_fecha_pedido":		valor = Pedido_mostrar.getFecha_Hora_Pedido();								break;
			case "Total": 				valor = Pedido_mostrar.getTotal();											break;
			case "PR_nombre":			valor = lista_productos.get(indiceParticipanteActual).getPR_nombre();		break;
			case "PR_Observacion": 		valor = lista_productos.get(indiceParticipanteActual).getPR_Observacion();	break;
			case "PP_producto_cantidad":valor = lista_productos.get(indiceParticipanteActual).getCantidad();		break;
			case "PP_precio":			valor = lista_productos.get(indiceParticipanteActual).getPR_precio();		break;
			case "PP_Observacion":		valor = lista_productos.get(indiceParticipanteActual).getPR_Observacion();	break;
			case "Precio_T": 			valor = Pedido_mostrar.getCliente().getNombre();							break;
			case "Total_pedido": 		valor = Pedido_mostrar.getCliente().getNombre();							break;
			case "CL_nombre": 			valor = Pedido_mostrar.getCliente().getNombre();							break;
			case "CL_telefono":			valor = Pedido_mostrar.getCliente().getTelefono_Fijo();						break;
			case "CL_direccion": 		valor = Pedido_mostrar.getCliente().getDomicilio();							break;
			case "TP_ID":				valor = lista_productos.get(indiceParticipanteActual).getPR_tipo_producto();break;
			case "TP_nombre":			valor = lista_productos.get(indiceParticipanteActual).getPR_TIPO_PRODUCTO_STRING();			break;
			default:																								break;
		}
		return valor;
	}
	
	/*>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/
	
	
}//---> FIN CLASE
