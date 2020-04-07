package io.pivotal.cartapi;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class StaticDatabase {
    private Map<UUID, CartItem> cart = new HashMap<>();

    public Map<UUID, CartItem> getCart() {
        return cart;
    }

    public CartItem save(CartItem cartItem) {
        UUID uuid = UUID.randomUUID();
        cartItem.setId(uuid);
        cart.put(uuid, cartItem);

        return cartItem;
    }

    public void remove(CartItem cartItem) {
        cart.remove(cartItem.getId());
    }

    public void emptyCart() {
        cart.clear();
    }
}
