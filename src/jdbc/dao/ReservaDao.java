package jdbc.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Reserva;

public class ReservaDao {
	private Connection connection;
	public ReservaDao(Connection connection) {
		this.connection=connection;
	}
	public List<Reserva> lista(){
		List<Reserva> listaReserva = new ArrayList<Reserva>();
		String query = "SELECT * FROM RESERVAS";
		try {
			final PreparedStatement statement = connection.prepareStatement(query);
			try(statement){
				final ResultSet resultSet=statement.executeQuery();
				try(resultSet){
					while (resultSet.next()) {
						Integer id=resultSet.getInt(1);
						Timestamp entrada=resultSet.getTimestamp(2);
						LocalDateTime localFechaEntrada = entrada.toLocalDateTime();
						//LocalDate fechaEntrada = localFechaEntrada.toLocalDate();
						Timestamp salida=resultSet.getTimestamp(3);
						LocalDateTime localFechaSalida = salida.toLocalDateTime();
						//LocalDate fechaSalida = localFechaSalida.toLocalDate();
						BigDecimal valor =resultSet.getBigDecimal(4);
						String formaPago=resultSet.getString(5);
						Reserva reserva = new Reserva(id,localFechaEntrada,localFechaSalida,valor,formaPago);
						listaReserva.add(reserva);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return listaReserva;
	}
	public Reserva addReserva(Reserva reserva) {
		String query = "INSERT INTO RESERVAS(fechaEntrada,fechaSalida,valor,formaPago)"
				+ "VALUES(?,?,?,?)";
		try {
			final PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try(statement){

				statement.setTimestamp(1, Timestamp.valueOf(reserva.getFechaEntrada()));
				statement.setTimestamp(2, Timestamp.valueOf(reserva.getFechaSalida()));
				statement.setBigDecimal(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());
				statement.execute();
				ResultSet resultSet =statement.getGeneratedKeys();
				try(resultSet){
					if(resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
				
		return reserva;
	}
}
