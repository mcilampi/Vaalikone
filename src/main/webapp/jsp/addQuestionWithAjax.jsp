<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lisää kysymys</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/style.css" rel="stylesheet">

<script>
	function insertValue(valueToBeInserted) {
		document.querySelector('input[name="tunniste"]').value = valueToBeInserted	;
	}
	</script>
<script>
function addQuestion(form) {
	let question = new Object();
	question.kysymys = form.kysymys.value;
	question.tunniste = form.tunniste.value;
	let jsonQuestion = JSON.stringify(question);
	
	let xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "ok") {
				document.getElementById("result").innerHTML = "Kysymys lisätty tietokantaan onnistuneesti!";
					form.kysymys.value = "";
					form.tunniste.value = "";
			}
		}
	};
	xmlhttp.open("PUT", "/rest/questionService/addQuestionRestful", true);
	xmlhttp.setRequestHeader("Content-type", "application/json");
	xmlhttp.send(jsonQuestion);	
	console.log("dodii");	
	console.log(jsonQuestion);
	console.log(this.responseText);
}
</script>

</head>



<body class="container-fluid">
<div class="card" id="div1">
<h2 class="card-header text-center text-light " id="header1">Lisää kysymys tietokantaan</h2>
<form>
	<table  class="table table-hover table-striped">
		<tr>
		<td>Kysymys:</td><td><textarea id="kysymys" name="kysymys" class="form-control" rows="3"></textarea></td>
		</tr>
		<tr>
		<td>Tunniste:</td><td> <input type='text' name='tunniste' value=''  class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
		<c:forEach var="tag" items="${requestScope.tunnisteet }">
			<button type="button" value="${tag }" onClick="insertValue(this.value)"  class="btn btn-info btn-sm" id="partybutton">${tag }</button>
		</c:forEach>
		</td>
		</tr>
		<tr>
		<td colspan="2"><input type='button' name='ok' value='Tallenna kysymys' onclick='addQuestion(this.form);' class="btn btn-outline-success">
		<input type="reset" value="Tyhjennä lomake"
						class="btn btn-outline-danger"></td>
		<td></td>
		</tr>
		<tr><td colspan="2" id="result">
		</td>
		
		</tr>
	</table>
</form>
<a href="../index.html" class="text-decoration-none">Takaisin etusivulle</a>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>