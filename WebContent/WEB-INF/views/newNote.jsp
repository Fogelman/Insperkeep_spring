<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form"
prefix="form" %><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Criar</title>
</head>
<body>

	<div class="container">

<br>


<form action="CriaNota" method="post">
		
			<div class="form-group">
			  <label for="title_id"><b>Título da nota *</b></label>
			  <input type="text" class="form-control" id="title_id" name="titulo" required>
			</div>
		
			<!--Título:
			<input type="text" name="title"><br><br>-->
			<div class="form-group">
			<label for="descricao"><b>Texto da nota *</b></label>
			<textarea name="nota_text" class="textarea_1 form-control" rows="5" cols="111" id ="descricao" required></textarea><br><br>
			</div>
			<a href="home.jsp"><button class="btn btn-outline-success btn-lg btn-block" type="submit" value="Adicionar">Gravar</button></a>
	
		</form>
		
			<form action="RedirectUser">
			<input type="hidden" name="person_id" value="2" />
						
						<button class="btn btn-outline-danger btn-lg btn-block" type="submit">Cancelar</button>
					</form>

	</div>

</body>
</html>