package conexionMySql;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionMySQL {
	private Connection conexion;
	private String nameDriver, url, usuario, password;

	public ConexionMySQL(String nameDriver, String url, String usuario, String password) {
		// TODO Auto-generated constructor stub
		this.nameDriver = nameDriver;
		this.url = url;
		this.usuario = usuario;
		this.password = password;
	}
	
	public ConexionMySQL(String ruta){
		Properties propiedades = new Properties();
		try{
			FileInputStream in = new FileInputStream(ruta + "/configuracion.properties");
			propiedades.load(in);
			this.nameDriver = propiedades.getProperty("nameDriver");
			this.url = propiedades.getProperty("url");
			this.usuario = propiedades.getProperty("usuario");
			this.password = propiedades.getProperty("password");
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
	public ConexionMySQL(){
		
	}
	
//	public static void main(String args[]){
//		Conexion con = new Conexion();
//		con.obtenerConexion();
//	}
	public Connection obtenerConexion(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gestionusuarios","root","RdrBcngr05");
			if(conexion==null){
				System.out.println("Conexion no establecida");
			}else{
				System.out.println("Conexion establecida exitosamente");
			}
			
		}catch(SQLException e){
			System.out.println("SQLException: "+e.getMessage());
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			
		}
		return conexion;
	}
	
	public Connection obtenerConexionRuta(){
		try{
			Class.forName(nameDriver);
			this.conexion = DriverManager.getConnection(url,usuario,password);
			if(conexion==null){
				System.out.println("Conexion no establecida");
			}else{
				System.out.println("Conexion establecida exitosamente");
			}
			
		}catch(SQLException e){
			System.out.println("SQLException: "+e.getMessage());
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			
		}
		return conexion;
	}
	
	public void cerrarConexion(){
		try{
			this.conexion.close();
		}catch(SQLException SQLE){
			SQLE.getMessage();
		}
		
	}
}
