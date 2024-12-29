

public class SizeDiscountHandler implements DiscountHandler {
    private DiscountHandler nextHandler;

    @Override
    public double applyDiscount(Pizza pizza, SeasonalPromotions promotion) {
        double discount = promotion.getSizeDiscount(pizza.getSize());

        if (nextHandler != null) {
            discount += nextHandler.applyDiscount(pizza, promotion);
        }

        return discount;
    }

    @Override
    public void setNextHandler(DiscountHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}
