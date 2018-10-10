package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;

import mvc.model.DAO;
import mvc.model.User;

@Controller
public class LoginController {
	@RequestMapping("/")
	public String execute_login() {
		return "redirect:login";
	}
	
	
	

//	@RequestMapping("login")
	@RequestMapping(value={"login"})
	public String execute() {
		return "index";
	}


	@RequestMapping("efetuaLogin")
	public String efetuaLogin(HttpSession session, @RequestParam(value = "login") String login,
			@RequestParam(value = "password") String password) throws SQLException {

		User user = new User();
		user.setLogin(login);
		user.setPassword(password);

		DAO dao = new DAO();
		user = dao.validateUser(user);
		if (user.getId() != null) {
			System.out.println("usuarioLogado");
			session.setAttribute("usuarioLogado", user.getLogin());
			session.setAttribute("idLogado", user.getId());
			session.setAttribute("adminLogado", user.getAdm());
			return "redirect:InicialNota";

		}
		return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

}
