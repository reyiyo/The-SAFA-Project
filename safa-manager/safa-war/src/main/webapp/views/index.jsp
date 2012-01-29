<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<body>

	<h1>OpenID Sample Home Page</h1>

	<h1>En este jsp es donde habría que levantar el js de Pablo</h1>
	
	<p>Welcome!</p>

	<h3>Technical Information</h3>
	<p>
		Your principal object is....:
		<%=request.getUserPrincipal()%>
	</p>
	<p>
		<a href="../../j_spring_security_logout">Logout</a>
	</p>
</body>
</html>
