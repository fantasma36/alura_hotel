package jdbc.modelo;

public class Usuario {
	private Integer id;
	private String login;
	private String password;
	public Usuario() {
		
	}
	public Usuario(Integer id,String login,String password) {
		this.id=id;
		this.login=login;
		this.password=password;
	}
	public Integer getId() {
		return this.id;
	}
	public void setLogin(String login) {
		this.login=login;
	}
	public String getLogin() {
		return this.login;
	}
	public void setPassword(String password) {
		this.password=password;
	}
	public String getPassword() {
		return this.password;
	}
}
