package game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNullPointerException;

public class RoomTest {
    @Test
    public void upcast_error() {
        Player player = new Room(Position.of(0, 0), "(0,0)", "(0,0) 설명", new Item("key"));

        assertThatNullPointerException().isThrownBy(() -> {
            player.move(Direction.EAST);
        });
    }
}
