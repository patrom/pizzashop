package be.ordina.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import be.ordina.domain.Authorities;
import be.ordina.domain.Person;
import be.ordina.repository.AuthoritiesRepository;
import be.ordina.repository.PersonRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	PersonRepository personRepository;
	
	@Autowired
	AuthoritiesRepository authoritiesRepository;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		List<Authorities> authorities = authoritiesRepository.findByUsername(username);
		Person person = personRepository.findByUsername(username);
		User user = new User(person.getUsername(), person.getPassword(), person.isEnabled(), true, true, true, authorities);
		return user;
	}

	
}
