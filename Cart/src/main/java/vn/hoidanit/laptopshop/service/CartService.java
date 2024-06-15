package vn.hoidanit.laptopshop.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hoidanit.laptopshop.model.Cart;
import vn.hoidanit.laptopshop.model.CartDetail;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;

    public Cart getCartByUser(int id) {
        return cartRepository.getByUserId(id);
    }

    public Cart getCart(int id) {
        return cartRepository.findById(id).get();
    }

    public Cart updateCart(Cart c) {
        return cartRepository.save(c);
    }

    public List<CartDetail> getCartDetailsByCart(int id) {
        return cartDetailRepository.getCartDetailsByCartId(id);
    }

    public CartDetail getCartDetail(int id) {
        return cartDetailRepository.findById(id).get();
    }

    public void deleteCartDetail(int id) {
        cartDetailRepository.deleteById(id);
    }

    public CartDetail updateCartDetail(CartDetail cd) {
        return cartDetailRepository.save(cd);
    }

    public CartDetail getCartDetailByCartAndProduct(int cId, int pId) {
        return cartDetailRepository.getCartDetailsByCartIdAndProductId(cId, pId);
    }
}
