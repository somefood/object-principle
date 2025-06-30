package game.player;

import game.item.Carrier;
import game.item.ForwardingCarrier;
import game.item.Inventory;
import game.worldmap.Direction;
import game.worldmap.Position;
import game.worldmap.Room;
import game.worldmap.WorldMap;

public class Player extends ForwardingCarrier {
    private WorldMap worldMap;
    private Position position;

    public Player(WorldMap worldMap, Position position) {
        this(worldMap, position, new Inventory());
    }

    public Player(WorldMap worldMap, Position position, Carrier carrier) {
        super(carrier);
        this.worldMap = worldMap;
        this.position = position;
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
