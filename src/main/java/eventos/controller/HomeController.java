package eventos.controller;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import eventos.modelo.dao.EventoDao;
import eventos.modelo.dao.PerfilDao;
import eventos.modelo.dao.UsuarioDao;
import eventos.modelo.entitis.Evento;
import eventos.modelo.entitis.Usuario;

@Controller
public class HomeController {
	@Autowired
	UsuarioDao udao;
	@Autowired
	PerfilDao pfdao;
	@Autowired
	EventoDao edao;

	@GetMapping("/")
	public String getMap1(Authentication aut, Model model) {
		return "home";
	}
	
	@GetMapping("/signup")
	public String registrar(Model model) {
	   model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/signup")
	public String proregistrar(Model model, Usuario usuario, RedirectAttributes ratt) {
		usuario.setPassword("{noop}"+usuario.getPassword());
		usuario.setEnabled(1);
		usuario.setFechaRegistro(new Date());
	 	usuario.addPerfil(pfdao.encontrarPorIdConDevolucion(3));
	 	if (udao.registro(usuario)) {
	 		model.addAttribute("mensaje", "Registro realizado con éxito");
	 		return "home";
	 	}
	 	else {
	 		model.addAttribute("mensaje", "Nombre de usuario no disponible");
	 		return "/registro";	
	 	}
	}
	
	@GetMapping("/reservas/{id}")
	public String eliminar(RedirectAttributes redirectAttributes, @PathVariable(name="id") String  nombreUsuario) {
		return "/reservas";
	}
	
}