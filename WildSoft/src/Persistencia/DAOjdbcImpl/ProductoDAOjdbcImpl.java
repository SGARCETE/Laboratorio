package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Producto;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.ProductoDAO;

public class ProductoDAOjdbcImpl implements ProductoDAO {
	private ConectorMySQL conex = new ConectorMySQL();
	

	public boolean AGREGAR_PRODUCTO(Producto p) {
		    String SentenciaSQL = " INSERT INTO Producto (PR_Nombre, PR_precio, PR_tipo_producto )VALUES ("+
				"'"+	p.getPR_nombre()			+"',"
				   +	p.getPR_precio()			+"',"
				   +    p.getPR_tipo_producto()			+"')";
		    
		   
			return conex.Insertar(SentenciaSQL);
		}

	public ArrayList<Producto> getVARIEDAD_DEL_PRODUCTO(String Tipo_Producto) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Producto, Tipo_producto WHERE producto.PR_tipo_producto = Tipo_producto.TP_id AND Tipo_producto.TP_nombre = '"+ Tipo_Producto + "'");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto p = new Producto();				
				p.setPR_id(Fila.getInt("PR_id"));
				p.setPR_nombre(Fila.getString("PR_nombre"));
				p.setPR_precio(Fila.getDouble("PR_precio"));
				p.setPR_tipo_producto(Fila.getInt("PR_tipo_producto"));
				Arreglo.add(p);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	// Retorna todos los productos
	public ArrayList<Producto> GET_PRODUCTOS() {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Producto, Tipo_producto WHERE producto.PR_tipo_producto = Tipo_producto.TP_id");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Producto p = new Producto();				
				p.setPR_id(Fila.getInt("PR_id"));
				p.setPR_nombre(Fila.getString("PR_nombre"));
				p.setPR_precio(Fila.getDouble("PR_precio"));
				p.setPR_tipo_producto(Fila.getInt("PR_tipo_producto"));
				Arreglo.add(p);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
	
	public boolean ELIMINAR_PRODUCTO(Producto p) {
		String SentenciaSQL = "DELETE * FROM Producto WHERE PR_id="
				+ p.getPR_id();
		return conex.Insertar(SentenciaSQL); // Insert devuelve un boolean
	}

	public ArrayList<String> getTipos_Producto() {
		ArrayList<String> Arreglo = new ArrayList<String>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Tipo_producto");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getString("TP_nombre"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

}// --> FIN

