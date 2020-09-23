package dev.web.collegue;

import java.util.ArrayList;
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
@RequestMapping
public class CollegueCtrl {

	private CollegueService colServ;

	public CollegueCtrl(CollegueService colServ) {
		this.colServ = colServ;
	}


/**
 * url : [SERVER]/collegue
 * @return un json de tous les Collegues en bdd
 */
	@GetMapping("collegue")
	public List<Collegue> listCollegue() {
		return colServ.getList();

	}
	
	/**
	 * url : [SERVER]/collegue/search?type={type}&value={value}
	 * @param type type de recherche [id, matricule, nom]
	 * @param value valeur de recherche
	 * @return une liste de json
	 */
	@GetMapping("collegue/search")
	public List<Collegue> getCollegue(@RequestParam String type, @RequestParam String value ) {
		List<Collegue> list=new ArrayList<Collegue>();
		Optional<Collegue> c=null;
		
		switch (type) {
		case "id":
			c=colServ.getById(Integer.parseInt(value));
			if(c.isPresent()) list.add(c.get());
			return list;
			
		case "matricule":
			
			c=colServ.getById(Integer.parseInt(value));
			if(c.isPresent()) list.add(c.get());
			return list;
		
		case "nom":
			return colServ.getByNom(value);
		default:
			return null;
		}
	}
	
	
	
	
	/**
	 * url : [SERVER]/collegue/getMatricule?nom={nom}
	 * @return un json de tous les matricules en bdd 
	 */
	@GetMapping("matricule")
	public List<String> getAllMatricules() {
		return colServ.getAllMatricules();
	}
		
	/**
	 * url : [SERVER]/matricule/search?type={type}&value={value}
	 * @param type type de recherche [id, nom]
	 * @param value valeur de recherche
	 * @return une liste de json
	 */
	@GetMapping("matricule/search")
	public List<String> searchMatricule(@RequestParam String type, @RequestParam String value ) {

		List<String> list=new ArrayList<String>();
		Optional<String> s=null;
		
		switch (type) {
		case "id":
			
			s=colServ.getMatriculesById(Integer.parseInt(value));
			if(s.isPresent()) list.add(s.get());
			return list;
			
		case "nom":
			s=colServ.getMatriculesByNom(value);
			if(s.isPresent()) list.add(s.get());
			return list;

		default:
			return null;
		}
	}
	
	
	
	/**
	 * url : [SERVER]/collegue
	 * @param collegueDto un objet collegueDto au format json
	 * @return un objet collegueDto au format json
	 */
	@PostMapping("collegue")
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
	@PutMapping("collegue")
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
	@DeleteMapping("collegue")
	public ResponseEntity<?> remUser(@RequestParam Integer id) {
		return ResponseEntity.ok(colServ.remUser(id));
		
	}
	
	
	
}
