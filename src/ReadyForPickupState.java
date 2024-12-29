
class ReadyForPickupState implements OrderState {
    @Override
    public void updateStatus(OrderTracking orderTracking) {
        orderTracking.setStatus("Pizza is ready for pickup!");
        orderTracking.displayStatus();
    }
}


