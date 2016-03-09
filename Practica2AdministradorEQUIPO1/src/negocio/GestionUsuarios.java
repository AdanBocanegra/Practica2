package negocio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;

public class GestionUsuarios {
	private Usuario unUsuario;
	private List<Usuario> usuariosBD;
	private ConexionMySQL nuevaConexion; 
	private Connection connection;
	private Statement consulta;
	private ResultSet rs;

	public GestionUsuarios() {
		// TODO Auto-generated constructor stub
		nuevaConexion = new ConexionMySQL();
		connection = nuevaConexion.obtenerConexion();
		unUsuario = new Usuario();
		usuariosBD = new ArrayList<Usuario>();
	}
	
	public GestionUsuarios(ConexionMySQL conex){
		this.nuevaConexion = conex;
		this.connection = conex.obtenerConexion();
		this.usuariosBD = new ArrayList<Usuario>();
		this.consulta = null;
		this.rs = null;
	}
	
	/**
	 * La funcion obtenerUsuarios regresa todos los usuarios que son de tipo 1
	 * y los ordena por su correo en un ArrayList @return List<Usuarios>
	 */
	public List<Usuario> obtenerUsuarios(){
		String query = "SELECT * FROM usuarios WHERE idTipoUsuario='1' ORDER BY correo";
		try{
			consulta = connection.createStatement();
			
			rs = consulta.executeQuery(query);
			
			while(rs.next()){
				unUsuario = new Usuario();
				unUsuario.setCorreo(rs.getString("correo"));
				unUsuario.setNombreCompleto(rs.getString("nombre"));
				unUsuario.setContrasenia(rs.getString("contrasenia"));
				unUsuario.setIdTipoUsuario(Integer.parseInt(rs.getString("idTipoUsuario")));
				unUsuario.setIdUsuario(Integer.parseInt(rs.getString("id")));
				usuariosBD.add(unUsuario);
			}
			connection.close();
		}catch(Exception SQLE){
			SQLE.printStackTrace();
		}
		System.gc();
		return usuariosBD;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new GestionUsuarios().obtenerUsuarios();
		
	}

}
