package dk.webshopmodule.service;

import dk.webshopmodule.model.Payment;
import dk.webshopmodule.repository.IPaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentImpl implements IPaymentService{

    @Autowired
    IPaymentRepo paymentRepo;

    @Override
    public void createPayment(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public List<Payment> fetchAllPayments() {
        return paymentRepo.findAll();
    }

    @Override
    public Payment fetchOnePayment(int id) {
        return paymentRepo.getOne(id);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentRepo.save(payment);
    }

    @Override
    public void deletePayment(int id) {
        paymentRepo.deleteById(id);
    }
}