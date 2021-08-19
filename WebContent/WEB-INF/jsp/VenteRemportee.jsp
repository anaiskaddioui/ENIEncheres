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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<title>Vente Remportée</title>
</head>
<body>
	<!-- Entete  -->
	<header class="container-fluid header">
		<h1>ENI-Encheres</h1>
	</header>

	<!-- Corps  -->
	<section class="container main-container">
		<h1 class="row justify-content-center">Vous avez remporté la
			vente</h1>

		<div class="row">
			<!-- Colonne avec image -->
			<div class="col-xs-sm-12 col-lg-4 text-center">
				<img src="img/objet.jpeg" alt="un objet" class="photo-objet" />
			</div>
			<!-- Colonne avec Formulaire -->

			<div class="col">
				<!-- Input Article -->
				<div class="row">
					<p>Nom de l'article</p>
				</div>
				<!-- Input Description -->
				<div class="row mt-2">
					<div class="col-xs-sm-12 col-lg-3">
						<label for="descrition">Description : </label>
					</div>
					<div class="col">
						<p>Lorem ipsum, dolor sit amet consectetur adipisicing elit.
							Quis assumenda, corporis quae ea rem numquam provident totam
							nihil placeat magni impedit deserunt ab ad tempore asperiores
							voluptatibus praesentium quo sed?</p>
					</div>
				</div>
				<!-- Meilleure offre -->
				<div class="row mt-2">
					<div class="col-6 col-sm-6 col-lg-3">
						<label for="best-offer">Meilleure offre :</label>
					</div>
					<div class="col">
						<p>XXX pts</p>
					</div>
				</div>
				<!-- input mise à prix -->
				<div class="row mt-2">
					<div class="col-6 col-sm-6 col-lg-3">
						<label for="prix">Mise à prix :</label>
					</div>
					<div class="col">
						<p>XXX points</p>
					</div>
				</div>
				<!--Bloc Retrait -->
				<div class="row mt-2">
					<div class="col-6 col-sm-6 col-lg-3">
						<label for="retrait">Retrait : </label>
					</div>
					<div class="col">
						<p>1 rue ENI</p>
						<p>44000 ST HERBLAIN</p>
					</div>
				</div>
				<!-- Input Vendeur -->
				<div class="row mt-2">
					<div class="col-6 col-sm-6 col-lg-3">
						<label for="vendeur">Vendeur :</label>
					</div>
					<div class="col">
						<p>Nom du vendeur</p>
					</div>
				</div>
				<!-- Input Telephone -->
				<div class="row mt-2">
					<div class="col-6 col-sm-6 col-lg-3">
						<label for="telephone">Telephone :</label>
					</div>
					<div class="col">
						<p>0123456789</p>
					</div>
				</div>

				<!-- Bouton Annuler -->
				<div class="col text-center">
					<button href="#" class="btn btn-primary btn-compte">Retour</button>
				</div>
			</div>
		</div>
	</section>
</body>
</html>