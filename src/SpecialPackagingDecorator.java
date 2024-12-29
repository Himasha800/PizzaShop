



class SpecialPackagingDecorator implements PizzaDecorator {
    private final String packaging;
    private final double cost;

    public SpecialPackagingDecorator(String packaging, double cost) {
        this.packaging = packaging;
        this.cost = cost;
    }

    @Override
    public void customize(Pizza pizza) {
        pizza.setPackaging(packaging);
        pizza.setPrice(pizza.getPrice() + cost);
        System.out.printf("Added special packaging: %s ($%.2f)%n", packaging, cost);
    }
}
