package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Cliente;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.ClienteDAO;

public class ClienteDAOjdbcImpl implements ClienteDAO{
	private ConectorMySQL conex = new ConectorMySQL();
	
	
	public Cliente getCliente(String nombre_Cliente) {
		Cliente cliente = null;
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Cliente WHERE CL_nombre = '"+nombre_Cliente+"'");
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){
				cliente = new Cliente();
				cliente.setID_Cliente(Fila.getInt("CL_id"));
				cliente.setNombre(Fila.getString("CL_nombre"));
				cliente.setDomicilio(Fila.getString("CL_direccion"));
				cliente.setTelefono_Fijo(Fila.getString("CL_telefono"));
				cliente.setDetalle(Fila.getString("CL_Detalle"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return cliente;
	}
	
	public Cliente getCliente(Integer ID_Cliente) {
		Cliente cliente = new Cliente();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Cliente WHERE CL_id = "+ID_Cliente);
			ResultSet Fila = st.getResultSet();
			Fila.first();
			cliente.setID_Cliente(Fila.getInt("CL_id"));
			cliente.setNombre(Fila.getString("CL_nombre"));
			cliente.setDomicilio(Fila.getString("CL_direccion"));
			cliente.setTelefono_Fijo(Fila.getString("CL_telefono"));
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return cliente;
	}
	
	
	/*------------------------------------------------------------------------------*/	
	public boolean Eliminar_Cliente(Cliente c) {
		String SentenciaSQL = "UPDATE Cliente SET CL_vigente=0 WHERE CL_id=" + c.getID_Cliente();
		return conex.Insertar(SentenciaSQL);
	}
	
	/*------------------------------------------------------------------------------*/	
	/** Carga los datos pedidos para el autocompletar. */	
	public ArrayList<Object> getAutoCompleter_Clientes() {
		ArrayList<Object> Arreglo = new ArrayList<Object>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Cliente where CL_vigente is null");
			ResultSet Fila = st.getResultSet();
			while(Fila.next()){	
				Arreglo.add(Fila.getString("CL_nombre"));
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	@Override
	public boolean guardar_cliente(Cliente c) {
		String SentenciaSQL = "INSERT INTO CLIENTE(CL_Nombre, CL_Direccion,CL_telefono, CL_Detalle) VALUES ("+
				"'"+	c.getNombre()			+"',"+
				"'"+	c.getDomicilio() 		+"',"+
				"'"+	c.getTelefono_Fijo()	+"',"+
				"'"+ 	c.getDetalle()			+"')";
			return conex.Insertar(SentenciaSQL);
	}


	@Override
	public ArrayList<Cliente> getListaCliente() {
		ArrayList<Cliente> Arreglo = new ArrayList<Cliente>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Cliente where CL_id>1 and CL_vigente is null");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Cliente C = new Cliente();
				C.setID_Cliente(Fila.getInt("CL_id"));
				C.setNombre(Fila.getString("CL_nombre"));
				C.setDomicilio(Fila.getString("CL_direccion"));
				C.setTelefono_Fijo(Fila.getString("CL_telefono"));
				C.setDetalle(Fila.getString("CL_detalle"));
				Arreglo.add(C);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	@Override
	public boolean modificar_cliente(Cliente c) {
		String SentenciaSQL = "UPDATE Cliente SET CL_nombre = '" + c.getNombre() + "', CL_direccion = '" + c.getDomicilio() +"' ,CL_telefono= '" +c.getTelefono_Fijo()+"', CL_detalle=' "+ c.getDetalle()+ "' WHERE CL_id=" + c.getID_Cliente();
		return conex.Insertar(SentenciaSQL);
	}

}//---> FIN CLASE
