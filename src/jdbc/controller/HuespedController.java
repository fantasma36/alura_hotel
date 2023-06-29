package jdbc.controller;
import java.util.List;

import jdbc.dao.HuespedDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Huesped;
import jdbc.modelo.Usuario;

public class HuespedController {
	HuespedDao huespedDao;
	public HuespedController() {
		ConnectionFactory factory = new ConnectionFactory();
		this.huespedDao=new HuespedDao(factory.recuperaConexion());
	}
	public List<Huesped> listar(){
		return huespedDao.listar();
	}
	public List<Huesped> listar(String nombre) {
		return this.huespedDao.listarPorNombre(nombre);
	}
	public void addHuesped(Huesped huesped) {
		huespedDao.add(huesped);
	}
}
