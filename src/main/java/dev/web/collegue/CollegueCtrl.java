package dev.web.collegue;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.dto.CollegueDto;
import dev.entite.Collegue;
import dev.services.CollegueService;



@RestController
@RequestMapping("collegue")
public class CollegueCtrl {

	private CollegueService colServ;

	public CollegueCtrl(CollegueService colServ) {
		this.colServ = colServ;
	}

	//methodes
	
	@GetMapping("all")
	public List<Collegue> listCollegue() {
		return colServ.getList();

	}
	
	@GetMapping("bymatricule")
	public Optional<Collegue> getByMatricule(@RequestParam String matricule ) {
		return colServ.getByMatricule(matricule);
	}
	
	@GetMapping("bynom")
	public List<Collegue> getByNom(@RequestParam String nom ) {
		return colServ.getByNom(nom);
	}
	
	
	@GetMapping("getMatricule")
	public List<String> getAllMatricules(@RequestParam String nom ) {
		return colServ.getAllMatricules(nom);
	}
		
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
	
	
	
	
}
