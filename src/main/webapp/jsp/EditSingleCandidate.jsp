<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="data.Ehdokas"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit candidate information</title>
</head>
<body>
<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Edit candidate</h2>
		
		<ol>
			<c:forEach var="ehdokas" items="${requestScope.ehdokasLista}">
				<li>${ehdokas.getEtunimi()} ${ehdokas.getSukunimi()} <a href='/delete?id=${ehdokas.getId()}'>Delete</a> <a href='/edit?id=${ehdokas.getId()}'>Edit</a> 
			</c:forEach>
		</ol>		
		<p><a href='index.html'>Return to the admin page</a>
		
	</div>
</body>
</html>