package be.ordina.service;

import be.ordina.domain.PizzaOrder;
import be.ordina.domain.PizzaOrderPk;
import java.util.List;

public interface PizzaOrderService {

	public abstract long countAllPizzaOrders();


	public abstract void deletePizzaOrder(PizzaOrder pizzaOrder);


	public abstract PizzaOrder findPizzaOrder(PizzaOrderPk id);


	public abstract List<PizzaOrder> findAllPizzaOrders();


	public abstract List<PizzaOrder> findPizzaOrderEntries(int firstResult, int maxResults);


	public abstract void savePizzaOrder(PizzaOrder pizzaOrder);


	public abstract PizzaOrder updatePizzaOrder(PizzaOrder pizzaOrder);

}
