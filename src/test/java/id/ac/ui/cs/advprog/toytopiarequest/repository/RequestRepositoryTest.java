package id.ac.ui.cs.advprog.toytopiarequest.repository;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

public class RequestRepositoryTest {
    private RequestRepository requestRepository;
    private List<Request> requests;

    @BeforeEach
    void setup() {
        requestRepository = new RequestRepository();
        requests = new ArrayList<>();

        Request request1 = Request.builder()
                .requestId("1")
                .productName("Toy Car")
                .imageLink("http://example.com/toycar.png")
                .price(200.0)
                .productLink("http://example.com/toycar")
                .currency("USD")
                .status("PENDING")
                .build();

        Request request2 = Request.builder()
                .requestId("2")
                .productName("Toy Train")
                .imageLink("http://example.com/toytrain.png")
                .price(150.0)
                .productLink("http://example.com/toytrain")
                .currency("USD")
                .status("PENDING")
                .build();

        requests.add(request1);
        requests.add(request2);
    }

    @Test
    void testSaveCreate() {
        Request newRequest = requests.get(0);
        Request savedRequest = requestRepository.save(newRequest);
        Request foundRequest = requestRepository.findById(newRequest.getRequestId());

        assertEquals(newRequest.getRequestId(), savedRequest.getRequestId());
        assertEquals(newRequest.getProductName(), foundRequest.getProductName());
    }

    @Test
    void testSaveUpdate() {
        Request originalRequest = requests.get(1);
        requestRepository.save(originalRequest);

        Request updatedRequest = Request.builder()
                .requestId(originalRequest.getRequestId())
                .productName(originalRequest.getProductName())
                .imageLink(originalRequest.getImageLink())
                .price(175.0)
                .productLink(originalRequest.getProductLink())
                .currency(originalRequest.getCurrency())
                .status("APPROVED")
                .build();

        Request result = requestRepository.save(updatedRequest);
        Request foundRequest = requestRepository.findById(updatedRequest.getRequestId());

        assertEquals(updatedRequest.getPrice(), result.getPrice());
        assertEquals(updatedRequest.getStatus(), foundRequest.getStatus());
    }

    @Test
    void testDelete() {
        Request requestToDelete = requests.get(0);
        requestRepository.save(requestToDelete);
        requestRepository.deleteById(requestToDelete.getRequestId());

        Request foundRequest = requestRepository.findById(requestToDelete.getRequestId());
        assertNull(foundRequest);
    }
}