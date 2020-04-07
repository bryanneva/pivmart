package io.pivotal.purchaseapi;

import java.util.List;

public interface PurchasesRepository {
    List<Purchase> findAll();

    Purchase save(Purchase purchase);
}
