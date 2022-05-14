<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
<title>Lisää kysymys tietokantaan</title>
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
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light"  id="header1">Lisää kysymys tietokantaan</h2>
		<form class="form" action="addQuestion" method="POST" onSubmit="return validateForm()">
			<table class="table table-hover table-striped">
				<tr>
					<td>Kysymys:</td>

					<td><textarea id="kysymys" name="kysymys"  class="form-control" rows="3"></textarea></td>

				</tr>
				<tr>
					<td>Tunniste:<br>
					(valitse napilla tai kirjoita uusi)</td>
					<td><input type="text" name="tunniste" id="tunniste" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
					<p>
					<c:forEach var="tag" items="${requestScope.tunnisteet }">
						<button type="button" value="${tag }" onClick="insertValue(this.value)" class="btn btn-info btn-sm" id="partybutton">${tag }</button>
					</c:forEach>
					
					</td>
				</tr>

				<tr>
					<td><input type="submit" value="Tallenna kysymys"
						class="btn btn-outline-success">
						</td>
						
					<td><input type="reset" value="Tyhjennä lomake"
						class="btn btn-outline-danger">
						</td>
						
				</tr>

				
			
			</table>
			<p><a href='index.html' class="text-decoration-none">Palaa ehdokkaiden ylläpitosivulle</a></p>
		</form>
	</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>