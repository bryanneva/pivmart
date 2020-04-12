package io.pivotal.cartapi;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.context.ApplicationEvent;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CheckoutEvent {

    private List<CartItem> cart;

    public CheckoutEvent(List<CartItem> cart) {
        this.cart = cart;
    }
}
