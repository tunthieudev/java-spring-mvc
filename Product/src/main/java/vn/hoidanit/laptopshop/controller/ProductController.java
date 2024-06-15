package vn.hoidanit.laptopshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import vn.hoidanit.laptopshop.model.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public List<Product> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProductById(id);
        ;
    }

    @PostMapping("/update")
    public Product updateProduct(@RequestBody String pJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(pJson, Product.class);
        return productService.updateProduct(product);
    }
}
