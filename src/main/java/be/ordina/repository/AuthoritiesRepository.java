package be.ordina.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import be.ordina.domain.Authorities;

@RooJpaRepository(domainType = Authorities.class)
public interface AuthoritiesRepository extends JpaRepository<Authorities, Long>, JpaSpecificationExecutor<Authorities> {
	
	List<Authorities> findByUsername(String username);
}
