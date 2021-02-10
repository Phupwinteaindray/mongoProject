<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Person</title>
<title>${title }</title>
<link rel="stylesheet" href="<c:url value='/resources/css/bootstrap.min.css'></c:url>" />
<link rel="stylesheet" href="<c:url value='/resources/css/font-awesome.min.css'></c:url>" />
<script src="<c:url value='resources/js/jquery.min.js'></c:url>"></script>
<script src="<c:url value='resources/js/popper.min.js'></c:url>"></script>
<script src="<c:url value='resources/js/bootstrap.min.js'></c:url>"></script>
<link rel="stylesheet" href="../resources/css/bootstrap.min.css" />
<link rel="stylesheet" href="../resources/css/font-awesome.min.css" />
<script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/js/jquery.min.js"></script>
<script type="text/javascript" src="../resources/js/popper.min.js"></script>
</head>
<body>
<div class="container">
	<%-- Person Add/Edit logic --%>
	<c:if test="${requestScope.error ne null}">
		<strong style="color: red;"><c:out
				value="${requestScope.error}"></c:out></strong>
	</c:if>
	<c:if test="${requestScope.success ne null}">
		<strong style="color: green;"><c:out
				value="${requestScope.success}"></c:out></strong>
	</c:if>
	<c:url value="/addPerson" var="addURL"></c:url>
	<c:url value="/editPerson" var="editURL"></c:url>

	<%-- Edit Request --%>
	<div class="row">
	<div class="col-6">
	<c:if test="${requestScope.person ne null}">
		<form action='<c:out value="${editURL}"></c:out>' method="post" class="form">
			<div class="form-group">
				<label for="id">ID</label>
				<input type="text" value="${requestScope.person.id}" name="id" class="form-control">
			</div>
			<div class="form-group">
				<label for="name">Name</label>
				<input type="text" value="${requestScope.person.name}" name="name" class="form-control">
			</div>
			<div class="form-group">
				 <label for="name">Phone Number</label>
				<input type="tel" value="${requestScope.person.country}" name="country" class="form-control">
			</div>
			<div class="form-group">
				<label for="name">Email</label>
				<input type="email" value="${requestScope.person.email}" name="email" class="form-control">
			</div>
			<div class="form-group">
				<label for="name">Roll Number</label>
				<input type="text" value="${requestScope.person.rollNumber}" name="rollNumber" class="form-control">
			</div>
			<button class="btn btn-outline-danger" type="submit">Edit Student</button>
		</form>
	</c:if>

	<%-- Add Request --%>
	<c:if test="${requestScope.person eq null}">
		<form action='<c:out value="${addURL}"></c:out>' method="post" class="form">
			
			<div class="form-group">
				<label for="name">Name</label>
				<input type="text" name="name" class="form-control">
			</div>
			<div class="form-group">
				 <label for="name">Phone Number</label>
				<input type="tel" name="country" class="form-control">
			</div>
			<div class="form-group">
				 <label for="name">Email</label>
				<input type="email" name="email" class="form-control">
			</div>
			<div class="form-group">
				 <label for="name">Roll Number</label>
				<input type="text" name="rollNumber" class="form-control">
			</div>
			<button class="btn btn-outline-danger" type="submit">Add Student</button>
		</form>
	</c:if>
	</div>
	</div>
	<c:url value="/student-upload" var="upload"></c:url>
			<form id="uploadForm" action="${upload }" method="post" enctype="multipart/form-data" class="d-none">
				<input  id="uploadFile" type="file" name="file"  />
			</form>
		<form class="form-inline mt-4 mb-2">
			<div class="form-group">
				<button id="uploadButton" type="button" class="btn btn-danger"><i class="fa fa-upload"></i>Upload</button>
			</div>
		</form> 
	<%-- Persons List Logic --%>
	<c:if test="${not empty requestScope.persons}">
		<table class="table mt-3">
			<thead>
				<tr>
					<th>ID</th>
					<th>Student Name</th>
					<th>Student Phone</th>
					<th>Student Email</th>
					<th>Student Roll Number</th>
					<th></th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				
				<c:forEach items="${requestScope.persons}" var="person">
					<c:url value="/editPerson" var="editURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<c:url value="/deletePerson" var="deleteURL">
						<c:param name="id" value="${person.id}"></c:param>
					</c:url>
					<tr>
						<td><c:out value="${person.id}"></c:out></td>
						<td><c:out value="${person.name}"></c:out></td>
						<td><c:out value="${person.country}"></c:out></td>
						<td><c:out value="${person.email}"></c:out></td>
						<td><c:out value="${person.rollNumber}"></c:out></td>
						<td> <a href="${editURL }" class="btn btn-link"><i
								class="fa fa-pencil">Edit</i> </a></td>
						<td>
						<td> <a href="${deleteURL }" class="btn btn-link"><i
								class="fa fa-trash">Delete</i> </a></td>
						<td>
						<%-- <td><a
							href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
						<td><a
							href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td> --%>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</c:if>
</div>
<script>
	$(()=>{
		$('#uploadButton').click(()=>$('#uploadFile').click())
		$('#uploadFile').change(()=>$('#uploadForm').submit())
	})
	
	</script>
</body>
</html>