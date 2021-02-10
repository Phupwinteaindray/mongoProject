<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WYTU</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'></c:url>" />
<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'></c:url>" />
<script src="<c:url value='resources/js/jquery.min.js'></c:url>"></script>
<script src="<c:url value='resources/js/popper.min.js'></c:url>"></script>
<script src="<c:url value='resources/js/bootstrap.min.js'></c:url>"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
</head>
<body>
<jsp:include page="/common/menu.jsp">
		<jsp:param value="WYTU" name="title" />
	</jsp:include>
</body>
</html>