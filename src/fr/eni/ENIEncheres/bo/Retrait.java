package fr.eni.ENIEncheres.bo;


	

	public class Retrait {
	    private int idArticle;
	    private String rue;
	    private int codePostal;
	    private String ville;

	    
	    public Retrait() {

	    }

	    public Retrait(int idArticle, String rue, int codePostal, String ville) {
	        this.setIdArticle(idArticle);
	        this.rue = rue;
	        this.codePostal = codePostal;
	        this.ville = ville;
	    }

	    
	    public String getRue() {
	        return rue;
	    }

	    public void setRue(String rue) {
	        this.rue = rue;
	    }

	    public int getCodePostal() {
	        return codePostal;
	    }

	    public void setCodePostal(int codePostal) {
	        this.codePostal = codePostal;
	    }

	    public String getVille() {
	        return ville;
	    }

	    public void setVille(String ville) {
	        this.ville = ville;
	    }

	    

		public int getIdArticle() {
			return idArticle;
		}

		public void setIdArticle(int idArticle) {
			this.idArticle = idArticle;
		}
	}


