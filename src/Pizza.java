



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Pizza {
    private static int idCounter = 1; 
    private final int id; 
    private final String size;
    private final String sauce;
    private final List<String> toppings;
    private final String cheese;
    private final String customName;
    private double price;
        private String packaging;
            private String orderType;
              private final List<String> extraToppings;
        
            private Pizza(PizzaBuilder builder) {
                this.id = idCounter++;
                this.size = builder.size;
                this.sauce = builder.sauce;
                this.toppings = builder.toppings;
                this.cheese = builder.cheese;
                this.customName = builder.customName;
                this.price = builder.price;
                this.packaging = builder.packaging;
                this.extraToppings = builder.extraToppings; 
                
            }
            public static class PizzaBuilder {
                private String size;
                private String sauce;
                private List<String> toppings = new ArrayList<>();
                private String cheese;
                private String customName;
                private double price;
                private String packaging;
                private List<String> extraToppings = new ArrayList<>(); 
        
                public void displayPizzaTypes() {
                    System.out.println("Available Pizza Types:");
                    System.out.println("1. Margherita - 8.00");
                    System.out.println("2. Pepperoni - 9.50");
                    System.out.println("3. Veggie Delight - 9.00");
                    System.out.println("4. Meat Lover's - 11.00");
                }
                public PizzaBuilder chooseBasePizza() {
                    Scanner sc = new Scanner(System.in);
                    displayPizzaTypes();
                    System.out.println("Choose a base pizza by entering the corresponding number:");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1 -> {
                            customName = "Margherita";
                            price = 8.00;
                        }
                        case 2 -> {
                            customName = "Pepperoni";
                            price = 9.50;
                        }
                        case 3 -> {
                            customName = "Veggie Delight";
                            price = 9.00;
                        }
                        case 4 -> {
                            customName = "Meat Lover's";
                            price = 11.00;
                        }
                        default -> {
                            System.out.println("Invalid choice. Defaulting to Margherita.");
                        }
                    }
                    return this;
                }
                    
                    public PizzaBuilder promptCustomization() {
                        System.out.printf("\nYou have selected a %s pizza.%n", customName);
                        System.out.println("Let's customize the type of pizza you have chosen as you like!");
                        System.out.println(); 
                    return this;
                }
        
                public PizzaBuilder chooseSize() {
                    System.out.println("Choose size: (1) Small (2) Medium (3) Large (4) Party Size/Jumbo");
                    int choice = new Scanner(System.in).nextInt();
                    size = switch (choice) {
                        case 1 -> "Small";
                        case 2 -> "Medium";
                        case 3 -> "Large";
                        case 4 -> "Party Size/Jumbo";
                        default -> "Medium";
                    };
                    return this;
                }
        
                public PizzaBuilder chooseSauce() {
                    System.out.println("Choose sauce: (1) Tomato (2) Barbecue (3) Marinara (4) Garlic Butter (5) Alfredo");
                    int choice = new Scanner(System.in).nextInt();
                    sauce = switch (choice) {
                        case 1 -> "Tomato";
                        case 2 -> "Barbecue";
                        case 3 -> "Marinara";
                        case 4 -> "Garlic Butter";
                        case 5 -> "Alfredo";
                        default -> "Tomato";
                    };
                    return this;
                }
        
                public PizzaBuilder chooseToppings() {
                    String[] availableToppings = {
                        "Pepperoni", "Mushrooms", "Onions",
                        "Sausage", "Bacon", "Extra cheese",
                        "Black olives", "Green peppers"
                    };
            
                    System.out.println("Available toppings: ");
                    for (int i = 0; i < availableToppings.length; i++) {
                        System.out.printf("(%d) %s%n", i + 1, availableToppings[i]);
                    }
            
                    System.out.println("Enter topping numbers separated by commas (e.g., 1,3,5) or type 0 to finish:");
                    Scanner sc = new Scanner(System.in);
            
                    while (true) {
                        String input = sc.nextLine().trim();
            
                        if (input.equals("0")) break;
            
                        String[] choices = input.split(",");
                        boolean validChoice = false;
            
                        for (String choice : choices) {
                            try {
                                int toppingIndex = Integer.parseInt(choice.trim());
                                if (toppingIndex >= 1 && toppingIndex <= availableToppings.length) {
                                    toppings.add(availableToppings[toppingIndex - 1]);
                                    price += 1.50; // Each topping costs 1.50
                                    validChoice = true;
                                } else {
                                    System.out.printf("Invalid topping number: %s. Try again.%n", choice);
                                }
                            } catch (NumberFormatException e) {
                                System.out.printf("Invalid input: %s. Please enter numbers only.%n", choice);
                            }
                        }
            
                        if (validChoice) break;
                    }
                    return this;
                }
        
        
                public PizzaBuilder chooseCheese() {
                    System.out.println("Choose cheese: (1) Mozzarella (2) Cheddar (3) Parmesan (4) No Cheese");
                    int choice = new Scanner(System.in).nextInt();
                    cheese = switch (choice) {
                        case 1 -> "Mozzarella";
                        case 2 -> "Cheddar";
                        case 3 -> "Parmesan";
                        default -> "No Cheese";
                    };
                    return this;
                }
        
        

            public PizzaBuilder chooseExtraToppings(Pizza pizza) {
                Scanner sc = new Scanner(System.in);
                System.out.println(); 
                System.out.println("Would you like to add extra toppings? (yes/no)");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println(); 
                    System.out.println("Available extra toppings: ");
                    String[] availableExtraToppings = {"Fresh basil", "Ham", "Goat Cheese", "Pineapple"};
                    double costPerTopping = 2.00;
            
                    for (int i = 0; i < availableExtraToppings.length; i++) {
                        System.out.printf("(%d) %s ($%.2f)%n", i + 1, availableExtraToppings[i], costPerTopping);
                    }
            
                    System.out.println("Enter topping numbers separated by commas (e.g., 1,3) or type 0 to finish:");
                    String[] choices = sc.nextLine().split(",");
                    for (String choice : choices) {
                        try {
                            int index = Integer.parseInt(choice.trim()) - 1;
                            if (index >= 0 && index < availableExtraToppings.length) {
                                extraToppings.add(availableExtraToppings[index]);
                                price += costPerTopping; // Add cost 
                                new ExtraToppingDecorator(availableExtraToppings[index], costPerTopping).customize(pizza);
                            } else if (choice.equals("0")) {
                                break;
                            } else {
                                System.out.println("Invalid choice. Skipping.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Skipping.");
                        }
                    }
                } else {
                    extraToppings.clear(); 
                }
                return this;
            }
            

            public PizzaBuilder chooseSpecialPackaging(Pizza pizza) {
                Scanner sc = new Scanner(System.in);
                System.out.println(); 
                System.out.println("Would you like special packaging? (yes/no)");
                if (sc.nextLine().equalsIgnoreCase("yes")) {
                    System.out.println(); 
                    System.out.println("Available packaging options:");
                    String[] packagingOptions = {"Luxury Box", "Reusable Box", "Gift Wrap"};
                    double[] packagingCosts = {5.00, 4.00, 3.50};
                    for (int i = 0; i < packagingOptions.length; i++) {
                        System.out.printf("(%d) %s ($%.2f)%n", i + 1, packagingOptions[i], packagingCosts[i]);
                    }
            
                    System.out.println("Enter your choice (1-3):");
                    try {
                        int choice = sc.nextInt() - 1;
                        if (choice >= 0 && choice < packagingOptions.length) {
                            new SpecialPackagingDecorator(packagingOptions[choice], packagingCosts[choice]).customize(pizza);
                        } else {
                            System.out.println("Invalid choice. No special packaging added.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. No special packaging added.");
                    }
                } else {
                    packaging = "No Special Packaging"; 
                }
                return this;
            }
            
        
        
        
                public Pizza build() {
                    return new Pizza(this);
                }
            }
        
            public String getSize() {
                return size;
            }
        
            
        
            public String getSauce() {
                return sauce;
            }
        
            public List<String> getToppings() {
                return toppings;
            }
        
            public String getCheese() {
                return cheese;
            }
        
            public String getCustomName() {
                return customName;
            }
        
            public double getPrice() {
                return price;
            }
        
            public String getPackaging() {
                return packaging;
            }
        
            public int getId() {
                return id;
            }
        
        
            public String getOrderType() {
                return orderType;
            }

            public List<String> getExtraToppings() {
                return extraToppings; 
            }
        
            public void setOrderType(String orderType) {
                this.orderType = orderType;
            }
        
            public void setPackaging(String packaging) {
                this.packaging = packaging;
        }
    
        public void setPrice(double price) {
            this.price = price;
    }

    

    public double displayPizzaDetails(String orderType) {
        Scanner sc = new Scanner(System.in);
        SeasonalSpecialsManager specialsManager = new SeasonalSpecialsManager();
    
        System.out.println("Select a seasonal promotion (enter the number): ");
        System.out.println("1. Winter Delight Special");
        System.out.println("2. Summer Breeze Special");
        int promoChoice = sc.nextInt();
        sc.nextLine(); 
    
        SeasonalPromotions selectedPromo = null;
        if (promoChoice == 1) {
            selectedPromo = specialsManager.getAllPromotions().get(0);
        } else if (promoChoice == 2) {
            selectedPromo = specialsManager.getAllPromotions().get(1);
        }
    
        System.out.println("\n--- Pizza Summary ---");
        System.out.println("ID: " + id); 
        System.out.println("Pizza Name: " + customName);
        System.out.println("Size: " + size);
        System.out.println("Sauce: " + sauce);
        System.out.println("Cheese: " + cheese);
        System.out.println("Toppings: " + (toppings.isEmpty() ? "None" : String.join(", ", toppings.stream()
        .filter(topping -> !extraToppings.contains(topping))
        .toArray(String[]::new))));

        if (packaging == null) {
            packaging = "No Special Packaging";
        }
        
        if (packaging.equals("No Special Packaging")) {
            System.out.println("Special Packaging: None");
        } else {
            System.out.println("Special Packaging: " + packaging);
        }
        if (extraToppings == null || extraToppings.isEmpty()) {
            System.out.println("Extra Toppings: None");
        } else {
            System.out.println("Extra Toppings: " + String.join(", ", extraToppings));
        }
        
        
        System.out.printf("Pizza price with selected toppings (Extra Toppings & Packaging price): $%.2f%n", price);
    
        double deliveryFee = 3.00; 
        double totalPrice = price;
    
        if (selectedPromo != null) {
            double discount = specialsManager.applyPromotion(this, selectedPromo);
            totalPrice -= discount;
    
            System.out.printf("Seasonal Discounts: $%.2f%n", discount);
        }
    
        if (orderType.equalsIgnoreCase("Delivery")) {
            totalPrice += deliveryFee;
            System.out.printf("Delivery Fee: $%.2f%n", deliveryFee);
            System.out.printf("Total Price after Discount and Delivery Fee: $%.2f%n", totalPrice);
        } else {
            System.out.printf("Total Price after Discount: $%.2f%n", totalPrice);
        }
    
        return totalPrice; 
    }
    
    
    
    
}
