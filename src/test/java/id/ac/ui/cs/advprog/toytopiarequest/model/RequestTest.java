package id.ac.ui.cs.advprog.toytopiarequest.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RequestTest {

    @Test
    void testNoArgsConstructor() {
        Request request = new Request();
        assertNull(request.getId());
        assertNull(request.getProductName());
        assertNull(request.getImageLink());
        assertNull(request.getPrice());
        assertNull(request.getProductLink());
        assertNull(request.getCurrency());
        assertNull(request.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        Request request = new Request("1", "Product", "http://image.link", 100.0, "http://product.link", "USD", "PENDING");
        assertEquals("1", request.getId());
        assertEquals("Product", request.getProductName());
        assertEquals("http://image.link", request.getImageLink());
        assertEquals(100.0, request.getPrice());
        assertEquals("http://product.link", request.getProductLink());
        assertEquals("USD", request.getCurrency());
        assertEquals("PENDING", request.getStatus());
    }

    @Test
    void testBuilder() {
        Request request = Request.builder()
                .id("1")
                .productName("Product")
                .imageLink("http://image.link")
                .price(100.0)
                .productLink("http://product.link")
                .currency("USD")
                .status("PENDING")
                .build();
        assertEquals("1", request.getId());
        assertEquals("Product", request.getProductName());
        assertEquals("http://image.link", request.getImageLink());
        assertEquals(100.0, request.getPrice());
        assertEquals("http://product.link", request.getProductLink());
        assertEquals("USD", request.getCurrency());
        assertEquals("PENDING", request.getStatus());
    }

    @Test
    void testSettersAndGetters() {
        Request request = new Request();
        request.setId("1");
        request.setProductName("Product");
        request.setImageLink("http://image.link");
        request.setPrice(100.0);
        request.setProductLink("http://product.link");
        request.setCurrency("USD");
        request.setStatus("PENDING");

        assertEquals("1", request.getId());
        assertEquals("Product", request.getProductName());
        assertEquals("http://image.link", request.getImageLink());
        assertEquals(100.0, request.getPrice());
        assertEquals("http://product.link", request.getProductLink());
        assertEquals("USD", request.getCurrency());
        assertEquals("PENDING", request.getStatus());
    }

    @Test
    void testToString() {
        Request request = Request.builder()
                .id("1")
                .productName("Product")
                .imageLink("http://image.link")
                .price(100.0)
                .productLink("http://product.link")
                .currency("USD")
                .status("PENDING")
                .build();
        String expected = "Request(id=1, productName=Product, imageLink=http://image.link, price=100.0, productLink=http://product.link, currency=USD, status=PENDING)";
        assertEquals(expected, request.toString());
    }

    @Test
    void testEqualsAndHashCode() {
        Request request1 = Request.builder()
                .id("1")
                .productName("Product")
                .imageLink("http://image.link")
                .price(100.0)
                .productLink("http://product.link")
                .currency("USD")
                .status("PENDING")
                .build();

        Request request2 = Request.builder()
                .id("2")
                .productName("Product2")
                .imageLink("http://image2.link")
                .price(200.0)
                .productLink("http://product2.link")
                .currency("EUR")
                .status("COMPLETED")
                .build();

        assertNotEquals(request1, request2);
        assertNotEquals(request1.hashCode(), request2.hashCode());
    }
}
