package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.DAO;
import mvc.model.Nota;
import mvc.model.User;

@Controller
public class UsuarioController {

	
	@RequestMapping("InicialUsuario")
	public String InicialNota() {
		return "usersAdm";
	}

	@RequestMapping("CriaUsuario")
	public String adiciona(@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String senha,
			@RequestParam(value = "nome_completo") String nomeCompleto,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "adm") Integer admin) throws SQLException{
		User user = new User();
		DAO dao = new DAO();
		
		user.setAdm(admin);
		user.setEmail(email);
		user.setNome(nomeCompleto);
		user.setPassword(senha);
		user.setLogin(login);
	
		dao.addUser(user);
		dao.close();
		return "redirect:InicialUsuario";
	}
	
	@RequestMapping("DeletaUser")
	public String deleta(@RequestParam(value = "person_id") String personId) throws SQLException{
		User user = new User();
		DAO dao = new DAO();
		
		user.setId(Integer.parseInt(personId));
		
		dao.deleteUser(user);
		dao.close();
		return "redirect:InicialUsuario";
	}
	
	@RequestMapping("EditaUsuario")
	public String edita(@RequestParam(value = "login") String login,
			@RequestParam(value = "password") String senha,
			@RequestParam(value = "nome_completo") String nomeCompleto,
			@RequestParam(value = "email") String email,
			@RequestParam(value = "adm") Integer admin) throws SQLException{
		User user = new User();
		DAO dao = new DAO();
		
		user.setAdm(admin);
		user.setEmail(email);
		user.setNome(nomeCompleto);
		user.setPassword(senha);
		user.setLogin(login);
	
		dao.alteraUser(user);
		dao.close();
		return "redirect:InicialUsuario";
	}
	
	@RequestMapping("FormNovoUsuario")
	public String callForm(){
		return "newUser";
	}
	
	@RequestMapping("FormEditaUsuario")
	public String callForm(@RequestParam(value = "person_id") Integer personId, ModelMap model) throws SQLException {
		DAO dao = new DAO();
		User user = dao.getSpecificUser(personId);
		model.addAttribute("login", user.getLogin());
		model.addAttribute("password", user.getPassword());
		model.addAttribute("nomeCompleto", user.getNome());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("admin", user.getAdm());
		
		return "editUser";
	}
	
}
