import java.util.Scanner;

class DigitalWalletPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount, Scanner sc) {
        System.out.println("\n--- Digital Wallet Payment ---");
        System.out.print("Enter Wallet ID/Email: ");
        String walletId = sc.nextLine();
        System.out.printf("Processing payment via Digital Wallet...\n", amount);
    }
}




