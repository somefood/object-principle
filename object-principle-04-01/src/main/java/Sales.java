public class Sales {
    
    private Game game;
    private int totalQuantity;
    private long totalPrice;

    public Sales(Game game) {
        this.game = game;
        this.totalQuantity = 0;
        this.totalPrice = 0L;
    }

    public void addSale(int quantity) {
        this.totalQuantity += quantity;
        this.totalPrice += game.calculateSalePrice() * quantity;
    }
    
    public long totalAmount() {
        return totalQuantity * game.calculateSalePrice();
    }
    
    public long profit() {
        return (long) Math.ceil(totalPrice * 0.2);
    }
}
