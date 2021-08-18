<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="./style.css" />
    <link
      rel="stylesheet"
      href="https://fonts.googleapis.com/css?family=Lato"
    />
    <title>Accueil</title>
  </head>
  <body>
    <!-- Entete  -->
    <header class="container-fluid header">
      <h1>ENI-Encheres</h1>
    </header>

    <!-- Corps  -->
    <section class="container main-container">
      <a
        href="<%=request.getContextPath()%>/ServletConnexion"
        class="row justify-content-end"
        >S'inscrire - Se connecter</a
      >
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
                  <input
                    type="text"
                    name="recherche"
                    placeholder="Le nom de l'article contient"
                    class="form-control barre-recherche"
                    id="recherche"
                  />
                </div>
              </div>

              <!-- Bloc Bouttons  -->
              <div class="col-12 col-md-6 text-center">
                <input
                  type="submit"
                  name="rechercher"
                  value="Rechercher"
                  class="btn btn-primary btn-compte col-6"
                />
              </div>
            </div>
          </div>

          <!-- Bloc d'objets enchères -->
          <div class="container-fluid rounded mt-5">
            <!-- Ligne d'objets -->
            <div class="row">
              <!-- Bloc pour 1 objet enchère -->
              <div class="col-lg-6 col-sm-12">
                <div class="container bloc-objet-encheres rounded mt-4">
                  <div class="row">
                    <div class="col-lg-6 col-sm-6">
                      <img
                        src="img/objet.jpeg"
                        alt="un objet"
                        class="photo-objet"
                      />
                    </div>
                    <div class="col-lg-6 col-sm-6">
                      <h2 class="titre-objet">Nom de l'objet</h2>
                      <p>Prix : XXX points</p>
                      <p>Fin de l'enchère : 11/11/11</p>
                      <p>Vendeur : nomDuVendeur</p>
                    </div>
                  </div>
                </div>
              </div>

              <!-- Bloc pour 1 objet enchère -->
              <div class="col-lg-6 col-sm-12">
                <div class="container bloc-objet-encheres rounded mt-4">
                  <div class="row">
                    <div class="col-lg-6 col-sm-6">
                      <img
                        src="img/objet.jpeg"
                        alt="un objet"
                        class="photo-objet"
                      />
                    </div>
                    <div class="col-lg-6 col-sm-6">
                      <h2 class="titre-objet">Nom de l'objet</h2>
                      <p>Prix : XXX points</p>
                      <p>Fin de l'enchère : 11/11/11</p>
                      <p>Vendeur : nomDuVendeur</p>
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