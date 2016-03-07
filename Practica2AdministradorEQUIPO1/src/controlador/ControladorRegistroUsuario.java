package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;
import negocio.GestionarArchivoUsuario;

/**
 * Servlet implementation class ControladorRegistroUsuario
 */
@WebServlet("/ControladorRegistroUsuario")
public class ControladorRegistroUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private Usuario nuevoUsuario;
    private GestionarArchivoUsuario archivoRegistro;
    private ConexionMySQL nuevaConexion; 
	private Connection connection;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorRegistroUsuario() {
        super();
        nuevoUsuario = new Usuario();
        archivoRegistro = new GestionarArchivoUsuario();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String ruta = request.getServletContext().getRealPath("/");
//		instancia hacia la conexion de mysql
		try{
			nuevaConexion = new ConexionMySQL();
			connection = nuevaConexion.obtenerConexion();
		}catch(Exception e){
			System.out.println("Error de conexion en login "+e.getMessage());
		}
		
		//instancia usuario para agregar los datos obtenidos
		//Usuario usuario = new Usuario();
		boolean creacionUsuario;
		System.out.println("paso 1");
		String nombreCompleto=request.getParameter("nombreCompleto");
		String correo = request.getParameter("correoElectronico");
		String contrasenia = request.getParameter("contrasena");

		//System.out.println(nombre+"\n"+apellidoMaterno+"\n"+apellidoPaterno+"\n"+correo+"\n"+contrasena);

		//Agrega los datos obtenidos a la instancia
		nuevoUsuario.setNombreCompleto(nombreCompleto);
		nuevoUsuario.setCorreo(correo);
		nuevoUsuario.setContrasenia(contrasenia);
		nuevoUsuario.setIdTipoUsuario(1);
		
		System.out.println(nuevoUsuario.getNombreCompleto());
		System.out.println(nuevoUsuario.getCorreo());
		System.out.println(nuevoUsuario.getContrasenia());
		System.out.println(nuevoUsuario.getIdTipoUsuario());
		

		//enviar datos al archivo
		System.out.println("paso 2");
		//archivoRegistro.crearUsuario(nuevoUsuario);
		creacionUsuario=archivoRegistro.crearUsuario(nuevoUsuario);
		if(creacionUsuario){
			response.sendRedirect("registroExitoso.html");
		}else{
			response.sendRedirect("registroFallido.html");
		}
		System.gc();
		try{
			connection.close();
		}catch(SQLException e){
			System.out.println("Error al cerrar la conexion");
		}
	}

}
