package eventos.modelo.dao;



import eventos.modelo.dao.PerfilDao;
import eventos.modelo.entitis.Perfil;

public interface PerfilDao {
	
	public abstract Perfil encontrarPorIdConDevolucion(int idPerfil); 
}
