





import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class OrderTracking implements Subject {
    private OrderState currentState;
    private String status;
    private List<Observer> observers;

    public OrderTracking() {
        this.currentState = new OrderPlacedState();  
        this.status = "Order Placed";
        this.observers = new ArrayList<>();
        displayStatus();
    }

    public void setState(OrderState newState) {
        this.currentState = newState;
    }

    public void updateStatus() {
        currentState.updateStatus(this);
    }

    public void displayStatus() {
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm:ss a");
        System.out.printf("\n--- Order Tracking ---\nCurrent Status: %s\nTime: %s%n", 
                          status, currentTime.format(formatter));
        
        update(status);
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }


    public void update(String status) {
        System.out.println("User notified: Order Status updated to - " + status);
    }

    public void startTracking(String orderType) {
        try {
            // Preparation State
            setState(new InPreparationState());
            setStatus("In Preparation");
            updateStatus();
            Thread.sleep(3000); 

            // Baking State
            setState(new BakingState());
            setStatus("Baking");
            updateStatus();
            Thread.sleep(5 * 60 * 1000);

            // Ready for Pickup or Out for Delivery
            if (orderType.equalsIgnoreCase("Pickup")) {
                setState(new ReadyForPickupState());
                setStatus("Ready for Pickup");
                updateStatus();
                Thread.sleep(3000);
            } else if (orderType.equalsIgnoreCase("Delivery")) {
                setState(new OutForDeliveryState());
                setStatus("Out for Delivery");
                updateStatus();
                Thread.sleep(15 * 60 * 1000); 
                setState(new DeliveredState());
                setStatus("Delivered");
                updateStatus();
                Thread.sleep(2000); // delivered
            }

        } catch (InterruptedException e) {
            System.out.println("Order tracking interrupted.");
        }
    }
}













