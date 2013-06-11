import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.ServletWebRequest;

import be.ordina.domain.Pizza;
import be.ordina.domain.Topping;
import be.ordina.service.MyUserDetailsService;
import be.ordina.web.PizzaController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/webmvc-config.xml",
		"/META-INF/spring/applicationContext.xml",
		"/META-INF/spring/applicationContext-jpa.xml",
		"/META-INF/spring/applicationContext-security.xml" })
@Transactional // default rollback
public class WebTest {

	@Autowired
	WebApplicationContext wac; // cached

	@Autowired
	MockServletContext servletContext; // cached

	@Autowired
	MockHttpSession session;

	@Autowired
	MockHttpServletRequest request;

	@Autowired
	MockHttpServletResponse response;

	@Autowired
	ServletWebRequest webRequest;

	@Autowired
	PizzaController pizzaController;

	@Autowired
	MyUserDetailsService myUserDetailsService;

	private MockMvc mockMvc;

	@Before
	public void beforeTest() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void findPizzasGet() throws Exception {
		this.mockMvc.perform(get("/pizzas").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.[*].name").value("pizza"));
	}
	
	@Test
	public void findPizzasGetId() throws Exception {
		this.mockMvc.perform(get("/pizzas/{Id}", 138).accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/json"))
			.andExpect(jsonPath("$.[*].name").value("pizza"));
	}
	
	@Test
	public void findPizzasPost() throws Exception {
		Pizza pizza = createPizza();
		this.mockMvc.perform(post("/pizzas").contentType( MediaType.APPLICATION_JSON)
				.content(pizza.toJson().getBytes()))
				.andExpect(status().isOk())
				.andExpect(content().string("pizza posted"));
	}

	@Test
	public void findPizzas() {
		Locale locale = request.getLocale();
		List<Pizza> pizzas = pizzaController.findPizzas(locale, request);
		Assert.notEmpty(pizzas);
	}

	@Test
	public void addPizza() {
		Pizza pizza = createPizza();
		pizzaController.addPizza(pizza);
	}

	@Test(expected = AccessDeniedException.class)
	public void deletePizza() {
		UserDetails user = myUserDetailsService.loadUserByUsername("pat");
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				user, user.getPassword(), user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

		pizzaController.deletePizza(1L);
	}
	
	private Pizza createPizza() {
		Pizza pizza = new Pizza();
		pizza.setName("pizza");
		Topping topping = new Topping();
		topping.setName("tomaat");
		Set<Topping> toppings = new HashSet<Topping>();
		toppings.add(topping);
		pizza.setToppings(toppings);
		return pizza;
	}

}
