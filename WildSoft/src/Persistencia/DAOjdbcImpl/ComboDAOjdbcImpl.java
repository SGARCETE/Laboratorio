package Persistencia.DAOjdbcImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Combo;
import Negocio.Modelo.Producto;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.ComboDAO;

public class ComboDAOjdbcImpl implements ComboDAO{
	
	private ConectorMySQL conex = new ConectorMySQL();
	public ArrayList<Producto> getLista_Productos(Combo C) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select * from Combo_productos COPRO join Combo CO join Producto PR " +
			"on COPRO.COPRO_combo_id = CO.CO_id and COPRO.COPRO_producto_id = PR.PR_id and CO.CO_id= " + 
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
	
	public ArrayList<Producto> getLista_Productos(String C) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select * from Combo_productos COPRO join Combo CO join Producto PR join Tipo_producto TP " +
			"on COPRO.COPRO_combo_id = CO.CO_id and TP.TP_id=PR.PR_tipo_producto and COPRO.COPRO_producto_id = PR.PR_id and CO.CO_nombre= '" + 
			     C + "'"   ;

//			System.out.println("getLista_Productos:\n"+Query);
			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto Prod = new Producto();
				Prod.setPR_id(Fila.getInt("PR_id"));
				Prod.setPR_nombre(Fila.getString("PR_nombre"));
				Prod.setPR_precio(Fila.getDouble("COPRO_Precio"));
				Prod.setPR_Observacion(Fila.getString("COPRO_observacion"));
				Prod.setPR_TIPO_PRODUCTO_STRING(Fila.getString("TP_nombre"));
				Prod.setCantidad(Fila.getInt("COPRO_cantidad"));
				Prod.setPR_tipo_producto(Fila.getInt("TP_id"));
				Arreglo.add(Prod);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	public ArrayList<Combo> getLista_Combos() {
		ArrayList<Combo> Arreglo = new ArrayList<Combo>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select CO_id,CO_nombre, CO_precio from Combo";

//			System.out.println("getLista_Productos:\n"+Query);
			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Combo combo = new Combo();
				combo.setId(Fila.getInt("CO_id"));
				combo.setNombre(Fila.getString("CO_nombre"));
				combo.setPrecio(Fila.getDouble("CO_precio"));
				Arreglo.add(combo);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	public Integer get_id_combo(String nombre) {
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select CO_id from Combo where CO_nombre= '" + nombre + "'");				
			rs.first();
			int ID = rs.getInt("CO_id");
			conex.cerrarConexion();
			return ID;
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,"No se puede dar la fila solicitada! \n ERROR : " + SQLE.getMessage());
		}
		return 0;
	}

	public Combo get_combo (Integer id_combo){
		Combo combo = null;
		try {
			
			combo = new Combo();
			
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			String Query = "select * from combo where CO_id ="+ id_combo ;
			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			
			while (Fila.next()) {
				combo.setId(Fila.getInt("CO_id"));
				combo.setNombre(Fila.getString("CO_nombre"));
				combo.setPrecio(Fila.getDouble("CO_precio"));
				combo.setLista_productos(getLista_Productos(Fila.getString("CO_nombre")));
			}
			conex.cerrarConexion();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return combo;
	}
	
	public boolean AGREGAR_COMBO_PRODUCTO(Combo C){
		boolean resultado = false;
		Integer COMBO_ID = C.getId();
		for (int i = 0; i < C.getLista_productos().size(); i++) {
			
			Integer PRODUCTO_ID = C.getLista_productos().get(i).getPR_id();
			String SentenciaSQL_producto_pedidos = "INSERT INTO combo_productos (COPRO_combo_id, COPRO_producto_id, COPRO_cantidad) "
					+ "VALUES ("+
					""+	 COMBO_ID									+","+	// INTEGER
					""+	 PRODUCTO_ID								+","+	// INTEGER
					""+	 C.getLista_productos().get(i).getCantidad()+");";	// INTEGER
			resultado = conex.Insertar(SentenciaSQL_producto_pedidos);
		}
		return resultado;
		
	}
	
	public boolean ELIMINAR_PRODUCTOS_DEL_COMBO(Integer id) {
		String SentenciaSQL = "delete from Combo_productos where COPRO_combo_id = " + id ;
		return conex.Insertar(SentenciaSQL);
	}
	
	public boolean ELIMINAR_Combo(Integer ID) {
		String SentenciaSQL = "UPDATE Combo SET CO_vigente=0 WHERE CO_id=" + ID + ";";
		return conex.Insertar(SentenciaSQL);
	}
	
	public boolean AGREGAR_COMBO_TABLA_COMBOS(Combo c) {
	    String SentenciaSQL = " INSERT INTO Combo (CO_Nombre, CO_precio)VALUES ("+
			   "'"+ c.getNombre()			+"',"
			   +	c.getPrecio()			    +")";
	    return conex.Insertar(SentenciaSQL);
	}
	
	public boolean AGREGAR_COMBO_TABLA_PRODUCTOS(Combo c) {
	    String SentenciaSQL = " INSERT INTO Producto (PR_Nombre, PR_precio, PR_tipo_producto )VALUES ("+
			   "'"+  	c.getNombre()			+"',"
			   +	c.getPrecio()			    +","
			   +    4			+")";
	    
	   
		return conex.Insertar(SentenciaSQL);
	}

	public void modificarCombo(Combo combo) {
            try {
            	conex.connectToMySQL();
    			PreparedStatement statement = conex.conexion.prepareStatement("UPDATE combo SET CO_nombre=?, CO_precio=? WHERE CO_id=?;");
				statement.setString(1, combo.getNombre());
				statement.setDouble(2, combo.getPrecio());
				statement.setInt(3, combo.getId());
	            statement.executeUpdate();
	            conex.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	
	
	
}
