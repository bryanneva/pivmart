package io.pivotal.pivmart.controllers;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.services.CatalogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/catalogs")
public class CatalogRestController {

    private CatalogService catalogService;

    public CatalogRestController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public Flux<Catalog> list() {
        return catalogService.getAll();
    }
}
