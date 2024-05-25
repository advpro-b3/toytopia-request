package id.ac.ui.cs.advprog.toytopiarequest.repository;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import org.springframework.stereotype.Repository;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class RequestRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Request save(Request request) {
        if (request.getId() == null) {
            entityManager.persist(request);
        } else {
            entityManager.merge(request);
        }
        return request;
    }

    public Request findById(String id) {
        return entityManager.find(Request.class, id);
    }

    public List<Request> findAll() {
        return entityManager.createQuery("SELECT r FROM Request r", Request.class).getResultList();
    }

    @Transactional
    public void deleteById(String id) {
        Request request = entityManager.find(Request.class, id);
        if (request != null) {
            entityManager.remove(request);
        }
    }
}
