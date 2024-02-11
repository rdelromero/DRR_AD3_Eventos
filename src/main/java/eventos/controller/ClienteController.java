package eventos.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.ReservaDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Reserva;
@Controller
public class ClienteController {
	
	@Autowired
	ReservaDao rdao;
	
	@GetMapping("/misreservas")
	public String getMap1(Model modelo, Authentication autenticacion) {
		System.out.println(autenticacion.getName());
		List<Reserva> lista = rdao.encontrarPorNombreUsuarioConDevolucion(autenticacion.getName());
		modelo.addAttribute("listaReservasPorNombreUsuario", lista);
		modelo.addAttribute("mensajeTotalEntradasCompradasPorUsuario", "Has adquirido "+rdao.encontrarSumaCantidadesPorUsername(autenticacion.getName())+" entradas en total.");
		return "home_misreservas";
	}
	
	@GetMapping("/reservas/borrar/{id}")
	public String getMap2(RedirectAttributes redirectAttributes, @PathVariable(name="id") int  idReserva) {
		if (rdao.borrarPorId(idReserva) == 1)
			redirectAttributes.addFlashAttribute("mensaje", "Reserva borrada correctamente");
		else
			redirectAttributes.addAttribute("mensaje", "reserva NOOO borrada");
		return "redirect:/misreservas";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
