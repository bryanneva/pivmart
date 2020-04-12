package io.pivotal.purchaseapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CheckoutEvent {

    private List<CartItem> cart;

    @JsonCreator
    public CheckoutEvent(List<CartItem> cart) {
        this.cart = cart;
    }
}
