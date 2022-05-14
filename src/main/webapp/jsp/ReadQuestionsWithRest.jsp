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

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/style.css" rel="stylesheet">

</head>
<body>
<div class="card" id="div1">
		<h2 class="card-header text-center text-light"  id="header1">Muokkaa kysymyksiä</h2>
<a href='/index.html' class="text-decoration-none">Palaa ehdokkaiden ylläpitosivulle</a>
<table  class="table table-hover table-striped">	
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
	<td><a href='/rest/questionService/updateQuestionRestful/${kysymys.id}' class="btn btn-warning btn-sm">Muokkaa</a></td>
	<td><a href='/rest/questionService/deleteQuestionRestful/${kysymys.id}' onclick="return confirm('Oletko varma että haluat poistaa kysymyksen?')" class="btn btn-danger btn-sm">Poista</a></td>

	<tr>

	</c:forEach>
</table>
<p><a href='/index.html' class="text-decoration-none">Palaa ehdokkaiden ylläpitosivulle</a></p>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>

