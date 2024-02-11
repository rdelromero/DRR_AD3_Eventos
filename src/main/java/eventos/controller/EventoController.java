package eventos.controller;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
import eventos.modelo.entitis.Usuario;
@Controller
public class EventoController {

    @Autowired
    EventoDao edao;
    @Autowired
    UsuarioDao udao;
    @Autowired
    ReservaDao rdao;
    
	@GetMapping("/eventos")
	public String getMap1(Model model) {
		List<Evento> lista = edao.encontrarTodosConDevolucion();
		model.addAttribute("listaEventos", lista);
		return "home_eventos";
	}
    
    //El valor que hay entre llaves, "id" debe ser el mismo en getmapping y en PathVariable
	@GetMapping("/eventos/detalle/{id}")
	public String getMap2(@PathVariable("id") int idEvento, Model modelo, RedirectAttributes redirectAttributes, Authentication autenticacion) {
		Evento evento = edao.encontrarPorIdConDevolucion(idEvento);
		if (evento != null) {
			//La página va a mostrar datos del evento
			modelo.addAttribute("evento", evento);
			int numeroEntradasDisponiblesDelEvento = edao.encontrarPorIdConDevolucion(idEvento).getAforoMaximo()-rdao.encontrarSumaCantidadesPorIdEvento(idEvento);
			modelo.addAttribute("mensajeNumeroEntradasDisponiblesDelEvento", "Hay "+numeroEntradasDisponiblesDelEvento+" entradas disponibles."); 
			//La página va a posibilitar hacer una reserva
			modelo.addAttribute("reserva", new Reserva());
			//Si el usuario está autenticado, le indicamos el número de entradas que ya ha adquirido del evento.
			if (autenticacion != null) {
				int numeroEntradasYaComparadas=rdao.encontrarSumaCantidadesPorIdEventoYUsername(idEvento, autenticacion.getName());
				modelo.addAttribute("mensajeNumeroEntradasYaComparadas", "Has adquirido hasta el momento "+numeroEntradasYaComparadas+" entradas del evento. No puedes adquirir más de 10.");
			}
			return "home_eventos_verEvento";
		}
		else
			redirectAttributes.addFlashAttribute("mensaje", "No hay datos de un evento con ese id.");
		//En caso de que no exista ese cliente, volvemos a usuarios.
		return "redirect:/eventos";
	}
	
    //El valor que hay entre llaves, "id" debe ser el mismo en getmapping y en PathVariable
	@GetMapping("/eventos/destacados")
	public String getMap3(Model modelo, RedirectAttributes redirectAttributes) {
		List<Evento> lista = edao.encontrarPorDestacadoConDevolucion();
		modelo.addAttribute("mensaje", "Lista de eventos destacados");
		modelo.addAttribute("listaEventosPor", lista);
		return "home_eventos_concondicion";
	}
	
	@GetMapping("/eventos/activos")
	public String getMap4(Model modelo, RedirectAttributes redirectAttributes) {
		List<Evento> lista = edao.encontrarPorEstadoActivoConDevolucion();
		modelo.addAttribute("mensaje", "Lista de eventos activos");
		modelo.addAttribute("listaEventosPor", lista);
		return "home_eventos_concondicion";
	}
	
	@GetMapping("/eventos/tipo/{idTipo}")
	public String getMap5(@PathVariable("idTipo") int idTipo, Model modelo, RedirectAttributes redirectAttributes) {
		List<Evento> lista = edao.encontrarPorIdTipoConDevolucion(idTipo);
		modelo.addAttribute("mensaje", "Lista de eventos por tipo "+idTipo);
		modelo.addAttribute("listaEventosPor", lista);
		return "home_eventos_concondicion";
	}
	
	@PostMapping("/eventos/reservar/{id}")
	public String posMap(@PathVariable("id") int idEvento, @RequestParam("username") String nombreUsuario, Reserva reserva, RedirectAttributes ratt) {
		//La reserva podrá realizarse si el número de entradas disponibles es mayor del que se solicita.
		if (edao.encontrarPorIdConDevolucion(idEvento).getAforoMaximo()-rdao.encontrarSumaCantidadesPorIdEvento(idEvento)-reserva.getCantidad()>=0 && rdao.encontrarSumaCantidadesPorIdEventoYUsername(idEvento, nombreUsuario)+reserva.getCantidad()<=10) {
			reserva.setIdReserva(rdao.encontrarTodasConDevolucion().size()+1);
			reserva.setEvento(edao.encontrarPorIdConDevolucion(idEvento));
			reserva.setPrecioVenta(edao.encontrarPorIdConDevolucion(idEvento).getPrecio());
			reserva.setUsuario(udao.encontrarPorIdConDevolucion(nombreUsuario));
			ratt.addFlashAttribute("mensaje", "Reserva realizada");
			rdao.registro(reserva);
		} else if (edao.encontrarPorIdConDevolucion(idEvento).getAforoMaximo()-rdao.encontrarSumaCantidadesPorIdEvento(idEvento)-reserva.getCantidad()<0) {
			ratt.addFlashAttribute("mensaje", "No se ha podido realizar la reserva. No hay tal cantidad de entradas disponibles.");
		} else if (rdao.encontrarSumaCantidadesPorIdEventoYUsername(idEvento, nombreUsuario)+reserva.getCantidad()>10) {
			ratt.addFlashAttribute("mensaje", "No se ha podido realizar la reserva. No puedes adquirir más de 10 entradas para un mismo evento.");
		}
		return "redirect:/misreservas";
	}
}