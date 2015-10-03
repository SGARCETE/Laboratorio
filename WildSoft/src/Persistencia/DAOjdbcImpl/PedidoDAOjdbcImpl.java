package Persistencia.DAOjdbcImpl;

import java.util.ArrayList;

import Negocio.Modelo.Pedido;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.PedidoDAO;

public class PedidoDAOjdbcImpl implements PedidoDAO{
	private ConectorMySQL conex = new ConectorMySQL();

	public boolean AGREGAR_PEDIDO(Pedido p) {
		String SentenciaSQL = "INSERT INTO PEDIDO(PE_fecha_pedido, PE_observacion, PE_estado, PE_cliente, PE_entrega) VALUES ("+
				"'"+	p.getFecha_Hora_Pedido()			+"',"+
				"'"+	null	+"',"+
				"'"+	null 		+"',"+
				"'"+	p.getCliente()	+"',"+
				"'"+    null        + "')"  ;
			return conex.Insertar(SentenciaSQL);
		}
	
	
	
	public ArrayList<Pedido> getPEDIDOS() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean ELIMINAR_PEDIDO(Pedido p) {
		String SentenciaSQL = "DELETE * FROM Pedido WHERE PD_id="
				+ p.getNumero_Pedido();
		return conex.Insertar(SentenciaSQL); // Insert devuelve un boolean
	}

	public Pedido MODIFICAR_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return null;
	}

	public Pedido OBTENER_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
	
	
	
	
	
	
}
