
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Pizza Shop!");
        System.out.println();  
        System.out.print("Enter your name: ");
        String username = sc.nextLine();

        UserProfile user = new UserProfile(username);
        LoyaltyProgram loyaltyProgram = new LoyaltyProgram(); 

        SeasonalSpecialsManager specialsManager = new SeasonalSpecialsManager();
        specialsManager.displayAllPromotions(); // Display available promotions
        

        while (true) {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Customize and Order Pizza");
            System.out.println("2. View Favorite Pizzas");
            System.out.println("3. Reorder Favorite Pizza");
            System.out.println("4. View Highly-Rated Pizzas");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine(); 

            System.out.println(); 

            switch (choice) {
                case 1 -> {
                    Order<LoyaltyProgram> order = new Order<>(loyaltyProgram);
                    order.placeOrder();

                    System.out.println("Save this pizza to your favorites? (yes/no)");
                    String saveResponse = sc.nextLine();
                    if (saveResponse.equalsIgnoreCase("yes")) {
                        user.saveFavoritePizza(order.getPizza());
                    }
                }
                case 2 -> user.displayFavorites();
                case 3 -> {
                    user.displayFavorites();
                    System.out.print("Select a pizza number to reorder: ");
                    int index = sc.nextInt();
                    sc.nextLine();
                    user.reorderFavoritePizza(index); // Reordering handled within the UserProfile class
                }
                case 4 -> user.displayHighlyRatedPizzas(); // Show highly-rated pizzas
                case 5 -> {
                    System.out.println("Thank you for visiting the Pizza Shop!");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}





