package domain.models;

import domain.enums.PaymentType;

public class MobilePayment implements Payment {
    private String phoneNumber;
    private String provider; // Apple Pay, Google Pay, etc.

    public MobilePayment(String phoneNumber, String provider) {
        this.phoneNumber = phoneNumber;
        this.provider = provider;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing mobile payment via " + provider + "...");
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Sending payment request to mobile device...");
        System.out.println("Payment confirmed!");
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.MOBILE_PAYMENT;
    }

    @Override
    public String getPaymentDetails() {
        return "Mobile Payment - " + provider + " (" + phoneNumber + ")";
    }
}

