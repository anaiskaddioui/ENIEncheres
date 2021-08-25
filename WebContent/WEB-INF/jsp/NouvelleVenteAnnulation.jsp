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
				<a href="<%=request.getContextPath()%>/ServletAccueil"><img src="img/logo.jpeg" alt="un objet" class="photo-logo" /></a>
			</c:when>
			<c:otherwise>
				<a href="<%=request.getContextPath()%>/ServletAccueilConnecte"><img src="img/logo.jpeg" alt="un objet" class="photo-logo" /></a>						
			</c:otherwise>
		</c:choose>
		
		<h1>ENI-Encheres</h1>
	</header>

	<!-- Corps  -->
	<section class="container main-container">
		<h1 class="row justify-content-center">Nouvelle Vente</h1>

		<div class="row">
			<!-- Colonne avec image -->
			<div class="col-xs-sm-12 col-lg-4 text-center">
				<img src="img/objet.jpeg" alt="un objet" class="photo-objet" />
			</div>
			<!-- Colonne avec Formulaire -->

			<div class="col">
				<form method="post" action="${pageContext.request.contextPath }/ServletNouvelleVenteAnnulation">
					<input type="hidden" name="idArticle" value="${idArticle }"/>
					<!-- Input Article -->
					<div class="row">
						<div class="col-sm-4 col-lg-3">
							<label for="article">Article : </label>
						</div>
						<div class="col-sm-8 col-lg-6">
							<input type="text" name="article" class="form-control"
								id="article" value="${nom_article }" />
						</div>
					</div>
					<!-- Input Description -->
					<div class="row mt-2">
						<div class="col-sm-4 col-lg-3">
							<label for="description">Description : </label>
						</div>
						<div class="col-sm-8 col-lg-6">
							<textarea name="description" id="description"
								class="form-control" rows="5">${description }</textarea>
						</div>
					</div>
					<!-- Select Categorie -->
					<div class="row mt-2">
						<div class="col-sm-4 col-lg-3">
							<label for="categorie">Catégorie :</label>
						</div>
						<div class="col-sm-8 col-lg-6">
							<select name="categorie" class="form-select">
								<c:choose>
									<c:when test="${categorie == 'informatique'}">
										<option value="">Toutes</option>
										<option value="informatique" selected>Informatique</option>
										<option value="ameublement">Ameublement</option>
										<option value="vetement">Vêtement</option>
										<option value="sport">Sport&Loisirs</option>
									</c:when>
									<c:when test="${categorie == 'ameublement'}">
										<option value="">Toutes</option>
										<option value="informatique">Informatique</option>
										<option value="ameublement" selected>Ameublement</option>
										<option value="vetement">Vêtement</option>
										<option value="sport">Sport&Loisirs</option>
									</c:when>
									<c:when test="${categorie == 'vetement'}">
										<option value="">Toutes</option>
										<option value="informatique">Informatique</option>
										<option value="ameublement">Ameublement</option>
										<option value="vetement" selected>Vêtement</option>
										<option value="sport">Sport&Loisirs</option>
									</c:when>
									<c:when test="${categorie == 'sport'}">
										<option value="">Toutes</option>
										<option value="informatique">Informatique</option>
										<option value="ameublement">Ameublement</option>
										<option value="vetement">Vêtement</option>
										<option value="sport" selected>Sport&Loisirs</option>
									</c:when>
									<c:otherwise>
										<option value="">Toutes</option>
										<option value="informatique">Informatique</option>
										<option value="ameublement">Ameublement</option>
										<option value="vetement">Vêtement</option>
										<option value="sport">Sport&Loisirs</option>
									</c:otherwise>
								</c:choose>
							</select>
						</div>
					</div>
					<!-- Bouton upload Photo -->
					<div class="row mt-2">
						<div class="col-sm-4 col-lg-3">
							<label for="upload-fichier">Photo de l'article : </label>
						</div>
						<div class="col-sm-8 col-lg-6">
							<input type="file" class="custom-file-input" id="upload-fichier"
								lang="fr" />
						</div>
					</div>
					<!-- input mise à prix -->
					<div class="row mt-2">
						<div class="col-sm-4 col-lg-3">
							<label for="prix">Mise à prix :</label>
						</div>
						<div class="col-sm-4 col-lg-6">
							<c:if test="${!empty venteCommencee}">
								<input type="number" id="prix" name="prix" min="0" max="10000"
								class="form-control" value="${prix_initial }" readonly="readonly"/>
							</c:if>
							<c:if test="${empty venteCommencee}">
								<input type="number" id="prix" name="prix" min="0" max="10000"
								class="form-control" value="${prix_initial }"/>
							</c:if>
						</div>
					</div>
					<!-- Input Dates -->
					<div class="row mt-2">
						<div class="col-sm-12 col-md-6 col-lg-6">
							<div>
								<c:if test="${!empty venteCommencee}">
									<label for="date-debut">Début de l'enchère :</label> <input
										type="date" id="date-debut" name="date-debut"
										class="form-control-sm" value="${date_debut_encheres}" readonly="readonly"/> <i
										class="fa fa-calendar"></i>
								</c:if>
								<c:if test="${empty venteCommencee}">
									<label for="date-debut">Début de l'enchère :</label> <input
										type="date" id="date-debut" name="date-debut"
										class="form-control-sm" value="${date_debut_encheres}"/> <i
										class="fa fa-calendar"></i>
								</c:if>
							</div>
						</div>
						<div class="col-sm-12 col-md-6 col-lg-6 mt-2">
							<div>
								<label for="date-fin">Fin de l'enchère :</label> <input
									type="date" id="date-fin" name="date-fin"
									class="form-control-sm" value="${date_fin_encheres }"/> <i
									class="fa fa-calendar"></i>
							</div>
						</div>
					</div>
					<!--Bloc Retrait -->
					<div class="form-group border w-75 rounded p-3 m-3">
						<h2 class="display-6">Retrait</h2>
						<!-- Input Rue -->
						<div class="row mt-2">
							<div class="col-sm-4 col-md-3 col-lg-4">
								<label for="rue">Rue : </label>
							</div>
							<div class="col-sm-8 col-md-9 col-lg-8">
								<input type="text" name="rue" class="form-control" id="rue" value="${rue_retrait}"/>
							</div>
						</div>
						<!-- Input CP -->
						<div class="row mt-2">
							<div class="col-sm-4 col-md-3 col-lg-4">
								<label for="code-postal">Code Postal : </label>
							</div>
							<div class="col-sm-8 col-md-9 col-lg-8">
								<input type="text" name="code-postal" class="form-control"
									id="code-postal" value="${codePostal_retrait}"/>
							</div>
						</div>
						<!-- Input  ville -->
						<div class="row mt-2">
							<div class="col-sm-4 col-md-3 col-lg-4">
								<label for="ville">Ville : </label>
							</div>
							<div class="col-sm-8 col-md-9 col-lg-8">
								<input type="text" name="ville" class="form-control" id="ville" value="${ville_retrait}"/>
							</div>
						</div>
					</div>

					<!-- Bloc Bouttons  -->
					<div class="row mt-2">
						<!-- Bouton Enregistrer -->
						<div class="col text-center">
							<input type="submit" name="enregistrer" value="Enregistrer"
								class="btn btn-primary btn-compte" />
						</div>
						<!-- Bouton Annuler -->
						<div class="col text-center">
							<a href="<c:url value="/ServletDetailArticle?idArticle=${idArticle}"/>" class="btn btn-primary btn-compte">
								Annuler</a>
						</div>
						<!-- Bouton Annuler Vente-->
						<div class="col text-center">
							<input type="submit" name="annuler-vente"
								value="Annuler la vente" class="btn btn-primary btn-compte" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</section>
</body>
</html>