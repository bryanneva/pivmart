package io.pivotal.cartapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.Message;

import java.io.IOException;
import java.util.UUID;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class CartServiceTest {

    @Autowired
    InputDestination input;

    @Autowired
    OutputDestination output;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    CartRepository cartRepository;

    @Autowired
    CartService cartService;

    @Test
    void checkOut() throws IOException {
        CartItem cartItem = CartItem.builder().id(UUID.randomUUID()).build();
        when(cartRepository.findAll()).thenReturn(asList(cartItem));

        cartService.checkOut();

        Message<byte[]> message = this.output.receive();
        CheckoutEvent actual = objectMapper.readValue(message.getPayload(), CheckoutEvent.class);

        assertThat(actual.getCart()).containsExactly(cartItem);
    }

    @SpringBootApplication
    @Import(TestChannelBinderConfiguration.class)
    public static class SampleConfiguration {
    }
}