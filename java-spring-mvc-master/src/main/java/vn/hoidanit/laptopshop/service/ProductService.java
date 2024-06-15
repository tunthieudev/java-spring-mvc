package vn.hoidanit.laptopshop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import vn.hoidanit.laptopshop.domain.Product;

@Service
public class ProductService {
    private String host = "/api/product";
    @Autowired
    private DataService dataService;

    public List<Product> getAllProduct() {
        return dataService.getDataFromUrl(host, new TypeReference<ArrayList<Product>>() {
        });
    }

    public Product getProduct(long id) {
        String url = host + "/" + id;
        return dataService.getDataFromUrl(url, Product.class);
    }

    public void deleteProduct(long id) {
        String url = host + "/delete/" + id;
        dataService.callUrl(url);
    }

    public void saveProduct(Product p) {
        String url = host + "/update";
        dataService.postDataToUrl(url, p);
    }

    public List<Product> listAllProductsByName(String keyword) {
        String url = host + "";
        return dataService.getDataFromUrl(url, new TypeReference<ArrayList<Product>>() {
        });
    }

    public List<Product> getAllProductsSortedByRevenueAsc() {
        String url = host + "";
        return dataService.getDataFromUrl(url, new TypeReference<ArrayList<Product>>() {
        });
    }

    public List<Product> getAllProductsSortedByRevenueDesc() {
        String url = host + "";
        return dataService.getDataFromUrl(url, new TypeReference<ArrayList<Product>>() {
        });
    }
}
