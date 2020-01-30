package io.pivotal.pivmart.services;

import io.pivotal.pivmart.models.Catalog;
import io.pivotal.pivmart.repositories.CatalogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CatalogServiceTest {
    private CatalogRepository catalogRepository;

    @BeforeEach
    void setUp() {
        catalogRepository = mock(CatalogRepository.class);
    }

    @Test
    void getAll() {
        CatalogService catalogService = new CatalogService(catalogRepository);

        catalogService.getAll();

        verify(catalogRepository).findAll();
    }
}