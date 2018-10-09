package mvc.model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAO {

	private Connection connection = null;

	public DAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/insper_keep", "root",
					"Z)L{e8wQstxcagg3=jJac6}?qzQ69CjU");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<Nota> getLista(String i) throws SQLException {
		List<Nota> notas = new ArrayList<Nota>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM nota WHERE person_id = ?");
		stmt.setString(1, i);

		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Nota note = new Nota();
			note.setNoteId(rs.getInt("nota_id"));
			note.setTitle(rs.getString("titulo"));                                                                                                                                                                                                                                                                                      
			note.setNote(rs.getString("nota_text"));

			// TODO checar como pegar esse id de person que depende do person_id da tabela user
			note.setPersonId(rs.getInt("person_id"));

			notas.add(note);
		}
		rs.close();
		stmt.close();
		return notas;
	}
	

	public Nota getSpecificNote(Integer note_id, Integer person_id) throws SQLException {
		Nota note = new Nota();
		
		String sql = "SELECT * FROM nota WHERE nota_id=? AND person_id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, note_id);
		stmt.setInt(2, person_id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			note.setNoteId(rs.getInt("nota_id"));
			note.setTitle(rs.getString("titulo"));
			note.setNote(rs.getString("nota_text"));
			note.setPersonId(rs.getInt("person_id"));
		}
		rs.close();
		stmt.close();
		return note;
	}
	
	public List<User> getUsers() throws SQLException {
		List<User> users = new ArrayList<User>();
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("user_id"));
			user.setLogin(rs.getString("login"));
			user.setPassword(rs.getString("password"));
			user.setNome(rs.getString("nome_completo"));
			user.setEmail(rs.getString("email"));
			user.setAdm(rs.getInt("administrador"));
			
			users.add(user);
		}
		rs.close();
		stmt.close();
		return users;

	}
	
	public User getSpecificUser(Integer person_id) throws SQLException {
		User user = new User();
		
		String sql = "SELECT * FROM user WHERE user_id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, person_id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			user.setAdm(rs.getInt("administrador"));
			user.setEmail(rs.getString("email"));
			user.setNome(rs.getString("nome_completo"));
			user.setPassword(rs.getString("password"));
			user.setLogin(rs.getString("login"));
			user.setId(rs.getInt("user_id"));
		}
		rs.close();
		stmt.close();
		return user;
	}

	public void adicionaNota(Nota nota) throws SQLException {
		String sql = "INSERT INTO nota" + "(titulo,nota_text,person_id) values(?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, nota.getTitle());
		stmt.setString(2, nota.getNote());
		stmt.setInt(3, nota.getPersonId());
		stmt.execute();
		stmt.close();
	}
	
	public void removeNota(Integer id, Integer personId) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("DELETE FROM nota WHERE nota_id=? AND person_id=?");
		stmt.setLong(1, id);
		stmt.setLong(2, personId);
		stmt.execute();
		stmt.close();
	}
	
	public void alteraNota(Nota nota) throws SQLException {
		String sql = "UPDATE nota SET " + "titulo=?, nota_text=? WHERE nota_id=? AND person_id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, nota.getTitle());
		stmt.setString(2, nota.getNote());
		stmt.setInt(3, nota.getNoteId());
		stmt.setInt(4, nota.getPersonId());
		stmt.execute();
		stmt.close();
	}
	
	public void addUser(User user) throws SQLException {
		String sql = "INSERT INTO user" + "(login,password,nome_completo,email,administrador) values(?,?,?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, user.getLogin());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getNome());
		stmt.setString(4, user.getEmail());
		stmt.setInt(5, user.getAdm());
		stmt.execute();
		stmt.close();
	}
	
	public void deleteUser(User user) throws SQLException{
		String sql = "DELETE FROM nota WHERE " + "person_id = ?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, user.getId());
		stmt.execute();
		
		sql = "DELETE FROM user WHERE " + "user_id = ?";
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, user.getId());
		stmt.execute();
		stmt.close();
		
	}
	
	public void alteraUser(User user) throws SQLException {
		String sql = "UPDATE user SET " + "login=?, password=?, nome_completo=?, email=?, administrador=? WHERE user_id=?";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, user.getLogin());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getNome());
		stmt.setString(4, user.getEmail());
		stmt.setInt(5, user.getAdm());
		stmt.setInt(6, user.getId());
		stmt.execute();
		stmt.close();
	}
	
	public User validateUser(User user) throws SQLException {
		PreparedStatement stmt = connection.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ? ");
		stmt.setString(1, user.getLogin());
		stmt.setString(2, user.getPassword());
		ResultSet rs = stmt.executeQuery();
		User login = new User();
		if (rs.next()) {

			
			login.setId(rs.getInt("user_id"));
			login.setLogin(rs.getString("login"));
			login.setPassword(rs.getString("password"));
			login.setNome(rs.getString("nome_completo"));
			login.setEmail(rs.getString("email"));
			login.setAdm(rs.getInt("administrador"));
		

			stmt.close();
			return login;
		}
		login.setId(null);
		stmt.close();
		return login;
		
	}
	
	
	public Boolean verifyAdm(String person_id) throws SQLException {

		
		String sql = "SELECT * FROM user WHERE administrador = 1 AND user_id = " + person_id;
		PreparedStatement stmt = connection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			rs.close();
			stmt.close();
			return true;
		}
		else {
			rs.close();
			stmt.close();
			return false;
		}
	}
	

	public void close() throws SQLException {
		// TODO Auto-generated method stub
		connection.close();
	}

}
