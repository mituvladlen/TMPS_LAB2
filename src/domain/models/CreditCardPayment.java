package domain.models;

import domain.enums.PaymentType;

public class CreditCardPayment implements Payment {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment...");
        System.out.println("Card Holder: " + cardHolderName);
        System.out.println("Card Number: **** **** **** " + cardNumber.substring(Math.max(0, cardNumber.length() - 4)));
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Authorizing transaction...");
        System.out.println("Payment successful!");
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CREDIT_CARD;
    }

    @Override
    public String getPaymentDetails() {
        return "Credit Card - " + cardHolderName + " (**** " +
               cardNumber.substring(Math.max(0, cardNumber.length() - 4)) + ")";
    }
}

