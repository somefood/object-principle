package game.world;

import game.world.item.Inventory;
import game.world.item.Item;
import game.world.worldmap.Position;
import game.world.worldmap.Room;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TransferTest {
    @Test
    public void transfer_is_possible() {
        Room source = new Room(Position.of(0,0), "", "", new Inventory(new Item("item")));
        Room target = new Room(Position.of(0,0), "", "", new Inventory(new Item("item")));

        Transfer transfer = new Transfer(source, target, "item");

        assertThat(transfer.isPossible()).isTrue();
    }

    @Test
    public void transfer() {
        Room source = new Room(Position.of(0,0), "", "", new Inventory(new Item("item")));
        Room target = new Room(Position.of(0,0), "", "", new Inventory(new Item("item")));

        Transfer transfer = new Transfer(source, target, "item");
        transfer.perform();

        assertThat(source.find("item")).isEmpty();
        assertThat(target.find("item")).isPresent();
    }
}
