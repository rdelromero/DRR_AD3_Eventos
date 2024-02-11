package eventos.modelo.dao;
import java.util.List;

import eventos.modelo.entitis.Reserva;
public interface ReservaDao {
	public abstract List<Reserva> encontrarTodasConDevolucion();
	public abstract Reserva encontrarPorIdConDevolucion(int idReserva);
	public abstract List<Reserva> encontrarPorNombreUsuarioConDevolucion(String nombreUsuario);
	boolean registro(Reserva reserva);
	int borrarPorId(int idReserva);
	public abstract int encontrarSumaCantidades();
	public abstract int encontrarSumaCantidadesPorIdEvento(int idEvento);
	public abstract int encontrarSumaCantidadesPorUsername(String nombreUsuario);
	public abstract int encontrarSumaCantidadesPorIdEventoYUsername(int idEvento, String nombreUsuario);
}
