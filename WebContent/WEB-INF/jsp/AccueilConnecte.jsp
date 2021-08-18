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
<link rel="stylesheet" href="style.css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Lato" />
<title>Accueil</title>
</head>

<body>
	<!-- Entete  -->

	<header class="container-fluid header">
		<h1>ENI-Encheres</h1>
	</header>

	<!-- Corps  -->

	<section class="container main-container">
		<!-- Liens -->
		<div class="row justify-content-end">
			<div class="col-1">
				<a href="#">Enchères</a>
			</div>
			<div class="col-2">
				<a href="#">Vendre un article</a>
			</div>
			<div class="col-1">
				<a href="#">Mon Profil</a>
			</div>
			<div class="col-1">
				<a href="#">Déconnexion</a>
			</div>
		</div>

		<!-- Titre Principal -->

		<h1 class="row justify-content-center">Liste des enchères</h1>

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
													value="achats" checked /> <label
													for="achats">Achats</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" class="mt-3" id="encheres-ouvertes"
													name="encheres-ouvertes" checked />
												<label for="encheres-ouvertes">enchères
													ouvertes</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="encheres-en-cours"
													name="encheres-en-cours" /> <label
													for="encheres-en-cours">mes
													enchères en cours</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="encheres-remportees"
													name="encheres-remportees" /> <label
													for="encheres-remportees">mes
													enchères remportées</label>
											</div>
										</div>
									</div>

									<!-- Bloc Boutons checkbox  -->
									<div class="col-12 col-md-6 mt-2">
										<div class="row">
											<div>
												<input type="radio" id="ventes" name="achats-ventes"
													value="ventes" checked /> <label
													for="ventes">Ventes</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="ventes-en-cours" class="mt-3"
													name="ventes-en-cours" checked />
												<label for="ventes-en-cours">mes
													ventes en cours</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="ventes-non-debutees"
													name="ventes-non-debutees" /> <label
													for="ventes-non-debutees">ventes
													non débutées</label>
											</div>
										</div>
										<div class="row">
											<div>
												<input type="checkbox" id="ventes-terminees"
													name="ventes-terminees" /> <label
													for="ventes-terminees">ventes terminées</label>
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
						<!-- Bloc pour 1 objet enchère -->
						<div class="col-lg-6 col-sm-12">
							<div class="container bloc-objet-encheres rounded mt-4">
								<div class="row">
									<div class="col-lg-6 col-sm-6">
										<img src="img/objet.jpeg" alt="un objet" class="photo-objet" />
									</div>
									<div class="col-lg-6 col-sm-6">
										<h2 class="titre-objet">Nom de l'objet</h2>
										<p>Prix : XXX points</p>
										<p>Fin de l'enchère : 11/11/11</p>
										<p>
											Vendeur : <a href="#">nomDuVendeur</a>
										</p>
									</div>
								</div>
							</div>
						</div>

						<!-- Bloc pour 1 objet enchère -->
						<div class="col-lg-6 col-sm-12">
							<div class="container bloc-objet-encheres rounded mt-4">
								<div class="row">
									<div class="col-lg-6 col-sm-6">
										<img src="img/objet.jpeg" alt="un objet" class="photo-objet" />
									</div>
									<div class="col-lg-6 col-sm-6">
										<h2 class="titre-objet">Nom de l'objet</h2>
										<p>Prix : XXX points</p>
										<p>Fin de l'enchère : 11/11/11</p>
										<p>
											Vendeur : <a href="#">nomDuVendeur</a>
										</p>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</section>
</body>
</html>