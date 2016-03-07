package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;

public class GestionarArchivoAdministrador {
	private Usuario unUsuario;
	private ConexionMySQL nuevaConexion; 
	private Connection connection;
	private Statement st=null;
	private ResultSet rs = null;
	
	public GestionarArchivoAdministrador(){
		unUsuario = new Usuario();
		nuevaConexion = new ConexionMySQL();
		connection = nuevaConexion.obtenerConexion();
	}

	public boolean crearUsuario(Usuario usuario) {
//		Consulta para verificar si existe algun usuario registrado con el mismo correo
		String verificacion = "SELECT correo,contrasenia FROM usuarios WEHRE correo='"+usuario.getCorreo()+"' and contrasenia='"+usuario.getContrasenia()+"'";
//		Insertar datos en la bd
		String consulta = "INSERT INTO usuarios(id, nombre, correo, contrasenia, idTipoUsuario) values (?,?,?,?,?)";
		try{
			st = connection.createStatement();
			rs= st.executeQuery(verificacion);
//			Verifica que la consulta no tenga filas de lo contrario, ya existe el usuario 
			if(rs.getRow()=='0'){
//				Precompila datos antes de intertarlos a la tabla, es similar a la sentencia PREPARE 
				PreparedStatement ps = connection.prepareStatement(consulta);
				ps.setNull(1, usuario.getIdUsuario());
				ps.setString(2, usuario.getNombreCompleto());
				ps.setString(3, usuario.getCorreo());
				ps.setString(4, usuario.getContrasenia());
				ps.setInt(5, usuario.getIdTipoUsuario());
//				aplicar cambios a BD 
				ps.executeUpdate();
//				Cerrar conexion
				ps.close();
			}else{
				System.out.println("el usuario ya existe");
				return false;
			}
			
		}catch(Exception e){
			System.out.println("Error al crear un nuevo usuario"+ e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				System.out.println("Error al cerrar la conexion");
			}
		}
		return true;	
	}
	
	public List<Usuario> mostrarUsuarios(){
		
		
		return null;	
	}
}
