package io.pivotal.purchaseapi;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.verify;

@SpringBootTest
class PurchaseApiApplicationTests {

	@Autowired
	InputDestination input;

	@Autowired
	OutputDestination output;

	@MockBean
	PurchasesRepository purchasesRepository;

	@Test
	void purchaseCreatedOnCartEvent() throws IOException {
		List<CartItem> cart = asList(CartItem.builder().id(UUID.randomUUID()).build());
		CheckoutEvent checkoutEvent = new CheckoutEvent(cart);
		this.input.send(new GenericMessage<CheckoutEvent>(checkoutEvent));

		verify(purchasesRepository).save(Purchase.builder().items(cart).build());
	}

	@SpringBootApplication
	@Import(TestChannelBinderConfiguration.class)
	public static class SampleConfiguration {
	}

	@Test
	void contextLoads() {
	}

}
