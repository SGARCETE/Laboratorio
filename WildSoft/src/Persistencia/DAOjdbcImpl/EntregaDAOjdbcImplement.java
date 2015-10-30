package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Cliente;
import Negocio.Modelo.Entrega;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Repartidor;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.EntregaDAO;

public class EntregaDAOjdbcImplement implements EntregaDAO {
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat(
			"yyyy-MM-dd");

	private ConectorMySQL conex = new ConectorMySQL();

	public ArrayList<Repartidor> getLISTA_REPARTIDORES() {

		ArrayList<Repartidor> Arreglo = new ArrayList<Repartidor>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String Query = "select * from Repartidor";
			System.out.println("getLISTA_REPARTIDORES " + Query);
			st.executeQuery(Query);

			// st.executeQuery("select P.PD_Delivery, P.PD_id, P.PD_numero,  P.PD_fecha_pedido, EST.PEST_nombre, C.CL_nombre,SUM(PP.PP_precio * PP.PP_producto_cantidad) as Precio "
			// +" from  Pedido P join producto_pedidos PP join Pe_estado EST join Cliente C  on C.cl_id= P.PD_cliente and  P.PD_id= PP.PP_pedidoid and P.PD_fecha_pedido= CURDATE() and P.PD_estado= EST.Pest_id"
			// +"  group by P.PD_id");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Repartidor r = new Repartidor();
				r.setID_Repartidor(Fila.getInt("RE_id"));
				r.setNombre(Fila.getString("RE_nombre"));
				r.setVehiculo(Fila.getString("RE_vehiculo"));

				Arreglo.add(r);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	// Devuelve una lista con todos los pedidos en estado preparado.
	public ArrayList<Pedido> getLISTA_PEDIDOS() {

		ArrayList<Pedido> Arreglo = new ArrayList<Pedido>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String Query = "select * from pedido PD join Cliente CL where PD.PD_estado=2 and PD.PD_cliente= CL.CL_id and PD.PD_Delivery= 1";
			System.out.println("getLISTA_PEDIDOS " + Query);
			st.executeQuery(Query);

			// st.executeQuery("select P.PD_Delivery, P.PD_id, P.PD_numero,  P.PD_fecha_pedido, EST.PEST_nombre, C.CL_nombre,SUM(PP.PP_precio * PP.PP_producto_cantidad) as Precio "
			// +" from  Pedido P join producto_pedidos PP join Pe_estado EST join Cliente C  on C.cl_id= P.PD_cliente and  P.PD_id= PP.PP_pedidoid and P.PD_fecha_pedido= CURDATE() and P.PD_estado= EST.Pest_id"
			// +"  group by P.PD_id");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Pedido p = new Pedido();
				p.setNumero_Pedido(Fila.getInt("PD_id"));
				p.setID_DIARIO(Fila.getInt("PD_numero"));
				p.setCliente(new Cliente(Fila.getString("CL_nombre")));

				Arreglo.add(p);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	// Devuelve la lista de pedidos pertenecientes a esa entrega
	public ArrayList<Pedido> getENTREGA_PEDIDOS(Entrega ent) {

		ArrayList<Pedido> Arreglo = new ArrayList<Pedido>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String Query = "select * from pedido PD join Cliente CL join Entrega ENT join Entrega_Pedido EP on "
					+ "PD.PD_cliente= CL.CL_id and ENT.ENT_id= EP.EP_entrega_id and PD.PD_id= EP.EP_pedido_id and ENT.ENT_id = "
					+ ent.getId();
			System.out.println("getENTREGA_PEDIDOS " + Query);
			st.executeQuery(Query);

			// st.executeQuery("select P.PD_Delivery, P.PD_id, P.PD_numero,  P.PD_fecha_pedido, EST.PEST_nombre, C.CL_nombre,SUM(PP.PP_precio * PP.PP_producto_cantidad) as Precio "
			// +" from  Pedido P join producto_pedidos PP join Pe_estado EST join Cliente C  on C.cl_id= P.PD_cliente and  P.PD_id= PP.PP_pedidoid and P.PD_fecha_pedido= CURDATE() and P.PD_estado= EST.Pest_id"
			// +"  group by P.PD_id");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Pedido p = new Pedido();
				p.setNumero_Pedido(Fila.getInt("PD_id"));
				p.setID_DIARIO(Fila.getInt("PD_numero"));
				p.setCliente(new Cliente(Fila.getString("CL_nombre")));

				Arreglo.add(p);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	// Agrega un pedido a la entrega
	public boolean AGREGAR_PEDIDO(Entrega e) {
		boolean resultado = false;
		Integer ENTREGA_ID = e.getId();
		for (int i = 0; i < e.getLista_pedidos().size(); i++) {

			Integer PEDIDO_ID = e.getLista_pedidos().get(i).getNumero_Pedido();

			String SentenciaSQL_producto_pedidos = "insert into Entrega_Pedido (EP_entrega_id, EP_pedido_id ) values ( "
					+ "" + ENTREGA_ID + "," + // INTEGER
					"" + PEDIDO_ID + ")"; // INTEGER

			resultado = conex.Insertar(SentenciaSQL_producto_pedidos);
			// System.out.println(SentenciaSQL_producto_pedidos);
		}
		return resultado;

	}

	public boolean ELIMINAR_PEDIDOS_DE_LA_ENTREGA(Entrega e) {
		String SentenciaSQL = "delete from Entrega_pedido where EP_entrega_id = "
				+ e.getId();
		return conex.Insertar(SentenciaSQL);
	}

	public boolean MODIFICAR_ENTREGA(Entrega e) {

		int repartidor = e.getRepartidor().getID_Repartidor();

		String SentenciaSQL = "UPDATE Entrega SET Ent_fecha_salida = '"
				+ formato_yyyyMMdd.format(e.getFecha_salida()) + "', "
				+ "ENT_repartidor = " + repartidor + " ;";
		return conex.Insertar(SentenciaSQL);
	}

	public void agregarEntrega(Entrega e) {
		String SentenciaSQL = "INSERT INTO entrega (ENT_fecha_salida, ENT_repartidor) VALUES ('"
				+ formato_yyyyMMdd.format(e.getFecha_salida())
				+ "',"
				+ e.getRepartidor().getID_Repartidor() + ");";
		conex.Insertar(SentenciaSQL);
	}

	public Integer obtenerUltimaEntrega() {
		Integer id = null;
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String Query = "SELECT ENT_id from entrega where ENT_id= (select max(ENT_id) from entrega)";
			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			Fila.next();
			id = Fila.getInt("ENT_id");
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return id;
	}

}