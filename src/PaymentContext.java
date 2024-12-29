import java.util.Scanner;

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }


    public void executePayment(double amount, Scanner sc) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment method not selected!");
        }
        paymentStrategy.processPayment(amount, sc);
    }
}


