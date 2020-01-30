package io.pivotal.productapi;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class StaticDatabase {
    private Map<UUID, Catalog> catalogs = new HashMap<>();

    public StaticDatabase() {
        seed();
    }

    public Map<UUID, Catalog> getCatalogs() {
        return catalogs;
    }

    private UUID save(Catalog catalog) {
        catalogs.put(catalog.getId(), catalog);

        return catalog.getId();
    }

    public void seed() {
        UUID homeGoods = save(CatalogFactory.create("Home Goods"));
        UUID sportingGoods = save(CatalogFactory.create("Sporting Goods"));
        UUID electronics = save(CatalogFactory.create("Electronics"));
        UUID clothes = save(CatalogFactory.create("Clothes"));
    }
}
