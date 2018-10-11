<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
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
<link rel="stylesheet" href="css/home.css" type="text/css"
	rel="stylesheet">

<%@ page import="java.util.*,mvc.model.*"%>
<%
	List<User> users = (ArrayList<User>) request.getAttribute("users");
%>


<title>Home</title>
</head>
<body>


	<div class="container">


		<jsp:include page="header.jsp" />

		<%-- <h1><%= request.getAttribute("attributeKey") %></h1> --%>
		<br>

		<form action="FormNovoUsuario">
			<button class="btn btn-outline-success btn-lg btn-block"
				type="submit">Adicionar nota</button>
		</form>


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

				<%
					for (User user : users) {
						String personId = user.getId().toString();
				%>

				<tr>
					<td><%=user.getLogin()%></td>
					<td class="max_width"><%=user.getEmail()%></td>
					<td>


						<form action="FormEditaUsuario">
							<input type="hidden" name="person_id" value="<%=personId%>" /> <input
								type="hidden" name="id" value="" />

							<button class="btn btn-sm btn-primary btn-block" type="submit">Editar</button>
						</form>







						<form action="DeletaUser">
							<input type="hidden" name="person_id" value="<%=personId%>" />
							<button class="btn btn-sm btn-danger	 btn-block" type="submit">Deletar</button>
						</form>


					</td>
				</tr>

				<%
					}
				%>


			</tbody>
		</table>




	</div>
</body>

</html>