package dev.dto;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class CollegueDto {

	private Integer id=null;
	private String matricule=null;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	private String nom;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	private String prenom;
	
	@NotNull
	@NotBlank
	private String email;
	
	@NotNull
	@Past
    private LocalDate dateNaissance;
	
	@NotBlank
	@NotNull
	private String photoUrl;
	
	public CollegueDto(Integer id, String matricule, String nom, String prenom, String email, LocalDate dateNaissance,
			String photoUrl) {
		this.id = id;
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.dateNaissance = dateNaissance;
		this.photoUrl = photoUrl;
	}

	
//	getteur setteur
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
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

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(LocalDate dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
		
}
