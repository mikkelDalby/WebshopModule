package dk.webshopmodule.repository;

import dk.webshopmodule.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepo extends JpaRepository<Payment, Integer> {
}