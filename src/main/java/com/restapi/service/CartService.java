package com.restapi.service;

import com.restapi.dto.CartDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.Cart;
import com.restapi.model.Product;
import com.restapi.repository.CartRepository;
import com.restapi.repository.ProductRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import com.restapi.response.CartResponse;
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
    @Autowired
    private CartDto cartDto;

    public List<CartResponse> findUserCart(Long userId) {
        List<Cart> cart = cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart", "userId", userId));
        return cartDto.mapToCartResponse(cart);
    }

    public List<CartResponse> addToCart(CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "UserId", cartRequest.getUserId()));
        Product product = productRepository.findById(cartRequest.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductId", cartRequest.getProductId()));
        Optional<List<Cart>> cartList = cartRepository.findUserCart(cartRequest.getUserId());

        if (cartList.isPresent()) {
            System.out.println("12345saasasa");
            boolean flag = false;
            for (Cart cartProduct : cartList.get()) {
                System.out.println(cartProduct.getProduct().getId() + "      " + cartRequest.getProductId());
                if (cartProduct.getProduct().getId().equals(cartRequest.getProductId())) {
                    System.out.println("equal");
                    cartProduct.setCount(cartRequest.getCount());
                    cartRepository.save(cartProduct);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                Cart cart = new Cart();
                cart.setAppUser(appUser);
                cart.setProduct(product);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }

        } else {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setProduct(product);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return findUserCart(cartRequest.getUserId());
    }


    public List<CartResponse> deleteCart(Long userId, Long cartProductId) {
        cartRepository.deleteById(cartProductId);
        return findUserCart(userId);
    }
}
