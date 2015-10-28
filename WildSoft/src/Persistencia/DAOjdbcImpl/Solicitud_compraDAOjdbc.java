package Persistencia.DAOjdbcImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import Negocio.Modelo.Solicitud_compra;
import Persistencia.Conector.ConectorMySQL;

public class Solicitud_compraDAOjdbc {

	private ConectorMySQL conex = new ConectorMySQL();
	private SimpleDateFormat formato_yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
	
	// FALTA TERMINAR ESTE METODO !!! 
	public boolean AGREGAR_SOLICITUD(Solicitud_compra solicitud)
	{
		
		String PROVEEDOR = "NULL";
		if(solicitud.getProveedor()!=null && solicitud.getProveedor().getId()!=0)
			PROVEEDOR = solicitud.getProveedor().getId().toString();
		
		String SentenciaSQL_Solicitud = "INSERT INTO Solicitud_compra (SD_estado, SD_proveedor,SD_fecha, SD_precio) VALUES ("+
				"'"+	1                                               	+"',"+
				""+		PROVEEDOR											+","+
				""+		formato_yyyyMMdd.format(solicitud.getFecha())		+","+											
				""+		solicitud.getPrecio()								+");";
		
		boolean Exito_al_Ingresar_Solicitud = conex.Insertar(SentenciaSQL_Solicitud);
		
		return false;
		
		
	}
	
	private Integer ObtenerUltimaSolicitud() {
		try {
			conex.connectToMySQL();
			Statement st = conex.conexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			ResultSet rs = st.executeQuery("SELECT PD_id FROM Solicitud_compra WHERE SD_id = (select max(SD_id) from Solicitud_compra)");				
			rs.first();
			int ID = rs.getInt("SD_id");
			conex.cerrarConexion();
			return ID;
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,"No se puede dar la fila solicitada! \n ERROR : " + SQLE.getMessage());
		}
		return 0;
	}
	
	
}
