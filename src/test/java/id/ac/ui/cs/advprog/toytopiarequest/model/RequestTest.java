package id.ac.ui.cs.advprog.toytopiarequest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestTest {
    private Request request;

    @BeforeEach
    void setUp() {
        request = Request.builder()
                .requestId("eb558e9f-1c39-460e-8860-71afbaf63bd6")
                .productName("Toytopia XYZ")
                .imageLink("http://example.com/image.png")
                .price(100000.0)
                .productLink("http://example.com/product")
                .currency("IDR")
                .status("PENDING")
                .build();
    }

    @Test
    void testCreateRequest() {
        assertEquals("eb558e9f-1c39-460e-8860-71afbaf63bd6", request.getRequestId());
        assertEquals("Toytopia XYZ", request.getProductName());
        assertEquals("http://example.com/image.png", request.getImageLink());
        assertEquals(100000.0, request.getPrice());
        assertEquals("http://example.com/product", request.getProductLink());
        assertEquals("IDR", request.getCurrency());
        assertEquals("PENDING", request.getStatus());
    }
}
