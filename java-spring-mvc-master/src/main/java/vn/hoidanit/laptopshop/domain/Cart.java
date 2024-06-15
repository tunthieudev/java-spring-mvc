package vn.hoidanit.laptopshop.domain;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cart {
    private long id;
    private int sum;
    private long userId;

    @JsonIgnore
    List<CartDetail> cartDetails;
}