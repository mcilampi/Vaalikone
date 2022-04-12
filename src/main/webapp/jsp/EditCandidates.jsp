<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Ehdokas"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit or delete candidates</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<style>
#div1 {
	width: 500px;
	height: 510px;
	margin: auto;
	margin-top: 100px;
}
</style>
</head>
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Edit candidates</h2>
		<table>
		
			<c:set var="counter" value="1" scope="page"/>
			<c:forEach var="ehdokas" items="${requestScope.ehdokasLista}">
				<tr><td>${counter}.</td><td>${ehdokas.getEtunimi()} ${ehdokas.getSukunimi()}</td> <td><a href='/delete?id=${ehdokas.getId()}' onclick="return confirm('Are you sure?')">Poista</a></td><td> <a href='/edit?id=${ehdokas.getId()}'>Muokkaa</a> </td></tr>
				<c:set var="counter" value="${counter + 1}" scope="page"/>
			</c:forEach>
		
		</table>		
		<p><a href='index.html'>Palaa ylläpitosivulle</a>
		
	</div>
</body>
</html>
