package eventos.modelo.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eventos.modelo.entitis.Perfil;
import eventos.modelo.repository.PerfilRepositorio;

@Repository
public class PerfilDaoImplMy8 implements PerfilDao{
	@Autowired
	PerfilRepositorio perfilrepositorio;

	@Override
	public Perfil encontrarPorIdConDevolucion(int idPerfil) {
		return perfilrepositorio.findById(idPerfil).orElse(null);
	}
	
	

}
