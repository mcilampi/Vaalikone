<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muokkaa tai poista kysymyksiä</title>
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
	width: 700px;
	
	margin: auto;
	margin-top: 100px;
}
</style>
</head>
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Muokkaa kysymyksiä</h2>
		<table>
			<!--  <tr><td>Numero</td><td>Kysymys</td><td>Tunniste <a href="/editQuestions?tag=all">(näytä kaikki)</a></td><td></td><td></td></tr>-->
			<tr><td>Numero</td><td>Kysymys</td><td>
			<label>Tunniste: </label><select name="forma" onchange="location = this.value;">
			 	<option value="/editQuestions?tag=all">Valitse</option>
			 	<c:forEach var="tunniste" items="${requestScope.tunnisteet}">
			 		<option value="/editQuestions?tag=${tunniste }">${tunniste }</option>
			 	</c:forEach>
			 	<option value="/editQuestions?tag=all">Kaikki</option>
			</select>
			
			</td><td></td><td></td></tr>
			<c:set var="counter" value="1" scope="page"/>
			<c:forEach var="kysymys" items="${requestScope.kysymykset}">
				<tr><td>${kysymys.getId()}.</td><td> <div style="word-wrap: break-word;">${kysymys.getKysymys()}</div></td><td><a href="/editQuestions?tag=${kysymys.getTunniste() }">${kysymys.getTunniste() }</a></td>  <td><a href='/deleteQ?id=${kysymys.getId()}' onclick="return confirm('Oletko varma että haluat poistaa kysymyksen?')">Poista</a></td><td> <a href='/editQuestion?id=${kysymys.getId()}'>Muokkaa</a> </td></tr>
				<c:set var="counter" value="${counter + 1}" scope="page"/>
			</c:forEach>
		
		</table>		
		<p><a href='index.html'>Palaa ylläpitosivulle</a>
		
	</div>
</body>
</html>
