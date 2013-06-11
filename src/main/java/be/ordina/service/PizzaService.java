package be.ordina.service;

import be.ordina.domain.Pizza;

import java.math.BigDecimal;
import java.util.List;

public interface PizzaService {

	public abstract long countAllPizzas();


	public abstract void deletePizza(Pizza pizza);


	public abstract Pizza findPizza(Long id);


	public abstract List<Pizza> findAllPizzas();


	public abstract List<Pizza> findPizzaEntries(int firstResult, int maxResults);


	public abstract void savePizza(Pizza pizza);


	public abstract Pizza updatePizza(Pizza pizza);
	
	public abstract BigDecimal priceCalculation();

}
