package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eventos.modelo.entitis.Evento;
import eventos.modelo.repository.EventoRepositorio;

@Service
public class EventoDaoImplMy8 implements EventoDao{
	@Autowired
	EventoRepositorio erepo;

	@Override
	public List<Evento> encontrarTodosConDevolucion() {
		return erepo.findAll();
	}
	
	@Override
	public Evento encontrarPorIdConDevolucion(int idEvento) {
		return erepo.findById(idEvento).orElse(null);
	}

	@Override
	public List<Evento> encontrarPorDestacadoConDevolucion() {
		return erepo.encontrarPorDestacado("S");
	}
	
	@Override
	public List<Evento> encontrarPorEstadoActivoConDevolucion() {
		return erepo.encontrarPorEstado("ACTIVO");
	}
	
	@Override
	public List<Evento> encontrarPorIdTipoConDevolucion(int idTipo) {
		return erepo.encontrarPorIdTipo(idTipo);
	}
}