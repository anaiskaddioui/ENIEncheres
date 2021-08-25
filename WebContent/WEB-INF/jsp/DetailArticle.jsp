<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<title>Nouvelle Vente</title>
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
	<form method="post" action="${pageContext.request.contextPath }/ServletEncherir">
		<h1 class="row justify-content-center">${nom_article }</h1>
		<input type="hidden" name="idArticle" value="${idArticle }"/>
		<div class="row">
			<!-- Colonne avec image -->
			<div class="col-xs-sm-12 col-lg-4 text-center">
				<img src="img/objet.jpeg" alt="un objet" class="photo-objet" />
			</div>
			<!-- Colonne avec Formulaire -->
			<div class="col">
					<!-- Input Description -->
					<div class="row mt-2">
						<div class="col-xs-sm-12 col-lg-3">
							<label for="descrition">Description : </label>
						</div>
						<div class="col">
							<p>${description }</p>
						</div>
					</div>
					<!-- Select Categorie -->
					<div class="row mt-2">
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="categories">Catégorie :</label>
						</div>
						<div class="col">
							<p>${categorie }</p>
						</div>
					</div>
					<!-- Meilleure offre -->
					<div class="row mt-2">
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="best-offer">Meilleure offre :</label>
						</div>
						<div class="col"> 
							<p>
								${prix_vente } pts par <a href="<c:url value="/ServletConsultationCompte?pseudo=Uzana"/>">Uzana</a>
							</p>
						</div>
					</div>
					<!-- input mise à prix -->
					<div class="row mt-2">
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="prix">Mise à prix :</label>
						</div>
						<div class="col">
							<p>${prix_initial } points</p>
						</div>
					</div>
					<!-- Input Dates -->
					<div class="row mt-2">
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="date-fin">Fin de l'enchère :</label>
						</div>
						<div class="col">
							<p>${date_fin_encheres }</p>
						</div>
					</div>
					<!--Bloc Retrait -->
					<div class="row mt-2">
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="retrait">Retrait : </label>
						</div>
						<div class="col">
							<p>${rue_retrait }</p>
							<p>${codePostal_retrait } ${ville_retrait }</p>
						</div>
					</div>
					<!-- Input Vendeur -->
					<div class="row mt-2">
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="vendeur">Vendeur :</label>
						</div>
						<div class="col">
							<p>${vendeur }</p>
						</div>
					</div>
					<!-- input proposition -->
					<div class="row mt-2">
					<p style="color: red;">${erreurCredit }</p>
						<div class="col-6 col-sm-6 col-lg-3">
							<label for="proposition">Ma proposition :</label>
						</div>
						<div class="col-4 col-sm-3 col-lg-2">
							<input type="number" id="proposition" name="proposition" min="${prix_vente + 1}"
								max="10000" class="form-control" value="${prix_vente }"/>
						</div>
						<!-- Bouton Enchérir -->
						<div class="col text-center">
							<input type="submit" name="encherir" value="Enchérir"
								class="btn btn-primary btn-compte" />
						</div>
					</div>
				<!-- input Modification et suppression -->
				<c:if test="${idVendeur} == ${sessionScope.idUtilisateur}">
					<div class="row mt-2">
				<p style="color: red;">${erreurSuppression }</p>
						<!-- Bouton Modifier  -->
						<div class="col">
							<a href="<c:url value="/ServletNouvelleVenteAnnulation?modification=modifier&idArticle=${idArticle}"/>" class="btn btn-primary btn-compte">
								Modifier la vente</a>
						</div>
						<!-- Bouton Supprimer -->
						<div class="col">
							<a href="<c:url value="/ServletNouvelleVenteAnnulation?modification=supprimer&idArticle=${idArticle}"/>" class="btn btn-primary btn-compte">
								Supprimer la vente</a>
						</div>
					</div>
				</c:if>
				</div>
			</div>
		</form>
	</section>
</body>
</html>