import java.util.Scanner;

interface PaymentStrategy {
    void processPayment(double amount, Scanner sc);
}
