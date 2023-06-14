package jdbc.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource dataSource;
	public ConnectionFactory() {
		ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
		String url ="jdbc:mysql://localhost/alura_hotel?useTimeZone=true&serverTimeZone=UTC";
		pooledDataSource.setJdbcUrl(url);
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("");
		this.dataSource=pooledDataSource;	
	}
	public Connection recuperaConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
