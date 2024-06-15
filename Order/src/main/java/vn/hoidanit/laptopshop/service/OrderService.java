package vn.hoidanit.laptopshop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.model.Order;
import vn.hoidanit.laptopshop.model.OrderDetail;
import vn.hoidanit.laptopshop.repository.OrderDetailRepository;
import vn.hoidanit.laptopshop.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public List<Order> getAllOrder() {
        return this.orderRepository.findAll();
    }

    public Order getOrder(int id) {
        return this.orderRepository.findById(id).get();
    }

    public List<Order> getOrderByUser(int id) {
        return this.orderRepository.findByUserId(id);
    }

    public void deleteOrder(int id) {
        orderRepository.deleteById(id);
    }

    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    public List<OrderDetail> getOrderDetail(int id) {
        return this.orderDetailRepository.findByOrderId(id);
    }

    public OrderDetail updateOrderDetail(OrderDetail od) {
        return orderDetailRepository.save(od);
    }

}
