package id.ac.ui.cs.advprog.toytopiarequest.repository;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RequestRepositoryTest {

    @Mock
    private EntityManager entityManager;

    @InjectMocks
    private RequestRepository requestRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveNewRequest() {
        Request request = new Request();
        request.setCurrency("USD");
        request.setPrice(100.0);

        requestRepository.save(request);

        verify(entityManager, times(1)).persist(request);
        verify(entityManager, never()).merge(any(Request.class));
    }

    @Test
    public void testSaveExistingRequest() {
        Request request = new Request();
        request.setId("1");
        request.setCurrency("USD");
        request.setPrice(100.0);

        requestRepository.save(request);

        verify(entityManager, times(1)).merge(request);
        verify(entityManager, never()).persist(any(Request.class));
    }

    @Test
    public void testFindById() {
        Request request = new Request();
        request.setId("1");
        when(entityManager.find(Request.class, "1")).thenReturn(request);

        Request foundRequest = requestRepository.findById("1");

        assertNotNull(foundRequest);
        assertEquals("1", foundRequest.getId());
        verify(entityManager, times(1)).find(Request.class, "1");
    }

    @Test
    public void testFindAll() {
        Request request1 = new Request();
        request1.setId("1");
        Request request2 = new Request();
        request2.setId("2");

        TypedQuery<Request> query = mock(TypedQuery.class);
        when(entityManager.createQuery("SELECT r FROM Request r", Request.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(Arrays.asList(request1, request2));

        List<Request> requests = requestRepository.findAll();

        assertEquals(2, requests.size());
        verify(entityManager, times(1)).createQuery("SELECT r FROM Request r", Request.class);
        verify(query, times(1)).getResultList();
    }

    @Test
    public void testDeleteById() {
        Request request = new Request();
        request.setId("1");
        when(entityManager.find(Request.class, "1")).thenReturn(request);

        requestRepository.deleteById("1");

        verify(entityManager, times(1)).remove(request);
    }

    @Test
    public void testDeleteByIdNotFound() {
        when(entityManager.find(Request.class, "1")).thenReturn(null);

        requestRepository.deleteById("1");

        verify(entityManager, never()).remove(any(Request.class));
    }
}
