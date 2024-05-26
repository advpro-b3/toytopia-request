package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.repository.RequestRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class RequestServiceImpl implements RequestService {
    private final RequestRepository requestRepository;
    private final CurrencyConversionService currencyConversionService;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository, CurrencyConversionService currencyConversionService) {
        this.requestRepository = requestRepository;
        this.currencyConversionService = currencyConversionService;
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Request> save(Request request) throws JSONException {
        if (!request.getCurrency().equals("IDR")) {
            double convertedPrice;
            if (request.getCurrency().equals("USD")) {
                convertedPrice = currencyConversionService.convertCurrency("USD", "IDR", request.getPrice());
            } else if (request.getCurrency().equals("JPY")) {
                convertedPrice = currencyConversionService.convertCurrency("JPY", "IDR", request.getPrice());
            } else {
                throw new IllegalArgumentException("Currency is not valid!");
            }
            request.setCurrency("IDR");
            request.setPrice(convertedPrice);
        }
        return CompletableFuture.completedFuture(requestRepository.save(request));
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Optional<Request>> findById(String requestId) {
        return CompletableFuture.completedFuture(requestRepository.findById(requestId));
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<List<Request>> findAll() {
        return CompletableFuture.completedFuture(requestRepository.findAll());
    }

    @Override
    @Async("taskExecutor")
    public CompletableFuture<Boolean> deleteById(String requestId) {
        Optional<Request> requestOptional = requestRepository.findById(requestId);
        if (requestOptional.isPresent()) {
            requestRepository.deleteById(requestId);
            return CompletableFuture.completedFuture(true);
        } else {
            return CompletableFuture.completedFuture(false);
        }
    }
}
