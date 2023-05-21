<%@ include file="common/header.jspf" %>
<body>
	<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<div><h1>Logged in user: ${name}!</h1></div>
		<div><a href="/listTodos">Manage</a> your Todos!</div>
	</div>
<%@ include file="common/footer.jspf" %>