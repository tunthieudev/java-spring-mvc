package vn.hoidanit.laptopshop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.model.Product;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id).get();
    }

    public void deleteProductById(int id) {
        productRepository.deleteById(id);
    }

    public Product updateProduct(Product p) {
        return productRepository.save(p);
    }

    public List<Product> listAllProductsByName(String keyword) {
        return productRepository.search(keyword);
    }

    public List<Product> getAllProductsSortedByRevenueAsc() {
        return productRepository.getAllProductsSortedByAsc();
    }

    public List<Product> getAllProductsSortedByRevenueDesc() {
        return productRepository.getAllProductsSortedByDesc();
    }
}
