package id.ac.ui.cs.advprog.toytopiarequest.controller;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RequestController.class)
public class RequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RequestService requestService;

    private Request request;

    @BeforeEach
    public void setUp() {
        request = new Request("1", "Product", "http://image.link", 100.0, "http://product.link", "USD", "PENDING");
    }

    @Test
    public void testCreateRequest() throws Exception {
        when(requestService.save(any(Request.class))).thenReturn(request);

        mockMvc.perform(post("/api/requests")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Product\",\"imageLink\":\"http://image.link\",\"price\":100.0,\"productLink\":\"http://product.link\",\"currency\":\"USD\",\"status\":\"PENDING\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(request.getId())))
                .andExpect(jsonPath("$.productName", is(request.getProductName())))
                .andExpect(jsonPath("$.imageLink", is(request.getImageLink())))
                .andExpect(jsonPath("$.price", is(request.getPrice())))
                .andExpect(jsonPath("$.productLink", is(request.getProductLink())))
                .andExpect(jsonPath("$.currency", is(request.getCurrency())))
                .andExpect(jsonPath("$.status", is(request.getStatus())));

        verify(requestService, times(1)).save(any(Request.class));
    }

    @Test
    public void testGetRequestById() throws Exception {
        when(requestService.findById("1")).thenReturn(request);

        mockMvc.perform(get("/api/requests/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(request.getId())))
                .andExpect(jsonPath("$.productName", is(request.getProductName())))
                .andExpect(jsonPath("$.imageLink", is(request.getImageLink())))
                .andExpect(jsonPath("$.price", is(request.getPrice())))
                .andExpect(jsonPath("$.productLink", is(request.getProductLink())))
                .andExpect(jsonPath("$.currency", is(request.getCurrency())))
                .andExpect(jsonPath("$.status", is(request.getStatus())));

        verify(requestService, times(1)).findById("1");
    }

    @Test
    public void testGetAllRequests() throws Exception {
        List<Request> requests = Arrays.asList(request);
        when(requestService.findAll()).thenReturn(requests);

        mockMvc.perform(get("/api/requests")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(request.getId())))
                .andExpect(jsonPath("$[0].productName", is(request.getProductName())))
                .andExpect(jsonPath("$[0].imageLink", is(request.getImageLink())))
                .andExpect(jsonPath("$[0].price", is(request.getPrice())))
                .andExpect(jsonPath("$[0].productLink", is(request.getProductLink())))
                .andExpect(jsonPath("$[0].currency", is(request.getCurrency())))
                .andExpect(jsonPath("$[0].status", is(request.getStatus())));

        verify(requestService, times(1)).findAll();
    }

    @Test
    public void testUpdateRequest() throws Exception {
        when(requestService.save(any(Request.class))).thenReturn(request);

        mockMvc.perform(put("/api/requests/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Product\",\"imageLink\":\"http://image.link\",\"price\":100.0,\"productLink\":\"http://product.link\",\"currency\":\"USD\",\"status\":\"PENDING\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(request.getId())))
                .andExpect(jsonPath("$.productName", is(request.getProductName())))
                .andExpect(jsonPath("$.imageLink", is(request.getImageLink())))
                .andExpect(jsonPath("$.price", is(request.getPrice())))
                .andExpect(jsonPath("$.productLink", is(request.getProductLink())))
                .andExpect(jsonPath("$.currency", is(request.getCurrency())))
                .andExpect(jsonPath("$.status", is(request.getStatus())));

        verify(requestService, times(1)).save(any(Request.class));
    }

    @Test
    public void testDeleteRequest() throws Exception {
        doNothing().when(requestService).deleteById("1");

        mockMvc.perform(delete("/api/requests/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(requestService, times(1)).deleteById("1");
    }
}
