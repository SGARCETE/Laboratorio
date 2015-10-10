package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.EstadoDAO;

public class EstadoDAOjdbcImpl implements EstadoDAO{
	
	private ConectorMySQL conex = new ConectorMySQL();

	public String getEstado(int id) {
		
		String nombre = null;
		
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("select * from ent_estado where PEST_id = " +id);
			rs.first();
			nombre = rs.getString("ENTE_nombre");
			conex.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return nombre;
	}

	public int getEstado(String nombre) {
			
		int id = 0;
				
				try {
					conex.connectToMySQL();
					Statement st = conex.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					ResultSet rs = st.executeQuery("select * from ent_estado where ENTE_nombre = '" + nombre + "'");
					rs.first();
					id = rs.getInt("PEST_id");
					conex.cerrarConexion();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return id;
	}

	public List<String> getListaEstados() {
		
		ArrayList<String> Arreglo = new ArrayList<String>();
		
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement();
			
			String Query = "SELECT * FROM ent_estado";
			st.executeQuery(Query);
			ResultSet Fila = st.getResultSet();
			
			while (Fila.next()) {
				
				String elemento = Fila.getString("ENTE_nombre");
				Arreglo.add(elemento);

			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		
		return Arreglo;
	}

}
