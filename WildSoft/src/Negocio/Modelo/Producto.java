package Negocio.Modelo;
import java.sql.*;


public class Producto {

	
	
	
	public static void main(String[] args){
	
		try{
			System.out.println("Probando conectar a DataBase");
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conexion= DriverManager.getConnection("jdbc:mysql://localhost:pizzeria","root","");
		}
		catch(Exception e){
			
		}
		
	}
}
