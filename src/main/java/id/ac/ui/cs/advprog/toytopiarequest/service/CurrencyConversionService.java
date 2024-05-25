package id.ac.ui.cs.advprog.toytopiarequest.service;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;

@Service
public class CurrencyConversionService {

    @Value("f4b953a0ccb938c1501bf0ee")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public double convertCurrency(String from, String to, double amount) throws JSONException {
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/latest/%s", apiKey, from);
        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);
        if (json.getString("result").equals("success")) {
            double rateTo = json.getJSONObject("conversion_rates").getDouble(to);
            return amount * rateTo;
        } else {
            throw new RuntimeException("Failed to fetch currency conversion rate");
        }
    }
}
