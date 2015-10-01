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
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){
				Producto p = new Producto();
//				p.setNombre(Fila.getString("PR_Variedad"))
//				p.setCategoria(Fila.getString("PR_Variedad"))
//				p.setNombre(Fila.getString("PR_Variedad"))
//				p.setNombre(Fila.getString("PR_Variedad"))
				Arreglo.add(p); // TODO- Comprobar nombre con el de la base de datos
				
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
}//--> FIN

