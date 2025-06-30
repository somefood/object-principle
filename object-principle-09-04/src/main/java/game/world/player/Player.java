package game.world.player;

import game.world.item.Carrier;
import game.world.item.ForwardingCarrier;
import game.world.item.Inventory;
import game.world.worldmap.Direction;
import game.world.worldmap.Position;
import game.world.worldmap.Room;
import game.world.worldmap.WorldMap;

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

    public WorldMap worldMap() {
        return worldMap;
    }
}
