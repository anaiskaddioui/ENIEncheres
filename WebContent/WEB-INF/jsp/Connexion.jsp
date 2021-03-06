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
<title>Connexion</title>
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

		<div class="bloc-titre">
			<h1>ENI Encheres</h1> 
			<h2>Achetez, Vendez, Partagez...</h2>
			<p class="rectangle"></p>
		</div>	
		<h3 class="row justify-content-center">Connexion</h3>
	<!-- Corps  -->
	<section class="container main-container formulaire-connexion">
	<h6 style="color: red;">${erreurPseudo}</h6>
	<h6 style="color: red;">${erreurPassword}</h6>
		<form method="post" action="./ServletConnexion">
			<!-- Bloc Identifiant  -->
			<div class="container">
				<div class="row justify-content-start bloc-text-input">
					<div class="col-sm-4 col-xs-12">
						<label for="utilisateur_identifiant" class="label">Identifiant
							: </label>
					</div>
					<div class="col-sm-8 col-xs-12">
						<input type="text" name="utilisateur_identifiant"
							id="utilisateur_identifiant" />
					</div>
				</div>
				<!-- Bloc Mot de passe  -->
				<div class="row justify-content-start bloc-text-input">
					<div class="col-sm-4 col-xs-12">
						<label for="utilisateur_motDePasse" class="label">Mot
							de passe : </label>
					</div>
					<div class="col-sm-8 col-xs-12">
						<input type="password" name="utilisateur_motDePasse"
							id="utilisateur_motDePasse" />
					</div>
				</div>
			</div>
			<!-- Bloc connexion -->
			<div class="container">
				<div class="row justify-content-center">
					<div class="col-sm- col-xs-4">
						<!-- Bouton connexion  -->
						<input type="submit" name="connexion" value="Connexion"
							class="btn btn-primary" href="<%=response.encodeURL(request.getContextPath()+"/jsp/Connexion/ServletManipulationSession")%>"/>
					</div>
					<div class="col-sm-6 col-xs-4">
						<!-- Checkbox se souvenir / lien mdp oublié  -->
						<input type="checkbox" id="connexion-auto" name="connexion-auto"
							checked /> <label for="connexion-auto">Se
							souvenir de moi</label>
					</div>
				</div>
				<div class="row justify-content-start col-sm-12 col-xs-12">
					<!-- <a href="#">Mot de passe oublié</a>-->
				</div>
				<!-- Bouton creer un compte  -->
				<div class="row justify-content-center col-sm-6 col-xs-4">
					<a href="<c:url value="/ServletCreationCompte"/>" class="btn btn-primary btn-compte">Créer
						un compte</a>
				</div>
			</div>
		</form>
	</section>
</body>
</html>