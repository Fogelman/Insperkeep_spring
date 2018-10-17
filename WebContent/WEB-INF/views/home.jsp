<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session = "true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link  rel="stylesheet" href="css/home.css" type="text/css" >
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!--  	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous" -->




<%@ page import="java.util.*,mvc.model.*"%>
<%List<Nota> notas = (ArrayList<Nota>) request.getAttribute("notas"); %>

<title>Home</title>


</head>
<body>

	<div class="container">	
	<jsp:include page="header.jsp" />

				<br>
				<form action="FormNovaNota">
				<button 
				class="btn btn-outline-success btn-lg btn-block" type="submit" >Adicionar
				nota</button>
					</form>
					

				
				
		<table class="table table-hover">

			<thead>

				<tr>

					<th scope="col" class="font-weight-bold">Titulo</th>
					<th scope="col" class="font-weight-bold">Nota</th>
					<th scope="col" class="font-weight-bold">Opções</th>

				</tr>
			</thead>
			<tbody>
<%

							for (Nota nota : notas) {
								String notaId = nota.getNoteId().toString();
				
								
				%>
				<tr>
					<td><%=nota.getTitle()%></td>
					<td class="max_width"><%=nota.getNote()%></td>
					
				<td>
					<form action="FormEditaNota">
						<input type="hidden" name="nota_id" value="<%=notaId%>" />
						
						<button class="btn btn-sm btn-primary btn-block" type="submit">Editar</button>
					</form> 
					
					
					
					<form action="DeletaNota">
						<input type="hidden" name="nota_id" value="<%=notaId%>" />
						<button class="btn btn-sm btn-danger	 btn-block" type="submit">Deletar</button>
					</form>
					
					</td>
				</tr>
				<%
					}

						

				%>




			</tbody>
		</table>
		<%String url = (String) session.getAttribute("gifUrl"); %>
		<img alt=GIPHY src="<%=url%>" class="image_gif">
		

	

	</div>
</body>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" ></script>
</html>