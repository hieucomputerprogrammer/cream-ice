package io.hieu.creamice.controllers;

import io.hieu.creamice.dto.AddCartItemDTO;
import io.hieu.creamice.dto.CartDTO;
import io.hieu.creamice.services.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://127.0.0.1:4200")
@RestController
public class CartController {
	@Autowired
	private ICartService cartService;

	@GetMapping(path = "/cart")
	public ResponseEntity<CartDTO> getCartItems(@RequestParam(name = "userId") Long userId) {
		CartDTO cartItems = cartService.getUserCartItems(userId);
		return new ResponseEntity(cartItems, HttpStatus.OK);
	}

	@PostMapping(path = "/cart")
	public ResponseEntity<CartDTO> addCartItem(@RequestBody AddCartItemDTO addCartItemDTO) {
		CartDTO cart = cartService.addCartItem(addCartItemDTO);
		return new ResponseEntity<CartDTO>(cart, HttpStatus.CREATED);
	}

	@DeleteMapping(path = "/cart")
	public ResponseEntity<CartDTO> removeCartItem(@RequestParam(name = "orderId") Long orderId,
			@RequestParam(name = "productId") Long productId) {
		CartDTO cartAfterRemovingItems = cartService.removeCartItem(orderId, productId);
		return new ResponseEntity<CartDTO>(cartAfterRemovingItems, HttpStatus.OK);
	}
}