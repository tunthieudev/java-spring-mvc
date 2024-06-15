package vn.hoidanit.laptopshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.model.CartDetail;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
    @Query("Select cd from CartDetail cd where cd.cartId=?1")
    public List<CartDetail> getCartDetailsByCartId(int cartId);

    @Query("Select cd from CartDetail cd where cd.cartId=?1 and cd.productId=?2")
    public CartDetail getCartDetailsByCartIdAndProductId(int cartId, int productId);
}
