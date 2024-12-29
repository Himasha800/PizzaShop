





class ExtraToppingDecorator implements PizzaDecorator {
    private final String topping;
    private final double cost;

    public ExtraToppingDecorator(String topping, double cost) {
        this.topping = topping;
        this.cost = cost;
    }

    @Override
    public void customize(Pizza pizza) {
        pizza.getToppings().add(topping);
        pizza.setPrice(pizza.getPrice() + cost);
        System.out.printf("Added extra topping: %s ($%.2f)%n", topping, cost);
    }
}
