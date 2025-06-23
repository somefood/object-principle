import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductTest {
    
    @Test
    void salePrice() {
        Game product = new Game("상품", Money.wons(1111));
        assertThat(product.salePrice(0.01)).isEqualTo(Money.wons(1099));
    }
}
