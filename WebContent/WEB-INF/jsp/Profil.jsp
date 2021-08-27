<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<style><%@ include file="/style.css"%></style>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato" />
<title>Consulter un compte</title>
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
			<div class="col">		
			</div>	
		
		</div>
	</header>

	<!-- Corps  -->
	<section class="container main-container">
		<form action="post">
			<div class="container">
				<div class="bloc-titre">
					<h1>ENI Encheres</h1> 
					<h2>Achetez, Vendez, Partagez...</h2>
					<p class="rectangle"></p>
				</div>	
				<h3 class="row justify-content-center">Mon Profil</h3>
				<div class="row justify-content-start bloc-text-input">
					<div class="col-lg-6 col-sm-12 col-xs-12">
						<!-- Bloc Pseudo  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="pseudo" class="label">Pseudo : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Mon pseudo</p>
							</div>
						</div>
						<!-- Bloc Nom  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="nom" class="label">Nom : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Mon nom</p>
							</div>
						</div>
						<!-- Bloc Prénom  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="prenom" class="label">Prénom : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Mon prénom</p>
							</div>
						</div>
						<!-- Bloc Email  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="email" class="label">Email : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Mon email</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-sm-12 col-xs-12">
						<!-- Bloc Telephone  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="telephone" class="label">Telephone : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Mon téléphone</p>
							</div>
						</div>

						<!-- Bloc Rue  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="rue" class="label">Rue : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Ma rue</p>
							</div>
						</div>
						<!-- Bloc Code Postal  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="code_postal" class="label">Code Postal : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Mon code postal</p>
							</div>
						</div>
						<!-- Bloc Ville  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-sm-4 col-xs-12">
								<label for="ville" class="label">Ville : </label>
							</div>
							<div class="col-sm-3 col-xs-12">
								<p class="saisie">Ma Ville</p>
							</div>
						</div>
					</div>
				</div>

				<!-- Bloc Bouttons  -->
				<div class="container">
					<!-- Bouton Supprimer  -->
					<div class="col-sm-4 col-xs-4">
						<button href="#" class="btn btn-primary btn-compte">
							Supprimer</button>
					</div>
				</div>
			</div>
		</form>
	</section>
</body>
</html>