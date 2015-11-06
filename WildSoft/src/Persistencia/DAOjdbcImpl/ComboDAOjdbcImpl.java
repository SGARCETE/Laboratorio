package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Combo;
import Negocio.Modelo.Pedido;
import Negocio.Modelo.Producto;
import Persistencia.Conector.ConectorMySQL;

public class ComboDAOjdbcImpl {
	
	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

	private ArrayList<Producto> getLista_Productos(Combo C) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select * from Combo_productos COPRO join Combo CO join Producto PR " +
			"on COPRO.COPRO_combo_id = CO.CO_id and COPRO.COPRO_producto_id = PR.PR_id and CO.CO_id=1; " + 
			     C.getId()   ;

//			System.out.println("getLista_Productos:\n"+Query);
			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto Prod = new Producto();
				Prod.setPR_id(Fila.getInt("PR_id"));
				Prod.setPR_nombre(Fila.getString("PR_nombre"));
				Prod.setPR_precio(Fila.getDouble("PP_precio"));
				Prod.setPR_Observacion(Fila.getString("PP_Observacion"));
				Prod.setPR_TIPO_PRODUCTO_STRING(Fila.getString("TP_nombre"));
				Prod.setCantidad(Fila.getInt("PP_producto_cantidad"));
				Prod.setPR_tipo_producto(Fila.getInt("TP_id"));
				Arreglo.add(Prod);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
	
	
	
	
}
