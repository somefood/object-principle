package game.world.worldmap;

import game.world.worldmap.Position;
import game.world.worldmap.Room;
import game.world.worldmap.Size;
import game.world.worldmap.WorldMap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WorldMapTest {
    @Test
    public void blocked() {
        WorldMap worldMap = new WorldMap(
                Size.with(2, 2),
                new Room(Position.of(0, 0), "", ""),
                new Room(Position.of(1, 0), "", ""),
                new Room(Position.of(1, 1), "", ""));

        assertThat(worldMap.isBlocked(Position.of(0, 0))).isFalse();
        assertThat(worldMap.isBlocked(Position.of(1, 0))).isFalse();

        assertThat(worldMap.isBlocked(Position.of(0, 1))).isTrue();
        assertThat(worldMap.isBlocked(Position.of(1, -1))).isTrue();
        assertThat(worldMap.isBlocked(Position.of(1, 2))).isTrue();
    }
}
