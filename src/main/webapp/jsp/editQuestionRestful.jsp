<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Kysymys"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add question</title>
</head>
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
	height: 510px;
	margin: auto;
	margin-top: 100px;
}
</style>
<script>
	function insertValue(valueToBeInserted) {
		document.querySelector('input[name="tunniste"]').value = valueToBeInserted	;
	}
	</script>
<script>
function addQuestion(form) {
	let question = new Object();
	question.kysymys = form.kysymys.value;
	let jsonQuestion = JSON.stringify(question);
	
	let xmlhttp = new XMLHttpRequest();
	xmlhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200) {
			if (this.responseText == "ok") {
				document.getElementById("result").innerHTML = "Lisäys onnistui!";
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


<body class="container-fluid">
<div class="card" id="div1">
<h2 class="card-header text-center text-light bg-primary">Lisää kysymys tietokantaan</h2>
<form>
	<table>
		<tr>
		<td>Kysymys:</td><td><textarea id="kysymys" name="kysymys" rows="5" cols="30"></textarea></td>
		</tr>
		<tr>
		<td>Tunniste:</td><td> <input type='text' name='tunniste' value=''><br>
		<c:forEach var="tag" items="${requestScope.tunnisteet }">
			<button type="button" value="${tag }" onClick="insertValue(this.value)" >${tag }</button>
		</c:forEach>
		</td>
		</tr>
		<tr>
		<td colspan="2"><input type='button' name='ok' value='OK' onclick='addQuestion(this.form);'></td>
		</tr>
		<tr><td colspan="2" id="result">Tähän tulee ilmoitus, jos tietokantaan lisääminen onnistui!</td>
		</tr>
	</table>
</form>
<a href="../index.html">Takaisin etusivulle</a>
</div>
</body>
</html>