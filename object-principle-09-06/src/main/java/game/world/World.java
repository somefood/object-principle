package game.world;

import game.InputOutput;
import game.world.item.Carrier;
import game.world.item.Item;
import game.world.item.Source;
import game.world.item.Target;
import game.world.player.Player;
import game.world.worldmap.Direction;

import java.util.stream.Collectors;

public class World {
    private Player player;
    private InputOutput io;

    public World(Player player, InputOutput io) {
        this.player = player;
        this.io = io;
    }

    public void tryMove(Direction direction) {
        if (player.canMove(direction)) {
            player.move(direction);
            showRoom();
            return;
        }

        showBlocked();
    }

    private void showBlocked() {
        io.showLine("이동할 수 없습니다.");
    }

    public void showRoom() {
        io.showLine("당신은 [" + player.currentRoom().name() + "]에 있습니다.");
        io.showLine(player.currentRoom().description());
        if (!player.currentRoom().items().isEmpty()) {
            showItems(player.currentRoom(), "아이템:");
        }
    }

    public void takeItem(String itemName) {
        transfer(player.currentRoom(), player, itemName,
                itemName + "을(를) 얻었습니다.",
                itemName + "을(를) 얻을 수 없습니다.");
    }

    public void dropItem(String itemName) {
        transfer(player, player.currentRoom(), itemName,
                itemName + "을(를) 버렸습니다.",
                itemName + "을(를) 버릴 수 없습니다.");
    }

    public void throwItem(String itemName) {
        transfer(player, player.worldMap(), itemName,
                itemName + "을(를) 어딘가로 던졌습니다.",
                itemName + "을(를) 던질 수 없습니다.");
    }

    public void transfer(Source source, Target target,
                         String itemName, String success, String fail) {
        Transfer transfer = new Transfer(source, target, itemName);

        if (transfer.isPossible()) {
            transfer.perform();
            io.showLine(success);
            return;
        }

        io.showLine(fail);
    }

    public void showInventory() {
        showItems(player, "인벤토리 목록:");
    }

    public void destroyItem(String itemName) {
        Destroy destroy = new Destroy(player, player.currentRoom(), itemName);
        if (destroy.isPossible()) {
            destroy.perform();
            io.showLine(itemName + "을(를) 파괴했습니다.");
            return;
        }

        io.showLine(itemName + "을(를) 파괴할 수 없습니다.");
    }

    private void showItems(Carrier carrier, String title) {
        io.showLine(
                carrier.items().stream()
                        .map(Item::name)
                        .collect(Collectors.joining(", ", title + " [ ", " ]")));
    }
}

