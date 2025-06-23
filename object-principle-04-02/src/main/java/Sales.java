public class Sales {
    private Game game;
    private int quantity;
    private Money totalAmount;

    public Sales(Game game) {
        this.game = game;
        this.quantity = 0;
        this.totalAmount = Money.wons(0);
    }

    public void addSale(int quantity, double discountRate) {
        this.quantity += quantity;
        this.totalAmount = this.totalAmount.plus(game.salePrice(discountRate).times(quantity));
    }

    public Money profit() {
        return totalAmount.ceil(0.03);
    }

    public Money totalAmount() {
        return totalAmount;
    }
}
