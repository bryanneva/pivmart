package io.pivotal.pivmart.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/catalogs")
public class CatalogRestController {

    private CatalogGateway catalogGateway;

    public CatalogRestController(CatalogGateway catalogGateway) {
        this.catalogGateway = catalogGateway;
    }

    @GetMapping
    public List<Catalog> list() {
        return catalogGateway.getAll();
    }
}
