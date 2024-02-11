package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.EventoRepositorio;
import eventos.modelo.repository.ReservaRepositorio;

@Repository
public class ReservaDaoImplMy8 implements ReservaDao{
	@Autowired
	ReservaRepositorio rrepo;
	@Autowired
	EventoRepositorio erepo;

	EventoDao edao;
	
	@Override
	public List<Reserva> encontrarTodasConDevolucion() {
		return rrepo.findAll();
	}
	
	@Override
	public boolean registro(Reserva reserva) {
			rrepo.save(reserva);
			return true;
	}
	

	@Override
	public List<Reserva> encontrarPorNombreUsuarioConDevolucion(String nombreUsuario) {
		return rrepo.encontrarPorNombreUsuario(nombreUsuario);
	}
	
	@Override
	public int encontrarSumaCantidades() {
		return rrepo.encontrarSumaCantidades();
	}
	
	@Override
	public int encontrarSumaCantidadesPorIdEvento(int idEvento) {
		if (rrepo.encontrarSumaCantidadesPorIdEvento(idEvento) != null) {
			return rrepo.encontrarSumaCantidadesPorIdEvento(idEvento);
		}
		else
			return 0;
	}
	
	@Override
	public int encontrarSumaCantidadesPorUsername(String nombreUsuario) {
		if (rrepo.encontrarSumaCantidadesPorUsername(nombreUsuario) != null) {
			return rrepo.encontrarSumaCantidadesPorUsername(nombreUsuario);
		}
		else
			return 0;
	}
	
	/*@Override
	public int encontrarNumeroEntradasDisponiblesPorIdEvento(int idEvento) {
			return rrepo.encontrarNumeroEntradasDisponiblesPorIdEvento(idEvento);
	}*/
	
	@Override
	public int encontrarSumaCantidadesPorIdEventoYUsername(int idEvento, String nombreUsuario) {
		if (rrepo.encontrarSumaCantidadesPorIdEventoYUsername(idEvento, nombreUsuario) != null) {
			return rrepo.encontrarSumaCantidadesPorIdEventoYUsername(idEvento, nombreUsuario);
		}
		else
			return 0;
	}
	
	@Override
	public Reserva encontrarPorIdConDevolucion(int idReserva) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int borrarPorId(int idReserva) {
		int filas = 0;
		try {
			rrepo.deleteById(idReserva);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}

}
