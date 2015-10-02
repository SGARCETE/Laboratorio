package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Repartidor;
import Persistencia.DAO.RepartidorDAO;

public class RepartidorDAOjdbcImpl implements RepartidorDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	
	public boolean Nuevo_Repartidor(Repartidor R) {
		String SentenciaSQL = "INSERT INTO Repartidor(RE_nombre, RE_vehiculo) VALUES ("+
			"'"+	R.getNombre()			+"',"+
			"'"+	R.getVehiculo()		    +"')";
		return conex.Insertar(SentenciaSQL);
	}
	
	public boolean Eliminar_Repartidor(Repartidor R) {
		String SentenciaSQL = "DELETE from Repartidor R where R.RE_id =" + R.getID_Repartidor();
		return conex.Insertar(SentenciaSQL);
	}
	
	public ArrayList<Repartidor> getRepartidores() {
		ArrayList<Repartidor> Arreglo = new ArrayList<Repartidor>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Repartidor");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Repartidor R = new Repartidor();
				R.setID_Repartidor(Fila.getInt("RE_id"));
				R.setNombre(Fila.getString("RE_nombre"));
				R.setVehiculo(Fila.getString("RE_vehiculo"));
				Arreglo.add(R);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}	
	
}
