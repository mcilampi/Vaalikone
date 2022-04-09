<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>  
<%@ page import="data.Ehdokas"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit or delete candidates</title>
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
	height: 510px;
	margin: auto;
	margin-top: 100px;
}
</style>
</head>
<body class="container-fluid">
	<div class="card" id="div1">
		<h2 class="card-header text-center text-light bg-primary">Edit candidates</h2>
		<% ArrayList<Ehdokas> ehdokkaat = (ArrayList<Ehdokas>)request.getAttribute("ehdokasLista");
		
		for (Ehdokas ehdokas: ehdokkaat) {
			out.printLn(ehdokas.getEtunimi() + " " + ehdokas.getSukunimi() + " " + "<a href='/delete?id=" + String.valueOf(ehdokas.getId()) + "'>Delete</a> <a href='/edit?id=" + String.valueOf(ehdokas.getId()) + "'>Edit</a>");
		}
		
		%>
		
		
	</div>
</body>
</html>
