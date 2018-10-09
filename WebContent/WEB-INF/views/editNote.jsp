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
		


<title>Editar nota</title>
</head>
<body>
	<%@ page import="java.util.*,br.edu.insper.*"%>
	
	<%@ page import="javax.servlet.ServletException"%>
	<%@ page import="javax.servlet.annotation.WebServlet"%>
	<%@ page import="javax.servlet.http.HttpServlet"%>
	<%@ page import="javax.servlet.http.HttpServletRequest"%>
	<%@ page import="javax.servlet.http.HttpServletResponse"%>
	
	<div class="container">
	
	
			<% 
			Integer id = Integer.parseInt(request.getParameter("nota_id"));
			String i = request.getParameter("person_id");
			Integer i_int = Integer.parseInt(i);%>
			
			
			
		<jsp:include page="header.jsp">
		
		<jsp:param name="id_header" value = "<%=i%>" />
		
		
		</jsp:include>

<br>


<form action="EditaNota">
			<!-- TODO: Passar os ids da nota e da pessoa aqui -->

			
			<input type="hidden" name="nota_id" value="<%=id%>" />
			<input type="hidden" name="i" value="<%=i_int%>" />
			
			<div class="form-group">
			  <label for="title_id"><b>Título da nota *</b></label>
			 	
			 	
			<%
					DAO dao = new DAO();
					Nota nota = dao.getSpecificNote(id,i_int);
				%>			 	
			 
			  <input type="text" class="form-control" id="title_id" name="title" required value="<%=nota.getTitle()%>">
			</div>
		
			<!--Título:
			<input type="text" name="title"><br><br>-->
			<div class="form-group">
			<label for="descricao"><b>Texto da nota *</b></label>
			<textarea class = "textarea_1 form-control" rows="5" cols="111" name="novaNota" id ="descricao" required><%=nota.getNote()%></textarea><br><br>
			</div>
			
			
			<%String link = "home.jsp?id="; 
			link+= i;%>
			<a href="home.jsp"><button class="btn btn-outline-success btn-lg btn-block" type="submit">Gravar</button></a>





<!-- 			<a href="home.jsp"><input type="submit" value="Enviar"></a>
			<a href="home.jsp"><input type="button" value="Cancelar"></a> -->
		</form>
		
		
						<form action="RedirectUser">
			<input type="hidden" name="id" value="<%=i%>" />
						
						<button class="btn btn-outline-danger btn-lg btn-block" type="submit">Cancelar</button>
					</form>
		

	</div>

</body>
</html>