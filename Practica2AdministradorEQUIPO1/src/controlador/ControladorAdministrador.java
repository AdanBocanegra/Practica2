package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import conexionMySql.ConexionMySQL;
import modelo.Usuario;
import negocio.GestionUsuarios;

import java.util.List;
import conexionMySql.*;
/**
 * Servlet implementation class ControladorAdministrador
 */
@WebServlet("/ControladorAdministrador")
public class ControladorAdministrador extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Usuario> todosLosUsuarios;
	private ConexionMySQL conexion;
	private GestionUsuarios gestion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControladorAdministrador() {
        super();
        
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
		/*response.setContentType("text/html");
		PrintWriter salida = response.getWriter();
		salida.println("<!DOCTYPE html><html>");
	    salida.println("<head><title>Modificación de usuario</title></head>");
	    salida.println("<body>");
	    salida.println("<div>");
	    salida.println("<header><h1> LOL </h1></header>");
	    salida.println("<section>");
	    salida.println("<div class='cuerpo-caja'>");
	    salida.flush();
	    salida.println("<table>");
	    salida.println("<thead>");
	    salida.println("<tr>");
	    salida.println("<th>Correo</th>");
	    salida.println("<th>Nombre</th>");
	    salida.println("</tr>");
	    salida.println("</thead>");
	    salida.println("<tbody>");
	    //salida.println("<tr>");
	    salida.flush();
	    for(int i = 0; i < todosLosUsuarios.size() ; i++){
	    	salida.println("<tr><td>"+todosLosUsuarios.get(i).getCorreo()+"</td><td>"+todosLosUsuarios.get(i).getNombreCompleto()+"</td><td><a href='./login?id=2'>Modificar</a> <a href=''>Eliminar</a> <a href=''>Consultar</a></tr>");
	  
	    }
	    //salida.println("</tr>");
	    salida.println("</tbody>");
	    salida.println("</table>");
	    salida.println("</div>");
	    salida.println("</div>");
	    salida.println("</body></html>");
	    salida.flush();
	    salida.close();*/
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
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
			gestion = new GestionUsuarios(conexion);
			todosLosUsuarios = gestion.obtenerUsuarios();
		}catch(Exception e){
			e.printStackTrace();
		}
		if(todosLosUsuarios.isEmpty()){
			
		}else{
			response.setContentType("text/html");
			PrintWriter salida = response.getWriter();
			salida.println("<!DOCTYPE html><html>");
		    salida.println("<head><title>Inicio de sesion</title></head>");
		    salida.println("<link type='text/css' rel='stylesheet' href='estilos/estilosAdmin.css'");
		    salida.println("<body>");
		    salida.println("<div id='contenedor'>");
		    salida.println("<header><h1> Bienvenido </h1></header>");
		    salida.println("<section>");
		    salida.println("<div class='box box-info'><div class='box-body'>");
		    salida.flush();
		    salida.println("<table class='table no-margin'>");
		    salida.println("<thead>");
		    salida.println("<tr>");
		    salida.println("<th>Correo</th>");
		    salida.println("<th>Nombre</th>");
		    salida.println("</tr>");
		    salida.println("</thead>");
		    salida.println("<tbody>");
		    //salida.println("<tr>");
		    salida.flush();
		    for(int i = 0; i < todosLosUsuarios.size() ; i++){
		    	salida.println("<tr><td>"+todosLosUsuarios.get(i).getCorreo()+"</td><td>"+todosLosUsuarios.get(i).getNombreCompleto()+"</td><td><a href='./ControladorAdministrador?id="+todosLosUsuarios.get(i).getIdUsuario()+"&option=1'>Modificar</a> <a href='./ControladorAdministrador?id="+todosLosUsuarios.get(i).getIdUsuario()+"&option=2'>Eliminar</a> <a href='./ControladorAdministrador?id="+todosLosUsuarios.get(i).getIdUsuario()+"&option=3'>Consultar</a></tr>");
		  
		    }
		    //salida.println("</tr>");
		    salida.println("</tbody>");
		    salida.println("</table>");
		    salida.println("</div></div>");
		    salida.println("</div>");
		    salida.println("</body></html>");
		    salida.flush();
		    salida.close();
		}
	}

}
