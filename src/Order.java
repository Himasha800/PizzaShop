











import java.util.Scanner;

class Order<T extends LoyaltyProgramInterface> {
    private Pizza pizza;
    private String orderType;
    private String address;
    private final double DELIVERY_FEE = 3.00;
    private T loyaltyProgram;

    public Order(T loyaltyProgram) {
        this.loyaltyProgram = loyaltyProgram;
    }

    public Order() {
    }

    public Order(Pizza pizza) {
        this.pizza = pizza;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void placeOrder() {
        Pizza.PizzaBuilder builder = new Pizza.PizzaBuilder();

        pizza = builder
            .chooseBasePizza()
            .promptCustomization()
            .chooseSize()
            .chooseSauce()
            .chooseToppings()
            .chooseCheese()
            .build();
            builder.chooseExtraToppings(pizza)
            .chooseSpecialPackaging(pizza);

        Scanner sc = new Scanner(System.in);
        System.out.println("\nChoose Order Type: (1) Pickup (2) Delivery");
        int choice = sc.nextInt();
        sc.nextLine();

        orderType = choice == 2 ? "Delivery" : "Pickup";
        System.out.println();

        if (orderType.equals("Delivery")) {
            System.out.println("Enter Delivery Address:");
            address = sc.nextLine();
            System.out.println("A delivery fee of $3.00 will be added.\n");
        }

        pizza.displayPizzaDetails(orderType); // Display PizzaDetails

        System.out.println("Order Type: " + orderType);
       
        double totalPrice = pizza.getPrice() + (orderType.equals("Delivery") ? DELIVERY_FEE : 0);
        PaymentSystem.processPayment(totalPrice);

       
        if (loyaltyProgram != null) {
            if (loyaltyProgram instanceof LoyaltyProgram) {
                System.out.println(); 
                ((LoyaltyProgram) loyaltyProgram).processLoyaltyProgram(pizza, orderType);
            }
            System.out.println("Would you like to redeem points? (yes/no)");
            String redeemResponse = sc.nextLine();
            if (redeemResponse.equalsIgnoreCase("yes")) {
                loyaltyProgram.redeemPoints();
                // ((LoyaltyProgram) loyaltyProgram).checkRewardEligibility(); 
               
            }
        }

        System.out.println("\nOrder placed successfully!");

        trackOrder();

        provideFeedback();
    }

    public void reviewOrder() {
        System.out.println("\n--- Pizza Summary ---");
        pizza.displayPizzaDetails(orderType);
        System.out.println("Order Type: " + orderType);

        if (orderType.equals("Delivery")) {
            System.out.println("Delivery Address: " + address);
        }

        System.out.println("Order placed successfully!\n");
    }

    public void trackOrder() {
        OrderTracking tracker = new OrderTracking();
        tracker.startTracking(orderType);
    }

    public void provideFeedback() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--- Provide Feedback ---");
        System.out.println("Please rate your pizza experience (1 to 5): ");
        int rating = sc.nextInt();
        sc.nextLine(); 

        System.out.println("Leave your comments: ");
        String comments = sc.nextLine();

        Feedback feedback = new Feedback(pizza.getCustomName(), comments, rating);

        feedback.displayFeedback();
        feedback.askToSaveFeedback();

        System.out.println("\nThank you for your feedback!");
        System.out.println(); 
    }

  
}












