package mvc.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
			@RequestParam(value = "nota_text") String texto,
			@RequestParam(value = "traduz") boolean traduz,
			@RequestParam(value = "linguaDesejada") String lingua) throws SQLException, IOException{
		
		if(traduz) {
			String[] traducoes = traduzNota(titulo,texto,lingua);
			titulo = traducoes[0];
			texto = traducoes[1];
		}
		
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
			@RequestParam(value = "nota_text") String texto,
			@RequestParam(value = "traduz") boolean traduz,
			@RequestParam(value = "linguaDesejada") String lingua) throws SQLException, IOException {
		
		if(traduz) {
			String[] traducoes = traduzNota(titulo,texto,lingua);
			titulo = traducoes[0];
			texto = traducoes[1];
		}
		
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
	
	
	private String[] traduzNota(String titulo,String texto,String lingua) throws IOException {
		String[] traducoes = new String[2];
		
		String key = "trnsl.1.1.20181016T112405Z.62a99ca2ad75022a.5abd853bd07c6d313dfc6a47c2b98ab259567470";
		String tituloTranslate = titulo;
		String notaTranslate = texto;
		String lang = lingua;
		String urlTitulo = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+key+"&text="+tituloTranslate+"&lang="+lang+"&format=plain&options=1";
		String urlTexto = "https://translate.yandex.net/api/v1.5/tr.json/translate?key="+key+"&text="+notaTranslate+"&lang="+lang+"&format=plain&options=1";
		
		urlTitulo = urlTitulo.replace(" ","%20");
		urlTexto = urlTexto.replace(" ","%20");
		
		URL url = new URL(urlTitulo);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		BufferedReader in = new BufferedReader(
			  new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
		con.disconnect();
	
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(content.toString());
			JSONObject jsonObject = (JSONObject) obj;
			JSONArray lista = (JSONArray) jsonObject.get("text");
			String valor = (String) lista.get(0);
			
			titulo = valor;

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		url = new URL(urlTexto);
		con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		
		in = new BufferedReader(
				  new InputStreamReader(con.getInputStream()));
				inputLine="";
				content = new StringBuffer();
				while ((inputLine = in.readLine()) != null) {
				    content.append(inputLine);
				}
				in.close();
			con.disconnect();
			
			parser = new JSONParser();
			try {
				obj = parser.parse(content.toString());
				JSONObject jsonObject = (JSONObject) obj;
				JSONArray lista = (JSONArray) jsonObject.get("text");
				String valor = (String) lista.get(0);
				
				texto = valor;

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			traducoes[0] = titulo;
			traducoes[1] = texto;
			return traducoes;
	}
	
}
