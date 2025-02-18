package events.order;

import java.math.BigDecimal;

public class CreateOrderEvent {
    private String id;
    private int customerId;
    private BigDecimal amount;

    public CreateOrderEvent() {
    }

    public CreateOrderEvent(String id, int customerId, BigDecimal amount) {
        this.id = id;
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
