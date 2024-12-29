

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class UserProfile {
    private String username;
    private List<Pizza> favoritePizzas = new ArrayList<>();
    static List<Feedback> feedbackList = new ArrayList<>(); 

    public UserProfile(String username) {
        this.username = username;
    }

    public void saveFavoritePizza(Pizza pizza) {
        favoritePizzas.add(pizza);
        System.out.println("Pizza saved to favorites!");
    }

    public void displayFavorites() {
        if (favoritePizzas.isEmpty()) {
            System.out.println("No favorite pizzas saved yet.");
            return;
        }

        System.out.println("\n--- Favorite Pizzas ---");
        for (Pizza pizza : favoritePizzas) {
            System.out.println("\n--- Pizza Summary ---");
            System.out.println("ID: " + pizza.getId()); 
            System.out.println("Pizza Name: " + pizza.getCustomName());
            System.out.println("Size: " + pizza.getSize());
            System.out.println("Sauce: " + pizza.getSauce());
            System.out.println("Cheese: " + pizza.getCheese());
            List<String> toppings = pizza.getToppings();
            List<String> extraToppings = pizza.getExtraToppings();
            System.out.println("Toppings: " + (toppings.isEmpty() ? "None" : String.join(", ", toppings.stream()
                    .filter(topping -> !extraToppings.contains(topping))
                    .toArray(String[]::new))));

            // Display special packaging
            if (pizza.getPackaging() == null || pizza.getPackaging().equals("No Special Packaging")) {
                System.out.println("Special Packaging: None");
            } else {
               System.out.println("Special Packaging: " + pizza.getPackaging());
            }

           // Display extra toppings
           if (pizza.getExtraToppings() == null || pizza.getExtraToppings().isEmpty()) {
              System.out.println("Extra Toppings: None");
            } else {
              System.out.println("Extra Toppings: " + String.join(", ", pizza.getExtraToppings()));
            } 

            System.out.printf("Pizza price with selected toppings (Extra Toppings & Packaging price): $%.2f%n", pizza.getPrice());
            
            SeasonalSpecialsManager specialsManager = new SeasonalSpecialsManager();
            SeasonalPromotions selectedPromo = specialsManager.getAllPromotions().get(0); 
            if (selectedPromo != null) {
                double discount = specialsManager.applyPromotion(pizza, selectedPromo);
                double totalPrice = pizza.getPrice() - discount;

                System.out.printf("Seasonal Discounts: $%.2f%n", discount);
            } else {
                System.out.println("No promotion selected.");
            } 
        }
    }

    public Pizza reorderFavoritePizza(int index) {
        if (index >= 1 && index <= favoritePizzas.size()) {
            System.out.println("Reordering your favorite pizza...");

            Pizza pizza = favoritePizzas.get(index - 1);

            Scanner sc = new Scanner(System.in);
            System.out.println("\nChoose Order Type: (1) Pickup (2) Delivery");
            int choice = sc.nextInt();
            sc.nextLine(); 

            String orderType = (choice == 2) ? "Delivery" : "Pickup";
            pizza.setOrderType(orderType);  

            String address = "";
            if (orderType.equals("Delivery")) {
                System.out.println("Enter Delivery Address:");
                address = sc.nextLine();
                System.out.println("A delivery fee of $3.00 will be added.");
            }

            System.out.println("Order Type: " + orderType);

            reviewOrder(pizza, orderType, address);

            PaymentSystem.processPayment(pizza.getPrice());  

            trackOrder(orderType);

            provideFeedback(pizza);

            return pizza;
        } else {
            System.out.println("Invalid selection.");
            return null;
        }
    }

    
    public void reviewOrder(Pizza pizza, String orderType, String address) {
         System.out.println("\n--- Pizza Summary ---");
         System.out.println("ID: " + pizza.getId());  
         System.out.println("Pizza Name: " + pizza.getCustomName());
         System.out.println("Size: " + pizza.getSize());
         System.out.println("Sauce: " + pizza.getSauce());
         System.out.println("Cheese: " + pizza.getCheese());

        List<String> toppings = pizza.getToppings();
            List<String> extraToppings = pizza.getExtraToppings();
            System.out.println("Toppings: " + (toppings.isEmpty() ? "None" : String.join(", ", toppings.stream()
                    .filter(topping -> !extraToppings.contains(topping))
                    .toArray(String[]::new))));
   
      

        // Display special packaging
        if (pizza.getPackaging() == null || pizza.getPackaging().equals("No Special Packaging")) {
            System.out.println("Special Packaging: None");
        } else {
            System.out.println("Special Packaging: " + pizza.getPackaging());
        }

        // Display extra toppings
        if (pizza.getExtraToppings() == null || pizza.getExtraToppings().isEmpty()) {
            System.out.println("Extra Toppings: None");
        } else {
            System.out.println("Extra Toppings: " + String.join(", ", pizza.getExtraToppings()));
        }


         System.out.printf("Pizza price with selected toppings (Extra Toppings & Packaging price):  $%.2f%n", pizza.getPrice());
         
        SeasonalSpecialsManager specialsManager = new SeasonalSpecialsManager();
        SeasonalPromotions selectedPromo = specialsManager.getAllPromotions().get(0);
        double discount = 0;
        if (selectedPromo != null) {
           discount = specialsManager.applyPromotion(pizza, selectedPromo);
           System.out.printf("Seasonal Discounts: $%.2f%n", discount);
        } else {
           System.out.println("No promotion selected.");
        }

        double priceAfterDiscount = pizza.getPrice() - discount;

        System.out.println("Order Type: " + orderType);

        if (orderType.equals("Delivery")) {
            
            double deliveryFee = 3.00;
            int timesFeeAdded = 1; 
            double totalPrice = priceAfterDiscount + deliveryFee;
    
            System.out.printf("Delivery Fee of $%.2f added %d time(s)%n", deliveryFee, timesFeeAdded);
            System.out.printf("Total Price after Discount and Delivery: $%.2f%n", totalPrice);
        } else {
            System.out.printf("Total Price after Discount: $%.2f%n", priceAfterDiscount);
        }

        System.out.println("Order placed successfully!\n");
    }

   
    public void trackOrder(String orderType) {
        OrderTracking tracker = new OrderTracking();
        tracker.startTracking(orderType);  
    }
    

    public String getUsername() {
        return username;
    }


     public void addFeedback(Feedback feedback) {
        feedbackList.add(feedback);
    }


    // highly-rated pizzas (ratings >= 4)
    public static void displayHighlyRatedPizzas() {
                  System.out.println("\n--- Highly-Rated Pizzas ---");
    for (Feedback feedback : feedbackList) {
        if (feedback.getRating() >= 4) { 
            feedback.displayFeedback();
            
           
        }
    }
}

   
    public void provideFeedback(Pizza pizza) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n--- Provide Feedback ---");
        System.out.println("Please rate your pizza experience (1 to 5): ");
        int rating = sc.nextInt();
        sc.nextLine(); 
        System.out.println("Leave your comments: ");
        String comments = sc.nextLine();
       
        Feedback feedback = new Feedback(pizza.getCustomName(), comments, rating);

        addFeedback(feedback);

        feedback.displayFeedback();

        System.out.println("\nThank you for your feedback!");
        System.out.println();
    }
    
    
}









