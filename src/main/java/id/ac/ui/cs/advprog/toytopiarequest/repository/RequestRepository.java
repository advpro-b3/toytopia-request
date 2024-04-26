package id.ac.ui.cs.advprog.toytopiarequest.repository;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RequestRepository {
    private List<Request> requests = new ArrayList<>();

    public Request save(Request request) {
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getRequestId().equals(request.getRequestId())) {
                requests.set(i, request);
                return request;
            }
        }
        requests.add(request);
        return request;
    }

    public Request findById(String requestId) {
        for (Request savedRequest : requests) {
            if (savedRequest.getRequestId().equals(requestId)) {
                return savedRequest;
            }
        }
        return null;
    }
}