package dev.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.entite.Collegue;
import dev.repository.CollegueRepository;

@Service
public class CollegueService {
	private CollegueRepository colRepo;
	public CollegueService(CollegueRepository colRepo) {
		this.colRepo=colRepo;
	}
	
	public List<Collegue> getList() {
		return colRepo.findAll();
	}

	public Optional<Collegue> getById(Integer id) {
		return colRepo.findById(id);
	}
	
	public Optional<Collegue> getByMatricule(String matricule) {
		return colRepo.findByMatricule(matricule);
	}

	public List<Collegue> getByNom(String nom){
	return colRepo.findAllBynom(nom);
	}
	
	public List<String> getAllMatricules() {
		return colRepo.findMatricule();
	}

	public Optional<String> getMatriculesById(Integer id) {
		return colRepo.findMatriculeById(id);
	}

	public Optional<String> getMatriculesByNom(String nom) {
		return colRepo.findMatriculeByNom(nom);
	}
		
	@Transactional
	public Collegue addCollegue(String nom, String prenom, String email, LocalDate dateNaissance,
			String photoUrl) {
		return colRepo.save(new Collegue(this.genMatricule(16),nom,prenom,email,dateNaissance,photoUrl));
	}

	public Collegue updateCollegue(Integer id, String matricule, String nom, String prenom, String email,
			LocalDate dateNaissance, String photoUrl) {
			Optional<Collegue> collegueToUpdate=this.getById(id);
			if(collegueToUpdate.isPresent()) {
				collegueToUpdate.get().setMatricule(matricule);
				collegueToUpdate.get().setNom(nom);
				collegueToUpdate.get().setPrenom(prenom);
				collegueToUpdate.get().setDateNaissance(dateNaissance);
				collegueToUpdate.get().setEmail(email);
				collegueToUpdate.get().setPhotoUrl(photoUrl);
			}
			return colRepo.save(collegueToUpdate.get());
	}

	public String remUser(Integer id) {
		
		Optional<Collegue> collegueToRemove=this.getById(id);
		if(collegueToRemove.isPresent()) {
			colRepo.delete(collegueToRemove.get());
			return "Suppression reussi";
		}
		return "id non trouvé";
	}
	
	public String genMatricule(int nbChar) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		String m = "";
		for(int i=0;i<nbChar;i++)
			m += chars.charAt((int)Math.floor(Math.random() * chars.length()));
		return m;
	}
	
	
}
