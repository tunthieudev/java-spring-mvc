package vn.hoidanit.laptopshop.controller.admin;

import java.util.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final UserService userService;
    private final ProductService productService;

    public DashboardController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/admin")
    public String getDashboard(Model model) {
        model.addAttribute("countUsers", this.userService.countUsers());
        model.addAttribute("countProducts", this.userService.countProducts());
        model.addAttribute("countOrders", this.userService.countOrders());

        return "admin/dashboard/show";
    }

    @GetMapping("/admin/statistic")
    public String getStatisticPage(Model model, @Param("keyword") String keyword,
            @RequestParam(required = false) String sortOrder) {
        List<Product> products = this.productService.getAllProducts();

        if (keyword != null) {
            products = this.productService.listAllProductsByName(keyword);
            model.addAttribute("keyword", keyword);
        }
        if (sortOrder != null && !sortOrder.isEmpty()) {
            if ("asc".equals(sortOrder)) {
                products = this.productService.getAllProductsSortedByRevenueAsc();
            } else if ("desc".equals(sortOrder)) {
                products = this.productService.getAllProductsSortedByRevenueDesc();
            }
        }
        model.addAttribute("products", products);
        return "admin/statistic/show";
    }

    // @GetMapping("/admin/statistic/sort")
    // public String sortData(Model model, @RequestParam String sortOrder) {
    // List<Product> products = this.productService.getAllProducts();

    // if ("asc".equals(sortOrder)) {
    // products = this.productService.getAllProductsSortedByRevenueAsc();
    // } else if ("desc".equals(sortOrder)) {
    // products = this.productService.getAllProductsSortedByRevenueDesc();
    // }
    // model.addAttribute("products", products);

    // return "admin/statistic/show";
    // }

}
