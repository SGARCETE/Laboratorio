package Persistencia.Conector;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class ConectorMySQL {
	public Connection conexion;

	private String DATABASE_NAME = "WILDSOFT"; // Poner el nombre de la base de datos
	private String USUARIO = Get_usuario(); // Usuario que administra la DB
	private String PASSWORD = Get_contraseña(); // Password del usuario
	private String Driver = "jdbc:mysql://localhost/" + DATABASE_NAME+ "?user=" + USUARIO + "&password=" + PASSWORD;

	/** Conecta a la base de datos **/
	public boolean connectToMySQL() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(Driver);
		} catch (Exception SQLE) {
			try{ 
				   //Si falla la conexión, abre el txt con los datos para inicializar la base de datos.
				   File fichero = new File("Datos.txt");
				   Runtime.getRuntime().exec("cmd /c start "+fichero.getName());
				   }catch(IOException e){
				      e.printStackTrace();
				   } 
			JOptionPane.showMessageDialog(null,
					"No se establecio una conexion a la base de datos!\nPor favor verifique que el usuario y contraseña sean correctos.");
			SQLE.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public boolean connectToMySQL_TEST() {
		Driver = "jdbc:mysql://localhost/test?user=" + USUARIO + "&password=" + PASSWORD;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection(Driver);
		} catch (Exception SQLE) {
			try{ 
				   //Si falla la conexión, abre el txt con los datos para inicializar la base de datos.
				   File fichero = new File("Datos.txt");
				   Runtime.getRuntime().exec("cmd /c start "+fichero.getName());
				   }catch(IOException e){
				      e.printStackTrace();
				   } 
			JOptionPane.showMessageDialog(null,
					"No se establecio una conexion a la base de datos!\nPor favor verifique que el usuario y contraseña sean correctos.");
			SQLE.printStackTrace();
			return false;
		}
		return true;
	}
		
	

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// CERRAR CONEXION
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/** Terminar la conexion a la base de datos **/
	public void cerrarConexion() {
		try {
			this.conexion.close();
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,
					"Error al cerrar conexion\n Error:" + SQLE.getMessage());
			SQLE.printStackTrace();
		}
		;
	}

	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	// INSERTAR QUERY's
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	/**
	 * Ingresar datos en una fila
	 * 
	 * @param SentenciaSQL
	 *            Recibe una sentanciaSQL (String)
	 */
	public boolean Insertar(String SentenciaSQL) {
		connectToMySQL();
		boolean salida = false;
		try {
			Statement st = conexion
					.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
							ResultSet.CONCUR_UPDATABLE);
			st.executeUpdate(SentenciaSQL);
			salida = true;
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,
					"Error ! \n ERROR : " + SQLE.getMessage());
			System.out.println(SQLE.getMessage());
			SQLE.printStackTrace();
		}
		cerrarConexion();
		return salida;
	}

	/**
	 * Ingresar datos en una fila
	 * 
	 * @param SentenciaSQL
	 *            Recibe una sentanciaSQL (String)
	 * @return Devuelve el id autonumerico que genera el insert
	 */
	@SuppressWarnings("finally")
	public int insert(String SentenciaSQL) {
		connectToMySQL();
		int id = 0;
		ResultSet rs = null;
		try {
			Statement st = conexion.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st.executeUpdate(SentenciaSQL);
			rs = st.getGeneratedKeys();
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException SQLE) {
			JOptionPane.showMessageDialog(null,"Error ! \n ERROR : " + SQLE.getMessage());
			System.out.println(SQLE.getMessage());
			SQLE.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			cerrarConexion();
			return id;
		}
	}
	
	
	// Devuelve el usuario que toma del txt de datos
	public String Get_usuario(){
		File fichero = new File("Datos.txt");
		File f = new File(fichero.getAbsolutePath());
		Scanner s;
		String usuario= "";
		try {
			s = new Scanner(f);
			int i=0;
			
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				@SuppressWarnings("resource")
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*,\\s*");
				if (i ==0){
					usuario=  sl.next().toString().replace("Usuario: ", "");
				}
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	// Devuelve la contraseña que toma del txt de datos.
	public String Get_contraseña(){
		File fichero = new File("Datos.txt");
		File f = new File(fichero.getAbsolutePath());
		Scanner s;
		String usuario= "";
		try {
			s = new Scanner(f);
			int i=0;
			
			while (s.hasNextLine()) {
				String linea = s.nextLine();
				@SuppressWarnings("resource")
				Scanner sl = new Scanner(linea);
				sl.useDelimiter("\\s*,\\s*");
				if (i ==1){
					usuario=  sl.next().toString().replace("Contraseña: ", "");
				}
				i++;
			}
			s.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	
	
	

}// --> FIN

