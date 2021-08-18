package fr.eni.ENIEncheres.bo;

public class Utilisateurs {
	
	private int noUtilisateur;
	private String pseudo;
	private String password;
	private String nom;
	private String prenom;
	private String email;
	private String tel = null;
	private String rue;
	private String coPostal;
	private String ville;
	private int credit;
	private boolean administrateur;
	
	
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	public String getPseudo() {
		return pseudo;
	}
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRue() {
		return rue;
	}
	public void setRue(String rue) {
		this.rue = rue;
	}
	public String getCoPostal() {
		return coPostal;
	}
	public void setCoPostal(String coPostal) {
		this.coPostal = coPostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public boolean isAdministrateur() {
		return administrateur;
	}
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}

	
	public Utilisateurs(int noUtilisateur, String pseudo, String password, String nom, String prenom, String email, 
			String tel, String rue, String coPostal, String ville, int credit, boolean administrateur) {
		
		this.setNoUtilisateur(noUtilisateur);
		this.setPseudo(pseudo);
		this.setPassword(password);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEmail(email);
		this.setTel(tel);
		this.setRue(rue);
		this.setCoPostal(coPostal);
		this.setVille(ville);
		this.setCredit(credit);
		this.setAdministrateur(administrateur);
	}
	
	public Utilisateurs(String pseudo, String password, String nom, String prenom, String email, 
			String tel, String rue, String coPostal, String ville, int credit, boolean administrateur) {
		
		this.setPseudo(pseudo);
		this.setPassword(password);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setEmail(email);
		this.setTel(tel);
		this.setRue(rue);
		this.setCoPostal(coPostal);
		this.setVille(ville);
		this.setCredit(credit);
		this.setAdministrateur(administrateur);
	}
	
	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur = "+ noUtilisateur +", pseudo =" + pseudo + ", nom = " + nom + ", prénom =" + prenom
				+ ", email = " + email + ", tel = " + tel + " rue = ]";
	}
	
	//Un commentaire pour pouvoir recommiter
}
