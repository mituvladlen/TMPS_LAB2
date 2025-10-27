package patterns.factory;

import domain.models.*;
import domain.enums.PaymentType;

/**
 * FACTORY METHOD PATTERN
 *
 * Purpose: Creates different types of payment methods without exposing creation logic.
 * Encapsulates the payment object creation process.
 *
 * Benefits:
 * - Centralizes payment method creation
 * - Easy to add new payment types
 * - Client doesn't need to know concrete payment classes
 */
public class PaymentFactory {

    /**
     * Factory Method to create appropriate payment based on payment type
     */
    public static Payment createPayment(PaymentType paymentType, Object... params) {
        switch (paymentType) {
            case CASH:
                if (params.length > 0 && params[0] instanceof Double) {
                    return new CashPayment((Double) params[0]);
                }
                throw new IllegalArgumentException("Cash payment requires amount given");

            case CREDIT_CARD:
                if (params.length >= 4) {
                    return new CreditCardPayment(
                        (String) params[0],  // card number
                        (String) params[1],  // card holder name
                        (String) params[2],  // expiry date
                        (String) params[3]   // cvv
                    );
                }
                throw new IllegalArgumentException("Credit card payment requires: cardNumber, cardHolderName, expiryDate, cvv");

            case MOBILE_PAYMENT:
                if (params.length >= 2) {
                    return new MobilePayment(
                        (String) params[0],  // phone number
                        (String) params[1]   // provider
                    );
                }
                throw new IllegalArgumentException("Mobile payment requires: phoneNumber, provider");

            case GIFT_CARD:
                if (params.length >= 2) {
                    return new GiftCardPayment(
                        (String) params[0],  // gift card number
                        (Double) params[1]   // balance
                    );
                }
                throw new IllegalArgumentException("Gift card payment requires: giftCardNumber, balance");

            default:
                throw new IllegalArgumentException("Unknown payment type: " + paymentType);
        }
    }

    // Convenience methods for specific payment types
    public static Payment createCashPayment(double amountGiven) {
        return createPayment(PaymentType.CASH, amountGiven);
    }

    public static Payment createCreditCardPayment(String cardNumber, String cardHolderName,
                                                   String expiryDate, String cvv) {
        return createPayment(PaymentType.CREDIT_CARD, cardNumber, cardHolderName, expiryDate, cvv);
    }

    public static Payment createMobilePayment(String phoneNumber, String provider) {
        return createPayment(PaymentType.MOBILE_PAYMENT, phoneNumber, provider);
    }

    public static Payment createGiftCardPayment(String giftCardNumber, double balance) {
        return createPayment(PaymentType.GIFT_CARD, giftCardNumber, balance);
    }
}

