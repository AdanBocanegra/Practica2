package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;
import negocio.GestionarLogin;

/**
 * Servlet implementation class ControladorLogin
 */
@WebServlet("/login")
public class ControladorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Usuario usuarioEncontrado;
	private GestionarLogin archivoregistro;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorLogin() {
        super();
        usuarioEncontrado = new Usuario();
        archivoregistro = new GestionarLogin();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("id"));
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		System.out.println(request.getAttribute("id"));
		String ruta = request.getServletContext().getRealPath("/");

//		Obtiene los parametros que el usuario ingreso para lograr iniciar sesion
		String correo = request.getParameter("usuario");
		String password = request.getParameter("contrasena");
//		Los parametros obtenidos se enviar al metodo buscarUsuario en la clase gestionar archivo usuario
		System.out.println("nombre: "+correo+" contrasena "+password);
//		instancia hacia la conexion de mysql
		try{
			ConexionMySQL conexion = new ConexionMySQL(ruta+"/archivos");
			conexion.obtenerConexionRuta();
			archivoregistro = new GestionarLogin(conexion);
		}catch(Exception e){
			System.out.println("Error de conexion en login "+e.getMessage());
		}
		usuarioEncontrado = archivoregistro.buscarUsuario(correo,password);
		
		
		if(usuarioEncontrado==null){	
			System.out.println("Usuario no encontrado");
		    response.sendRedirect("loginError.html");
//		}else if((usuarioEncontrado.getCorreo().equals(correo)) && (usuarioEncontrado.getContrasenia().equals(password))){
//		Usuario
		}else if(usuarioEncontrado.getIdTipoUsuario()=='1'){
			System.out.println("usuario encontrado");
			response.setContentType("text/html");
			PrintWriter salida = response.getWriter();
			salida.println("<!DOCTYPE html><HTML>");
		    salida.println("<HEAD><TITLE>Inicio de sesion</TITLE></HEAD>");
		    salida.println("<BODY>");
		    salida.println("<header><h1> Bienvenido"+usuarioEncontrado.getNombreCompleto()+"</h1><header>");
		    salida.println("</BODY></HTML>");
		    salida.flush();
		    salida.close();
//		Usuario administrador   
		}else if(usuarioEncontrado.getIdTipoUsuario()==2){
			response.sendRedirect("registroExitoso.html");
		}
		System.gc();
	}

}
