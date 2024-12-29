class DeliveredState implements OrderState {
    @Override
    public void updateStatus(OrderTracking orderTracking) {
        orderTracking.setStatus("Pizza delivered! Enjoy your meal!");
        orderTracking.displayStatus();
    }
}

