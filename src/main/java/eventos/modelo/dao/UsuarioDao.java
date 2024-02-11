package eventos.modelo.dao;
import eventos.modelo.entitis.Usuario;

public interface UsuarioDao {
	//public abstract List<Usuario> encontrarTodosConDevolucion();
	public abstract Usuario encontrarPorIdConDevolucion(String username);
	public abstract boolean registro(Usuario usuario);
	//public abstract int borrarPorId(String nombreUsuario);
	//public abstract int actualizarUsuario(Usuario usuario);
	
}
