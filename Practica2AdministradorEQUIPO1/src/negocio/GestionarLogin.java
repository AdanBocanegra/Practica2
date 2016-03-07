package negocio;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;

public class GestionarLogin {
	private Usuario unUsuario;
	private ConexionMySQL nuevaConexion; 
	private Connection connection;
	private Statement st=null;
	private ResultSet rs = null;
	
	public GestionarLogin() {
		unUsuario = new Usuario();
		nuevaConexion = new ConexionMySQL();
		connection = nuevaConexion.obtenerConexion();
	}
	
//	Busca al usuario correspondiente para el login
	public Usuario buscarUsuario(String correo,String contrasenia) {
		String consulta = "SELECT id, nombre, correo,contrasenia, idTipoUsuario  FROM usuarios where correo='"+correo+"' AND constrasenia='"+contrasenia+"'";
		try {
//			Un resulset contiene todas las filas que satisface las con
			rs= st.executeQuery(consulta);
//			el metodo resultset.next se usa para mover a la siguiente fila , convirtiendola en la fila actual
			while(rs.next()){
				if((rs.getString("correo").equals(correo)) && (rs.getString("contrasenia").equals(contrasenia))){
					System.out.println("Usuario encontrado");
					unUsuario.setIdUsuario();
					unUsuario.setNombreCompleto(rs.getString("nombre"));
					unUsuario.setCorreo(rs.getString("correo"));
					unUsuario.setContrasenia(rs.getString("contrasenia"));
					unUsuario.setIdTipoUsuario(rs.getInt("idTipoUsuario"));
				}
			}
		}catch (SQLException e){
			System.out.println("error al buscar al usuario" + e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				System.out.println("Error al cerrar la conexion");
			}
		}
		return unUsuario;
	}

}
