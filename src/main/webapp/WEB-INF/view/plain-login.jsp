<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- 

"Plain login page"

Ugly login page with support for just a simple "Wrong password" message.
It has support for csrf through the use of `<form:form></form:form>`

-->

<html>
<head>
	<title>My Custom Login Page</title>
	<style>
		.failed {
			color: red;
		}
	</style>
</head>
<body>
<h2>My Custom Login Page</h2>

	<!-- Check for login error -->
	<c:if test="${param.error != null}">
		<i class="failed">Sorry! Invalid username/password</i>
	</c:if>
	<form:form action="${pageContext.request.contextPath}/authenticateTheUser" method="POST">
	<p>
		User: <input type="text" name="username" />
	</p>
	<p>
		Password: <input type="password" name="password" />
	</p>
	<p>
		<input type="submit" value="Login" />
	</p>
	</form:form>
</body>
</html>