package eventos.modelo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import eventos.modelo.entitis.Reserva;

public interface ReservaRepositorio extends JpaRepository<Reserva, Integer>{
	
	@Query("select r from Reserva r where r.evento.idEvento = ?1")
	public List<Reserva> encontrarPorIdEvento(int idEvento);
	
	@Query("select r from Reserva r where r.usuario.username = ?1")
	public List<Reserva> encontrarPorNombreUsuario(String nombreUsuario);
	
	@Query("SELECT SUM(r.cantidad) as sum FROM Reserva r")
	public int encontrarSumaCantidades();
	
	//Ponemos que devuelva un integer porque podría devolver null
	@Query("SELECT SUM(r.cantidad) as sum FROM Reserva r WHERE r.evento.idEvento =?1")
    public Integer encontrarSumaCantidadesPorIdEvento(int idEvento);
	
	@Query("select SUM(r.cantidad) as sum FROM Reserva r WHERE r.usuario.username =?1")
	public Integer encontrarSumaCantidadesPorUsername(String nombreUsuario);
	
	@Query("SELECT SUM(r.cantidad) as sum FROM Reserva r WHERE r.evento.idEvento =?1 AND r.usuario.username =?2")
    public Integer encontrarSumaCantidadesPorIdEventoYUsername(int idEvento, String username);
}
