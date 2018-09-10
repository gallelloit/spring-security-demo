<!-- 

Very simple home page simulation with welcome message, information about the authenticated
user and a link to two restricted pages. These two links only visible for those users with
the appropriate roles. 

-->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>GallelloIT Company Home Page</title>
</head>
<body>
	<h2>GallelloIT Company Home Page</h2>
	<hr>
	Welcome to the GallelloIT  company home page!
	<p>
		User: <security:authentication property="principal.username"/>
	<br><br>
		Roles(s): <security:authentication property="principal.authorities"/>
	</p>
	<hr>
	
		<!-- Add a link to point to leaders... this is for the managers -->
		<security:authorize access="hasRole('MANAGER')">
			<p>
				<a href="${pageContext.request.contextPath}/leaders">Leadership Meeting</a>
			</p>
		</security:authorize>
		<security:authorize access="hasRole('ADMIN')">
			<p>
				<a href="${pageContext.request.contextPath}/systems">System Meeting</a>
			</p>
		</security:authorize>
	
	<hr>
	<form:form action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout"/>
	</form:form>
</body>
</html>