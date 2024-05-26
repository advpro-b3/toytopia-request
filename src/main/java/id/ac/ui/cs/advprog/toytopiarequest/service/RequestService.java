package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import org.json.JSONException;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface RequestService {
    CompletableFuture<Request> save(Request request) throws JSONException;
    CompletableFuture<Optional<Request>> findById(String requestId);
    CompletableFuture<List<Request>> findAll();
    CompletableFuture<Boolean> deleteById(String requestId);
}
