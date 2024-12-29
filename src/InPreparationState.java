
class InPreparationState implements OrderState {
    @Override
    public void updateStatus(OrderTracking orderTracking) {
        orderTracking.setStatus("Pizza is being prepared...");
        orderTracking.displayStatus();
    }
}


