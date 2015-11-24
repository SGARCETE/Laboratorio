package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import Negocio.Modelo.Proveedor;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.ProveedorDAO;

public class ProveedorDAOjdbcImpl implements ProveedorDAO {

	private ConectorMySQL conex = new ConectorMySQL();

	public void AGREGAR_PROVEEDOR(Proveedor p) {
		String SentenciaSQL = "INSERT INTO Proveedor (PV_nombre, PV_direccion, PV_telefono, PV_mail) "
				+ "VALUES ('" + p.getNombre() + "','" + p.getDireccion() + "','" + p.getTelefono() + "','" + p.getMail() + "');";
		int id = conex.insert(SentenciaSQL);
		for (int i = 0; i < p.getCategorias().size(); i++) {
			insertarCategorias(id, p.getCategorias().get(i));
		}
	}

	private void insertarCategorias(int idProveedor, int idCategoria) {
		String SentenciaSQL = "INSERT INTO proveedor_categoria VALUES (" + idProveedor + "," + idCategoria + ");";
		conex.insert(SentenciaSQL);
	}

	public ArrayList<String> getCategoriasProveedor(String nombreProveedor) {
		ArrayList<String> Arreglo = new ArrayList<String>();
		try {

			conex.connectToMySQL();

			Statement st = conex.conexion.createStatement();
			st.executeQuery("select CA_nombre from Categoria_MP CA, Proveedor PV , proveedor_categoria PC where PC.PC_categoria_id=CA.CA_id"
					+ " and PV.PV_id=PC.PC_proveedor_id and PV.PV_nombre= '"
					+ nombreProveedor + "';");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Arreglo.add(Fila.getString("CA_Nombre"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	/**
	 * Devuelve todos los proveedores pertenecientes a la categoria seleccionada
	 * @param categoria_MP Es el nombre de la categoria
	 * @return Devuelve un arreglo con todos los proveedores obtenidos, en aso de que no haya proveedores en esa categoria devolvera un arreglo vacio
	 */
	public ArrayList<Proveedor> get_Proveedor_Categoria(String categoria_MP) {
		ArrayList<Proveedor> Arreglo = new ArrayList<Proveedor>();
		try {

			conex.connectToMySQL();// Conectar base

			Statement st = conex.conexion.createStatement();
			st.executeQuery("select * from Proveedor PV JOIN Proveedor_categoria PC JOIN Categoria_MP CA  where PV.PV_id= PC.PC_proveedor_id and  "
					+ "CA.CA_id = PC.PC_categoria_id and CA.CA_nombre = '"
					+ categoria_MP + "'");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {

				Proveedor p = new Proveedor();
				p.setId(Fila.getInt("PV_id"));
				p.setNombre(Fila.getString("PV_nombre"));
				p.setDireccion(Fila.getString("PV_direccion"));
				p.setTelefono(Fila.getString("PV_telefono"));
				Arreglo.add(p);
			}

			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	public ArrayList<Proveedor> get_Proveedores() {
		ArrayList<Proveedor> Arreglo = new ArrayList<Proveedor>();
		try {

			conex.connectToMySQL();// Conectar base

			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Proveedor WHERE PV_vigente is null");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {

				Proveedor p = new Proveedor();
				p.setId(Fila.getInt("PV_id"));
				p.setNombre(Fila.getString("PV_nombre"));
				p.setDireccion(Fila.getString("PV_direccion"));
				p.setTelefono(Fila.getString("PV_telefono"));
				p.setMail(Fila.getString("PV_mail"));

				Arreglo.add(p);
			}

			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	public boolean ELIMINAR_Proveedor(Integer ID) {
		String SentenciaSQL = "UPDATE proveedor SET PV_vigente=0 WHERE PV_id=" + ID + ";";
		return conex.Insertar(SentenciaSQL);
	}

	@Override
	public Proveedor obtenerProveedor(String nombre) {
		
		Proveedor p = new Proveedor();
		
		try {

			conex.connectToMySQL();

			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT * FROM Proveedor WHERE PV_nombre='" + nombre + "';");
			ResultSet Fila = st.getResultSet();
			
			while (Fila.next()) {
				p.setId(Fila.getInt("PV_id"));
				p.setNombre(nombre);
				p.setDireccion(Fila.getString("PV_direccion"));
				p.setTelefono(Fila.getString("PV_telefono"));
				p.setMail(Fila.getString("PV_mail"));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return p;
	}

	public HashMap<Integer, String> obtenerCategorias(){
		
		HashMap<Integer, String> mapa = new HashMap<Integer, String>();
		try {

			conex.connectToMySQL();

			Statement st = conex.conexion.createStatement();
			st.executeQuery("select * from categoria_mp");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				mapa.put(Fila.getInt("CA_id"), Fila.getString("CA_nombre"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return mapa;
	}

	@Override
	public ArrayList<Integer> getCategoriasProveedor(int iD) {
		ArrayList<Integer> arreglo = new ArrayList<Integer>();
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement();
			st.executeQuery("SELECT PC_categoria_id FROM proveedor_categoria WHERE PC_proveedor_id=" + iD + ";");
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				arreglo.add(Fila.getInt("PC_categoria_id"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return arreglo;
	}

	@Override
	public void modificarProveedor(Proveedor p) {
		actualizarProveedor(p);
		eliminarCategoriasProveedor(p);
		for (int i = 0; i < p.getCategorias().size(); i++) {
			insertarCategorias(p.getId(), p.getCategorias().get(i));
		}
	}
	
	private void eliminarCategoriasProveedor(Proveedor p) {
		String SentenciaSQL = "DELETE FROM proveedor_categoria WHERE PC_proveedor_id=" + p.getId() + ";";
		conex.Insertar(SentenciaSQL);
	}

	private void actualizarProveedor(Proveedor p){
		String SentenciaSQL = "UPDATE proveedor SET PV_nombre='" + p.getNombre() + "', PV_direccion='" + p.getDireccion()
				+ "', PV_mail='" + p.getMail() + "', PV_telefono='" + p.getTelefono() + "' WHERE PV_id=" + p.getId() + ";";
		conex.Insertar(SentenciaSQL);
	}
}
