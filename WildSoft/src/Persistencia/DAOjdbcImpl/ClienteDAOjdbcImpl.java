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

		// aca hay que hacer la query en sql para que se inserte un nuevo cliente.
		// Si existe una restriccion para agregar un cliente, 
		// se verifica ademas en la parte de Servicio_Cliente

		String SentenciaSQL = "INSERT INTO CLIENTE(CL_Direccion,CL_telefono) VALUES ("+
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
			st.executeQuery("SELECT * FROM Clientes");
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){	
				Arreglo.add(Fila.getString("CL_Nombre"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

}//---> FIN CLASE
