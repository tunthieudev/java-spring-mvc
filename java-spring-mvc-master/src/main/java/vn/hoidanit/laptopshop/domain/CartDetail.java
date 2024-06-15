package vn.hoidanit.laptopshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CartDetail {
    private long id;
    private long quantity;
    private double price;
    private long cartId;
    private long productId;

    @JsonIgnore
    private Product product;
}