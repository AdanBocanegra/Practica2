package modelo;

public class Usuario {
	private int idUsuario;
	private String nombreCompleto;
	private String correo;
	private String contrasenia;
	private int idTipoUsuario;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
		
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombre) {
		this.nombreCompleto = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public int getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(int idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	
	
	
	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", nombre=" + nombreCompleto + ", correo=" + correo + ", contrasenia="
				+ contrasenia + ", idTipoUsuario=" + idTipoUsuario + "]";
	}

//	public boolean isEmpty(){
//		return idUsuario.equals(null) && nombreCompleto.equals("") && correo.equals("") && contrasenia.equals("") && idTipoUsuario.equals(null) ; 
//	}

}
