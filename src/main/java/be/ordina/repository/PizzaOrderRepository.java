package be.ordina.repository;

import be.ordina.domain.PizzaOrder;
import be.ordina.domain.PizzaOrderPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaOrderRepository extends JpaSpecificationExecutor<PizzaOrder>, JpaRepository<PizzaOrder, PizzaOrderPk> {
}
