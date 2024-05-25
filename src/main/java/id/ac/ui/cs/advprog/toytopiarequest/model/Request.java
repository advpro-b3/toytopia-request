package id.ac.ui.cs.advprog.toytopiarequest.model;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String productName;

    @Column
    private String imageLink;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String productLink;

    @Column(nullable = false)
    private String currency;

    @Column(nullable = false)
    private String status;
}
