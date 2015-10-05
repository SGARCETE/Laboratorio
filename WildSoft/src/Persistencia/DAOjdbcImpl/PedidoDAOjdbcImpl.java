package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Negocio.Modelo.Repartidor;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.PedidoDAO;

public class PedidoDAOjdbcImpl implements PedidoDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

	public boolean AGREGAR_PEDIDO(Pedido p) {
		String CLIENTE = "NULL";
		if(p.getCliente()!=null && p.getCliente().getID_Cliente()!=0)
			CLIENTE = p.getCliente().getID_Cliente().toString();
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
	
	
	
	public ArrayList<Pedido> getLISTA_PEDIDOS() {
		ArrayList<Pedido> Arreglo = new ArrayList<Pedido>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("select  P.PD_id, P.PD_fecha_pedido, EST.PEST_nombre, PD_cliente,SUM(PP.PP_precio) as Precio" +
			" from  Pedido P join producto_pedidos PP join Pe_estado EST  on P.PD_id= PP.PP_pedidoid and P.PD_estado= EST.Pest_id " +
			"group by P.PD_id");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Pedido P = new Pedido();
				P.setNumero_Pedido(Fila.getInt("PD_id"));
				P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
				P.setESTADO(Fila.getString("PEST_nombre"));
				P.setCliente(null);
				P.setTotal(Fila.getDouble("Precio"));
				Arreglo.add(P);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
		
	
	public ArrayList<Producto> getLista_Productos(Pedido P) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("select P.PD_id, PR.Pr_nombre, PP.PP_precio from Producto PR join Producto_pedidos PP join Pedido P " +
			"on PR.Pr_id=PP.PP_productoid and P.PD_id=PP.PP_pedidoid and P.PD_id=" + P.getNumero_Pedido());
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto Prod = new Producto();
				Prod.setPR_id(Fila.getInt("Pd_id"));
				Prod.setPR_nombre(Fila.getString("PR_nombre"));
				Prod.setPR_precio(Fila.getDouble("PP_precio"));
				Arreglo.add(Prod);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
	public boolean ELIMINAR_PEDIDO(Pedido P) {
		String SentenciaSQL = "UPDATE Pedido SET PD_estado = 5 where PD_id= " +P.getNumero_Pedido() ;
		return conex.Insertar(SentenciaSQL);
	}

	

	public Pedido MODIFICAR_PEDIDO(Pedido p) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Pedido OBTENER_PEDIDO(Integer Numero_Pedido) {
		Pedido p = null;
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String SentenciaSQL = "select  P.PD_id, P.PD_fecha_pedido, EST.PEST_nombre, PD_cliente,SUM(PP.PP_precio) "
					+ "as Precio from  Pedido P join producto_pedidos PP join Pe_estado EST  on P.PD_id= PP.PP_pedidoid "
					+ "and P.PD_estado= EST.Pest_id and P.PD_id="+ Numero_Pedido + " group by P.Pd_id";
			st.executeQuery(SentenciaSQL);
			ResultSet Fila = st.getResultSet();
			Fila.first();
			Pedido P = new Pedido();
			P.setNumero_Pedido(Fila.getInt("PD_id"));
			P.setFecha_Hora_Pedido(Fila.getDate("PD_fecha_pedido"));
			P.setESTADO(Fila.getString("PEST_nombre"));
			P.setCliente(null);
			P.setTotal(Fila.getDouble("Precio"));
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return p;
		
	}
	
}
