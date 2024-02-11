package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Evento;

public interface EventoRepositorio extends JpaRepository<Evento, Integer>{
	
	@Query("select e from Evento e where e.destacado = ?1")
	public List<Evento> encontrarPorDestacado(String destacado);
	
	@Query("select e from Evento e where e.estado = ?1")
	public List<Evento> encontrarPorEstado(String estado);
	
	@Query("select e from Evento e where e.tipo.idTipo = ?1")
	public List<Evento> encontrarPorIdTipo(int idTipo);
}
