package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import vn.hoidanit.laptopshop.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

        @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
        List<Product> search(String keyword);

        @Query("SELECT p FROM Product p LEFT JOIN OrderDetail od ON p.id = od.product.id " +
                        "GROUP BY p.id " +
                        "ORDER BY SUM(od.price * od.quantity) DESC")
        List<Product> findAllOrderByRevenueDesc();

        @Query("SELECT p FROM Product p LEFT JOIN OrderDetail od ON p.id = od.product.id " +
                        "GROUP BY p.id " +
                        "ORDER BY SUM(od.price * od.quantity) ASC")
        List<Product> findAllOrderByRevenueAsc();

        Page<Product> findAll(Pageable page);

        Page<Product> findAll(Specification<Product> spec, Pageable page);

}
