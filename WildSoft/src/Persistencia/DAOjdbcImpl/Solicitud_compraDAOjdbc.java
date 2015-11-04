package Persistencia.DAOjdbcImpl;

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

public class Solicitud_compraDAOjdbc implements Solicitud_compraDAO{

	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud){
		String PROVEEDOR = "NULL";
		if(solicitud.getProveedor()!=null && solicitud.getProveedor().getId()!=0)
			PROVEEDOR = solicitud.getProveedor().getId().toString();
		
		String SentenciaSQL_Solicitud = "INSERT INTO Solicitud_compra ( SD_fecha, SD_estado, SD_proveedor) VALUES ("+
				"'"+	formato_yyyyMMdd.format(new Date())                 +"',"+
				""+		1											        +","+
				""+     PROVEEDOR                                           +");";

		boolean Exito_al_Ingresar_Solicitud = conex.Insertar(SentenciaSQL_Solicitud);
		
		return Exito_al_Ingresar_Solicitud;
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public ArrayList<Solicitud_compra> getLISTA_SOLICITUDES() {
		
		ArrayList<Solicitud_compra> Arreglo = new ArrayList<Solicitud_compra>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "select * from Solicitud_compra SD join Solicitud_estado SEST join Proveedor PV on " +
		    "SD.SD_Proveedor= PV.PV_id and SD.SD_estado= SEST.SEST_id ORDER BY SD_id DESC;" ;
			System.out.println("getLISTA_MATERIA_SOLICITUDES "+Query);
			st.executeQuery(Query);
			
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Solicitud_compra sd = new Solicitud_compra();
				sd.setId(Integer.parseInt(Fila.getString("SD_id")));
				sd.setEstado(Fila.getString("SEST_nombre"));
				sd.setFecha(Fila.getDate("SD_fecha"));
				sd.setProveedor(new Proveedor (Fila.getString("PV_nombre")));
				sd.setPrecio(Fila.getInt("SD_precio"));
				
				Arreglo.add(sd);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public ArrayList<Materia_Prima> getLISTA_Materia_Prima(Solicitud_compra sd) {
		
		ArrayList<Materia_Prima> Arreglo = new ArrayList<Materia_Prima>();
		try {
			conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String Query = "SELECT * FROM " +
			"Solicitud_compra SD JOIN Solicitud_estado SE JOIN Proveedor PV JOIN Materia_prima MP " +
			"JOIN Compra_MateriaPrima CM JOIN Categoria_MP CA  ON  SD.SD_estado= SE.SEST_id " +
			"AND SD.SD_proveedor= PV.PV_id AND SD.SD_id= CM.CM_compra  AND MP.MP_id= CM.CM_materia_prima " +
			"AND MP.MP_categoria= CA.CA_id and SD.SD_id = " + sd.getId() ;
			st.executeQuery(Query);
			
			ResultSet Fila = st.getResultSet();
			while (Fila.next()) {
				Materia_Prima mp = new Materia_Prima();
				mp.setCategoria_string(Fila.getString("CA_NOMBRE"));
				mp.setNombre(Fila.getString("MP_nombre"));
				mp.setCantidad(Fila.getInt("CM_cantidad_mp"));
				mp.setFecha_vencimiento(Fila.getDate("MP_fecha_vencimiento"));
				
				Arreglo.add(mp);
			}
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Arreglo;
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
	public boolean MODIFICAR_ESTADO(Solicitud_compra sd, Integer numero) {
		String SentenciaSQL = "UPDATE Solicitud_compra SET SD_estado = "+ numero+ " where SD_id= " + sd.getId() ;
		return conex.Insertar(SentenciaSQL);
	}
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
    public boolean MODIFICAR_Solicitud(Solicitud_compra sd) {
		
		int estado = obtenerEstado(sd.getEstado());
		int Proveedor = sd.getProveedor().getId();
		
		String SentenciaSQL = "UPDATE Solicitud_compra SET SD_fecha = '" + formato_yyyyMMdd.format(sd.getFecha()) + "', "
				+ "SD_estado = " + estado + ", " + "SD_proveedor =" + Proveedor + ", " + "SD_precio =" + sd.getPrecio() + " WHERE Solicitud_compra.SD_id=" + sd.getId() + ";";
		return conex.Insertar(SentenciaSQL);
	}
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    public Proveedor getProveedor(Integer ID_Proveedor) {
		Proveedor Proveedor= new Proveedor();
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement();

			st.executeQuery("SELECT * FROM Proveedor WHERE PV_id = "+ID_Proveedor);
			ResultSet Fila = st.getResultSet();
			Fila.first();
			//Proveedor.setCategoria(Fila.getInt("PV_categoria")); TODO
			Proveedor.setDireccion(Fila.getString("PV_direccion"));
			Proveedor.setNombre(Fila.getString("PV_nombre"));
			Proveedor.setTelefono(Fila.getString("PV_telefono"));
			
			conex.cerrarConexion();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Error al cargar la tabla \n ERROR : " + e.getMessage());
		}
		return Proveedor;
	}
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public int obtenerEstado(String estado){
		int resultado = 1;
		conex.connectToMySQL();
		Statement st;
		try {
			st = conex.conexion.createStatement();
			String SentenciaSQL = "select SEST_id from Solicitud_estado where SEST_nombre='"+ estado    +"'";
			ResultSet Fila = st.executeQuery(SentenciaSQL);
			
			while (Fila.next()){	
				resultado = Fila.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    public void eliminarSolicitud(Solicitud_compra sc){
    	String SentenciaSQL = "delete from solicitud_compra where solicitud_compra.SD_id = " +sc.getId() ;
		conex.Insertar(SentenciaSQL);
    }
    
    public boolean ELIMINAR_MATERIAS_PRIMAS_DE_SOLICITUD(Solicitud_compra sd) {
		String SentenciaSQL = "delete from Compra_MateriaPrima where Compra_MateriaPrima.CM_compra = " +sd.getId() ;
		return conex.Insertar(SentenciaSQL);
	}
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    public boolean AGREGAR_MATERIA_PRIMA_SOLICITUD(Solicitud_compra sc){
    	
		boolean resultado = false;
		Integer SOLICITUD_ID = obtenerUltimaSolicitud();
		for (int i = 0; i < sc.getLista_materia_prima().size(); i++) {
			
			Integer MATERIA_ID = sc.getLista_materia_prima().get(i).getId();
		    Integer CANTIDAD = sc.getLista_materia_prima().get(i).getCantidad();
			
			String SentenciaSQL_producto_pedidos = "INSERT INTO Compra_MateriaPrima " +
					"(CM_compra, CM_materia_prima, CM_cantidad_mp) VALUES ("+
					
					""+	 SOLICITUD_ID									+","+	// INTEGER
					""+	 MATERIA_ID								+","+	// INTEGER
					""+	 CANTIDAD 	// INTEGER
					+ ")";	// DOUBLE
			resultado = conex.Insertar(SentenciaSQL_producto_pedidos);
		}
		return resultado;
	}
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    public Integer obtenerUltimaSolicitud()
    {
    	try
    	{
    		conex.connectToMySQL();// Conectar base
			Statement st = conex.conexion.createStatement();
			
			String SentenciaSQL= "SELECT SD_id from Solicitud_compra where SD_id= (select max(SD_id) from Solicitud_compra)";
			ResultSet rs= st.executeQuery(SentenciaSQL);
			rs.first();
			int ID = rs.getInt("SD_id");
			conex.cerrarConexion();
			return ID;
    	}
    	catch (SQLException SQLE)
    	{
    		JOptionPane.showMessageDialog(null,"No se puede dar la fila solicitada! \n ERROR : " + SQLE.getMessage());
    	}
    	return 0;
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
}
