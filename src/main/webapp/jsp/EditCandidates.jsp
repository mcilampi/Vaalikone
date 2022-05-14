<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Ehdokas"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muokkaa tai poista ehdokkaita</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/style.css" rel="stylesheet">
</head>
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light"  id="header1">Muokkaa ehdokkaita</h2>
		<table class="table table-hover table-striped">
			<thead>
				<tr><th scope="col"><a href="/editCandidates?sorter=2" class="text-decoration-none">Numero</a></th><th scope="col"><a href="/editCandidates?sorter=1" class="text-decoration-none">Nimi</a></th><th scope="col"><a href="/editCandidates?sorter=3" class="text-decoration-none">Puolue</a></th><th scope="col"></th><th  scope="col"></th></tr>
			</thead>
			<c:set var="counter" value="1" scope="page"/>
			<c:forEach var="ehdokas" items="${requestScope.ehdokasLista}">
				<tr><td>${ehdokas.getEhdokasNumero()}.</td><td> ${ehdokas.getSukunimi()} ${ehdokas.getEtunimi()}</td> <td>${ehdokas.getPuolue()}</td><td> <a href='/edit?id=${ehdokas.getId()}' class="btn btn-warning btn-sm">Muokkaa</a> </td> <td><a href='/delete?id=${ehdokas.getId()}' onclick="return confirm('Oletko varma että haluat poistaa ehdokkaan?')" class="btn btn-danger btn-sm">Poista</a></td></tr>
				<c:set var="counter" value="${counter + 1}" scope="page"/>
			</c:forEach>
		
		</table>		
		<p><a href='index.html'  class="text-decoration-none">Palaa ylläpitosivulle</a>
		
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
