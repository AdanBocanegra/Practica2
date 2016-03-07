package negocio;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;

public class GestionarArchivoUsuario {
	private Usuario unUsuario;
	private ConexionMySQL nuevaConexion; 
	private Connection connection;
	private Statement st=null;
	private ResultSet rs = null;
	
	public GestionarArchivoUsuario(){
		unUsuario = new Usuario();
		nuevaConexion = new ConexionMySQL();
		connection = nuevaConexion.obtenerConexion();
	}
//	Registra un nuevo usuario en la BD
	public boolean crearUsuario(Usuario usuario) {
//		Consulta para verificar si existe algun usuario registrado con el mismo correo
		String verificacion = "SELECT correo,contrasenia FROM usuarios WEHRE correo='"+usuario.getCorreo()+"' and contrasenia='"+usuario.getContrasenia()+"'";
//		Insertar datos en la bd
		String consulta = "INSERT INTO usuarios (id, nombre, correo, contrasenia, idTipoUsuario) values (?,?,?,?) id= LAST_INSERT_ID(id)";
		try{
			st = connection.createStatement();
			rs= st.executeQuery(verificacion);
//			el metodo resultset.next se usa para mover a la siguiente fila , convirtiendola en la fila actual
			while(rs.next()){
//				Verifica que la consulta no tenga filas de lo contrario, ya existe el usuario 
				if((rs.getString("correo").equals(usuario.getCorreo())) && (rs.getString("contrasenia").equals(usuario.getContrasenia()))){
					System.out.println("Usuario encontrado");
					System.out.println("el usuario ya existe");
					return false;
				}else{
//					Precompila datos antes de intertarlos a la tabla, es similar a la sentencia PREPARE 
					PreparedStatement ps = connection.prepareStatement(consulta);
					ps.setString(2, usuario.getNombreCompleto());
					ps.setString(3, usuario.getCorreo());
					ps.setString(4, usuario.getContrasenia());
					ps.setInt(5, usuario.getIdTipoUsuario());
				
//					aplicar cambios a BD 
					ps.executeUpdate();
//					Cerrar conexion
					ps.close();
				}
			}	
		}catch(Exception e){
			System.out.println("Error al crear un nuevo usuario "+ e.getMessage());
			return false;
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				System.out.println("Error al cerrar la conexion");
			}
		}
		return true;
	}
	
//	Busca al usuario para recuperacion de contraseña
	public Usuario buscarUsuario(String correo) {
		String consulta = "SELECT nombre, correo, contrasenia FROM usuarios where correo='"+correo+"'";
		try {
//			Un resulset contiene todas las filas que satisface la consulta
			st = connection.createStatement();
			rs= st.executeQuery(consulta);
//			el metodo resultset.next se usa para mover a la siguiente fila , convirtiendola en la fila actual
			while(rs.next()){
				if(rs.getString("correo").equals(correo)){
					System.out.println("Usuario encontrado");
					unUsuario.setNombreCompleto(rs.getString("nombre"));
					unUsuario.setCorreo(rs.getString("correo"));
					unUsuario.setContrasenia(rs.getString("contrasenia"));				}
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
