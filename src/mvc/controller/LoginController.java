package mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
			@RequestParam(value = "password") String password) throws SQLException, IOException, ParseException {

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
			session.setAttribute("gifUrl", gifUrl());
			
			return "redirect:InicialNota";
			
			
			
			
			
			
			
			

		}
		return "redirect:login";
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:login";
	}

	
	

	private String gifUrl () throws IOException, ParseException {
		URL url = new URL( "https://api.giphy.com/v1/gifs/translate?s=carro&weirdness=9&api_key=8vwIyzx663wh44O4bkZgP5ZdBjcfjuLo");
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");

//		int status = con.getResponseCode();

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			content.append(inputLine);
		}
		in.close();
		con.disconnect();
		System.out.println(content);
		
		

		JSONParser parser = new JSONParser();
		Object obj = parser.parse(content.toString());
		JSONObject jsonObject = (JSONObject) obj;
		
		
		jsonObject = (JSONObject) jsonObject.get("data");
        jsonObject = (JSONObject) jsonObject.get("images");
        jsonObject = (JSONObject) jsonObject.get("original");
        String gifUrl = (String) jsonObject.get("url");
        
//        System.out.println(gifUrl);
		return gifUrl;
	}
}
