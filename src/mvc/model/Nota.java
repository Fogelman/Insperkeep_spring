package mvc.model;

public class Nota {
	private Integer nota_id;
	private String titulo;
	private String nota_text;
	private Integer person_id;

	public Integer getNoteId() {
		return this.nota_id;
	}

	public void setNoteId(Integer noteId) {
		this.nota_id = noteId;
	}

	public String getNote() {
		return this.nota_text;
	}

	public void setNote(String note) {
		this.nota_text = note;
	}

	public Integer getPersonId() {
		return this.person_id;
	}

	public void setPersonId(Integer personId) {
		this.person_id = personId;
	}

	public String getTitle() {
		return this.titulo;
	}

	public void setTitle(String title) {
		this.titulo = title;
	}
}
