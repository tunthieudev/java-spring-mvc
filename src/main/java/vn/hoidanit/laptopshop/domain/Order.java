package vn.hoidanit.laptopshop.domain;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String status;

    @Column(name = "date")
    private String datePlaceOrder;

    // userId
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // receiver_id
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private ReceiverInfo receiverInfo;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDatePlaceOrder() {
        return datePlaceOrder;
    }

    public void setDatePlaceOrder(String datePlaceOrder) {
        this.datePlaceOrder = datePlaceOrder;
    }

    public ReceiverInfo getReceiverInfo() {
        return receiverInfo;
    }

    public void setReceiverInfo(ReceiverInfo receiverInfo) {
        this.receiverInfo = receiverInfo;
    }

}
