import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SalesTest {

    @Test
    public void profit() {
        Sales sales = new Sales(new Game("상품", Money.wons(1111)));
        sales.addSale(1, 0.01);
        sales.addSale(2, 0.02);

        assertThat(sales.profit()).isEqualTo(Money.wons(99));
    }
}
