




import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SeasonalSpecialsManager {
    private List<SeasonalPromotions> promotions;

    public SeasonalSpecialsManager() {
        promotions = new ArrayList<>();
        loadDefaultPromotions();
    }

    private void loadDefaultPromotions() {
        int currentYear = LocalDate.now().getYear();

        // Winter Warmth Special
        SeasonalPromotions winterPromo = new SeasonalPromotions(
            "Winter Delight Special", 
            LocalDate.of(currentYear, 12, 1), 
            LocalDate.of(currentYear + 1, 2, 28)
        );
        winterPromo.addToppingDiscount("Pepperoni", 0.5);
        winterPromo.addToppingDiscount("Bacon", 0.4);
        winterPromo.addSizeDiscount("Small", 1.5);
        promotions.add(winterPromo);

        // Summer Splash Special
        SeasonalPromotions summerPromo = new SeasonalPromotions(
            "Summer Breeze Special", 
            LocalDate.of(currentYear, 6, 1), 
            LocalDate.of(currentYear, 8, 31)
        );
        summerPromo.addToppingDiscount("Mushrooms", 0.3);
        summerPromo.addSizeDiscount("Party Size/Jumbo", 1.0);
        promotions.add(summerPromo);
    }

    public List<SeasonalPromotions> getAllPromotions() {
        return promotions;
    }

    public void displayAllPromotions() {
        System.out.println("\n--- Seasonal Promotions ---");
        for (SeasonalPromotions promo : promotions) {
            if (promo.isActive()) {
                promo.displayPromotionDetails();
            } else {
                System.out.println(); 
                System.out.printf("Promotion '%s' is not active.%n", promo.getName());
            }
        }
    }

    public double applyPromotion(Pizza pizza, SeasonalPromotions promotion) {
        if (!promotion.isActive()) {
            System.out.println("This promotion is not currently active.");
            return 0.0;
        }

        DiscountHandler toppingHandler = new ToppingDiscountHandler();
        DiscountHandler sizeHandler = new SizeDiscountHandler();

        toppingHandler.setNextHandler(sizeHandler);

        return toppingHandler.applyDiscount(pizza, promotion);
    }
}
