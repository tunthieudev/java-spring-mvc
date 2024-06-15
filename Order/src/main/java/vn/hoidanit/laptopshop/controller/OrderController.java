package vn.hoidanit.laptopshop.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.hoidanit.laptopshop.model.Order;
import vn.hoidanit.laptopshop.model.OrderDetail;
import vn.hoidanit.laptopshop.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/user/{id}")
    public List<Order> getOrderByUser(@PathVariable int id) {
        return this.orderService.getOrderByUser(id);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable int id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }

    @PostMapping("/update")
    public Order updateOrder(@RequestBody String oJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Order o = mapper.readValue(oJson, Order.class);
        return orderService.updateOrder(o);
    }

    @PostMapping("/order-detail/update")
    public OrderDetail updateOrderDetail(@RequestBody String odJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        OrderDetail od = mapper.readValue(odJson, OrderDetail.class);
        return orderService.updateOrderDetail(od);
    }

    @GetMapping("/order-detail/{id}")
    public List<OrderDetail> getAllOrderDetail(@PathVariable int id) throws Exception {
        return orderService.getOrderDetail(id);
    }
}
