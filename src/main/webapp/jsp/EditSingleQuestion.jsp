<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muokkaa Kysymystä</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/style.css" rel="stylesheet">

<script>
function insertValue(valueToBeInserted) {
	document.querySelector('input[name="tunniste"]').value = valueToBeInserted	;
}
</script>
<script>
function validateForm() {
	var userName = document.getElementById("kysymys").value;
	var tunniste = document.getElementById("tunniste").value;
	if (userName == "") {
		alert("Kysymys ei voi olla tyhjä!");
		return false;
	}
	if (tunniste == "") {
		alert("Tunniste ei voi olla tyhjä! Valitse joku vaihtoehdoista tai kirjoita omasi.")
		return false;
	}
}
</script>

</head>
<body>
<div class="card" id="div1">
		<h2 class="card-header text-center text-light" id="header1">Muokkaa kysymyksen tietoja</h2>
		<form action="UpdateQuestion" method="post">
		<table  class="table table-hover table-striped">
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
			<td><textarea id="kysymys" name="kysymys"  class="form-control" rows="3">${requestScope.kysymys.getKysymys()}</textarea><br>
			</td>
		</tr>
				 <tr>
		 	<td>
			Tunniste: (kirjoita uusi tai valitse napilla)
			</td>
			<td><input type="text" id="tunniste" name="tunniste" value="${requestScope.kysymys.getTunniste()}"  class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
					<c:forEach var="tag" items="${requestScope.tunnisteet }">
						<button type="button" value="${tag }" onClick="insertValue(this.value)"  class="btn btn-info btn-sm" id="partybutton">${tag }</button>
					</c:forEach>
			</td>
		</tr>
		<tr>
		
		
		</table>


		</table>
		<tr>
			<td>
			<input type='submit' name='update' value='Tallenna tiedot' class="btn btn-outline-success">
			</td>
			<td><a href='/editQuestions?tag=all'  class="btn btn-outline-danger">Hylkää muutokset ja palaa kysymysten ylläpitosivulle</a></td>
		</tr> 
		</form>
			
		<p>
		
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>