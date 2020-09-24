package dev.web.collegue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@RestController
@CrossOrigin
@RequestMapping("collegue")
public class CollegueCtrl {

	private CollegueService colServ;

	public CollegueCtrl(CollegueService colServ) {
		this.colServ = colServ;
	}

	@GetMapping
	public List<Collegue> listCollegue() {
		return colServ.getList();
	}
	
	@GetMapping(params = {"type", "value"})
	public List<Collegue> getCollegue(@RequestParam String type, @RequestParam String value ) {
		List<Collegue> list=new ArrayList<Collegue>();
		Optional<Collegue> c=null;
		switch (type) {
		case "id":
			c=colServ.getById(Integer.parseInt(value));
			if(c.isPresent()) list.add(c.get());
			return list;	
		case "matricule":
			c=colServ.getByMatricule(value);
			if(c.isPresent()) list.add(c.get());
			return list;
		case "nom":
			return colServ.getByNom(value);
		default:
			return null;
		}
	}
	
	@PostMapping
	public ResponseEntity<?> createCollegue(@Valid @RequestBody CollegueDto collegueDto, BindingResult resValid) {
		if (!resValid.hasErrors()) {
			Collegue newCollegue = colServ.addCollegue(
					collegueDto.getNom(),
					collegueDto.getPrenom(),
					collegueDto.getEmail(),
					collegueDto.getDateNaissance(),
					collegueDto.getPhotoUrl()
					);
			collegueDto.setId(newCollegue.getId());
			collegueDto.setMatricule(newCollegue.getMatricule());
			return ResponseEntity.ok(collegueDto);
		}else {
			return ResponseEntity.badRequest().body(resValid.getAllErrors());
		}		
	}
	

	@PutMapping
	public ResponseEntity<?> editUser(@RequestBody @Valid CollegueDto collegueDto, BindingResult resValid) {
		if (!resValid.hasErrors()) {
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
		}else {
			return ResponseEntity.badRequest().body(resValid.getAllErrors());
		}
	}

	@DeleteMapping
	public ResponseEntity<?> remUser(@RequestParam Integer id) {
		return ResponseEntity.ok(colServ.remUser(id));	
	}
}
