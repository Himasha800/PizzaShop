



import java.util.Scanner;

public class PaymentSystem {
    public static void processPayment(double amount) {
        Scanner sc = new Scanner(System.in);
        PaymentContext paymentContext = new PaymentContext();

        System.out.println("\n--- Payment Options ---");
        System.out.println("1. Credit Card");
        System.out.println("2. Digital Wallet");
        System.out.print("Choose your payment method: ");
        int choice = sc.nextInt();
        sc.nextLine(); 

        switch (choice) {
            case 1 -> paymentContext.setPaymentStrategy(new CreditCardPaymentStrategy());
            case 2 -> paymentContext.setPaymentStrategy(new DigitalWalletPaymentStrategy());
            default -> {
                System.out.println("Invalid payment method. Order canceled.");
                System.exit(0);
            }
        }
        paymentContext.executePayment(amount, sc);

        System.out.println("Payment processed successfully.");
    }
}



