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
			st.executeQuery("SELECT * FROM producto, Tipo_producto WHERE producto.PR_tipo_producto = Tipo_producto.TP_id AND Tipo_producto.TP_nombre = '"+Tipo_Producto+"'");
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){
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
	
	
}//--> FIN

