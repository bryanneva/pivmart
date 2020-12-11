package io.pivotal.cartapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CartService {
    private CartRepository cartRepository;
    private StreamBridge streamBridge;

    public CartService(CartRepository cartRepository, StreamBridge streamBridge) {
        this.cartRepository = cartRepository;
        this.streamBridge = streamBridge;
    }

    public List<CartItem> get() {
        return cartRepository.findAll();
    }

    public CartItem add(Product product) {
        log.debug( "Product={}", product);
        return cartRepository.save(CartItem.builder()
                .product(product)
                .quantity(1)
                .build());
    }

    public void remove(UUID cartItemId) {
        CartItem cartItem = cartRepository.find(cartItemId);
        if (cartItem != null) {
            cartRepository.remove(cartItem);
        }
    }

    public void checkOut() {
        List<CartItem> cart = cartRepository.findAll();
        CheckoutEvent checkoutEvent = new CheckoutEvent(cart);
        streamBridge.send("cartPublisher-out-0", checkoutEvent);
        cartRepository.removeAll();
    }
}
