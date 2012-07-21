<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	var defaultOrder = {
		"selectedTags" : [ {
			"id" : 1,
			"tagType" : {
				"id" : 1,
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
			"id" : 1,
			"tagType" : {
				"id" : 1,
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
			"id" : 2,
			"tagDataType" : "STRING",
			"tagName" : "Facultad"
		},
		"selectedTags" : [ {
			"id" : 1,
			"tagType" : {
				"id" : 1,
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

	<p>
		INDEX :

		<c:choose>
			<c:when test="${securityLevel eq 'Protected'}">
Protected Area.
</c:when>
			<c:when test="${securityLevel eq 'Public'}">

Public Area. 
<p>
					<a href="/protected">Attempt to access</a> a protected resource
				</p>
			</c:when>
		</c:choose>
	</p>

	<authz:authorize access="!hasRole('ROLE_USER')">
		<p>
			You are not logged in. &nbsp;<a href="/oauthlogin.jsp" />Login</a>
		</p>
	</authz:authorize>
	<authz:authorize access="hasRole('ROLE_USER')">
						<p><b>You are logged in locally as <c:out value="${userName}" /></b>. &nbsp;<a
			href="/logout">Logout</a></p>

		<p>Technical Information</p>
		<p>
			Your principal object is:<br />
			<%=request.getUserPrincipal()%>
		</p>

		<p>
			<button onclick="testContenSearch(defaultOrder)">Test
				Default Content Search!</button>
			<button onclick="testContenSearch(titleOrder)">Test Simple
				Order Content Search!</button>
		</p>
		<p>
			<button onclick="testTagFilter(filterTagRequest)">Test Tag
				Filter!</button>
		</p>

		</p>

	</authz:authorize>

	<authz:authorize access="hasRole('ROLE_USER_TWITTER')">
		<p>You are connected with Twitter.</p>
	</authz:authorize>
	<authz:authorize access="hasRole('ROLE_USER_FACEBOOK')">
		<p>You are connected with Facebook.</p>
	</authz:authorize>


	<authz:authorize
		access="hasRole('ROLE_USER') and !hasRole('ROLE_USER_FACEBOOK')">
		<p>
			<a href="/oauthconnect.jsp">Connect</a> your account with Facebook
		</p>
	</authz:authorize>
	<authz:authorize
		access="hasRole('ROLE_USER') and !hasRole('ROLE_USER_TWITTER')">
		<p>
			<a href="/oauthconnect.jsp">Connect</a> your account with Twitter
		</p>
	</authz:authorize>


</body>
</html>