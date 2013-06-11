package be.ordina.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import be.ordina.domain.Authorities;
import be.ordina.domain.Person;
import be.ordina.repository.PersonRepository;

@Controller
public class LoginLogoutController {
	
	private static Logger logger = Logger.getLogger(LoginLogoutController.class);
	
	@Autowired
	PersonRepository personRepository;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String createUser() {
		 Person user = new  Person();
		 user.setUsername("pat");
		 user.setPassword("pat");
		 user.setEnabled(true);
		 Authorities authority = new Authorities();
		 authority.setAuthority("ROLE_USER");
		 authority.setUsername(user.getUsername());
		 List<Authorities> authoritiesList = new ArrayList<Authorities>();
		 authoritiesList.add(authority);
		 user.setAuthorities(authoritiesList);
		 Person saved = personRepository.findByUsername(user.getUsername());
		 if (saved == null) {
			 personRepository.save(user);
		}	
		return "login";
	}
}
