package domain.models;

import domain.enums.PaymentType;

public interface Payment {
    void processPayment(double amount);
    PaymentType getPaymentType();
    String getPaymentDetails();
}

