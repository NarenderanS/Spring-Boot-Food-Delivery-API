package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Product;
import com.restapi.repository.CartRepository;
import com.restapi.repository.ProductRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserService userService;

    public List<Cart> findUserCart(Long userId) {
        List<Cart> cart = cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));
        return cart;
    }

    public List<Cart> addToCart(CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", cartRequest.getUserId()));
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", cartRequest.getProductId()));
        List<Cart> cartList = findUserCart(cartRequest.getUserId());
        if (cartList.isEmpty()) {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setProduct(product);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        } else {
            boolean flag = false;
            for (Cart cartProduct : cartList) {
                if (cartProduct.getProduct().getId().equals(product.getId())) {
                    cartProduct.setCount(cartRequest.getCount());
                    cartRepository.save(cartProduct);
                    flag = true;
                }
                if (!flag) {
                    Cart cart = new Cart();
                    cart.setAppUser(appUser);
                    cart.setProduct(product);
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);

                }
            }

        }
        return findUserCart(cartRequest.getUserId());
    }


    public List<Cart> deleteCart(Long userId, Long cartProductId) {
        cartRepository.deleteById(cartProductId);
        return findUserCart(userId);
    }
}
