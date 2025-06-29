package game;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class PlayerTest {
    @Test
    public void move_east() {
        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "", ""),
                new Room(Position.of(1, 0), "", ""),
                new Room(Position.of(1, 1), "", ""));
        Player player = new Player(worldMap, Position.of(0, 0));

        assertThat(player.canMove(Direction.EAST)).isTrue();

        player.move(Direction.EAST);
        assertThat(player.position()).isEqualTo(Position.of(1, 0));
    }

    @Test
    public void can_not_move_west() {
        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "", ""),
                new Room(Position.of(1, 0), "", ""),
                new Room(Position.of(1, 1), "", ""));
        Player player = new Player(worldMap, Position.of(1, 1));

        assertThatIllegalArgumentException().isThrownBy(() -> {
            player.move(Direction.WEST);
        });
    }

    @Test
    public void can_not_move_outside() {
        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "", ""),
                new Room(Position.of(1, 0), "", ""),
                new Room(Position.of(1, 1), "", ""));
        Player player = new Player(worldMap, Position.of(1, 1));

        assertThatIllegalArgumentException().isThrownBy(() -> {
            player.move(Direction.WEST);
        });
    }

    @Test
    public void add_item() {
        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "", ""),
                new Room(Position.of(1, 0), "", ""),
                new Room(Position.of(1, 1), "", ""));
        Player player = new Player(worldMap, Position.of(1, 1));

        player.add(new Item("key"));

        assertThat(player.items()).contains(new Item("key"));
    }

    @Test
    public void remove_item() {
        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "", ""),
                new Room(Position.of(1, 0), "", ""),
                new Room(Position.of(1, 1), "", ""));
        Player player = new Player(worldMap, Position.of(1, 1), new Item("key"));

        player.remove(new Item("key"));

        assertThat(player.items()).doesNotContain(new Item("key"));
    }
}
