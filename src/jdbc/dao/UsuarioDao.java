package jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import jdbc.modelo.Huesped;
import jdbc.modelo.Usuario;

public class UsuarioDao {

	private Connection connection;

	public UsuarioDao(Connection connection) {
		this.connection = connection;
	}

	public List<Usuario> listar() {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		String querry = "SELECT * FROM USUARIOS";
		try {
			final PreparedStatement statement = connection.prepareStatement(querry);
			try (statement) {
				final ResultSet resultSet = statement.executeQuery();
				try (resultSet) {
					while (resultSet.next()) {
						Integer id = resultSet.getInt(1);
						String login = resultSet.getString(2);
						String password = resultSet.getString(3);
						Usuario usuario = new Usuario(id, login, password);
						listaUsuario.add(usuario);
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return listaUsuario;
	}

	public Usuario login(String user, String password) {
		Usuario usuario=null;
		String query = "SELECT * FROM USUARIOS WHERE LOGIN =? AND PASSWORD =?";
		try {
			PreparedStatement statement = this.connection.prepareStatement(query);
			statement.setString(1,user);
			statement.setString(2,password);
			final ResultSet resulSet=statement.executeQuery();
			try(resulSet){
				if(resulSet.next()) {
					Integer id=resulSet.getInt(1);
					String login=resulSet.getString(2);
					String clave=resulSet.getString(3);
					usuario = new Usuario(id,login,clave);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return usuario;
	}


}
