<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'></c:url>" />
<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'></c:url>" />
<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.css'></c:url>" />
<script src="<c:url value='resources/js/jquery.min.js'></c:url>"></script>
<script src="<c:url value='resources/js/popper.min.js'></c:url>"></script>
<script src="<c:url value='resources/js/bootstrap.min.js'></c:url>"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.css" />
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<c:url value="/home" var="home"></c:url>
		<a class="navbar-brand" href="${home }"><i class="fa fa-home "></i>Department</a>
		<ul class="navbar-nav nav mr-auto">
			
				<li class="nav-item"><c:url value="/departments"
						var="register"></c:url> <a href="${register }" class="nav-link"><i
						class="fa fa-plus"></i>Departments</a></li>
				<li class="nav-item"><c:url value="/teachers"
						var="register"></c:url> <a href="${register }" class="nav-link"><i
						class="fa fa-plus"></i>Teachers</a></li>
			

			<li class="nav-item"><c:url value="views/persons.jsp" var="student"></c:url>
				<a href="view/persons.jsp" class="nav-link"><i class="fa fa-users"></i>Student</a></li>
		
		
				<li class="nav-item"><c:url value="/courses" var="courses"></c:url>
					<a href="${courses }" class="nav-link"><i class="fa fa-book"></i>Courses</a></li>
				
			

		</ul>
		

	</div>
</nav>
</body>
</html>