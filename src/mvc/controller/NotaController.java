package mvc.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.Nota;
import mvc.model.DAO;

@Controller
public class NotaController {
	@RequestMapping("CriaNota")
	public String adiciona(HttpSession session,@RequestParam(value = "titulo") String titulo,
			@RequestParam(value = "nota_text") String texto) throws SQLException{
		Nota nota = new Nota();
		DAO dao = new DAO();
		Integer personId = (Integer) session.getAttribute("idLogado");
		
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
		Integer personId = (Integer) session.getAttribute("idLogado");
	
		dao.removeNota(notaId,personId);
		dao.close();
		return "home";
	}
	
	@RequestMapping("EditaNota")
	public String edita(HttpSession session,@RequestParam(value = "nota_id") Integer notaId,
			@RequestParam(value = "titulo") String titulo,
			@RequestParam(value = "nota_text") String texto) throws SQLException {
		Nota nota = new Nota();
		DAO dao = new DAO();
		Integer personId = (Integer) session.getAttribute("idLogado");
		
		nota.setPersonId(personId);
		nota.setNote(texto);
		nota.setTitle(titulo);
		
		dao.alteraNota(nota);
		dao.close();
		
		return "home";
	}
	
	@RequestMapping("FormNovaNota")
	public String callForm(){
		return "newNote";
	}
	
	@RequestMapping("FormEditaNota")
	public String callEdit(HttpSession session,@RequestParam(value = "nota_id") Integer notaId, ModelMap model) throws SQLException{
		DAO dao = new DAO();
		Integer personId = (Integer) session.getAttribute("idLogado");
		Nota nota = dao.getSpecificNote(notaId, personId);
		model.addAttribute("notaId", notaId);
		model.addAttribute("titulo", nota.getTitle());
		model.addAttribute("texto", nota.getNote());
		
		return "editNote";
	}
}
