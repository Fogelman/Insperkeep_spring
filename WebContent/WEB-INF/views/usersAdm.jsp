<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session = "true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<!--  	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous" -->
<link href="${pageContext.request.contextPath}/css/home.css"
	rel="stylesheet">



<title>Home</title>
</head>
<body>
	<div class="container">
	<%
		String i = "1";
	%>


	<%
		try {
			i = request.getAttribute("id").toString();
		} catch (NullPointerException e) {
			
			/* i = "a"; */
		
		    String redirectURL = "index.jsp";
		    response.sendRedirect(redirectURL);


		}
	%> 


	<%
	 if (i != "a") {

		} %>
	
	
	
	<%String link_adicionar = "newUser.jsp?id=";
		link_adicionar += i;
	%>
	
	 	
		<jsp:include page="header.jsp">
		<jsp:param name="id_header" value = "<%=i%>" />
		</jsp:include>
		<%-- <h1><%= request.getAttribute("attributeKey") %></h1> --%>
		<br> <a href=<%=link_adicionar%>><button type="button"
				class="btn btn-outline-success btn-lg btn-block">Adicionar
				Usuários</button></a>
	
	
		<table class="table table-hover">

		<%-- <% if (${name} =! nulls) %> --%>
			<thead>

				<tr>

					<th scope="col" class="font-weight-bold">Username</th>
					<th scope="col" class="font-weight-bold">email</th>
					<th scope="col" class="font-weight-bold">Opções</th>

				</tr>
			</thead>
			<tbody>

				
				
				<tr>
					<td></td>
					<td></td>
					
					<td><a href= ><button class="btn btn-sm btn-primary btn-block" type="submit">Editar</button></a>
				
					<form action="DeletaUser">
						<input type="hidden" name="person_id" value="" />
						<input type="hidden" name="id" value=""/>
						
						<button class="btn btn-sm btn-danger	 btn-block" type="submit">Deletar</button>
					</form>
					
					<!-- <button class="btn btn-sm btn-danger	 btn-block" type="button">Deletar</button> --></td>
				</tr>


			</tbody>
		</table>


	

	</div>
</body>

</html>