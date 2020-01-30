package io.pivotal.productapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs")
public class CatalogRestController {

    private CatalogRepository catalogRepository;

    public CatalogRestController(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }

    @GetMapping()
    public List<Catalog> getAll() {
        return catalogRepository.findAll();
    }


    @GetMapping("/{catalogKey}")
    public Catalog findByKey(@PathVariable() String catalogKey) {
        return catalogRepository.findByKey(catalogKey);
    }


}
