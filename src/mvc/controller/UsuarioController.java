package mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsuarioController {

	
	@RequestMapping("InicialUsuario")
	public String InicialNota() {
	return "home";
}

}
