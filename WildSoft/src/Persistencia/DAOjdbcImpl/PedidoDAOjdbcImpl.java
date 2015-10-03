package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Pedido;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.PedidoDAO;

public class PedidoDAOjdbcImpl implements PedidoDAO{
	private ConectorMySQL conex = new ConectorMySQL();

	public boolean AGREGAR_PEDIDO(Pedido p) {
		String SentenciaSQL_PEDIDO = "INSERT INTO PEDIDO(PE_fecha_pedido, PE_estado, PE_cliente, PE_entrega) VALUES ("+
				"'"+	p.getFecha_Hora_Pedido()			+"',"+
				"'"+	p.getESTADO()	+"',"+
				"'"+	p.getCliente().getID_Cliente()		+"',"+
				"'"+	p.getCliente()	+"',"+
				"'"+    0        + "')";
			conex.Insertar(SentenciaSQL_PEDIDO);
			
			Integer ID_PEDIDO = ObtenerUltimoPedido();
			
			for (int i = 0; i < p.getLista_Productos().size(); i++) {
				
				String SentenciaSQL_producto_pedidos = "INSERT INTO producto_pedidos (PE_fecha_pedido, PE_estado, PE_cliente, PE_entrega) VALUES ("+
						"'"+	p.getFecha_Hora_Pedido()			+"',"+
						"'"+	p.getESTADO()	+"',"+
						"'"+	p.getCliente().getID_Cliente()		+"',"+
						"'"+	p.getCliente()	+"',"+
						"'"+    0        + "')";
					conex.Insertar(SentenciaSQL_PEDIDO);
					/**
					  `PP_pedidoid` int(11) DEFAULT NULL,
					  `PP_productoid` int(11) DEFAULT NULL,
					  `PP_producto_cantidad` int(11) DEFAULT NULL,
					  `PP_Observacion` varchar(300) DEFAULT NULL,
					  `PP_precio` double DEFAULT NULL,
					  KEY `PP_pedidoid` (`PP_pedidoid`),
					  KEY `PP_productoid` (`PP_productoid`),
					  CONSTRAINT `producto_pedidos_ibfk_1` FOREIGN KEY (`PP_pedidoid`) REFERENCES `pedido` (`PD_id`),
					  CONSTRAINT `producto_pedidos_ibfk_2` FOREIGN KEY (`PP_productoid`) REFERENCES `pedido` (`PD_id`)
					  */
			}
			
		/**
		 `PD_id` int(11) NOT NULL AUTO_INCREMENT,
		 `PD_fecha_pedido` date DEFAULT NULL,
		 `PD_estado` int(11) DEFAULT NULL,
		 `PD_cliente` int(11) DEFAULT NULL,
		 `PD_entrega` int(11) DEFAULT NULL,
		*/
		return false;
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

	public Pedido OBTENER_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	
	
	
	
	
	
	
	
}
