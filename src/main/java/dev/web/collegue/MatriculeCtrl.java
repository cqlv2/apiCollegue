package dev.web.collegue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.services.CollegueService;

@RestController
@RequestMapping("matricule")
public class MatriculeCtrl {
	
	private CollegueService colServ;

	public MatriculeCtrl(CollegueService colServ) {
		this.colServ = colServ;
	}

	@GetMapping
	public List<String> getAllMatricules() {
		return colServ.getAllMatricules();
	}
	
	@GetMapping(params = {"type", "value"})
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
}
