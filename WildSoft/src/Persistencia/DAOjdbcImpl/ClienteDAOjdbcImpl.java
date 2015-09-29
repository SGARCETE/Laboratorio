package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Cliente;
import Persistencia.DAO.ClienteDAO;

public class ClienteDAOjdbcImpl implements ClienteDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	
	
	@Override
	public boolean Nuevo_Cliente(Cliente c) {
		String SentenciaSQL = "INSERT INTO CLIENTE(CL_Nombre, CL_Apellido, CL_Direccion,CL_telefono) VALUES ("+
			"'"+	c.getNombre()			+"',"+
			"'"+	c.getApellido()			+"',"+
			"'"+	c.getDomicilio() 		+"',"+
			"'"+	c.getTelefono_Fijo()	+"')";
		return conex.Insertar(SentenciaSQL);
	}
	
	
	/*------------------------------------------------------------------------------*/	
	/** Carga los datos pedidos para el autocompletar. */										
	public ArrayList<Object> getAutoCompleterCliente() {
		ArrayList<Object> Arreglo = new ArrayList<Object>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Cliente");
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){	
				Arreglo.add(Fila.getString("CL_Telefono"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

}//---> FIN CLASE
