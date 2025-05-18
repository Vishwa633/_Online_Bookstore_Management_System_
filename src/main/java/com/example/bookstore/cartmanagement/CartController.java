package com.example.bookstore.cartmanagement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.bookstore.bookmanagement.BookRepository;
import com.example.bookstore.bookmanagement.Book;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*") // Allow frontend requests (adjust for production)
public class CartController {
    private final CartManager cartManager;
    private final BookRepository bookRepository;

    @Autowired
    public CartController(CartManager cartManager, BookRepository bookRepository) {
        this.cartManager = cartManager;
        this.bookRepository = bookRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItem>> getCart(@PathVariable String userId) {
        List<CartItem> items = cartManager.getCartItems(userId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{userId}/add")
    public ResponseEntity<?> addToCart(@PathVariable String userId, @RequestParam Long itemId) {
        // Fetch the book from the repository
        Book book = bookRepository.getBookById(itemId.intValue());
        if (book == null) {
            return ResponseEntity.badRequest().body("Book not found");
        }
        // Use the real book data
        String imageUrl = book.getImage(); // If your Book class has getImage()
        BookCartItem cartItem = new BookCartItem(
            book.getId(),
            book.getTitle(),
            book.getAuthor(),
            book.getPrice(),
            1,
            imageUrl != null ? imageUrl : ""
        );
        cartManager.addToCart(userId, cartItem);
        return ResponseEntity.ok("Added to cart successfully");
    }


    @PutMapping("/{userId}/update")
    public void updateQuantity(@PathVariable String userId, @RequestParam int itemId, @RequestParam int quantity) {
        cartManager.updateCartItemQuantity(userId, itemId, quantity);
    }

    @DeleteMapping("/{userId}/remove")
    public void removeFromCart(@PathVariable String userId, @RequestParam int itemId) {
        cartManager.removeFromCart(userId, itemId);
    }

    @DeleteMapping("/{userId}/clear")
    public void clearCart(@PathVariable String userId) {
        cartManager.clearCart(userId);
    }

    @GetMapping("/{userId}/total")
    public double getTotal(@PathVariable String userId) {
        return cartManager.getCartTotal(userId);
    }
}
