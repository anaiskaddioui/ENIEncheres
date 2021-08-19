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
<title>Connexion</title>
</head>
<body>
	<!-- Entete  -->
	<header class="container-fluid header">
		<h1>ENI-Encheres</h1>
	</header>

	<!-- Corps  -->
	<section class="container main-container">
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
						<input type="text" name="utilisateur_motDePasse"
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
							class="btn btn-primary" />
					</div>
					<div class="col-sm-6 col-xs-4">
						<!-- Checkbox se souvenir / lien mdp oublié  -->
						<input type="checkbox" id="connexion-auto" name="connexion-auto"
							checked /> <label for="connexion-auto">Se
							souvenir de moi</label>
					</div>
				</div>
				<div class="row justify-content-start col-sm-12 col-xs-12">
					<a href="#">Mot de passe oublié</a>
				</div>
				<!-- Bouton creer un compte  -->
				<div class="row justify-content-center col-sm-6 col-xs-4">
					<button href="#" class="btn btn-primary btn-compte">Créer
						un compte</button>
				</div>
			</div>
		</form>
	</section>
</body>
</html>