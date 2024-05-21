package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.hoidanit.laptopshop.domain.ReceiverInfo;
import java.util.List;

public interface ReceiverInfoRepository extends JpaRepository<ReceiverInfo, Long> {
    List<ReceiverInfo> findByUserId(Long userId);
}