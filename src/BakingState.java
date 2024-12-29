class BakingState implements OrderState {
    @Override
    public void updateStatus(OrderTracking orderTracking) {
        orderTracking.setStatus("Pizza is baking in the oven...");
        orderTracking.displayStatus();
    }
}
