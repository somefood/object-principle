import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SalesTest {

    @Test
    void profit() {
        Game game = new Game("오브젝트 퀘스트", 10000, 0.1);
        Sales sales = new Sales(game);

        sales.addSale(3);
        sales.addSale(2);

        assertThat(sales.totalAmount()).isEqualTo(45000);
        assertThat(sales.profit()).isEqualTo(9000);
    }
    
    @Test
    void invalid_profit() {
        Game game = new Game("오브젝트 퀘스트", 10000, 0.1);
        Sales sales = new Sales(game);
        
        sales.addSale(3);
        
        assertThat(sales.totalAmount()).isEqualTo(27000);
        assertThat(sales.profit()).isEqualTo(5400);

        Sales anotherSales = new Sales(game);
        anotherSales.addSale(2);

        assertThat(anotherSales.totalAmount()).isEqualTo(18000);
        assertThat(anotherSales.profit()).isEqualTo(3600);
    }
}
