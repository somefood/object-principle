package game.worldmap;

import game.item.Item;
import game.item.Target;

public class WorldMap implements Target {
    private Size size;
    private Room[] rooms;

    public WorldMap(Size area, Room... rooms) {
        this.size = area;
        this.rooms = new Room[size.area()];
        for(Room room : rooms) {
            this.rooms[size.indexOf(room.position())] = room;
        }
    }

    public boolean isBlocked(Position position) {
        return isExcluded(position) || roomAt(position) == null;
    }

    private boolean isExcluded(Position position) {
        return !size.contains(position);
    }

    public Room roomAt(Position position) {
        return rooms[size.indexOf(position)];
    }

    @Override
    public void add(Item item) {
        Position position = size.anyPosition();
        if (isBlocked(position)) {
            return;
        }
        roomAt(position).add(item);
    }
}
