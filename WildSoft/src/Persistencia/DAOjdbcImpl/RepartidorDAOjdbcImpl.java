package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Repartidor;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.RepartidorDAO;

public class RepartidorDAOjdbcImpl implements RepartidorDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean Nuevo_Repartidor(Repartidor R) {
		String SentenciaSQL = "INSERT INTO Repartidor(RE_nombre, RE_vehiculo) VALUES ("+
			"'"+	R.getNombre()			+"',"+
			"'"+	R.getVehiculo()		    +"')";
		System.out.println(SentenciaSQL);
		return conex.Insertar(SentenciaSQL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean Eliminar_Repartidor(Repartidor R) {
		String SentenciaSQL = "DELETE FROM Repartidor WHERE RE_id =" + R.getID_Repartidor();
		return conex.Insertar(SentenciaSQL);
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
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

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean Modificar_Repartidor(Repartidor R) {
		String SentenciaSQL = "UPDATE Repartidor SET RE_nombre = '" + R.getNombre() + "', RE_vehiculo = '" + R.getVehiculo() + "' WHERE RE_id=" + R.getID_Repartidor();
		return conex.Insertar(SentenciaSQL);
	}

	@Override
	public Repartidor getRepartidor(String nombreRepartidor) {
		Repartidor R = new Repartidor();
		String SentenciaSQL = "SELECT * FROM repartidor WHERE RE_nombre='" + nombreRepartidor + "';";
		conex.connectToMySQL();// Conectar base
		Statement st;
		try {
			st = conex.conexion.createStatement();
			st.executeQuery(SentenciaSQL);
			ResultSet Fila = st.getResultSet();
			
			while (Fila.next()) {
				R.setID_Repartidor(Fila.getInt("RE_id"));
				R.setNombre(Fila.getString("RE_nombre"));
				R.setVehiculo(Fila.getString("RE_vehiculo"));
			}
			
			conex.cerrarConexion();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return R;
	}
	
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
}//--> FIN
