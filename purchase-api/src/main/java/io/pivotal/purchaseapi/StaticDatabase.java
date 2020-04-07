package io.pivotal.purchaseapi;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class StaticDatabase {
    private Map<UUID, Purchase> purchases = new HashMap<>();

    public Map<UUID, Purchase> getPurchases() {
        return purchases;
    }

    public Purchase save(Purchase purchase) {
        UUID uuid = UUID.randomUUID();
        purchase.setId(uuid);
        purchases.put(uuid, purchase);

        return purchase;
    }
}
