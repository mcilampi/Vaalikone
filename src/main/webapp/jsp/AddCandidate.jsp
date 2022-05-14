<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Kysymys"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lisää ehdokas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/style.css" rel="stylesheet">
<script>
	function insertValue(valueToBeInserted) {
		document.querySelector('input[name="puolue"]').value = valueToBeInserted	;
	}
	</script>


<script>
function getList() {
	let list = [
	    <c:forEach items="${ehdokasnumerot}" var="ehdokasnumero">
	      '<c:out value="${ehdokasnumero}" />',  
	    </c:forEach>
	 ];
	 console.log(list);
	 return list;
}


function validateForm() {
	let givenNumber = document.getElementById("ehdokasnumero").value;
	let list = getList();
	let a = document.getElementById("etunimi").value;
	let b = document.getElementById("sukunimi").value;
	let c = document.getElementById("puolue").value;
	let d = document.getElementById("ehdokasnumero").value;
	let e = document.getElementById("esittely").value;
	let f = document.getElementById("kayttajanimi").value;
	let g = document.getElementById("salasana").value;

	if (list.includes(givenNumber)) {
		alert("Ehdokasnumero on jo olemassa!");
		return false;
	}
	
    if (a == null || a == "", b == null || b == "", c == null || c == "", d == null || d == "", e == null || e == "", f == null || f == "", g == null || g == "") {
        alert("Täytä kaikki kentät!");
        return false;
      }
    
    if (Number.isInteger(+d) === false) {
    	alert("Ehdokasnumeron on oltava kokonaisluku!");
    	return false;
    }
}
</script>

</head>
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light" id="header1">Lisää ehdokas tietokantaan</h2>
		<form class="form" action="addCandidate" method="POST" onSubmit="return validateForm()">
			<table class="table table-hover table-striped">
				<tr>
					<td>Etunimi:</td>
					<td><input type="text" name="etunimi" id="etunimi" class="form-control" aria-label="Username" aria-describedby="basic-addon1"></td>
				</tr>
				<tr>
					<td>Sukunimi:</td>
					<td><input type="text" name="sukunimi" id="sukunimi" class="form-control" aria-label="Username" aria-describedby="basic-addon1"></td>
				</tr>
				<tr>
					<td>Puolue:</td>
					<td><input type="text" name="puolue" id="puolue" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
					<p>
					<c:forEach var="puolue" items="${requestScope.puoluelista }">
						<button type="button" value="${puolue }" onClick="insertValue(this.value)"  class="btn btn-info" id="partybutton">${puolue }</button>
					</c:forEach>
					
					</td>
				</tr>
				<tr>
					<td>Esittely:</td>
					<td><textarea id="esittely" name="esittely" class="form-control" id="exampleFormControlTextarea1" rows="6"></textarea></td>
				</tr>
				<tr>
					<td>Ehdokasnumero:</td>
					<td><input type="text" name="ehdokasnumero" id="ehdokasnumero" class="form-control" aria-label="Username" aria-describedby="basic-addon1"></td>
				</tr>
				<tr>
					<td>Käyttäjänimi:</td>
					<td><input type="text" name="kayttajanimi" id="kayttajanimi" class="form-control" aria-label="Username" aria-describedby="basic-addon1"></td>
				</tr>
				<tr>
					<td>Salasana:</td>
					<td><input type="password" name="salasana" id="salasana" class="form-control" aria-label="Username" aria-describedby="basic-addon1"></td>
				</tr>
				<tr>
					<td><input type="submit" value="Rekisteröi ehdokas"
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
