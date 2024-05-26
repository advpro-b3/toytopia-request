package id.ac.ui.cs.advprog.toytopiarequest.controller;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RequestControllerTest {

    @Mock
    private RequestService requestService;

    @InjectMocks
    private RequestController requestController;

    private Request request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new Request("1", "Product", "http://image.link", 100.0, "http://product.link", "USD", "PENDING");
    }

    @Test
    void testCreateRequest() throws Exception {
        when(requestService.save(any(Request.class))).thenReturn(CompletableFuture.completedFuture(request));

        CompletableFuture<ResponseEntity<Request>> result = requestController.createRequest(request);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(request), result.join());
        verify(requestService, times(1)).save(any(Request.class));
    }

    @Test
    void testGetRequestById() throws Exception {
        when(requestService.findById("1")).thenReturn(CompletableFuture.completedFuture(Optional.of(request)));

        CompletableFuture<ResponseEntity<Request>> result = requestController.getRequestById("1");

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(request), result.join());
        verify(requestService, times(1)).findById("1");
    }

    @Test
    void testGetRequestByIdNotFound() throws Exception {
        when(requestService.findById("1")).thenReturn(CompletableFuture.completedFuture(Optional.empty()));

        CompletableFuture<ResponseEntity<Request>> result = requestController.getRequestById("1");

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.notFound().build(), result.join());
        verify(requestService, times(1)).findById("1");
    }

    @Test
    void testGetAllRequests() throws Exception {
        List<Request> requests = Arrays.asList(request);
        when(requestService.findAll()).thenReturn(CompletableFuture.completedFuture(requests));

        CompletableFuture<ResponseEntity<List<Request>>> result = requestController.getAllRequests();

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(requests), result.join());
        verify(requestService, times(1)).findAll();
    }

    @Test
    void testUpdateRequest() throws Exception {
        when(requestService.save(any(Request.class))).thenReturn(CompletableFuture.completedFuture(request));

        CompletableFuture<ResponseEntity<Request>> result = requestController.updateRequest("1", request);

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok(request), result.join());
        verify(requestService, times(1)).save(any(Request.class));
    }

    @Test
    void testDeleteRequest() throws Exception {
        when(requestService.deleteById("1")).thenReturn(CompletableFuture.completedFuture(true));

        CompletableFuture<ResponseEntity<Void>> result = requestController.deleteRequest("1");

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.ok().build(), result.join());
        verify(requestService, times(1)).deleteById("1");
    }

    @Test
    void testDeleteRequestNotFound() throws Exception {
        when(requestService.deleteById("1")).thenReturn(CompletableFuture.completedFuture(false));

        CompletableFuture<ResponseEntity<Void>> result = requestController.deleteRequest("1");

        assertNotNull(result);
        assertTrue(result.isDone());
        assertEquals(ResponseEntity.notFound().build(), result.join());
        verify(requestService, times(1)).deleteById("1");
    }
}
