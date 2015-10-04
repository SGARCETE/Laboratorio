package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Pedido;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.PedidoDAO;

public class PedidoDAOjdbcImpl implements PedidoDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

	public boolean AGREGAR_PEDIDO(Pedido p) {
		String CLIENTE = "NULL";
		if(p.getCliente()!=null && p.getCliente().getID_Cliente()!=0)
			p.getCliente().getID_Cliente();
		String PEDIDO = "NULL";
		String SentenciaSQL_PEDIDO = "INSERT INTO PEDIDO(PD_fecha_pedido, PD_estado, PD_cliente, PD_entrega) VALUES ("+
				"'"+	formato_yyyyMMdd.format(p.getFecha_Hora_Pedido())	+"',"+
				""+		1													+","+
				""+		CLIENTE												+","+
				""+		PEDIDO												+")";
		System.out.println(SentenciaSQL_PEDIDO);
		boolean Exito_al_Ingresar_pedido = conex.Insertar(SentenciaSQL_PEDIDO);
		
		if(Exito_al_Ingresar_pedido){
			Integer PEDIDO_ID = ObtenerUltimoPedido();
			for (int i = 0; i < p.getLista_Productos().size(); i++) {
				
				Integer PRODUCTO_ID = p.getLista_Productos().get(i).getPR_id();
				Double PRECIO_ACTUAL = p.getLista_Productos().get(i).getPR_precio();
				String OBSERVACION =  p.getLista_Productos().get(i).getPR_Observacion();
				String SentenciaSQL_producto_pedidos = "INSERT INTO producto_pedidos (PP_pedidoid, PP_productoid, PP_producto_cantidad, PP_Observacion, PP_precio)"
						+ "VALUES ("+
						""+	 PEDIDO_ID			+","+	// INTEGER
						""+	 PRODUCTO_ID		+","+	// INTEGER
						""+	 1					+","+	// INTEGER
						"'"+ OBSERVACION		+" ',"+	// STRING
						""+  PRECIO_ACTUAL		+ ")";	// DOUBLE
				boolean Asociar_producto = conex.Insertar(SentenciaSQL_producto_pedidos);
				System.out.println(SentenciaSQL_producto_pedidos);
				if(!Asociar_producto)
					return false;
			}
			return true;
		}
		return false;
		/**
		 PP_pedidoid` int(11) DEFAULT NULL,
		 PP_productoid` int(11) DEFAULT NULL,
		 PP_producto_cantidad` int(11) DEFAULT NULL,
		 PP_Observacion` varchar(300) DEFAULT NULL,
		 PP_precio` double DEFAULT NULL,
		 
		 KEY `PP_pedidoid` (`PP_pedidoid`),
		 KEY `PP_productoid` (`PP_productoid`),
		 CONSTRAINT `producto_pedidos_ibfk_1` FOREIGN KEY (`PP_pedidoid`) REFERENCES `pedido` (`PD_id`),
		 CONSTRAINT `producto_pedidos_ibfk_2` FOREIGN KEY (`PP_productoid`) REFERENCES `pedido` (`PD_id`)
		 */
	/**
	 `PD_id` int(11) NOT NULL AUTO_INCREMENT,
	 `PD_fecha_pedido` date DEFAULT NULL,
	 `PD_estado` int(11) DEFAULT NULL,
	 `PD_cliente` int(11) DEFAULT NULL,
	 `PD_entrega` int(11) DEFAULT NULL,
	*/
	}
	/*------------------------------------------------------------------------------*/

	private Integer ObtenerUltimoPedido() {
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT PD_id FROM PEDIDO WHERE PD_id = (select max(PD_id) from PEDIDO)");				
			rs.first();
			int ID = rs.getInt("PD_id");
			conex.cerrarConexion();
			return ID;
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,"No se puede dar la fila solicitada! \n ERROR : " + SQLE.getMessage());
		}
		return 0;
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



	@Override
	public Pedido OBTENER_PEDIDO(Integer Numero_Pedido) {
		Pedido p = new Pedido();
		
		return p;
	}
	

	
	
	
	
	
	
	
	
	
}
