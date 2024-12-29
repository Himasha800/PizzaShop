
public interface DiscountHandler {
    double applyDiscount(Pizza pizza, SeasonalPromotions promotion);
    void setNextHandler(DiscountHandler nextHandler);
}
