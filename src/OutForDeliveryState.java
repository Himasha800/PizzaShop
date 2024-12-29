class OutForDeliveryState implements OrderState {

    OutForDeliveryState() {
    }
    @Override
    public void updateStatus(OrderTracking orderTracking) {
        orderTracking.setStatus("Pizza is out for delivery!");
        orderTracking.displayStatus();
    }
}



