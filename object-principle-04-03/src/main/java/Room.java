public class Room {
    private Position position;
    private String name;
    private String description;

    public Room(Position position, String name, String description) {
        this.position = position;
        this.name = name;
        this.description = description;
    }

    public String name() {
        return name;
    }

    public String description() {
        return description;
    }

    public int x() {
        return position().x();
    }

    public int y() {
        return position().y();
    }

    public Position position() {
        return Position.of(position.x(), position.y());
    }
}
