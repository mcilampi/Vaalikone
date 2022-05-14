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
<title>Kysymyksen muokkaaminen</title>
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
		<h2 class="card-header text-center text-light bg-primary">Muokkaa kysymyksen tietoja</h2>
		<form action="UpdateQuestionRestful" method="post">
		<table>
				<tr>
		
			<tr>
			<td>
			Kysymyksen ID 
			</td><td>
			 <input type ="number" name="id" value="${requestScope.kysymys.getId()}" readonly><br>
			 </td>
		 </tr>
		 <tr>
		 	<td>
			Kysymys: 
			</td>
			<td><textarea id="kysymys" name="kysymys" rows="15" cols="35">${requestScope.kysymys.getKysymys()}</textarea><br>
			</td>
		</tr>
				 <tr>
		 	<td>
			Tunniste: (kirjoita uusi tai valitse napilla)
			</td>
			<td><input type="text" id="tunniste" name="tunniste" value="${requestScope.kysymys.getTunniste()}"><br>
					<c:forEach var="tag" items="${requestScope.tunnisteet }">
						<button type="button" value="${tag }" onClick="insertValue(this.value)" >${tag }</button>
					</c:forEach>
			</td>
		</tr>
		<tr>
		
		
		</table>


		</table>
		<tr>
			<td>
			<input type='submit' name='update' value='Tallenna tiedot'>
			</td>
		</tr> 
		</form>
			
		<p><a href='index.html'>Palaa kysymysten ylläpitosivulle</a>
		
	</div>
</body>
</html>