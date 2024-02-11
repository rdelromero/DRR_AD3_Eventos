package eventos.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import eventos.modelo.dao.ReservaDao;
import eventos.modelo.entitis.Reserva;


@Controller
public class ReservaController {
	
	@Autowired
	ReservaDao rdao;

	@GetMapping("/reservas")
	public String getMap1(Model model) {
		List<Reserva> lista = rdao.encontrarTodasConDevolucion();
		model.addAttribute("listaReservas", lista);
		return "home_reservas";
	}
}
