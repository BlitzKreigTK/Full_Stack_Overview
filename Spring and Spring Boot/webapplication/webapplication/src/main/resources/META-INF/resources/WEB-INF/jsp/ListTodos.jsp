<%@ include file="common/header.jspf" %>
<body>
	<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<div><h1>Welcome to To Do List ${name}! Your todos are</h1></div>
		<table class="table">
			<thead>
				<tr>
					<th>UserName</th>
					<th>Description</th>
					<th>Target Date</th>
					<th>Done</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${todo}" var="todos">
					<tr>
						<td>${todos.username}</td>
						<td>${todos.description}</td>
						<td>${todos.targetDate}</td>
						<td>${todos.done}</td>
						<td><a href="del-Todos?id=${todos.id}" class="btn btn-warning">Delete</a></td>
						<td><a href="update-Todos?id=${todos.id}" class="btn btn-success">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<a href="addNewTodo" class="btn btn-success">Add New Todo</a>
	</div>
<%@ include file="common/footer.jspf" %>