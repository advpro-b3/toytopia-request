package id.ac.ui.cs.advprog.toytopiarequest.factory;

import id.ac.ui.cs.advprog.toytopiarequest.model.Request;

public class RequestFactory {

    public static Request createRequest(String productName, String imageLink, Double price, String productLink, String currency, String status) {
        return Request.builder()
                .productName(productName)
                .imageLink(imageLink)
                .price(price)
                .productLink(productLink)
                .currency(currency)
                .status(status)
                .build();
    }
}
