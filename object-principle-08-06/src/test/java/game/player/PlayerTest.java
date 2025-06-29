package game.player;

import game.item.Inventory;
import game.item.Item;
import game.worldmap.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.fail;

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
        try {
            WorldMap worldMap = new WorldMap(
                    Size.with(2, 2),
                    new Room(Position.of(0, 0), "", ""),
                    new Room(Position.of(1, 0), "", ""),
                    new Room(Position.of(1, 1), "", ""));
            Player player = new Player(worldMap, Position.of(1, 1));

            assertThat(player.canMove(Direction.WEST)).isFalse();
            player.move(Direction.WEST);
            fail();
        } catch (IllegalArgumentException ex) {}
    }

    @Test
    public void can_not_move_outside() {
        try {
            WorldMap worldMap = new WorldMap(
                    Size.with(2, 2),
                    new Room(Position.of(0, 0), "", ""),
                    new Room(Position.of(1, 0), "", ""),
                    new Room(Position.of(1, 1), "", ""));
            Player player = new Player(worldMap, Position.of(1, 1));

            assertThat(player.canMove(Direction.EAST)).isFalse();
            player.move(Direction.WEST);
            fail();
        } catch (IllegalArgumentException ex) {}
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
        Player player = new Player(worldMap, Position.of(1, 1), new Inventory(new Item("key")));

        player.remove(new Item("key"));

        assertThat(player.items()).doesNotContain(new Item("key"));
    }
}
