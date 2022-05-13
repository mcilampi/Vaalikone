<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="javax.ws.rs.client.*" %>  
<%@ page import="javax.ws.rs.client.Invocation.Builder" %> 
<%@ page import="javax.ws.rs.core.*" %>    
<%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Kysymykset</title>
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
	width: 700px;
	
	margin: auto;
	margin-top: 100px;
}
</style>
</head>
<body>
<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Lisää kysymys tietokantaan</h2>
<table>	
	<tr>
	<th>ID</th>
	<th>Kysymys</th>
	<th>Tunniste</th>
	<th>Poista</th>
	<th>Muokkaa</th>
	</tr>
	<c:forEach var="kysymys" items="${requestScope.kysymykset }">
	<tr>
	<td>${kysymys.getId()}</td>
	<td>${kysymys.getKysymys() }</td>
	<td>${kysymys.getTunniste() }</td>
	<td><a href='/rest/questionService/deleteQuestionRestful/${kysymys.id}' onclick="return confirm('Oletko varma että haluat poistaa kysymyksen?')">Poista</a></td>
	<td><a href='/rest/questionService/updateQuestionRestful/${kysymys.id}' >Muokkaa</a></td>
	<tr>

	</c:forEach>
</table>
<p><a href='/index.html'>Palaa ehdokkaiden ylläpitosivulle</a></p>
</div>
</body>
</html>

