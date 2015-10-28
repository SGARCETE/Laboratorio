package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Materia_Prima;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.MateriaPrimaDAO;

public class MateriaPrimaDAOjdbcImpl implements MateriaPrimaDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	
	
	
	
	public boolean AGREGAR_Materia_Prima(Materia_Prima m) {
	    String SentenciaSQL = "INSERT INTO Materia_Prima (MP_nombre, MP_fecha_vencimiento, MP_categoria) VALUES"+
			"'"+	m.getNombre()			+"',"
			   +	m.getFecha_vencimiento()			+"',"
			   +    m.getCategoria()		+"')";
	    
	   
		return conex.Insertar(SentenciaSQL);
	}

	
	public ArrayList<Materia_Prima> getVARIEDAD_Materia_Prima(String Categoria_mp) {
		ArrayList<Materia_Prima> Arreglo = new ArrayList<Materia_Prima>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("select * from Categoria_MP CM JOIN Materia_Prima MP where MP.MP_categoria= CA_id and CM.CA_nombre = '" + Categoria_mp + "'");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Materia_Prima m = new Materia_Prima();				
				m.setId(Fila.getInt("MP_id"));
				m.setNombre(Fila.getString("MP_nombre"));
				m.setFecha_vencimiento(Fila.getDate("MP_fecha_vencimiento"));
				m.setCategoria(Fila.getInt("MP_categoria"));
				
				
				Arreglo.add(m);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
	public boolean ELIMINAR__Materia_Prima(Materia_Prima m) {
		String SentenciaSQL = "DELETE * FROM Materia_Prima WHERE MP_id="
				+ m.getId();
		return conex.Insertar(SentenciaSQL); // Insert devuelve un boolean
	}
	
	public ArrayList<String> getCategoria_MP() {
		ArrayList<String> Arreglo = new ArrayList<String>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Categoria_MP");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getString("CA_nombre"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}


	
	
	
	
}
