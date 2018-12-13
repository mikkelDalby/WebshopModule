package dk.webshopmodule.repository;

import dk.webshopmodule.model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderlineRepo extends JpaRepository<OrderLine, Integer> {
}