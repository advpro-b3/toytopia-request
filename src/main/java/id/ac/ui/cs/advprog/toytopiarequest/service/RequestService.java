package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import org.json.JSONException;

import java.util.List;

public interface RequestService {
    Request save(Request request) throws JSONException;
    Request findById(String requestId);
    List<Request> findAll();
    void deleteById(String requestId);
}
