package io.pivotal.purchaseapi;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseListener {
    private PurchasesRepository purchasesRepository;

    public PurchaseListener(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    @EventListener(CheckoutEvent.class)
    public void onCheckOut(CheckoutEvent checkoutEvent) {
        List<CartItem> cart = checkoutEvent.getCart();
        Purchase purchase = Purchase.builder()
                .items(cart)
                .build();

        purchasesRepository.save(purchase);
    }
}
