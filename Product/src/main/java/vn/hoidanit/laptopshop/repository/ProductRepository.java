package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    List<Product> search(String keyword);

    @Query("SELECT p FROM Product p ORDER BY (p.price*p.sold) ASC")
    List<Product> getAllProductsSortedByAsc();

    @Query("SELECT p FROM Product p ORDER BY (p.price*p.sold) DESC")
    List<Product> getAllProductsSortedByDesc();

}
