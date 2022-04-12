<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="data.Ehdokas"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muokkaa ehdokkaan tietoja</title>
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
	height: 600px;
	margin: auto;
	margin-top: 100px;
}
label,
esittely{
	display:inline-block;
  	vertical-align:middle;
}
</style>
</head>
<body>
<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Muokkaa ehdokkaan tietoja</h2>
		<form action="Update" method="post">
		<table>
				<tr>
			<td>
			Ehdokkaan ID 
			</td><td>
			 <input type ="text" name="id" value="${requestScope.ehdokas.getId()}" readonly><br>
			 </td>
		 </tr>
		<tr>
			<td>
			Etunimi:
			</td><td>
			 <input type ="text" name="etunimi" value="${requestScope.ehdokas.getEtunimi()}"><br>
			 </td>
		 </tr>
		 <tr>
		 	<td>
			Sukunimi: 
			</td>
			<td><input type ="text" name="sukunimi" value="${requestScope.ehdokas.getSukunimi()}"><br>
			</td>
		</tr>
		<tr>
		 	<td>
			Puolue:
			</td>
			<td> 
			<input type ="text" name="puolue" value="${requestScope.ehdokas.getPuolue()}"><br>
			</td>
		</tr>
		<tr>
			<td>
			Esittely: 
			</td>
			<td><textarea id="esittely" name="esittely" rows="15" cols="35">${requestScope.ehdokas.getEsittely()}</textarea><br>
			</td>
		</tr>
		<tr>
			<td>
			Ehdokasnumero: 
			</td>
			<td>
			<input type ="number" name="ehdokasnumero" value="${requestScope.ehdokas.getEhdokasNumero()}"><br>
			</td>
		</tr>
		</table>


		</table>
		<tr>
			<td>
			<input type='submit' name='update' value='Tallenna tiedot'>
			</td>
		</tr> 
		</form>
			
		<p><a href='index.html'>Palaa ehdokkaiden ylläpitosivulle tallentamatta muutoksia</a>
		
	</div>
</body>
</html>