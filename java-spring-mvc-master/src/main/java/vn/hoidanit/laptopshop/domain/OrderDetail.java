package vn.hoidanit.laptopshop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetail {
    private long id;
    private long quantity;
    private double price;
    private long orderId;
    private long productId;
    @JsonIgnore
    private Product product;

}
