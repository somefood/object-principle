public class Game {
    private String name;
    private Money price;

    public Game(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    public Money salePrice(double discountRate) {
        return price.minus(price.ceil(discountRate));
    }
}
