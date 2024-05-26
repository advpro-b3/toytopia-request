package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.repository.RequestRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class RequestServiceImplTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @InjectMocks
    private RequestServiceImpl requestServiceImpl;

    private Request request;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        request = new Request("1", "Product", "http://image.link", 100.0, "http://product.link", "USD", "PENDING");
    }

    @Test
    void testSaveWithUSD() throws JSONException {
        request.setCurrency("USD");
        request.setPrice(100.0);

        when(currencyConversionService.convertCurrency("USD", "IDR", 100.0)).thenReturn(1400000.0);
        when(requestRepository.save(any(Request.class))).thenAnswer(invocation -> {
            Request savedRequest = invocation.getArgument(0);
            assertEquals("IDR", savedRequest.getCurrency());
            assertEquals(1400000.0, savedRequest.getPrice());
            return savedRequest;
        });

        CompletableFuture<Request> savedRequestFuture = requestServiceImpl.save(request);
        Request savedRequest = savedRequestFuture.join();

        assertNotNull(savedRequest);
        assertEquals("IDR", savedRequest.getCurrency());
        assertEquals(1400000.0, savedRequest.getPrice());

        verify(currencyConversionService, times(1)).convertCurrency("USD", "IDR", 100.0);
        verify(requestRepository, times(1)).save(any(Request.class));
    }

    @Test
    void testSaveWithJPY() throws JSONException {
        request.setCurrency("JPY");
        request.setPrice(100.0);

        when(currencyConversionService.convertCurrency("JPY", "IDR", 100.0)).thenReturn(130000.0);
        when(requestRepository.save(any(Request.class))).thenAnswer(invocation -> {
            Request savedRequest = invocation.getArgument(0);
            assertEquals("IDR", savedRequest.getCurrency());
            assertEquals(130000.0, savedRequest.getPrice());
            return savedRequest;
        });

        CompletableFuture<Request> savedRequestFuture = requestServiceImpl.save(request);
        Request savedRequest = savedRequestFuture.join();

        assertNotNull(savedRequest);
        assertEquals("IDR", savedRequest.getCurrency());
        assertEquals(130000.0, savedRequest.getPrice());

        verify(currencyConversionService, times(1)).convertCurrency("JPY", "IDR", 100.0);
        verify(requestRepository, times(1)).save(any(Request.class));
    }

    @Test
    void testSaveWithoutCurrencyConversion() throws JSONException {
        request.setCurrency("IDR");
        request.setPrice(100.0);

        when(requestRepository.save(any(Request.class))).thenAnswer(invocation -> {
            Request savedRequest = invocation.getArgument(0);
            assertEquals("IDR", savedRequest.getCurrency());
            assertEquals(100.0, savedRequest.getPrice());
            return savedRequest;
        });

        CompletableFuture<Request> savedRequestFuture = requestServiceImpl.save(request);
        Request savedRequest = savedRequestFuture.join();

        assertNotNull(savedRequest);
        assertEquals("IDR", savedRequest.getCurrency());
        assertEquals(100.0, savedRequest.getPrice());

        verify(currencyConversionService, times(0)).convertCurrency(anyString(), anyString(), anyDouble());
        verify(requestRepository, times(1)).save(any(Request.class));
    }

    @Test
    void testFindById() {
        when(requestRepository.findById(anyString())).thenReturn(Optional.of(request));

        CompletableFuture<Optional<Request>> foundRequestFuture = requestServiceImpl.findById("1");
        Optional<Request> foundRequest = foundRequestFuture.join();

        assertTrue(foundRequest.isPresent());
        assertEquals("1", foundRequest.get().getId());

        verify(requestRepository, times(1)).findById("1");
    }

    @Test
    void testFindByIdNotFound() {
        when(requestRepository.findById(anyString())).thenReturn(Optional.empty());

        CompletableFuture<Optional<Request>> foundRequestFuture = requestServiceImpl.findById("1");
        Optional<Request> foundRequest = foundRequestFuture.join();

        assertFalse(foundRequest.isPresent());

        verify(requestRepository, times(1)).findById("1");
    }

    @Test
    void testFindAll() {
        List<Request> requests = List.of(request);
        when(requestRepository.findAll()).thenReturn(requests);

        CompletableFuture<List<Request>> foundRequestsFuture = requestServiceImpl.findAll();
        List<Request> foundRequests = foundRequestsFuture.join();

        assertNotNull(foundRequests);
        assertEquals(1, foundRequests.size());
        assertEquals("1", foundRequests.get(0).getId());

        verify(requestRepository, times(1)).findAll();
    }

    @Test
    void testDeleteById() {
        when(requestRepository.findById(anyString())).thenReturn(Optional.of(request));
        doNothing().when(requestRepository).deleteById(anyString());

        CompletableFuture<Boolean> deleteFuture = requestServiceImpl.deleteById("1");
        Boolean result = deleteFuture.join();

        assertTrue(result);

        verify(requestRepository, times(1)).deleteById("1");
    }

    @Test
    void testDeleteByIdNotFound() {
        when(requestRepository.findById(anyString())).thenReturn(Optional.empty());

        CompletableFuture<Boolean> deleteFuture = requestServiceImpl.deleteById("1");
        Boolean result = deleteFuture.join();

        assertFalse(result);

        verify(requestRepository, times(1)).findById("1");
        verify(requestRepository, times(0)).deleteById("1");
    }
}
