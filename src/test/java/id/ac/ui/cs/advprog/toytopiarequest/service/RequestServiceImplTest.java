package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.repository.RequestRepository;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RequestServiceImplTest {

    @Mock
    private RequestRepository requestRepository;

    @Mock
    private CurrencyConversionService currencyConversionService;

    @InjectMocks
    private RequestServiceImpl requestService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveWithUSD() throws JSONException {
        Request request = new Request();
        request.setCurrency("USD");
        request.setPrice(100.0);

        when(currencyConversionService.convertCurrency("USD", "IDR", 100.0)).thenReturn(1604500.0);
        when(requestRepository.save(any(Request.class))).thenReturn(request);

        Request savedRequest = requestService.save(request);

        assertEquals("IDR", savedRequest.getCurrency());
        assertEquals(1604500.0, savedRequest.getPrice());
        verify(currencyConversionService, times(1)).convertCurrency("USD", "IDR", 100.0);
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    public void testSaveWithJPY() throws JSONException {
        Request request = new Request();
        request.setCurrency("JPY");
        request.setPrice(100.0);

        when(currencyConversionService.convertCurrency("JPY", "IDR", 100.0)).thenReturn(10222.0);
        when(requestRepository.save(any(Request.class))).thenReturn(request);

        Request savedRequest = requestService.save(request);

        assertEquals("IDR", savedRequest.getCurrency());
        assertEquals(10222.0, savedRequest.getPrice());
        verify(currencyConversionService, times(1)).convertCurrency("JPY", "IDR", 100.0);
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    public void testSaveWithInvalidCurrency() {
        Request request = new Request();
        request.setCurrency("EUR");

        assertThrows(IllegalArgumentException.class, () -> requestService.save(request));
        verify(currencyConversionService, never()).convertCurrency(anyString(), anyString(), anyDouble());
        verify(requestRepository, never()).save(any(Request.class));
    }

    @Test
    public void testSaveWithIDR() throws JSONException {
        Request request = new Request();
        request.setCurrency("IDR");
        request.setPrice(100.0);

        when(requestRepository.save(any(Request.class))).thenReturn(request);

        Request savedRequest = requestService.save(request);

        assertEquals("IDR", savedRequest.getCurrency());
        assertEquals(100.0, savedRequest.getPrice());
        verify(currencyConversionService, never()).convertCurrency(anyString(), anyString(), anyDouble());
        verify(requestRepository, times(1)).save(request);
    }

    @Test
    public void testFindById() {
        Request request = new Request();
        request.setId("1");

        when(requestRepository.findById("1")).thenReturn(request);

        Request foundRequest = requestService.findById("1");

        assertNotNull(foundRequest);
        assertEquals("1", foundRequest.getId());
        verify(requestRepository, times(1)).findById("1");
    }

    @Test
    public void testFindAll() {
        Request request1 = new Request();
        request1.setId("1");
        Request request2 = new Request();
        request2.setId("2");

        when(requestRepository.findAll()).thenReturn(Arrays.asList(request1, request2));

        List<Request> requests = requestService.findAll();

        assertEquals(2, requests.size());
        verify(requestRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteById() {
        doNothing().when(requestRepository).deleteById("1");

        requestService.deleteById("1");

        verify(requestRepository, times(1)).deleteById("1");
    }
}
