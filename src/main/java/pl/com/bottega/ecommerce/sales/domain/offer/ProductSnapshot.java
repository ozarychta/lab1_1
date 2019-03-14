package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class ProductSnapshot {

    private String id;

    private String name;

    private Date snapshotDate;

    private String type;

    private Money price;

    public ProductSnapshot(String id, String name, Date snapshotDate, String type, Money price) {
        this.id = id;
        this.name = name;
        this.snapshotDate = snapshotDate;
        this.type = type;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getSnapshotDate() {
        return snapshotDate;
    }

    public String getType() {
        return type;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProductSnapshot that = (ProductSnapshot) o;
        return Objects.equals(id, that.id)
               && Objects.equals(name, that.name)
               && Objects.equals(snapshotDate, that.snapshotDate)
               && Objects.equals(type, that.type)
               && Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, snapshotDate, type, price);
    }
}
