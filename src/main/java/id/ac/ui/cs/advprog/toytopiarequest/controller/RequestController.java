package id.ac.ui.cs.advprog.toytopiarequest.controller;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public ResponseEntity<Request> createRequest(@RequestBody Request request) {
        Request createdRequest = requestService.save(request);
        return ResponseEntity.ok(createdRequest);
    }

    @GetMapping("/{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable String requestId) {
        Request foundRequest = requestService.findById(requestId);
        return ResponseEntity.ok(foundRequest);
    }

    @GetMapping
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> allRequests = requestService.findAll();
        return ResponseEntity.ok(allRequests);
    }

    @PutMapping("/{requestId}")
    public ResponseEntity<Request> updateRequest(@PathVariable String requestId, @RequestBody Request request) {
        request.setRequestId(requestId);
        Request updatedRequest = requestService.save(request);
        return ResponseEntity.ok(updatedRequest);
    }

    @DeleteMapping("/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable String requestId) {
        requestService.deleteById(requestId);
        return ResponseEntity.ok().build();
    }
}
