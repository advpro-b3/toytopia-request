package id.ac.ui.cs.advprog.toytopiarequest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Request {
    String requestId;
    String productName;
    String imageLink;
    Double price;
    String productLink;
    String currency;
    String status;


    public Request(String requestId, String productName, String imageLink, Double price, String productLink, String currency, String status) {
        this.requestId = requestId;
        this.productName = productName;
        this.imageLink = imageLink;
        this.price = price;
        this.productLink = productLink;
        this.currency = currency;
        this.status = status;
    }
}
