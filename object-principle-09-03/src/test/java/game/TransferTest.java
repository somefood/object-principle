package game;

import game.item.Inventory;
import game.item.Item;
import game.worldmap.Position;
import game.worldmap.Room;
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
