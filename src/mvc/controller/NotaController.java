package mvc.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mvc.model.Nota;
import mvc.model.DAO;

@Controller
public class NotaController {
	
	
	private DAO dao;

	@RequestMapping("InicialNota")
		public String InicialNota(HttpSession session, Model model) throws SQLException {
		dao = new DAO();
		Integer personId = (Integer) session.getAttribute("idLogado");
		ArrayList<Nota>  notas =  (ArrayList<Nota>) dao.getLista(personId);
		model.addAttribute("notas", notas);
		
		return "home";
	
	
	
	}
	
//	redirect:InicialNota
	
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
		return "redirect:InicialNota";
	}
	
	@RequestMapping("DeletaNota")
	public String deleta(HttpSession session,@RequestParam(value = "nota_id") Integer notaId) throws SQLException {
		DAO dao = new DAO();
		Integer personId = (Integer) session.getAttribute("idLogado");
	
		dao.removeNota(notaId,personId);
		dao.close();
		return "redirect:InicialNota";
	}
	
	@RequestMapping("EditaNota")
	public String edita(HttpSession session,@RequestParam(value = "nota_id") Integer noteId,
			@RequestParam(value = "titulo") String titulo,
			@RequestParam(value = "nota_text") String texto) throws SQLException {
		Nota nota = new Nota();
		DAO dao = new DAO();
		Integer personId = (Integer) session.getAttribute("idLogado");
		
		
		nota.setNoteId(noteId);
		nota.setPersonId(personId);
		nota.setNote(texto);
		nota.setTitle(titulo);
		
		dao.alteraNota(nota);
		dao.close();
		
		return "redirect:InicialNota";
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
