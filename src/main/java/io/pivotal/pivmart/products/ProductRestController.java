package io.pivotal.pivmart.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductRestController {

    private ProductGateway productGateway;

    public ProductRestController(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @GetMapping
    public List<Product> list(@RequestParam(value = "catalog", required = false) String catalogKey) {
        return productGateway.getForCatalog(catalogKey);
    }
}
