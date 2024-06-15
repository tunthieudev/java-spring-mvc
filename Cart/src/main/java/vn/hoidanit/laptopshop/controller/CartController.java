package vn.hoidanit.laptopshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import vn.hoidanit.laptopshop.model.Cart;
import vn.hoidanit.laptopshop.model.CartDetail;
import vn.hoidanit.laptopshop.service.CartService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("/user/{id}")
    public Cart getCartByUser(@PathVariable int id) {
        return cartService.getCartByUser(id);
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable int id) {
        return cartService.getCart(id);
    }

    @PostMapping("/update")
    public Cart updateCart(@RequestBody String cJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Cart c = mapper.readValue(cJson, Cart.class);
        return cartService.updateCart(c);
    }

    @GetMapping("/cart-detail/{id}")
    public List<CartDetail> getCartDetails(@PathVariable int id) {
        return cartService.getCartDetailsByCart(id);
    }

    @GetMapping("/cart-detail/info/{id}")
    public CartDetail getCartDetail(@PathVariable int id) {
        return cartService.getCartDetail(id);
    }

    @GetMapping("/cart-detail/delete/{id}")
    public void deleteCartDetail(@PathVariable int id) {
        cartService.deleteCartDetail(id);
    }

    @PostMapping("/cart-detail/update")
    public CartDetail updateCartDetail(@RequestBody String cdJson) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        CartDetail cd = mapper.readValue(cdJson, CartDetail.class);
        return cartService.updateCartDetail(cd);
    }

    @GetMapping("/cart-detail")
    public CartDetail getMethodName(@RequestParam int cartId, @RequestParam int productId) {
        return cartService.getCartDetailByCartAndProduct(cartId, productId);
    }

}
