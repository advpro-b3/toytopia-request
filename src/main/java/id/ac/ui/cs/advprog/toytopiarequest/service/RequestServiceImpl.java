package id.ac.ui.cs.advprog.toytopiarequest.service;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;
import id.ac.ui.cs.advprog.toytopiarequest.repository.RequestRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Request save(Request request) throws JSONException {
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
        return requestRepository.save(request);
    }

    @Override
    public Request findById(String requestId) {
        return requestRepository.findById(requestId);
    }

    @Override
    public List<Request> findAll() {
        return requestRepository.findAll();
    }

    @Override
    public void deleteById(String requestId) {
        requestRepository.deleteById(requestId);
    }
}
