package vn.hoidanit.laptopshop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import vn.hoidanit.laptopshop.model.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Transactional
    @Modifying
    @Query(value = "select * from order_detail where order_id=?1", nativeQuery = true)
    public List<OrderDetail> findByOrderId(int orderId);
}
