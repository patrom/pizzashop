package be.ordina.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import be.ordina.domain.Person;

@RooJpaRepository(domainType = Person.class)
public interface PersonRepository extends JpaSpecificationExecutor<Person>, JpaRepository<Person, Long>{
	
	Person findByUsername(String username);
}
