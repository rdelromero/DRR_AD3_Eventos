package eventos.modelo.dao;

import java.util.List;

import eventos.modelo.entitis.Evento;

public interface EventoDao {
	List<Evento> encontrarTodosConDevolucion();
	Evento encontrarPorIdConDevolucion(int idEvento);
	List<Evento> encontrarPorDestacadoConDevolucion();
	List<Evento> encontrarPorEstadoActivoConDevolucion();
	List<Evento> encontrarPorIdTipoConDevolucion(int idTipo);
}
