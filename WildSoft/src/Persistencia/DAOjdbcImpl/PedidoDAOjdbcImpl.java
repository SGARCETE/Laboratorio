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
				"'"+	null		+"',"+
				"'"+	p.getCliente()	+"',"+
				"'"+    null        + "')"  ;
			conex.Insertar(SentenciaSQL_PEDIDO);
			
			Integer ID_PEDIDO = ObtenerUltimoPedido();
			
			
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
