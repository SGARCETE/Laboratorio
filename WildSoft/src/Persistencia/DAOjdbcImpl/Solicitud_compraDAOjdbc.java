package Persistencia.DAOjdbcImpl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import Negocio.Modelo.Materia_Prima;
import Negocio.Modelo.Proveedor;
import Negocio.Modelo.Solicitud_compra;
import Persistencia.Conector.ConectorMySQL;
import Persistencia.DAO.Solicitud_compraDAO;

public class Solicitud_compraDAOjdbc implements Solicitud_compraDAO {

	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Override
	public Solicitud_compra getSOLICITUD_COMPRA(Integer iD_SC) {
		Solicitud_compra SOLICITUD_OBTENIDA = null;
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();

			String Query = "SELECT * FROM Solicitud_compra SD, Solicitud_estado SE, Proveedor PV WHERE SD.SD_proveedor = PV.PV_id AND SD.SD_estado = SE.SEST_id AND SD.SD_id ="
					+ iD_SC;

			st.executeQuery(Query);

			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Proveedor PV = new Proveedor();
				PV.setNombre(Fila.getString("PV_nombre"));
				PV.setId(Fila.getInt("PV_id"));
				PV.setDireccion(Fila.getString("PV_direccion"));
				PV.setTelefono(Fila.getString("PV_telefono"));
				PV.setMail(Fila.getString("PV_mail"));
				PV.setVigente(Fila.getBoolean("PV_vigente"));

				SOLICITUD_OBTENIDA = new Solicitud_compra();
				SOLICITUD_OBTENIDA.setId(Fila.getInt("SD_id"));
				SOLICITUD_OBTENIDA.setEstado(Fila.getString("SEST_nombre"));
				SOLICITUD_OBTENIDA.setFecha(Fila.getDate("SD_fecha"));
				SOLICITUD_OBTENIDA.setPrecio(Fila.getDouble("SD_precio"));
				SOLICITUD_OBTENIDA.setProveedor(PV);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return SOLICITUD_OBTENIDA;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public int AGREGAR_SOLICITUD(Solicitud_compra solicitud) {
		String PROVEEDOR = "NULL";
		if (solicitud.getProveedor() != null && solicitud.getProveedor().getId() != 0) {
			PROVEEDOR = solicitud.getProveedor().getId().toString();
		}

		String SentenciaSQL_Solicitud = "INSERT INTO Solicitud_compra ( SD_fecha, SD_estado, SD_proveedor) VALUES ('"
				+ formato_yyyyMMdd.format(new Date()) + "'," + 1 + "," + PROVEEDOR + ");";

		int Exito_al_Ingresar_Solicitud = conex.insert(SentenciaSQL_Solicitud);

		return Exito_al_Ingresar_Solicitud;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void ELIMINAD_SOLICITUD(Solicitud_compra sc) {
		String SentenciaSQL = "delete from solicitud_compra where solicitud_compra.SD_id = " + sc.getId();
		conex.Insertar(SentenciaSQL);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void MODIFICAR_Solicitud(Solicitud_compra sc) {
		try {
			conex.connectToMySQL();
			String SentenciaSQL = "UPDATE Solicitud_compra SET SD_fecha = ?, SD_estado = ?, SD_proveedor = ?, SD_precio = ? WHERE Solicitud_compra.SD_id = ?;";
			PreparedStatement statement = conex.conexion.prepareStatement(SentenciaSQL);
			statement.setString(1, formato_yyyyMMdd.format(sc.getFecha()));
			if (sc.getPrecio() > 0.0) {
				statement.setInt(2, 3);
			} else {
				statement.setInt(2, obtener_ID_Estado_Solicitud(sc.getEstado()));
			}
			statement.setInt(3, sc.getProveedor().getId());
			statement.setDouble(4, sc.getPrecio());
			statement.setInt(5, sc.getId());
			statement.executeUpdate();
			conex.cerrarConexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean MODIFICAR_ESTADO(Integer ID_SOLICITUD, Integer ID_ESTADO_SOLICITUD) {
		String SentenciaSQL = "UPDATE Solicitud_compra SET SD_estado = " + ID_ESTADO_SOLICITUD + " WHERE SD_id= "
				+ ID_SOLICITUD;
		return conex.Insertar(SentenciaSQL);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public int obtener_ID_Estado_Solicitud(String estado) {
		int resultado = 1;
		conex.connectToMySQL();
		Statement st;
		try {
			st = conex.conexion.createStatement();
			String SentenciaSQL = "select SEST_id from Solicitud_estado where SEST_nombre='" + estado + "'";
			ResultSet Fila = st.executeQuery(SentenciaSQL);

			while (Fila.next()) {
				resultado = Fila.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Solicitud_compra> getLISTA_SOLICITUDES() {

		ArrayList<Solicitud_compra> Arreglo = new ArrayList<Solicitud_compra>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();

			String Query = "select * from Solicitud_compra SD join Solicitud_estado SEST join Proveedor PV on "
					+ "SD.SD_Proveedor= PV.PV_id and SD.SD_estado= SEST.SEST_id ORDER BY SD_id DESC;";
			st.executeQuery(Query);

			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Solicitud_compra sd = new Solicitud_compra();
				sd.setId(Fila.getInt("SD_id"));
				sd.setEstado(Fila.getString("SEST_nombre"));
				sd.setFecha(Fila.getDate("SD_fecha"));
				sd.setProveedor(new Proveedor(Fila.getString("PV_nombre")));
				sd.setPrecio(Fila.getDouble("SD_precio"));

				Arreglo.add(sd);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd) {

		ArrayList<Materia_Prima> Lista_MateriasPrimas = new ArrayList<Materia_Prima>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();

			String Query = "SELECT * FROM Solicitud_compra SD JOIN Solicitud_estado SE JOIN Proveedor PV JOIN Materia_prima MP JOIN Compra_MateriaPrima CM JOIN Categoria_MP CA  ON  SD.SD_estado= SE.SEST_id "
					+ "AND SD.SD_proveedor= PV.PV_id AND SD.SD_id= CM.CM_compra  AND MP.MP_id= CM.CM_materia_prima AND MP.MP_categoria= CA.CA_id and SD.SD_id = "
					+ sd.getId();
			st.executeQuery(Query);

			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Materia_Prima mp = new Materia_Prima();
				mp.setCategoria_string(Fila.getString("CA_NOMBRE"));
				mp.setNombre(Fila.getString("MP_nombre"));
				mp.setCantidad(Fila.getInt("CM_cantidad_mp"));

				Lista_MateriasPrimas.add(mp);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Lista_MateriasPrimas;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public Proveedor getProveedor(Integer ID_Proveedor) {
		Proveedor Proveedor = new Proveedor();
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement();

			st.executeQuery("SELECT * FROM Proveedor WHERE PV_id = " + ID_Proveedor);
			ResultSet Fila = st.getResultSet();
			Fila.first();
			Proveedor.setId(Fila.getInt("PV_ID"));
			Proveedor.setDireccion(Fila.getString("PV_direccion"));
			Proveedor.setNombre(Fila.getString("PV_nombre"));
			Proveedor.setTelefono(Fila.getString("PV_telefono"));
			Proveedor.setMail(Fila.getString("PV_mail"));
			Proveedor.setVigente(Fila.getBoolean("PV_vigente"));

			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Proveedor;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public boolean ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(Solicitud_compra sd) {
		String SentenciaSQL = "delete from Compra_MateriaPrima where Compra_MateriaPrima.CM_compra = " + sd.getId();
		return conex.Insertar(SentenciaSQL);
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void AGREGAR_MATERIA_PRIMA_SOLICITUD(Solicitud_compra sc) {
		for (int i = 0; i < sc.getLista_materia_prima().size(); i++) {
			try {
				conex.connectToMySQL();
				String SentenciaSQL = "INSERT INTO Compra_MateriaPrima (CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES ( ?, ?, ?);";
				PreparedStatement statement = conex.conexion.prepareStatement(SentenciaSQL);
				statement.setInt(1, sc.getId());
				statement.setInt(2, sc.getLista_materia_prima().get(i).getId());
				statement.setInt(3, sc.getLista_materia_prima().get(i).getCantidad());
				statement.executeUpdate();
				conex.cerrarConexion();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@Override
	public boolean Registrar_envio_solicitud(Integer ID_SOLICITUD) {
		return MODIFICAR_ESTADO(ID_SOLICITUD, 2);
	}

}// --> FIN
