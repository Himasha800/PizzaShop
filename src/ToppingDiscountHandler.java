
public class ToppingDiscountHandler implements DiscountHandler {
    private DiscountHandler nextHandler;

    @Override
    public double applyDiscount(Pizza pizza, SeasonalPromotions promotion) {
        double discount = 0.0;

        for (String topping : pizza.getToppings()) {
            discount += promotion.getToppingDiscount(topping);
        }

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
