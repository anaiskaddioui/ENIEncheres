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
<title>Creer un compte</title>
</head>
<body>
	<!-- Entete  -->
	<header class="container-fluid header">
		<h1>ENI-Encheres</h1>
	</header>

	<!-- Corps  -->
	<section class="container main-container">


		<form method="post" action="./ServletCreationCompte">
			<div class="container">
				<h1 class="mb-5 text-center">Mon Profil</h1>
				<div class="row justify-content-start bloc-text-input">
					<div class="col-lg-6 col-sm-12">
						<!-- Bloc Pseudo  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="pseudo" class="label">Pseudo : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="pseudo" class="form-control-sm"
									id="pseudo" required="required" />
							</div>
						</div>
						<!-- Bloc Nom  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="nom" class="label">Nom : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="nom" class="form-control-sm" id="nom"
									required="required" />
							</div>
						</div>
						<!-- Bloc Prénom  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="prenom" class="label">Prénom : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="prenom" class="form-control-sm"
									id="prenom" required="required" />
							</div>
						</div>
						<!-- Bloc Email  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="email" class="label">Email : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="email" name="email" class="form-control-sm"
									id="email" required="required" />
							</div>
						</div>
						<!-- Bloc Telephone  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="telephone" class="label">Téléphone : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="telephone" name="telephone" class="form-control-sm"
									id="telephone" />
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-sm-12">
						<!-- Bloc Rue  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="rue" class="label">Rue : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="rue" class="form-control-sm" id="rue"
									required="required" />
							</div>
						</div>
						<!-- Bloc Code Postal  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="code_postal" class="label">Code Postal : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="code_postal" class="form-control-sm"
									id="code_postal" required="required" />
							</div>
						</div>
						<!-- Bloc Ville  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="ville" class="label">Ville : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="ville" class="form-control-sm"
									id="ville" required="required" />
							</div>
						</div>
						<!-- Bloc Mot de Passe  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="password" class="label">Mot de passe : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="password" name="password" class="form-control-sm"
									id="password" required="required" />
							</div>
						</div>
						<!-- Bloc Confirmation Mot de Passe  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="password-confirmation" class="label">Confirmation
									:</label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="password" name="password-confirmation"
									class="form-control-sm" id="password-confirmation"
									required="required" />
							</div>
						</div>
					</div>
				</div>

				<!-- Bloc Bouttons  -->
				<div class="container">
					<div class="row">
						<div class="col-4 col-sm-6">
							<!-- Bouton creer un compte  -->
							<input type="submit" name="creer" value="Créer"
								class="btn btn-primary btn-compte" />
						</div>
						<!-- Bouton Annuler  -->
						<div class="col-4 col-sm-6">
							<button href="#" class="btn btn-primary btn-compte">
								Annuler</button>
						</div>
					</div>
				</div>
		</form>
	</section>
</body>
</html>