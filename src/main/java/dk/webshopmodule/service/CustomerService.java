package dk.webshopmodule.service;

import dk.webshopmodule.model.Customer;
import dk.webshopmodule.repository.ICustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    ICustomerRepo customerRepo;

    @Override
    public int addCustomer(Customer customer) {
        return customerRepo.save(customer).getId();
    }
}
