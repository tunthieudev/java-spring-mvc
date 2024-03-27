package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    // void deleteById(long id);
}
