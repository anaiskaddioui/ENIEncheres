<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet"
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
			integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
			crossorigin="anonymous" />
		<style><%@ include file="/style.css"%></style>
		<link rel="stylesheet"
			href="https://fonts.googleapis.com/css?family=Lato" />
				<title>Utilisateur inconnu</title>
	</head>
	<body>
		
		<!-- Entete  -->
		<header class="container-fluid header">
	
		<!-- 		Lien qui redirige vers la page accueil connecté ou non connectée -->
			<div class="row">
				<div class="col-9">
				<c:choose>
				<c:when test="${!isConnected }">
					<a href="${pageContext.request.contextPath }/"><img src="https://drive.google.com/thumbnail?id=1SzlCtIcpedH_9Ry8XQwGZShmojPhAvPc" alt="un objet" class="photo-logo img-fluid" /></a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath }/ServletAccueilConnecte"><img src="https://drive.google.com/thumbnail?id=1SzlCtIcpedH_9Ry8XQwGZShmojPhAvPc" alt="un objet" class="photo-logo img-fluid" /></a>						
				</c:otherwise>
			</c:choose>		
				
				</div>
				
			</div>
		</header>
	
		<!-- Corps  -->
		<section class="container main-container">
			<div class="bloc-titre">
				<h1>ENI Encheres</h1> 
				<h2>Achetez, Vendez, Partagez...</h2>
				<p class="rectangle"></p>
			</div>
			
			<div class="w-100 m-auto">
				<h3 class="text-center mt-4 pt-4">Vous n'êtes pas enregistré chez nous</h3>
			
				<p class="pt-5 fw-bold">Souhaitez-vous : 
					<ul>
						<li><a href="${pageContext.request.contextPath }/ServletCreationCompte">Créer un compte</a> ?</li>
						<li><a href="${pageContext.request.contextPath }/ServletAccueil">Retourner à la page d'accueil</a> ?</li>
					</ul>
				</p>
			</div>	
		</section>
	</body>
</html>