package dev.web.collegue;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.CollegueDto;
import dev.entite.Collegue;
import dev.services.CollegueService;


/**
 * controlleur de l'api Collegue
 * @version 0.1
 * @author cql-v2
 *
 */
@RestController
@RequestMapping("collegue")
public class CollegueCtrl {

	private CollegueService colServ;

	public CollegueCtrl(CollegueService colServ) {
		this.colServ = colServ;
	}

	//methodes
/**
 * url : [SERVER]/collegue/all
 * @return un json de tous les Collegues en bdd
 */
	@GetMapping("all")
	public List<Collegue> listCollegue() {
		return colServ.getList();

	}
	
	/**
	 * 
	 * @param id identifiant rechercher
	 * @return un json du Collegue en bdd correspondant a l'id
	 */
	@GetMapping("byid")
	public Optional<Collegue> getById(@RequestParam Integer id ) {
		return colServ.getById(id);
	}
	
	
	
	
	/**
	 * url : [SERVER]/collegue/bymatricule?matricule={matricule}
	 * @param matricule le matricule recherché
	 * @return un json de tous les Collegues en bdd correspondant au matricule
	 */
	@GetMapping("bymatricule")
	public Optional<Collegue> getByMatricule(@RequestParam String matricule ) {
		return colServ.getByMatricule(matricule);
	}
	
	/**
	 * url : [SERVER]/collegue/bynom?nom={nom}
	 * @param nom le nom recherché
	 * @return un json de tous les Collegues en bdd correspondant au nom
	 */
	@GetMapping("bynom")
	public List<Collegue> getByNom(@RequestParam String nom ) {
		return colServ.getByNom(nom);
	}
	
	/**
	 * url : [SERVER]/collegue/getMatricule?nom={nom}
	 * @param nom nom le nom recherché
	 * @return un json de tous les matricules en bdd correspondant au nom
	 */
	@GetMapping("getMatricule")
	public List<String> getAllMatricules(@RequestParam String nom ) {
		return colServ.getAllMatricules(nom);
	}
		
	
	/**
	 * url : [SERVER]/collegue/add
	 * @param collegueDto un objet collegueDto au format json
	 * @return un objet collegueDto au format json
	 */
	@PostMapping("add")
	public ResponseEntity<?> newCollegue(@RequestBody CollegueDto collegueDto) {
			Collegue newCollegue = colServ.addCollegue(
					collegueDto.getMatricule(),
					collegueDto.getNom(),
					collegueDto.getPrenom(),
					collegueDto.getEmail(),
					collegueDto.getDateNaissance(),
					collegueDto.getPhotoUrl()
					);
			newCollegue.setId(newCollegue.getId());
			return ResponseEntity.ok(newCollegue);
	}
	
	/**
	 * 
	 * @param collegueDto un objet collegueDto au format json
	 * @return un objet collegueDto au format json
	 */
	@PutMapping("edit")
	public ResponseEntity<?> editUser(@RequestBody CollegueDto collegueDto) {
		Collegue editCollegue = colServ.updateCollegue(
				collegueDto.getId(),
				collegueDto.getMatricule(),
				collegueDto.getNom(),
				collegueDto.getPrenom(),
				collegueDto.getEmail(),
				collegueDto.getDateNaissance(),
				collegueDto.getPhotoUrl()
				);
		return ResponseEntity.ok(editCollegue);
	}
	/**
	 * 
	 * @param id id du collegue a remove
	 * @return une String 
	 */
	@DeleteMapping("remove")
	public ResponseEntity<?> remUser(@RequestParam Integer id) {
		return ResponseEntity.ok(colServ.remUser(id));
		
	}
	
	
	
}
