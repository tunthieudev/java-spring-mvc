package vn.hoidanit.laptopshop.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;

@Service
public class CartService {
    private String host = "/api/cart";
    @Autowired
    private DataService dataService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    public Cart getCart(long id) {
        String url = "/" + id;
        Cart c = dataService.getDataFromUrl(host + url, Cart.class);
        c.setCartDetails(getCartDetailsByCart(c.getId()));
        return c;
    }

    public Cart getCartByUser(long id) {
        String url = "/user/" + id;
        Cart c = dataService.getDataFromUrl(host + url, Cart.class);
        c.setCartDetails(getCartDetailsByCart(c.getId()));
        return c;
    }

    public Cart saveCart(Cart c) {
        String url = host + "/update";
        return dataService.postDataToUrl(url, c, Cart.class);
    }

    public CartDetail getCartDetail(long id) {
        String url = host + "/cart-detail/info/" + id;
        CartDetail c = dataService.getDataFromUrl(url, CartDetail.class);
        c.setProduct(productService.getProduct(c.getProductId()));
        return c;
    }

    public List<CartDetail> getCartDetailsByCart(long id) {
        String url = host + "/cart-detail/" + id;
        var cartDetails = dataService.getDataFromUrl(url, new TypeReference<ArrayList<CartDetail>>() {
        });
        for (CartDetail c : cartDetails) {
            c.setProduct(productService.getProduct(c.getProductId()));
        }
        return cartDetails;
    }

    public void deleteCartDetail(long id) {
        String url = host + "/cart-detail/delete/" + id;
        dataService.callUrl(url);
    }

    public CartDetail saveCartDetail(CartDetail cd) {
        String url = host + "/cart-detail/update";
        CartDetail c = dataService.postDataToUrl(url, cd, CartDetail.class);
        c.setProduct(productService.getProduct(c.getProductId()));
        return c;
    }

    public CartDetail getCartDetailByCartAndProduct(long cartId, long productId) {
        String url = host + "/cart-detail?cartId=" + cartId + "&productId=" + productId;
        CartDetail c = dataService.getDataFromUrl(url, CartDetail.class);
        return c;
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session, long quantity) {
        User user = this.userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có Cart chưa ? nếu chưa -> tạo mới
            Cart cart = this.getCartByUser(user.getId());
            if (cart == null) {
                // tạo mới cart
                Cart otherCart = new Cart();
                otherCart.setSum(0);
                otherCart.setUserId(user.getId());
                cart = this.saveCart(otherCart);
            }

            // save cart_detail
            // tìm product by id

            Product realProduct = productService.getProduct(productId);
            if (realProduct != null) {
                // check sản phẩm đã từng được thêm vào giỏ hàng trước đây chưa ?
                CartDetail oldDetail = this.getCartDetailByCartAndProduct(cart.getId(), realProduct.getId());
                if (oldDetail == null) {
                    CartDetail cd = new CartDetail();
                    cd.setCartId(cart.getId());
                    cd.setProductId(realProduct.getId());
                    cd.setPrice(realProduct.getPrice());
                    cd.setQuantity(quantity);
                    this.saveCartDetail(cd);
                    // update cart (sum);
                    cart.setSum(getCartDetailsByCart(cart.getId()).size());
                    this.saveCart(cart);
                    System.out.println(cart.getSum());
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + quantity);
                    this.saveCartDetail(oldDetail);
                }
            }
            session.setAttribute("sum", cart.getSum());
        }
    }

    public void handleRemoveCartDetail(long cartDetailId, HttpSession session) {
        CartDetail cartDetail = this.getCartDetail(cartDetailId);
        if (cartDetail != null) {
            Cart currentCart = getCart(cartDetail.getCartId());
            // delete cart-detail
            this.deleteCartDetail(cartDetailId);

            // update cart
            if (currentCart.getSum() > 1) {
                currentCart.setSum(getCartDetailsByCart(currentCart.getId()).size());
                saveCart(currentCart);
                session.setAttribute("sum", currentCart.getSum());
            } else {
                // delete cart (sum = 1)
                this.deleteCartDetail(currentCart.getId());
                session.setAttribute("sum", 0);
            }
        }
    }

    public void handleUpdateCartBeforeCheckout(List<CartDetail> cartDetails) {
        for (CartDetail cartDetail : cartDetails) {
            CartDetail currentCartDetail = this.getCartDetail(cartDetail.getId());
            if (currentCartDetail != null) {
                currentCartDetail.setQuantity(cartDetail.getQuantity());
                this.saveCartDetail(currentCartDetail);
            }
        }
    }
}
