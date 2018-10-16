<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">


<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">



<link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
<%-- <link href="${pageContext.request.contextPath}/css/reset.css" rel="stylesheet"> --%>
		


<title>Insperkeep</title>
</head>
<body>

	
	<div class="container">
			
			
		<jsp:include page="header.jsp" />

<br>


<form action="CriaNota">
			<!-- TODO: Passar os ids da nota e da pessoa aqui -->

			
			
			<div class="form-group">
			  <label for="title_id"><b>Título da nota *</b></label>
			 	
			 				 	
			 
			  <input type="text" class="form-control" id="title_id" name="titulo" required >
			</div>
		
			<!--Título:
			<input type="text" name="title"><br><br>-->
			<div class="form-group">
			<label for="descricao"><b>Texto da nota *</b></label>
			<textarea class = "textarea_1 form-control" rows="5" cols="111" name="nota_text" id ="descricao" required></textarea><br><br>
			</div>
			
			<div class="form-group">
				<label><b>Traduzir texto</b></label>
				<input name="traduz" type="checkbox">
			</div>
			
			<div class="form-group">
			<label><b>Para:</b></label>
			<select name="linguaDesejada">
				<option value="es">Espanhol</option>
				<option value="he">Hebraico</option>
				<option value="en" selected>Inglês</option>
			    <option value="pt">Português</option>
			    <option value="yi">Yiddish</option>
			  </select>
			</div>
			
			<button class="btn btn-outline-success btn-lg btn-block" type="submit">Gravar</button>

		</form>
		
		
						<form action="InicialNota">
						
						<button class="btn btn-outline-danger btn-lg btn-block" type="submit">Cancelar</button>
					</form>
					

	</div>

</body>
</html>