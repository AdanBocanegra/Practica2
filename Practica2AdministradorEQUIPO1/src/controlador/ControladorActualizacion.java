package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;

/**
 * Servlet implementation class ControladorActualizacion
 */
@WebServlet("/ControladorActualizacion")
public class ControladorActualizacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession sesion, envioUsuarioActual;
	private Usuario usuarioActual;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorActualizacion() {
        super();
        // TODO Auto-generated constructor stub
        usuarioActual=new Usuario();
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		sesion =request.getSession();
		usuarioActual = (Usuario)sesion.getAttribute("ACTUALIZACION");
		System.out.println("Usuario actual "+ usuarioActual);
		//obtencion de datos
		String nombre = usuarioActual.getNombreCompleto();
		String correo = usuarioActual.getCorreo();
		String contrasena = usuarioActual.getContrasenia();
		
		System.out.println("nombre actual: "+ nombre);
		System.out.println("correo actual: "+ correo);
		System.out.println("contrasena actual: "+ contrasena);
		
		try{
			PrintWriter modificarDatos = response.getWriter();
			modificarDatos.println("<!DOCTYPE html><html>");
			modificarDatos.println("<head><h1>Actualizacion de datos </h1></head>");
			modificarDatos.println("<body>");
			modificarDatos.println("<div id='contenedorPrincipal'>");
			modificarDatos.println("<section id='seccionActualizacionDatos'>");
			modificarDatos.println("<form id='formularioActualizacion' method='post' action='SeguimientoControlador'>");
			modificarDatos.println("<input type='text' name='nombreActualizado' id='nombreActualizado' required='required' value='"+nombre +"'>");
			modificarDatos.println("<input type='email' name='correoActualizado' id='correoActualizado' required='required' value='"+correo +"'>");
			modificarDatos.println("<input type='password' name='contrasenaActualizado' id='contrasenaActualizado' required='required' value='"+contrasena +"'>");
			modificarDatos.println("<button type='submit'>Actualizar</button>");
			modificarDatos.println("</form>");
			modificarDatos.println("</section>");
			modificarDatos.println("</div>");
			modificarDatos.println("</body>");
			modificarDatos.println("</html>");
			
			
			envioUsuarioActual = request.getSession();
			envioUsuarioActual.setAttribute("USUARIOACTUAL", usuarioActual);
			modificarDatos.close();

			
		}catch(IOException e){
			
		}
		
	}

}
