<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Consulter un compte</title>
</head>
<body>
	<!-- Entete  -->
	<header class="container-fluid header">
	<!-- 		Lien qui redirige vers la page accueil connecté ou non connectée -->
		<c:choose>
			<c:when test="${!isConnected }">
				<a href="<%=request.getContextPath()%>/ServletAccueil"><img src="../img/logo.jpeg"  alt="logo" class="photo-objet" /></a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/ServletAccueilConnecte"><img src="../img/logo.jpeg"  alt="logo" class="photo-objet" /></a>						
			</c:otherwise>
		</c:choose>
		<h1>ENI-Encheres</h1>
	</header>

	<!-- Corps  -->
	<section class="container main-container">
		<form action="post">
			<div class="container">
				<h1 class="mb-5 text-center">Profil de ${pseudo}</h1>
				<div class="row justify-content-start bloc-text-input">
					<div class="col-lg-6 col-sm-12">
						<!-- Bloc Pseudo  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="pseudo" class="label">Pseudo : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${pseudo}</p>
							</div>
						</div>
						<!-- Bloc Nom  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="nom" class="label">Nom : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${nom}</p>
							</div>
						</div>
						<!-- Bloc Prénom  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="prenom" class="label">Prénom : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${prenom}</p>
							</div>
						</div>
						<!-- Bloc Email  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="email" class="label">Email : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${email}</p>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-sm-12">
						<!-- Bloc Telephone  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="telephone" class="label">Téléphone : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${telephone}</p>
							</div>
						</div>

						<!-- Bloc Rue  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="rue" class="label">Rue : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${rue}</p>
							</div>
						</div>
						<!-- Bloc Code Postal  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="code_postal" class="label">Code Postal : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${codePostal}</p>
							</div>
						</div>
						<!-- Bloc Ville  -->
						<div class="row justify-content-center bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="ville" class="label">Ville : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p class="saisie">${ville}</p>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</section>
</body>
</html>