package eventos.modelo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Usuario;
import eventos.modelo.repository.UsuarioRepositorio;



@Repository
public class UsuarioDaoImplMy8 implements UsuarioDao {
	@Autowired
	private UsuarioRepositorio usuariorepositorio;
	
	/*@Override
	public List<Usuario> encontrarTodosConDevolucion() {
		return usuariorepositorio.findAll();
	}*/
	
	@Override
	public Usuario encontrarPorIdConDevolucion(String username) {
		return usuariorepositorio.findById(username).orElse(null);
	}
	
	@Override
	public boolean registro(Usuario usuario) {
		if (encontrarPorIdConDevolucion(usuario.getUsername()) == null) {
			usuariorepositorio.save(usuario);
			return true;
		}
		return false;
	}
	
	/*
	@Override
	public int actualizarUsuario(Usuario usuario) {
		int filas = 0;
		Usuario u1 = null;
		try {
			u1 = usuariorepositorio.findById(usuario.getUsername()).orElse(null);
			u1 = usuario;
			usuariorepositorio.save(u1);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}*/
	
	/*
	@Override
	public int borrarPorId(String nombreUsuario) {
		int filas = 0;
		try {
			usuariorepositorio.deleteById(nombreUsuario);
			filas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filas;
	}*/
}
