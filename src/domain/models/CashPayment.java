package domain.models;

import domain.enums.PaymentType;

public class CashPayment implements Payment {
    private double amountGiven;
    private double change;

    public CashPayment(double amountGiven) {
        this.amountGiven = amountGiven;
    }

    @Override
    public void processPayment(double amount) {
        if (amountGiven >= amount) {
            change = amountGiven - amount;
            System.out.println("Processing cash payment...");
            System.out.println("Amount Given: $" + String.format("%.2f", amountGiven));
            System.out.println("Total: $" + String.format("%.2f", amount));
            System.out.println("Change: $" + String.format("%.2f", change));
            System.out.println("Payment successful!");
        } else {
            System.out.println("Insufficient cash! Need $" + String.format("%.2f", amount - amountGiven) + " more.");
        }
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CASH;
    }

    @Override
    public String getPaymentDetails() {
        return "Cash Payment - Amount Given: $" + String.format("%.2f", amountGiven);
    }

    public double getChange() {
        return change;
    }
}

