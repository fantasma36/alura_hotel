package jdbc.controller;
import java.util.List;

import jdbc.dao.UsuarioDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Usuario;

public class UsuarioController {
	UsuarioDao usuarioDao;
	public UsuarioController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.usuarioDao=new UsuarioDao(factory.recuperaConexion());
	}
	public List<Usuario> listar(){
		return usuarioDao.listar();
	}
	public Usuario iniciarSesion(String user, String password) {
		return usuarioDao.login(user,password);
	}

}
