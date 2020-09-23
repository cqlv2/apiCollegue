package dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import dev.entite.Collegue;

public interface CollegueRepository extends JpaRepository<Collegue, Integer>{

	@Query("select c.matricule from Collegue c where c.nom=?1")
	List<String> findMatriculeBynom(String nom);

	

	List<Collegue>findAllBynom(String nom);


	@Query("select c from Collegue c where c.matricule=?1")
	Optional<Collegue> findByMatricule(String matricule);
	
}
