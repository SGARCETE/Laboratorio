package Persistencia.DAOjdbcImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;



public class ConectorMySQL {
	public 	Connection conexion;
	
	private String DATABASE_NAME = "WILDSOFT";	// Poner el nombre de la base de datos
	private String USUARIO = "root";			// Usuario que administra la DB
	private String PASSWORD = "root";			// Password del usuario
	private String Driver = "jdbc:mysql://localhost/"+  DATABASE_NAME  +"?user="+   USUARIO   +"&password="+  PASSWORD;
	
	
	/** Conecta a la base de datos **/		
	public boolean connectToMySQL() {
	    try {
	        Class.forName("com.mysql.jdbc.Driver"); 
	        conexion = DriverManager.getConnection(Driver);
	    } catch (Exception SQLE) {
	        JOptionPane.showMessageDialog(null,"No se establecio una conexion a la base de datos!\n");
	        SQLE.printStackTrace();
	        return false;
	    }
	    return true;
	}
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			CERRAR CONEXION
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**		Terminar la conexion a la base de datos**/													
	public void cerrarConexion(){
		try{
			this.conexion.close();
		}catch(SQLException SQLE){
            JOptionPane.showMessageDialog(null,"Error al cerrar conexion\n Error:" + SQLE.getMessage());
            SQLE.printStackTrace();
		};
	}

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//			INSERTAR QUERY's
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/** Ingresar datos en una fila
	 * @param SentenciaSQL Recibe una sentanciaSQL (String)	*/	
	public boolean Insertar(String SentenciaSQL) {
		connectToMySQL();
		boolean salida = false;
        try {
        	Statement st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            st.executeUpdate(SentenciaSQL);
            salida = true;
        }
        catch (SQLException SQLE) {
            JOptionPane.showMessageDialog(null,"Error ! \n ERROR : " + SQLE.getMessage());
            SQLE.printStackTrace();
        }
        cerrarConexion();
        return salida;
    }

}//--> FIN

