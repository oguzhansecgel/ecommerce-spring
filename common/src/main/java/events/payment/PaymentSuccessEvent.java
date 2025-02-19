package events.payment;

public class PaymentSuccessEvent {
    private String orderId;

    public PaymentSuccessEvent() {
    }

    public PaymentSuccessEvent(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
