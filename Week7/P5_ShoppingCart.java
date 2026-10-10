/**
 * Week 7 - Problem 5: The Shopping Cart
 * Prices stay private; only the total and the item count are exposed (computed on request).
 */
public class P5_ShoppingCart {

    public static void main(String[] args) {
        Cart cart = new Cart("CART-5", 20);
        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        System.out.println("Total: " + cart.getTotal());          // 500
        System.out.println("Items: " + cart.getItemCount());      // 3
    }
}

class Cart {
    private final String cartId;
    private final int[] prices;
    private int itemCount;

    Cart(String cartId, int maxItems) {
        this.cartId = cartId;
        this.prices = new int[maxItems];
    }

    boolean addItem(int price) {
        if (itemCount == prices.length || price < 0) {
            return false;                 // cart full or invalid price
        }
        prices[itemCount++] = price;
        return true;
    }

    int getTotal() {                      // computed every time, never stored separately
        int total = 0;
        for (int i = 0; i < itemCount; i++) {
            total += prices[i];
        }
        return total;
    }

    int getItemCount() {
        return itemCount;
    }

    String getCartId() {
        return cartId;
    }
}
