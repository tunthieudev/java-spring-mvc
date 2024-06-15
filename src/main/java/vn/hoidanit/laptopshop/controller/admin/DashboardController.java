package vn.hoidanit.laptopshop.controller.admin;

import java.time.LocalDate;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.OrderDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.OrderService;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final UserService userService;
    private final ProductService productService;
    private final OrderService orderService;

    private String firstDate;
    private String secondDate;

    public DashboardController(UserService userService, ProductService productService, OrderService orderService) {
        this.userService = userService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {
        model.addAttribute("countUsers", this.userService.countUsers());
        model.addAttribute("countProducts", this.userService.countProducts());
        model.addAttribute("countOrders", this.userService.countOrders());

        return "admin/dashboard/show";
    }

    @GetMapping("/admin/statistic")
    public String getStatisticPage(
            Model model,
            @Param("keyword") String keyword,
            @RequestParam(required = false) String sortOrder,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> products = this.productService.getAllProducts(pageable);
        List<Product> listProducts = products.getContent();

        List<Double> listRevenue = new ArrayList<>();
        List<Long> listAmountSold = new ArrayList<>();
        List<Long> totalQuantityProduct = new ArrayList<>();
        List<Long> listQuantityChange = new ArrayList<>();

        String startDateStr = startDate != null ? startDate.toString() : null;
        String endDateStr = endDate != null ? endDate.toString() : null;
        firstDate = startDateStr;
        secondDate = endDateStr;

        for (Product product : products) {
            List<OrderDetail> listOrders = new ArrayList<>();
            List<List<OrderDetail>> newOrderDetail = new ArrayList<>();

            if (startDateStr != null && endDateStr != null) {
                List<Order> orders = this.orderService.getOrdersBetweenDates(firstDate, secondDate);
                for (Order order : orders) {
                    newOrderDetail.add(this.orderService.getOrderByOrderId(order.getId()));
                }
                for (List<OrderDetail> x : newOrderDetail) {
                    for (OrderDetail orderDetail : x) {
                        if (orderDetail.getProduct().getId() == product.getId()) {
                            listOrders.add(orderDetail);
                        }
                    }
                }
            } else {
                listOrders = this.orderService.getOrderByProductId(product.getId());
            }

            double totalRevenue = 0;
            long amountSold = 0;
            long count = 0;

            for (OrderDetail orderDetail : listOrders) {
                totalRevenue += orderDetail.getPrice() * orderDetail.getQuantity();
                amountSold += orderDetail.getQuantity();
            }
            listAmountSold.add(amountSold);
            listRevenue.add(totalRevenue);

            count += (product.getQuantity() + product.getSold());
            totalQuantityProduct.add(count);
        }

        for (int i = 0; i < totalQuantityProduct.size(); i++) {
            long cnt = totalQuantityProduct.get(i) - listAmountSold.get(i);
            listQuantityChange.add(cnt);
        }

        // filter
        if (sortOrder != null && !sortOrder.isEmpty()) {
            if ("asc".equals(sortOrder)) {
                listProducts = this.productService.getAllProductsSortedByRevenueAsc();
                Collections.sort(listRevenue);
            } else if ("desc".equals(sortOrder)) {
                listProducts = this.productService.getAllProductsSortedByRevenueDesc();
                Collections.sort(listRevenue, Collections.reverseOrder());
            }
        }

        double totalAllRevenue = 0;
        for (double x : listRevenue) {
            totalAllRevenue += x;
        }

        // search by key
        if (keyword != null) {
            listProducts = this.productService.listAllProductsByName(keyword);
            model.addAttribute("keyword", keyword);
        }

        model.addAttribute("totalAllRevenue", totalAllRevenue);
        model.addAttribute("listAmountSold", listAmountSold);
        model.addAttribute("listQuantityChange", listQuantityChange);
        model.addAttribute("listRevenue", listRevenue);
        model.addAttribute("products", listProducts);
        return "admin/statistic/show";
    }

    @GetMapping("/admin/statistic/{id}")
    public String getDetailStatisticProduct(
            Model model,
            @PathVariable long id) {
        List<OrderDetail> listOrders = new ArrayList<>();
        LinkedHashSet<Long> listOrderId = new LinkedHashSet<>();
        List<List<OrderDetail>> newOrderDetail = new ArrayList<>();

        if (firstDate != null && !firstDate.isEmpty() && secondDate != null && !secondDate.isEmpty()) {
            List<Order> orders = this.orderService.getOrdersBetweenDates(firstDate, secondDate);
            for (Order order : orders) {
                newOrderDetail.add(this.orderService.getOrderByOrderId(order.getId()));
            }
            for (List<OrderDetail> x : newOrderDetail) {
                for (OrderDetail orderDetail : x) {
                    if (orderDetail.getProduct().getId() == id) {
                        listOrders.add(orderDetail);
                    }
                }
            }
        } else {
            listOrders = this.orderService.getOrderByProductId(id);
        }

        double totalRevenue = 0;
        for (OrderDetail orderDetail : listOrders) {
            long orderId = orderDetail.getOrder().getId();
            listOrderId.add(orderId);
            totalRevenue += orderDetail.getPrice() * orderDetail.getQuantity();
        }

        List<String> listDatePlace = new ArrayList<>();
        List<String> listNameUsers = new ArrayList<>();
        String datePlaceOrder = "";
        for (Long x : listOrderId) {
            Optional<Order> orderOptional = this.orderService.fetchOrderById(x);
            datePlaceOrder = orderOptional.get().getDatePlaceOrder();
            long user_id = orderOptional.get().getUser().getId();
            User user = this.userService.getUserByIdUser(user_id);
            listNameUsers.add(user.getFullName());
            listDatePlace.add(datePlaceOrder);
        }

        model.addAttribute("totalRevenue", totalRevenue);
        model.addAttribute("listNameUsers", listNameUsers);
        model.addAttribute("listDatePlace", listDatePlace);
        model.addAttribute("id", id);
        model.addAttribute("orders", listOrders);
        return "admin/statistic/detail";
    }

}
