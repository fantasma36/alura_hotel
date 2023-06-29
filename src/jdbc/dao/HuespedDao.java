package jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huesped;
import jdbc.modelo.Reserva;
import util.FormatoFecha;

public class HuespedDao {

	private Connection connection;

	public HuespedDao(Connection connection) {
		this.connection = connection;
	}

	public List<Huesped> listar() {
		List<Huesped> listaHuesped = new ArrayList<Huesped>();
		String querry = "SELECT H.*,R.* FROM HUESPEDES AS H "
				+ "INNER JOIN RESERVAS R ON R.id=H.idReserva";
		try {
			final PreparedStatement statement = connection.prepareStatement(querry);
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						Integer id = resultSet.getInt(1);
						String nombre = resultSet.getString(2);
						String apellido = resultSet.getString(3);
						LocalDate fechaNacimiento = (resultSet.getDate(4)).toLocalDate();
						String nacionalidad = resultSet.getString(5);
						String telefono = resultSet.getString(6);
						Reserva reserva = new Reserva();
						reserva.setId(1);
						//reserva.setFechaEntrada(null);
						Huesped huesped = new Huesped(
						id,nombre,apellido,fechaNacimiento,nacionalidad,telefono,
						reserva);
						listaHuesped.add(huesped);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
		return listaHuesped;
	}

	public void add(Huesped huesped) {
		String query = "INSERT INTO HUESPEDES(nombre,apellido,fechaNacimiento,nacionalidad,telefono,idReserva)"
				+ "VALUES(?,?,?,?,?,?)";
		try {
			final PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			try(statement){

				/*statement.setTimestamp(1, Timestamp.valueOf(reserva.getFechaEntrada()));
				statement.setTimestamp(2, Timestamp.valueOf(reserva.getFechaSalida()));
				statement.setBigDecimal(3, reserva.getValor());
				statement.setString(4, reserva.getFormaPago());*/
				statement.setString(1,huesped.getNombre());
				statement.setString(2,huesped.getApellido());
				statement.setDate(3, Date.valueOf(huesped.getFechaNacimiento()));
				statement.setString(4,huesped.getNacionalidad());
				statement.setString(5,huesped.getTelefono());
				statement.setInt(6, huesped.getIdReserva().getId());
				statement.execute();
				final ResultSet resultSet =statement.getGeneratedKeys();
				try(resultSet){
					if(resultSet.next()) {
						//reserva.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
				
	}

	public List<Huesped> listarPorNombre(String nombre) {
		List<Huesped> listaHuesped = new ArrayList<>();
		String query = "SELECT * FROM HUESPEDES WHERE NOMBRE LIKE ?";
		try {
			final PreparedStatement statement = connection.prepareStatement(query);
			try(statement){
				statement.setString(1, "%"+nombre+"%");
				try(ResultSet resultSet = statement.executeQuery()){
					while(resultSet.next()) {
						Integer id = resultSet.getInt(1);
						nombre = resultSet.getString(2);
						String apellido = resultSet.getString(3);
						//LocalDate fecha = (resultSet.getDate(4)).toLocalDate();
						LocalDate fecha = resultSet.getObject(4,LocalDate.class);
						String nacionalidad = resultSet.getString(5);
						String telefono = resultSet.getString(6);
						Integer idReserva =  resultSet.getInt(7);
						Reserva reserva = new Reserva();
						reserva.setId(idReserva);
						Huesped huesped = new Huesped(id,nombre,apellido,fecha,nacionalidad,telefono,reserva);
						listaHuesped.add(huesped);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return listaHuesped;
	}
}
