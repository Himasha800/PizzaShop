
public class CreateOrderCommand implements Command {
    private final UserProfile user;
    private final LoyaltyProgram loyaltyProgram;
    private Pizza pizza;

    public CreateOrderCommand(UserProfile user, LoyaltyProgram loyaltyProgram) {
        this.user = user;
        this.loyaltyProgram = loyaltyProgram;
    }

    @Override
    public void execute() {
        Order<LoyaltyProgram> order = new Order<>(loyaltyProgram);
        order.placeOrder();
        this.pizza = order.getPizza();
    }

    @Override
    public void undo() {
        System.out.println("Undoing order creation...");
    }

    public Pizza getPizza() {
        return pizza;
    }
}
