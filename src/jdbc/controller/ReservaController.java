package jdbc.controller;
import java.util.List;

import jdbc.dao.ReservaDao;
import jdbc.factory.ConnectionFactory;
import jdbc.modelo.Reserva;

public class ReservaController {
	private ReservaDao reservaDao;
	public ReservaController() {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		this.reservaDao = new ReservaDao(connectionFactory.recuperaConexion());
	}
	public List<Reserva> listar(){
		return this.reservaDao.lista();
	}
	public List<Reserva> listar(String nombre) {
		return this.reservaDao.listarPorNombre();
	}
	public Reserva registrar(Reserva reserva) {
		return this.reservaDao.addReserva(reserva);
	}

}
