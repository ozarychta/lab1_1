/*
 * Copyright 2011-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package pl.com.bottega.ecommerce.sales.domain.offer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class OfferItem {

    // product
    private ProductSnapshot product;

    private int quantity;

    private Money totalCost;

    // discount
    private Discount discount;

    public OfferItem(ProductSnapshot product, int quantity) {
        this(product, quantity, null);
    }

    public OfferItem(ProductSnapshot product, int quantity, Discount discount) {
        this.product = product;

        this.quantity = quantity;
        this.discount = discount;

        BigDecimal discountValue = new BigDecimal(0);
        if (discount != null && discount.getAmount() != null) {
            discountValue = discountValue.add(discount.getAmount().getValue());
        }

        this.totalCost = new Money(null, product.getPrice().getValue().multiply(new BigDecimal(quantity)).subtract(discountValue));
    }

    public String getProductId() {
        return product == null ? null : product.getId();
    }

    public ProductSnapshot getProduct() {
        return product;
    }

    public Money getTotalCost() {
        return totalCost;
    }

    public String getTotalCostCurrency() {
        return totalCost.getCurrency();
    }

    public Discount getDiscount() {
        return discount;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(discount, product, quantity, totalCost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        OfferItem other = (OfferItem) obj;
        return Objects.equals(discount, other.discount)
               && Objects.equals(product, other.product)
               && quantity == other.quantity
               && Objects.equals(totalCost, other.totalCost);
    }

    /**
     * @param other
     * @param delta acceptable percentage difference
     * @return
     */
    public boolean sameAs(OfferItem other, double delta) {
        if (product == null) {
            if (other.product != null) {
                return false;
            }
        } else if (!product.equals(other.product)) {
            return false;
        }

        if (quantity != other.quantity) {
            return false;
        }

        if (totalCost == null) {
            if (other.totalCost != null) {
                return false;
            }
        } else {
            if (totalCost.getValue() == null && (other.totalCost == null ? null : other.totalCost.getValue() == null)) {
                return false;
            }
        }

        BigDecimal max;
        BigDecimal min;

        if (totalCost.getValue().compareTo(other.totalCost.getValue()) > 0) {
            max = totalCost.getValue();
            min = other.totalCost.getValue();
        } else {
            max = other.totalCost.getValue();
            min = totalCost.getValue();
        }

        BigDecimal difference = max.subtract(min);
        BigDecimal acceptableDelta = max.multiply(BigDecimal.valueOf(delta / 100));

        return acceptableDelta.compareTo(difference) > 0;
    }

}
