package vn.hoidanit.laptopshop.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private long id;
    private double totalPrice;
    private String receiverName;
    private String receiverAddress;
    private String receiverPhone;
    private String status;
    private long userId;
    private String date;

    @JsonIgnore
    private List<OrderDetail> orderDetails;

}
