















import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class SeasonalPromotions {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private Map<String, Double> toppingDiscounts = new HashMap<>();
    private Map<String, Double> sizeDiscounts = new HashMap<>();

    public SeasonalPromotions(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addToppingDiscount(String topping, double discount) {
        toppingDiscounts.put(topping, discount);
    }

    public void addSizeDiscount(String size, double discount) {
        sizeDiscounts.put(size, discount);
    }

    public String getName() {
        return name;
    }

    public double getToppingDiscount(String topping) {
        return toppingDiscounts.getOrDefault(topping, 0.0);
    }

    public double getSizeDiscount(String size) {
        return sizeDiscounts.getOrDefault(size, 0.0);
    }

    public boolean isActive() {
        LocalDate today = LocalDate.now();
        return !today.isBefore(startDate) && !today.isAfter(endDate);
    }

    public void displayPromotionDetails() {
    
        System.out.println("\nAvailable Promotion: " + name);
        System.out.printf("Valid from %s to %s%n", startDate, endDate);
        System.out.println(); 
        System.out.println("Topping Discounts:");
        toppingDiscounts.forEach((topping, discount) -> 
            System.out.printf("%s: $%.2f off%n", topping, discount)
        );
        System.out.println(); 
        System.out.println("Size Discounts:");
        sizeDiscounts.forEach((size, discount) -> 
            System.out.printf("%s: $%.2f off%n", size, discount)
        );
    }
}





