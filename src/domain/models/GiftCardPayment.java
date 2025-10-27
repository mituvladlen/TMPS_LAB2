package domain.models;

import domain.enums.PaymentType;

public class GiftCardPayment implements Payment {
    private String giftCardNumber;
    private double balance;

    public GiftCardPayment(String giftCardNumber, double balance) {
        this.giftCardNumber = giftCardNumber;
        this.balance = balance;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing gift card payment...");
        System.out.println("Gift Card: **** **** " + giftCardNumber.substring(Math.max(0, giftCardNumber.length() - 4)));
        System.out.println("Current Balance: $" + String.format("%.2f", balance));
        System.out.println("Amount: $" + String.format("%.2f", amount));

        if (balance >= amount) {
            balance -= amount;
            System.out.println("Remaining Balance: $" + String.format("%.2f", balance));
            System.out.println("Payment successful!");
        } else {
            System.out.println("Insufficient balance! Need $" + String.format("%.2f", amount - balance) + " more.");
        }
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.GIFT_CARD;
    }

    @Override
    public String getPaymentDetails() {
        return "Gift Card - Balance: $" + String.format("%.2f", balance);
    }

    public double getBalance() {
        return balance;
    }
}

