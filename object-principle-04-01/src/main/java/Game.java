public class Game {
    
    private String title;
    private long price;
    private double discountRate;

    public Game(String title, int price, double discountRate) {
        this.title = title;
        this.price = price;
        this.discountRate = discountRate;
    }

    public long calculateSalePrice() {
        return price - (long) Math.ceil(price * discountRate);
    }
}
