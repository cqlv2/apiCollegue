package dev.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
	
	//methodes
	/**
	 * 
	 * @return une list d'objet  de tout les collegues
	 */
	public List<Collegue> getList() {
		return colRepo.findAll();
	}
	/**	
	 * 
	 * @param matricule le matricule recherché
	 * @return un objet Collegue correspondant au matricule
	 */

	
	public Optional<Collegue> getByMatricule(String matricule) {
		return colRepo.findByMatricule(matricule);
	}
	
	/**	
	 * 
	 * @param nom le nom recherché
	 * @return une liste d'objet Collegue correspondant au nom
	 */
	public List<Collegue> getByNom(String nom){
	return colRepo.findAllBynom(nom);
	}
	
	/**
	 * 
	 * @param nom le nom recherché
	 * @return une liste de string de tous les matricules correspondant au nom
	 */
	public List<String> getAllMatricules(String nom) {
		return colRepo.findMatriculeBynom(nom);
	}

	@Transactional
	public Collegue addCollegue(String matricule, String nom, String prenom, String email, LocalDate dateNaissance,
			String photoUrl) {
			
		return colRepo.save(new Collegue(matricule,nom,prenom,email,dateNaissance,photoUrl));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
