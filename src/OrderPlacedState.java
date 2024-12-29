class OrderPlacedState implements OrderState {
    @Override
    public void updateStatus(OrderTracking orderTracking) {
        orderTracking.setStatus("Order Placed");
        orderTracking.displayStatus();
    }
}



