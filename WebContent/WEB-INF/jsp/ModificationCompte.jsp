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
<title>Modifier un compte</title>
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


		<form method="post" action="${pageContext.request.contextPath }/ServletVerification">
		<input type="hidden" name="hiddenPath" value="modification"/>
			<div class="container">
				<div class="bloc-titre">
					<h1>ENI Encheres</h1> 
					<h2>Achetez, Vendez, Partagez...</h2>
					<p class="rectangle"></p>
				</div>	
				<h3 class="row justify-content-center sous-titre">Mon Profil</h3>
				<h6 style="color: red;">${pseudoValidation }</h6>
				<h6 style="color: red;">${emailValidation }</h6>
				<div class="row justify-content-start bloc-text-input">
					<div class="col-lg-6 col-sm-12">
						<!-- Bloc Pseudo  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="pseudo" class="label">Pseudo : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="pseudo" class="form-control-sm"
									id="pseudo" required="required" maxlength="30" value="${pseudo }"/>
							</div>
						</div>
						<!-- Bloc Nom  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="nom" class="label">Nom : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="nom" class="form-control-sm" id="nom"
									required="required" maxlength="30" value="${nom}"/>
							</div>
						</div>
						<!-- Bloc Prénom  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="prenom" class="label">Prénom : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="prenom" class="form-control-sm"
									id="prenom" required="required" maxlength="30" value="${prenom }"/>
							</div>
						</div>
						<!-- Bloc Email  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="email" class="label">Email : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="email" name="email" class="form-control-sm"
									id="email" required="required" maxlength="50" value="${email}"/>
							</div>
						</div>
						<!-- Bloc Telephone  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="telephone" class="label">Téléphone : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="tel" name="telephone" class="form-control-sm"
									id="telephone" pattern="[0]{1}[0-9]{9}" value="${telephone }"/>
							</div>
						</div>
						<!-- Bloc Crédit  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="credit" class="label">Crédit : </label>
							</div>
							<div class="col-6 col-sm-3">
								<p>${credit }</p>
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
									required="required" maxlength="30" value="${rue}"/>
							</div>
						</div>
						<!-- Bloc Code Postal  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="code_postal" class="label">Code Postal : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="code_postal" class="form-control-sm"
									id="code_postal" required="required" maxlength="30" value="${codePostal}"/>
							</div>
						</div>
						<!-- Bloc Ville  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="ville" class="label">Ville : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="text" name="ville" class="form-control-sm"
									id="ville" required="required" maxlength="30" value="${ville}"/>
							</div>
						</div>
<!-- 						Bloc Mot de Passe Actuel -->
<!-- 						<div class="row justify-content-start bloc-text-input"> -->
<!-- 							<div class="col-4 col-sm-4"> -->
<!-- 								<label for="password-actuel" class="label">Mot de passe -->
<!-- 									actuel: </label> -->
<!-- 							</div> -->
<!-- 							<div class="col-6 col-sm-3"> -->
<!-- 								<input type="password" name="password-actuel" -->
<!-- 									class="form-control-sm" id="password-actuel" -->
<!-- 									required="required" /> -->
<!-- 							</div> -->
<!-- 						</div> -->
						<!-- Bloc Nouveau Mot de Passe  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="password-nouveau" class="label">Changer le mot
									de passe : </label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="password" name="password-nouveau"
									class="form-control-sm" id="password-nouveau"
									 />
							</div>
						</div>
						<!-- Bloc Confirmation Mot de Passe  -->
						<div class="row justify-content-start bloc-text-input">
							<div class="col-4 col-sm-4">
								<label for="password-confirm" class="label">Confirmation du nouveau mot de passe
									:</label>
							</div>
							<div class="col-6 col-sm-3">
								<input type="password" name="password-confirm"
									class="form-control-sm" id="password-confirm"
									/>
							</div>
						</div>
					</div>
				</div>

				<!-- Bloc Bouttons  -->
				<div class="container text-center">
					<div class="row">
						<div class="col">
							<!-- Bouton Enregistrer le compte  -->
							<input type="submit" name="creer" value="Enregistrer"
								class="btn btn-primary btn-compte" onclick="return Validate()"/>
							</div>
							<!-- Bouton Annuler  -->
							<div class="col">
							<a href="<c:url value="/ServletConsultationCompteBouton"/>" class="btn btn-primary btn-compte">
								Annuler</a>
						</div>
					</div>
				</div>
		</form>
	</section>
	
<script type="text/javascript">
function Validate(){
	var password = document.getElementById("password-nouveau").value;
	var confirmPassword = document.getElementById("password-confirm").value;
	if (password != confirmPassword){
		alert("Les mots de passe ne correspondent pas");
		return false;
	}
	return true;
}
</script>
</body>
</html>