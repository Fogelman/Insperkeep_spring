package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.Nota;
import mvc.model.DAO;

@Controller
public class NotaController {
	@RequestMapping("/")
    public String execute() {
        System.out.println("Abrindo o criar notas");
        return "newNote";
    }
	@RequestMapping("CriaNota")
	public String adiciona(HttpSession session,@RequestParam(value = "titulo") String titulo,
			@RequestParam(value = "nota_text") String texto) throws SQLException{
		Nota nota = new Nota();
		DAO dao = new DAO();
		String personIdString = (String) session.getAttribute("idLogado");
		Integer personId = Integer.parseInt(personIdString);
		
		nota.setPersonId(personId);
		nota.setNote(texto);
		nota.setTitle(titulo);
		
		dao.adicionaNota(nota);
		dao.close();
		return "home";
	}
	
	@RequestMapping("DeletaNota")
	public String deleta(HttpSession session,@RequestParam(value = "nota_id") Integer notaId) throws SQLException {
		DAO dao = new DAO();
		String personIdString = (String) session.getAttribute("idLogado");
		Integer personId = Integer.parseInt(personIdString);
		
		
		dao.removeNota(notaId,personId);
		dao.close();
		return "home";
	}

}
