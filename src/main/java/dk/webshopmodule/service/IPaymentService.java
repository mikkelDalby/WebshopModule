package dk.webshopmodule.service;

import dk.webshopmodule.model.Payment;

import java.util.List;

public interface IPaymentService {
    void createPayment(Payment payment);
    List<Payment> fetchAllPayments();
    Payment fetchOnePayment(int id);
    void updatePayment(Payment payment);
    void deletePayment(int id);
}