package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Producto;
import Persistencia.DAO.ProductoDAO;

public class ProductoDAOjdbcImpl implements ProductoDAO {
	private ConectorMySQL conex = new ConectorMySQL();
	

	public boolean AGREGAR_PRODUCTO(Producto p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Producto> getVARIEDAD_DEL_PRODUCTO(String Tipo_Producto) {
		ArrayList<Producto> Arreglo = new ArrayList<Producto>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM producto");		// TODO- Comprobar nombre con el de la base de datos
			st.executeQuery("SELECT * FROM Producto, Tipo_producto WHERE producto.PR_tipo_producto = Tipo_producto.TP_id AND Tipo_producto.TP_nombre = '"
					+ Tipo_Producto + "'");
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){
			while (Fila.next()) {
				Producto p = new Producto();
//				p.setNombre(Fila.getString("PR_Variedad"))
//				p.setCategoria(Fila.getString("PR_Variedad"))
//				p.setNombre(Fila.getString("PR_Variedad"))
//				p.setNombre(Fila.getString("PR_Variedad"))
				Arreglo.add(p); // TODO- Comprobar nombre con el de la base de datos
				
				p.setPR_id(Fila.getInt("PR_id"));
				p.setPR_nombre(Fila.getString("PR_nombre"));
				p.setPR_precio(Fila.getDouble("PR_precio"));
				p.setPR_tipo_producto(Fila.getInt("PR_tipo_producto"));
				Arreglo.add(p);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
}//--> FIN

	@Override
	public boolean ELIMINAR_PRODUCTO(Producto p) {
		String SentenciaSQL = "DELETE * FROM Producto WHERE PR_id="
				+ p.getPR_id();
		return conex.Insertar(SentenciaSQL); // Insert devuelve un boolean
	}

}// --> FIN

