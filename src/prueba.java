import java.util.List;

import jdbc.controller.ReservaController;
import jdbc.controller.UsuarioController;
import jdbc.modelo.Reserva;
import jdbc.modelo.Usuario;

public class prueba {
	public static void main(String[] args) {
		/*UsuarioController usuarioController = new UsuarioController();
		List<Usuario> listaUsuario=usuarioController.listar();
		for(int i=0;i<listaUsuario.size();i++) {
			System.out.println(listaUsuario.get(i));
		}*/
		ReservaController reservaController = new ReservaController();
		List<Reserva> listaReserva=reservaController.listar();
		for(int i=0;i<listaReserva.size();i++) {
			System.out.println(listaReserva.get(i));
		}
	}
}
