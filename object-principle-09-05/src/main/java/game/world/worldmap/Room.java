package game.world.worldmap;

import game.world.item.Carrier;
import game.world.item.ForwardingCarrier;
import game.world.item.Inventory;

public class Room extends ForwardingCarrier {
    private String name;
    private String description;
    private Position position;

    public Room(Position position, String name, String description) {
        this(position, name, description, new Inventory());
    }

    public Room(Position position, String name, String description, Carrier carrier) {
        super(carrier);
        this.position = position;
        this.name = name;
        this.description = description;
    }

    public Position position() {
        return position;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }
}