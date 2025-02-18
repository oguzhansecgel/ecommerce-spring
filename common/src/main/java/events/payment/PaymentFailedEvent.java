package events.payment;

public class PaymentFailedEvent {
    private String orderId;

    public PaymentFailedEvent(String orderId) {
        this.orderId = orderId;
    }

    public PaymentFailedEvent() {
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
