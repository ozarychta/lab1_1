package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;

public class Discount {

    private String cause;

    private Money amount;

    public Discount(String cause, Money amount) {
        this.cause = cause;
        this.amount = amount;
    }

    public String getCause() {
        return cause;
    }

    public Money getAmount() {
        return amount;
    }
}
