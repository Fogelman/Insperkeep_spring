package mvc.model;

public class User {
	
	private Integer id;
	private String login;
	private String password;
	private String nome;
	private String email;
	private Integer adm;

	public Integer getId() {
		return this.id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAdm() {
		return this.adm;
	}

	public void setAdm(Integer adm) {
		this.adm = adm;
	}

	public void setId(Integer user_id) {
		this.id = user_id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	
	}
	
	
}
