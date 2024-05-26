package id.ac.ui.cs.advprog.toytopiarequest.factory;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestFactoryTest {

    @Test
    void testCreateRequest() {
        String productName = "Product1";
        String imageLink = "http://image.com";
        Double price = 100.0;
        String productLink = "http://product.com";
        String currency = "USD";
        String status = "NEW";

        Request request = RequestFactory.createRequest(productName, imageLink, price, productLink, currency, status);

        assertNotNull(request);
        assertEquals(productName, request.getProductName());
        assertEquals(imageLink, request.getImageLink());
        assertEquals(price, request.getPrice());
        assertEquals(productLink, request.getProductLink());
        assertEquals(currency, request.getCurrency());
        assertEquals(status, request.getStatus());
    }
}
