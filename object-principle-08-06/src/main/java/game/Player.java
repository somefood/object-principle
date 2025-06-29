package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Player {
    private WorldMap worldMap;
    private Position position;
    private List<Item> items = new ArrayList<>();

    public Player(WorldMap worldMap, Position position, Item... items) {
        this.worldMap = worldMap;
        this.position = position;
        this.items.addAll(List.of(items));
    }

    public List<Item> items() {
        return Collections.unmodifiableList(items);
    }

    public void add(Item item) {
        this.items.add(item);
    }

    public Optional<Item> find(String itemName) {
        return items.stream().filter(item -> item.name().equalsIgnoreCase(itemName)).findFirst();
    }

    public void remove(Item item) {
        this.items.remove(item);
    }

    public boolean canMove(Direction direction) {
        return !worldMap.isBlocked(position.shift(direction));
    }

    public void move(Direction direction) {
        if (!canMove(direction)) {
            throw new IllegalArgumentException();
        }

        this.position = this.position.shift(direction);
    }

    Position position() {
        return position;
    }

    public Room currentRoom() {
        return worldMap.roomAt(position);
    }
}