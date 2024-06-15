package vn.hoidanit.laptopshop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import vn.hoidanit.laptopshop.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional
    @Modifying
    @Query(value = "select * from order1 where user_id=?1", nativeQuery = true)
    public List<Order> findByUserId(int userId);
}
