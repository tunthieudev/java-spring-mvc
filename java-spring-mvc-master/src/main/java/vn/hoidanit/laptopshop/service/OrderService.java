package vn.hoidanit.laptopshop.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;

@Service
public class OrderService {
    @Autowired
    private DataService dataService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    private String host = "/api/order";

    public List<Order> getAllOrder() {
        var orders = dataService.getDataFromUrl(host, new TypeReference<ArrayList<Order>>() {
        });
        if (orders != null) {
            for (Order order : orders) {
                order.setOrderDetails(getOrderDetailsByOrder(order.getId()));
            }
        }
        return orders;
    }

    public Order getOrder(long id) {
        String url = host + "/" + id;
        Order o = dataService.getDataFromUrl(url, Order.class);
        o.setOrderDetails(getOrderDetailsByOrder(o.getId()));
        return o;
    }

    public void deleteOrder(long id) {
        String url = host + "/delete/" + id;
        dataService.callUrl(url);
    }

    public Order saveOrder(Order o) {
        String url = host + "/update";
        return dataService.postDataToUrl(url, o, Order.class);
    }

    public ArrayList<OrderDetail> getOrderDetailsByOrder(long id) {
        String url = host + "/order-detail/" + id;
        var orderDetails = dataService.getDataFromUrl(url, new TypeReference<ArrayList<OrderDetail>>() {
        });
        for (OrderDetail od : orderDetails) {
            od.setProduct(productService.getProduct(od.getProductId()));
        }
        return orderDetails;
    }

    public void saveOrderDetail(OrderDetail od) {
        String url = host + "/order-detail/update";
        dataService.postDataToUrl(url, od);
    }

    public List<Order> getOrderByUser(long id) {
        String url = host + "/user/" + id;
        var orders = dataService.getDataFromUrl(url, new TypeReference<ArrayList<Order>>() {
        });
        if (orders != null) {
            for (Order order : orders) {
                order.setOrderDetails(getOrderDetailsByOrder(order.getId()));
            }
        }
        return orders;
    }

    public void handlePlaceOrder(User user, List<Long> listPrSelected, HttpSession session,
            String receiverName, String receiverAddress, String receiverPhone) throws Exception {
        HashMap<Long, Long> map = new HashMap<>();
        // get cart by user
        Cart cart = this.cartService.getCartByUser(user.getId());
        if (cart != null) {
            List<CartDetail> cartDetails = new ArrayList<>();
            for (var x : cart.getCartDetails()) {
                if (listPrSelected.contains(x.getProductId()))
                    cartDetails.add(x);
            }

            if (cartDetails != null) {
                // create order
                Order order = new Order();
                order.setUserId(user.getId());
                order.setReceiverName(receiverName);
                order.setReceiverAddress(receiverAddress);
                order.setReceiverPhone(receiverPhone);
                order.setStatus("PENDING");

                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDateTime = now.format(formatter);
                order.setDate(formattedDateTime);

                // System.out.println("date pace order: " + order.getDatePlaceOrder());

                double sum = 0;
                for (CartDetail cd : cartDetails) {
                    sum += cd.getPrice();
                }
                order.setTotalPrice(sum);
                order = saveOrder(order);

                // create orderDetail

                for (CartDetail cd : cartDetails) {
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setOrderId(order.getId());
                    orderDetail.setProductId(cd.getProductId());
                    orderDetail.setPrice(cd.getPrice());
                    orderDetail.setQuantity(cd.getQuantity());
                    map.put(orderDetail.getProductId(), orderDetail.getQuantity());
                    saveOrderDetail(orderDetail);
                }
                for (Entry<Long, Long> entry : map.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    Product pr = productService.getProduct(entry.getKey());
                    pr.setQuantity(pr.getQuantity() - entry.getValue());
                    pr.setSold(pr.getSold() + entry.getValue());
                    productService.saveProduct(pr);
                }
                System.out.println("size cartDetails: " + cart.getSum());
                for (CartDetail cd : cartDetails) {
                    this.cartService.deleteCartDetail(cd.getId());
                }
                cart.setSum(cartService.getCartDetailsByCart(cart.getId()).size());
                this.cartService.saveCart(cart);
                session.setAttribute("sum", cart.getSum());
            }
        }
    }
}
