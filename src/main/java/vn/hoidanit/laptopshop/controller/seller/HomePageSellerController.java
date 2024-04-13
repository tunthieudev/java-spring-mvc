package vn.hoidanit.laptopshop.controller.seller;

import java.util.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;

@Controller
public class HomePageSellerController {

    private final ProductService productService;

    public HomePageSellerController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/seller")
    public String getHomePageSeller(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "seller/homepage/show";
    }

}
