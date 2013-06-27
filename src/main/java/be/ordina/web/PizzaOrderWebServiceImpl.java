package be.ordina.web;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import be.ordina.domain.Pizza;
import be.ordina.service.PizzaService;


@WebService(endpointInterface="be.ordina.web.PizzaOrderWebService") 
public class PizzaOrderWebServiceImpl extends SpringBeanAutowiringSupport implements PizzaOrderWebService{
	
	@Autowired
	PizzaService pizzaService;

	public PizzaOrderWebServiceImpl() {}

	@Override
	public Pizza findPizza(Long id) {
		Pizza pizza = pizzaService.findPizza(id);
		return pizza; 
	}

}
