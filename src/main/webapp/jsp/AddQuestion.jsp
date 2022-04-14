<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
<title>Lisää kysymys tietokantaan</title>
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


<style>
#div1 {
	width: 500px;
	
	margin: auto;
	margin-top: 100px;
}
</style>
</head>
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Lisää kysymys tietokantaan</h2>
		<form class="form" action="addQuestion" method="POST" onSubmit="return validateForm()">
			<table class="table table-hover table-striped">
				<tr>
					<td>Kysymys:</td>

					<td><textarea id="kysymys" name="kysymys" rows="3" cols="30"></textarea></td>

				</tr>
				<tr>
					<td>Tunniste:<br>
					(valitse napilla tai kirjoita uusi)</td>
					<td><input type="text" name="tunniste" id="tunniste">
					<p>
					<c:forEach var="tag" items="${requestScope.tunnisteet }">
						<button type="button" value="${tag }" onClick="insertValue(this.value)" >${tag }</button>
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
			<p><a href='index.html'>Palaa ehdokkaiden ylläpitosivulle</a></p>
		</form>
	</div>

</body>
</html>