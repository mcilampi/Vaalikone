<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="data.Ehdokas"%>  

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Muokkaa ehdokkaan tietoja</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<link href="/css/style.css" rel="stylesheet">

</head>
<body>
<div class="card" id="div1">
		<h2 class="card-header text-center text-light" id="header1">Muokkaa ehdokkaan tietoja</h2>
		<form action="Update" method="post">
		<table class="table table-hover table-striped">
				<tr>
			<td>
			Ehdokkaan ID 
			</td><td>
			 <input type ="text" name="id" value="${requestScope.ehdokas.getId()}" readonly class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
			 </td>
		 </tr>
		<tr>
			<td>
			Etunimi:
			</td><td>
			 <input type ="text" name="etunimi" value="${requestScope.ehdokas.getEtunimi()}" class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
			 </td>
		 </tr>
		 <tr>
		 	<td>
			Sukunimi: 
			</td>
			<td><input type ="text" name="sukunimi" value="${requestScope.ehdokas.getSukunimi()}" class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
			</td>
		</tr>
		<tr>
		 	<td>
			Puolue:
			</td>
			<td> 
			<input type ="text" name="puolue" value="${requestScope.ehdokas.getPuolue()}" class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
			</td>
		</tr>
		<tr>
			<td>
			Esittely: 
			</td>
			<td><textarea id="esittely" name="esittely"  class="form-control" rows="6">${requestScope.ehdokas.getEsittely()}</textarea><br>
			</td>
		</tr>
		<tr>
			<td>
			Ehdokasnumero: 
			</td>
			<td>
			<input type ="number" name="ehdokasnumero" value="${requestScope.ehdokas.getEhdokasNumero()}"  class="form-control" aria-label="Username" aria-describedby="basic-addon1"><br>
			</td>
		</tr>
		</table>


		</table>
		<tr>
			<td>
			<input type='submit' name='update' value='Tallenna tiedot' class="btn btn-outline-success"> 
			</td>
			<td>
			<a href='/editCandidates?sorter=2' class="btn btn-outline-danger">Hylkää muutokset ja palaa etusivulle</a>
		</tr> 
		</form>
			
		<p>
		
	</div>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>