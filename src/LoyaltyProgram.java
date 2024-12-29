

public class LoyaltyProgram implements LoyaltyProgramInterface {
    private double points; 
    private double earnedPoints; 
    private static final double POINTS_PER_DOLLAR = 1; // Points earned per dollar spent
    private static final double DISCOUNT_THRESHOLD = 10; // Points needed for a discount
    private static final double FREE_ITEM_THRESHOLD = 50; // Points needed for a free item

    public LoyaltyProgram() {
        this.points = 0;
        this.earnedPoints = 0;
    }

    @Override
    public void addPoints(double totalPrice) {
        earnedPoints = totalPrice * POINTS_PER_DOLLAR; 
        this.points += earnedPoints; 
        System.out.println("Added " + earnedPoints + " loyalty points for a purchase of $" + totalPrice + ". Total points: " + this.points);
    }

    public void processLoyaltyProgram(Pizza pizza, String orderType) {
        double totalPrice = pizza.getPrice(); 
        SeasonalSpecialsManager specialsManager = new SeasonalSpecialsManager();
        SeasonalPromotions selectedPromo = specialsManager.getAllPromotions().get(0);

        if (selectedPromo != null) {
            double discount = specialsManager.applyPromotion(pizza, selectedPromo);
            totalPrice -= discount; 
        }
        if (orderType.equalsIgnoreCase("Delivery")) {
            totalPrice += 3.00; 
        }

        this.addPoints(totalPrice); 
    }

    @Override
    public void redeemPoints() {
        if (points >= FREE_ITEM_THRESHOLD) {
            System.out.println("Congratulations! You have " + earnedPoints + " points earned from your last purchase, "
                    + "enough for a FREE item! Redeeming now...");
            points = 0; // Free
            System.out.println("You are eligible for a free item!");
        } else if (points >= DISCOUNT_THRESHOLD) {
            double discount = points * 0.10;
            // discount to two decimal places
            System.out.println("You earned " + earnedPoints + " points from your last purchase and have enough points "
                    + "for a discount of $" + String.format("%.2f", discount) + "!");
            points = 0; // Discount
            System.out.println("You are eligible for a discount!");
        } else {
            System.out.println("You earned " + earnedPoints + " points from your last purchase. Total points: " + points + ". "
                    + "Earn at least " + (DISCOUNT_THRESHOLD - points) + " more points to get rewards.");
            System.out.println("Keep shopping to earn rewards!"); // Not enough points
        }
    }

    public double getPoints() {
        return points;
    }


}
