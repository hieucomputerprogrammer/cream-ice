package io.hieu.creamice.services;

import io.hieu.creamice.dto.AddCartItemDTO;
import io.hieu.creamice.dto.CartDTO;

public interface ICartService {
    CartDTO getUserCartItems(Long userId);
    CartDTO addCartItem(AddCartItemDTO addCartItemDTO);
    CartDTO removeCartItem(Long orderId, Long productId);
}