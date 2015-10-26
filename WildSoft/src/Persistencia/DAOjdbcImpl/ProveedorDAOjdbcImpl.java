package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.ProveedorDAO;

public class ProveedorDAOjdbcImpl implements ProveedorDAO{
	
	private ConectorMySQL conex = new ConectorMySQL();
	
	
	public boolean AGREGAR_PROVEEDOR(Proveedor p)
	{
		String SentenciaSQL= " INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_telefono ) VALUES ("+
		"'"+ p.getNombre() + "',"
		   + p.getDireccion() + "',"
		   + p.getTelefono()+ "',";
		  
		
		return conex.Insertar(SentenciaSQL);
	}
	
	
	public ArrayList<String> getCategoriasProveedor(String nombreProveedor) {
		ArrayList<String> Arreglo = new ArrayList<String>();
		try {
			
			conex.connectToMySQL();// Conectar base
			
			Statement st = conex.conexion.createStatement();
			st.executeQuery("select CA_nombre from Categoria_MP CA JOIN Proveedor PV on PV.PV_categoria= " +
					        "CA.CA_id and PV.PV_nombre= '" + nombreProveedor + "'");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getString("CA_Nombre"));
				}
		
	
		
	}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
		}
	
	public ArrayList<Proveedor> get_Proveedor_Categoria(String categoria_MP) {
		ArrayList<Proveedor> Arreglo = new ArrayList<Proveedor>();
		try {
			
			conex.connectToMySQL();// Conectar base
			
			Statement st = conex.conexion.createStatement();
			st.executeQuery(" select * from Proveedor PV JOIN Proveedor_categoria PC JOIN Categoria_MP CA  where PV.PV_id= PC.PC_proveedor_id and  " +
					"CA.CA_id = PC.PC_categoria_id and CA.CA_nombre = '"+ categoria_MP + "'");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				
				Proveedor p = new Proveedor();
				p.setId(Fila.getInt("PV_id"));
				p.setNombre(Fila.getString("PV_nombre"));
				p.setDireccion(Fila.getString("PV_direccion"));
				p.setTelefono(Fila.getString("PV_telefono"));
				p.setCategoria(Fila.getInt("PV_categoria"));
				Arreglo.add(p);
			}
			
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	
	
	
	public ArrayList<Proveedor> get_Proveedores() {
		ArrayList<Proveedor> Arreglo = new ArrayList<Proveedor>();
		try {
			
			conex.connectToMySQL();// Conectar base
			
			Statement st = conex.conexion.createStatement();
			st.executeQuery(" select * from Proveedor P JOIN Categoria_MP C where P.PV_categoria=C.CA_id");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				
				Proveedor p = new Proveedor();
				p.setId(Fila.getInt("PV_id"));
				p.setNombre(Fila.getString("PV_nombre"));
				p.setDireccion(Fila.getString("PV_direccion"));
				p.setTelefono(Fila.getString("PV_telefono"));
				p.setCategoria(Fila.getInt("PV_categoria"));
			}
			
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	
	public boolean ELIMINAR_Proveedor(Proveedor p) {
		String SentenciaSQL = "DELETE * FROM Proveedor WHERE MP_id="
				+ p.getId();
		return conex.Insertar(SentenciaSQL); // Insert devuelve un boolean
	}
	
	
	
	
}
