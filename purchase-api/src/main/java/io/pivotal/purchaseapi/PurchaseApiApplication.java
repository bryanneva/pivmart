package io.pivotal.purchaseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.function.Consumer;

@SpringBootApplication
public class PurchaseApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PurchaseApiApplication.class, args);
	}

	@Bean
	public Consumer<CheckoutEvent> checkoutListener(PurchasesRepository purchasesRepository) {
		return checkoutEvent -> {
			List<CartItem> cart = checkoutEvent.getCart();
			Purchase purchase = Purchase.builder()
					.items(cart)
					.build();

			purchasesRepository.save(purchase);
		};
	}
}
