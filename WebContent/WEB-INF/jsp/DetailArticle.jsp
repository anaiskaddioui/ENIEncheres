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
	<form method="post" action="${pageContext.request.contextPath }/ServletEncherir">
	<div class="row justify-content-center">
		<c:if test="${etat_vente == 'TE' }">
		<c:choose>
			<c:when test="${!empty idUserEnchere }">
				<c:if test="${idUserEnchere == sessionScope.idUser }">
					<h1 class="text-center" style="color : green;">Vous avez remporté la vente</h1>
				</c:if>
				<c:if test="${idUserEnchere != sessionScope.idUser }">
					<h1 class="text-center" style="color : green;">${pseudoEnchere} a remporté la vente</h1>
				</c:if>
			</c:when>
			<c:otherwise>
				<h1 class="text-center" style="color : #E9967A;">Personne n'a enchéri sur votre vente</h1>
			</c:otherwise>
		</c:choose>
		</c:if>
	</div>
		<h1 class="row justify-content-center">${nom_article }</h1>
		<input type="hidden" name="idArticle" value="${idArticle }"/>
		<div class="row">
			<!-- Colonne avec image -->
			<div class="col-xs-sm-12 col-lg-4 text-center">
				<img src="https://drive.google.com/thumbnail?id=1QPpB2Svw2r74TOlM0TBFi28N8umXBu1W" alt="un objet" class="photo-objet" />
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
								${prix_vente } pts par <a href="<c:url value="/ServletConsultationCompte?idUtilisateur=${idUserEnchere }"/>">${pseudoEnchere }</a>
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
					<c:if test="${etat_vente == 'EC' }">
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
					</c:if>
				<!-- input Modification et suppression -->
				<c:if test="${idVendeur == sessionScope.idUser}">
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