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
<title>Accueil</title>
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
		<a href="<%=request.getContextPath()%>/ServletConnexion"
			class="row justify-content-end">S'inscrire - Se
			connecter</a>
		<h1 class="row justify-content-center">Liste des enchères</h1>

		<form action="./" method="post">
			<div class="container">
				<!-- Bloc Filtre/recherche/bouton  -->
				<div class="container border rounded">
					<p class="mt-3">Filtres :</p>
					<div class="row justify-content-center bloc-text-input">
						<!-- Bloc Liste Catégories/recherche  -->
						<div class="col-lg-6 col-sm-12">
							<div class="row justify-content-center bloc-text-input">
								<div class="col-4 col-sm-4 col-lg-3">
									<label for="categories">Catégories:</label>
								</div>

								<div class="col-7 col-sm-8 col-lg-9 mt-3">
									<select name="categorie" class="form-select">
										<option value="">Toutes</option>
										<option value="informatique">Informatique</option>
										<option value="ameublement">Ameublement</option>
										<option value="vetement">Vêtement</option>
										<option value="sport">Sport&Loisirs</option>
									</select>
								</div>
							</div>

							<!-- Bloc Barre recherche  -->
							<div class="row justify-content-left bloc-text-input">
								<input type="text" name="recherche"
									placeholder="Le nom de l'article contient"
									class="form-control barre-recherche" id="recherche" />
							</div>
							<c:if test="${!libelleCategorie.isEmpty()}">
								<p class="text-decoration-underline">Vous visualisez les ventes de la catégorie : </p>
								<p class="ms-2"> - ${libelleCategorie.toUpperCase() }</p>
							</c:if>
							<c:if test="${!articleContient.isEmpty() }">
								<p class="ms-2"> - Le nom contient : "${articleContient }"</p>								
							</c:if>
						</div>

						<!-- Bloc Bouttons  -->
						<div class="col-12 col-md-6 text-center">
							<input type="submit" name="rechercher" value="Rechercher"
								class="btn btn-primary btn-compte col-6" />
						</div>
					</div>
				</div>

				<!-- Bloc d'objets enchères -->
				<div class="container-fluid rounded mt-5">
					<!-- Ligne d'objets -->
					<div class="row">
					
<!-- 				Affiche les articles selon le filtre categories ou message si pas d'articles en cours -->
					<c:choose>
						<c:when test="${listeArticlesEnCours.size()<1 }">
							<p class="text-center display-6">Aucune vente en cours avec vos critères</p> 
						
						</c:when>
						<c:otherwise>
							<c:forEach var="el" items="${listeArticlesEnCours }" >
								<!-- Bloc pour 1 objet enchère -->
								<div class="col-lg-6 col-sm-12">
									<div class="container bloc-objet-encheres rounded mt-4">
										<div class="row">
											<div class="col-lg-6 col-sm-6">
												<img src="<%=getServletContext().getResourceAsStream("/img/objet.jpeg")%>" alt="un objet" class="photo-objet" />
											</div>
											<div class="col-lg-6 col-sm-6">
												<h2 class="titre-objet"><a href="<c:url value="/ServletDetailArticle?idArticle=${el.getIdArticle() }"/>">${el.getNomArticle() }</a></h2>
												<p>Prix : ${el.getPrixInitial() } points</p>
												<p>Fin de l'enchère : ${el.getDateFinEncheres() }</p>
												<p>Vendeur : ${el.getPseudo() }</p>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>						
						</c:otherwise>
					
					</c:choose>

					</div>
				</div>
			</div>
		</form>
	</section>
</body>
</html>