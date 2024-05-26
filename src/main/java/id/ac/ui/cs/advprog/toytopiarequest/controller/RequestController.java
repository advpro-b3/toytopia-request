package id.ac.ui.cs.advprog.toytopiarequest.controller;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<Request>> createRequest(@RequestBody Request request) {
        return requestService.save(request)
                .thenApply(ResponseEntity::ok);
    }

    @GetMapping("/{requestId}")
    public CompletableFuture<ResponseEntity<Request>> getRequestById(@PathVariable String requestId) {
        return requestService.findById(requestId)
                .thenApply(request -> request.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<List<Request>>> getAllRequests() {
        return requestService.findAll()
                .thenApply(ResponseEntity::ok);
    }

    @PutMapping("/{requestId}")
    public CompletableFuture<ResponseEntity<Request>> updateRequest(@PathVariable String requestId, @RequestBody Request request) {
        request.setId(requestId);
        return requestService.save(request)
                .thenApply(ResponseEntity::ok);
    }

    @DeleteMapping("/{requestId}")
    public CompletableFuture<ResponseEntity<Void>> deleteRequest(@PathVariable String requestId) {
        return requestService.deleteById(requestId)
                .thenApply(success -> success ? ResponseEntity.ok().build() : ResponseEntity.notFound().build());
    }
}
