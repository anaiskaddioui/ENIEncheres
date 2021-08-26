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
		<div class="row">
			<div class="col-9">
			<c:choose>
				<c:when test="${!isConnected }">
					<a href="<%=request.getContextPath()%>/ServletAccueil"><img src="img/logo.png" alt="un objet" class="photo-logo" /></a>
				</c:when>
				<c:otherwise>
					<a href="<%=request.getContextPath()%>/ServletAccueilConnecte"><img src="img/logo.png" alt="un objet" class="photo-logo" /></a>						
				</c:otherwise>
			</c:choose>		
			
			</div>
							<!-- Liens -->
				<div class="row justify-content-end">
					<div class="col-2">
						<a href="<%=request.getContextPath()%>/ServletNouvelleVente">Vendre un article</a>
					</div>
					<div class="col-1">
						<a href="<%=request.getContextPath()%>/ServletConsultationCompteBouton">Mon Profil</a>
					</div>
					<div class="col-1">
						<c:if test = "${ isConnected }"> <!-- Mini formulaire de déconnexion -->
							<form method="post" action="<%=request.getContextPath()%>/ServletDeconnexion">
								<div class="col-1">
									<input type="hidden" name="deconnexion" value="deconnexion" />
									<input type="submit" value="Déconnexion" />
								</div>
							</form>
						</c:if>
					</div>
				</div>
		</div>
	</header>

	<!-- Corps  -->

	<section class="container main-container">


		<!-- Titre Principal -->

		<div class="bloc-titre">
			<h1>ENI Encheres</h1> 
			<h2>Achetez, Vendez, Partagez...</h2>
			<p class="rectangle"></p>
		</div>	
		<h3 class="row justify-content-center">Liste des enchères</h3>

		<form method="post" action="./ServletAccueilConnecte">
			<div class="container">
				<!-- Bloc Filtre/recherche/bouton  -->

				<div class="container border rounded">
					<p class="mt-3">Filtres :</p>
					<div class="row justify-content-center bloc-text-input">
						<!-- Bloc Liste Catégories/recherche  -->
						<div class="col-lg-6 col-sm-12">
							<div class="row justify-content-center bloc-text-input">
								<div class="col-4 col-sm-4 col-lg-3">
									<label for="categories">Catégorie :</label>
								</div>

								<div class="col-7 col-sm-8 col-lg-9 mt-3">
									<select name="categories" class="form-select">
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

							<!-- Bloc Boutons radio/checkbox  -->

							<div class="container">
								<!-- Bloc Boutons checkbox  -->
								<div class="row">
									<div class="col-12 col-md-6">
										<div class="row">
											<div>
												<input type="radio" id="achats" name="achats-ventes"
													value="achats" checked onclick="clikedAchats;" /> 
												<label for="achats">Achats</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" class="mt-3" id="encheres-ouvertes"
													name="cbox-achat" value="encheres-ouvertes" checked />
												<label for="encheres-ouvertes" id="encheres-ouvertes-label" >enchères
													ouvertes</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="encheres-en-cours"
													name="cbox-achat" value="encheres-en-cours"  />
												<label for="encheres-en-cours" id="encheres-en-cours-label">mes
													enchères en cours</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="encheres-remportees"
													name="cbox-achat" value="encheres-remportees" />
												<label for="encheres-remportees" id="encheres-remportees-label">mes
													enchères remportées</label>
											</div>
										</div>
									</div>

									<!-- Bloc Boutons checkbox  -->
									<div class="col-12 col-md-6 mt-2">
										<div class="row">
											<div>
												<input type="radio" id="ventes" name="achats-ventes"
													value="ventes" onclick="clikedVentes;"/> <label
													for="ventes">Ventes</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="ventes-en-cours" class="mt-3"
													name="cbox-vente" value="ventes-en-cours" />
												<label for="ventes-en-cours" id="ventes-en-cours-label">mes
													ventes en cours</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="ventes-non-debutees"
													name="cbox-vente" value="ventes-non-debutees"/>
												<label for="ventes-non-debutees" id="ventes-non-debutees-label">ventes non débutées</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="ventes-terminees"
													name="cbox-vente" value="ventes-terminees"/> 
												<label for="ventes-terminees" id="ventes-terminees-label">ventes terminées</label>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<!-- Bloc Bouttons  -->
						<div class="col-lg-6 text-center">
							<input type="submit" name="rechercher" value="Rechercher"
								class="btn btn-primary btn-compte col-6" />
						</div>
					</div>
				</div>

				<!-- Bloc objets enchères -->

				<div class="container-fluid rounded mt-5">
					<!-- Ligne d'objets -->
					<div class="row">


<!-- 				Affiche les articles selon le filtre categories ou message si pas d'articles en cours -->
					<c:choose>
						<c:when test="${listeArticlesSelonCasesCochees.size()<1 }">
							<p class="text-center display-6">Aucune vente en cours avec vos critères</p> 
						
						</c:when>
						<c:otherwise>
							<c:forEach var="el" items="${listeArticlesSelonCasesCochees }" >
								<!-- Bloc pour 1 objet enchère -->
								<div class="col-lg-6 col-sm-12">
									<div class="container bloc-objet-encheres rounded mt-4" id="article">
										<div class="row">
											<div class="col-lg-6 col-sm-6">
												<img src="img/objet.jpeg" alt="un objet" class="photo-objet" />
											</div>
											<div class="col-lg-6 col-sm-6">
												<h2 class="titre-objet">${el.getNomArticle() }</h2>
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
<!-- 	Fonction Javascript qui desactive les boutons radio et checkbox -->
	<script type="text/javascript">
		var RadioAchat = document.getElementById("achats");
		var encheresOuvertes = document.getElementById("encheres-ouvertes");
		var encheresEnCours = document.getElementById("encheres-en-cours");
		var encheresRemportees = document.getElementById("encheres-remportees");

		var RadioVente = document.getElementById("ventes");		
		var ventesEnCours = document.getElementById("ventes-en-cours");
		var ventesNonDebutees = document.getElementById("ventes-non-debutees");
		var ventesTerminees = document.getElementById("ventes-terminees");
		
		var encheresOuvertesLabel = document.getElementById("encheres-ouvertes-label");
		var encheresEnCoursLabel = document.getElementById("encheres-en-cours-label");
		var encheresRemporteesLabel = document.getElementById("encheres-remportees-label");	
		var ventesEnCoursLabel = document.getElementById("ventes-en-cours-label");
		var ventesNonDebuteesLabel = document.getElementById("ventes-non-debutees-label");
		var ventesTermineesLabel = document.getElementById("ventes-terminees-label");
		
		RadioAchat.addEventListener('click',
		function clikedAchats() {
			ventesEnCours.disabled = true;
			ventesEnCours.checked = false;
			ventesEnCoursLabel.style.color = "grey";
			ventesNonDebutees.disabled = true;
			ventesNonDebutees.checked = false;
			ventesNonDebuteesLabel.style.color = "grey";
			ventesTerminees.disabled = true;
			ventesTerminees.checked = false;
			ventesTermineesLabel.style.color = "grey";
			encheresOuvertes.disabled = false;
			encheresOuvertesLabel.style.color = "white";
			encheresEnCours.disabled = false;
			encheresEnCoursLabel.style.color = "white";
			encheresRemportees.disabled = false;
			encheresRemporteesLabel.style.color = "white";
		})

		RadioVente.addEventListener('click',
		function clikedVentes() {
			encheresOuvertes.disabled = true;
			encheresOuvertes.checked = false;
			encheresOuvertesLabel.style.color = "grey";
 			encheresEnCours.disabled = true;
 			encheresEnCours.checked = false;
 			encheresEnCoursLabel.style.color = "grey";
 			encheresRemportees.disabled = true;
 			encheresRemportees.checked = false;
 			encheresRemporteesLabel.style.color = "grey";
			ventesEnCours.disabled = false;	
			ventesEnCoursLabel.style.color = "white";
			ventesNonDebutees.disabled = false;	
			ventesNonDebuteesLabel.style.color = "white";
			ventesTerminees.disabled = false;
			ventesTermineesLabel.style.color = "white";
		})
		
	</script>
</body>
</html>