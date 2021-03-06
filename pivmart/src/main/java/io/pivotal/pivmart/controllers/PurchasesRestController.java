package io.pivotal.pivmart.controllers;

import io.pivotal.pivmart.models.Purchase;
import io.pivotal.pivmart.repositories.PurchasesRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/purchases")
public class PurchasesRestController {

    private PurchasesRepository purchasesRepository;

    public PurchasesRestController(PurchasesRepository purchasesRepository) {
        this.purchasesRepository = purchasesRepository;
    }

    @GetMapping
    public List<Purchase> list() {
        return purchasesRepository.findAll();
    }
}
