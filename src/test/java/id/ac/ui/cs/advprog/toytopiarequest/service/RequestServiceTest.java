package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.repository.RequestRepository;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequestServiceTest {

    private RequestServiceImpl requestService;
    private RequestRepository requestRepository;

    private Request request1;
    private Request request2;

    @BeforeEach
    void setUp() {
        requestRepository = new RequestRepository();
        requestService = new RequestServiceImpl(requestRepository);

        request1 = Request.builder()
                .requestId("1")
                .productName("Toy Car")
                .imageLink("http://example.com/toycar.png")
                .price(200.0)
                .productLink("http://example.com/toycar")
                .currency("USD")
                .status("PENDING")
                .build();

        request2 = Request.builder()
                .requestId("2")
                .productName("Toy Train")
                .imageLink("http://example.com/toytrain.png")
                .price(150.0)
                .productLink("http://example.com/toytrain")
                .currency("USD")
                .status("PENDING")
                .build();
    }

    @Test
    void testSaveRequest() {
        Request savedRequest = requestService.save(request1);
        Request foundRequest = requestService.findById(request1.getRequestId());

        assertEquals(request1, savedRequest);
        assertEquals(request1, foundRequest);
    }

    @Test
    void testFindRequestById() {
        requestService.save(request1);
        Request foundRequest = requestService.findById("1");

        assertEquals(request1, foundRequest);
    }

    @Test
    void testFindAllRequests() {
        requestService.save(request1);
        requestService.save(request2);

        List<Request> foundRequests = requestService.findAll();

        assertEquals(2, foundRequests.size());
        assertEquals(request1, foundRequests.get(0));
        assertEquals(request2, foundRequests.get(1));
    }

    @Test
    void testDeleteRequestById() {
        requestService.save(request1);
        requestService.deleteById("1");

        Request foundRequest = requestService.findById("1");
        assertNull(foundRequest);
    }
}
