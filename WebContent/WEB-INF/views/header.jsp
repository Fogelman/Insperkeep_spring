
<link rel="stylesheet" type="text/css" href="css/header.css" />
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<a class="navbar-brand" href="#">InsperKeep</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarText" aria-controls="navbarText"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav mr-auto">

		</ul>




		<%
			Integer admin = (Integer) session.getAttribute("adminLogado");
			if (admin == 1) {
		%>





		<form action="InicialNota">
			<!-- <input type="hidden" name="id" value="" /> -->
			<button class="btn" type="submit">Administrar Usuários</button>
		</form>

		<%
			} 
			/* else {
				System.out.println("Usuário não definido");
				System.out.println(admin);

			}
 */		
 
 %>


	
		<form action="InicialNota">
			<!-- <input type="hidden" name="id" value="" /> -->
			<button class="btn" type="submit">Gerenciar Notas</button>
		</form>


				<a href="logout"><span
			class="navbar-text buttons_nav"> Log Out </span></a>



	</div>
</nav>

