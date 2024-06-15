package vn.hoidanit.laptopshop.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private String image;
    private String detailDesc;
    private String shortDesc;
    private int quantity;
    private int sold;
    private String factory;
    private String target;
}
