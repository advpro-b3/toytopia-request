package id.ac.ui.cs.advprog.toytopiarequest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {
    private Request request;

    @BeforeEach
    void setUp() {
        request = new Request();
        request.setRequestId("eb558e9f-1c39-460e-8860-71afbaf63bd6");
        request.setProductName("Toytopia XYZ");
        request.setImageLink("http://example.com/image.png");
        request.setPrice(100000);
        request.setProductLink("http://example.com/product");
        request.setCurrency("IDR");
        request.setStatus("PENDING");
    }
    }

    @Test
    void testCreateRequest() {
        assertEquals(eb558e9f-1c39-460e-8860-71afbaf63bd6, request.getRequestId());
        assertEquals("Toytopia XYZ", request.getProductName());
        assertEquals("https://example.com/image.jpg", request.getImageLink());
        assertEquals(100000, request.getPrice());
        assertEquals("https://example.com/product", request.getProductLink());
        assertEquals("IDR", request.getCurrency());
        assertEquals("PENDING", request.getStatus());
    }
}