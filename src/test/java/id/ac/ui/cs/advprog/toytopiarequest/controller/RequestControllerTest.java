package id.ac.ui.cs.advprog.toytopiarequest.controller;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestService;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestServiceImpl;
import id.ac.ui.cs.advprog.toytopiarequest.repository.RequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RequestControllerTest {

    private RequestController requestController;
    private RequestService requestService;
    private RequestRepository requestRepository;

    private Request request1;
    private Request request2;

    @BeforeEach
    void setUp() {
        requestRepository = new RequestRepository();
        requestService = new RequestServiceImpl(requestRepository);
        requestController = new RequestController(requestService);

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
    void testCreateRequest() {
        requestController.createRequest(request1);
        Request createdRequest = requestController.getRequestById("1").getBody();

        assertEquals(request1.getRequestId(), createdRequest.getRequestId());
        assertEquals(request1.getProductName(), createdRequest.getProductName());
        assertEquals(request1.getPrice(), createdRequest.getPrice());
    }

    @Test
    void testGetRequestById() {
        requestController.createRequest(request1);
        Request foundRequest = requestController.getRequestById("1").getBody();

        assertEquals(request1.getRequestId(), foundRequest.getRequestId());
        assertEquals(request1.getProductName(), foundRequest.getProductName());
    }

    @Test
    void testGetAllRequests() {
        requestController.createRequest(request1);
        requestController.createRequest(request2);

        List<Request> foundRequests = requestController.getAllRequests().getBody();

        assertEquals(2, foundRequests.size());
        assertEquals(request1.getRequestId(), foundRequests.get(0).getRequestId());
        assertEquals(request2.getRequestId(), foundRequests.get(1).getRequestId());
    }

    @Test
    void testUpdateRequest() {
        requestController.createRequest(request1);
        request1.setProductName("Updated Toy Car");
        requestController.updateRequest("1", request1);

        Request updatedRequest = requestController.getRequestById("1").getBody();

        assertEquals(request1.getRequestId(), updatedRequest.getRequestId());
        assertEquals("Updated Toy Car", updatedRequest.getProductName());
    }

    @Test
    void testDeleteRequest() {
        requestController.createRequest(request1);
        requestController.deleteRequest("1");

        Request foundRequest = requestController.getRequestById("1").getBody();
        assertNull(foundRequest);
    }
}
