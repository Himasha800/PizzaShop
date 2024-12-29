import java.util.Scanner;

class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount, Scanner sc) {
        System.out.println("\n--- Credit Card Payment ---");
        System.out.print("Enter Card Number: ");
        String cardNumber = sc.nextLine();
        System.out.print("Enter Expiry Date (MM/YY): ");
        String expiryDate = sc.nextLine();
        System.out.print("Enter CVV: ");
        String cvv = sc.nextLine();
        System.out.printf("Processing payment via Credit Card...\n", amount);
      
    }
}




