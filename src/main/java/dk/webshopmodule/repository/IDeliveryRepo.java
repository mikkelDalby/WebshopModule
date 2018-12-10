package dk.webshopmodule.repository;

import dk.webshopmodule.model.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDeliveryRepo extends JpaRepository<Delivery, Integer> {
}
