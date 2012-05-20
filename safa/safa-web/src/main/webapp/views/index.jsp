<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	var defaultOrder = {
		"selectedTags" : [ {
			"tagId" : 1,
			"tagType" : {
				"tagDataType" : "STRING",
				"tagName" : "Universidad"
			},
			"value" : "UTN",
			"iconURL" : ""
		} ],
		"firstResult" : 0,
		"maxResults" : 10,
		"orderBy" : "default",
		"orderDirection" : "DESC"
	};
	
	var titleOrder = {
			"selectedTags" : [ {
				"tagId" : 1,
				"tagType" : {
					"tagDataType" : "STRING",
					"tagName" : "Universidad"
				},
				"value" : "UTN",
				"iconURL" : ""
			} ],
			"firstResult" : 0,
			"maxResults" : 10,
			"orderBy" : "title",
			"orderDirection" : "ASC"
		};
	
	var filterTagRequest = {
			"tagType" : {
				"tagDataType" : "STRING",
				"tagName" : "Facultad"
			},
			"selectedTags" : [ {
				"tagId" : 1,
				"tagType" : {
					"tagDataType" : "STRING",
					"tagName" : "Universidad"
				},
				"value" : "UTN",
				"iconURL" : ""
			} ],
			"value" : "FR"
		};
	
	function testTagFilter(filterRequest) {
		$.ajax({
			url : "../tags/filterTags",
			type : "POST",
			data : JSON.stringify(filterRequest),
			success : function(data) {
				alert(JSON.stringify(data));
				console.log(data);
				console.log(JSON.stringify(data));
			},
			dataType : "json",
			contentType : "application/json"
		});
	}

	function testContenSearch(searchRequest) {

		$.ajax({
			url : "../content/search",
			type : "POST",
			data : JSON.stringify(searchRequest),
			success : function(data) {
				alert(JSON.stringify(data));
				console.log(data);
			},
			dataType : "json",
			contentType : "application/json"
		});
	};
</script>
</head>
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
		<button onclick="testContenSearch(defaultOrder)">Test Default Content Search!</button>
		<button onclick="testContenSearch(titleOrder)">Test Simple Order Content Search!</button>
	</p>
	<p>
		<button onclick="testTagFilter(filterTagRequest)">Test Tag Filter!</button>
	</p>
	<p>
		<a href="../../j_spring_security_logout">Logout</a>
	</p>
</body>
</html>
