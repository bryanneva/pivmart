package io.pivotal.cartapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/cart")
public class CartRestController {

    private CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public List<CartItem> list() {
        return cartService.get();
    }

    @PostMapping
    public CartItem add(@RequestBody Product product) {
        return cartService.add(product);
    }

    @DeleteMapping("/{id}")
    public List<CartItem> remove(@PathVariable UUID id) {
        cartService.remove(id);
        return cartService.get();
    }

    @PostMapping("checkout")
    public ResponseEntity checkOut() {
        cartService.checkOut();
        return ResponseEntity.ok().build();
    }
}
