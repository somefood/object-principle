package adventure;

import java.util.Objects;

public class Size {
    
    private final int width;
    private final int height;
    
    public static Size with(int width, int height) {
        return new Size(width, height);
    }
    
    private Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
    
    public int width() {
        return width;
    }
    
    public int height() {
        return height;
    }
    
    public int area() {
        return width * height;
    }
    
    public boolean contains(Position position) {
        return position.x() >= 0 && position.x() < width &&
               position.y() >= 0 && position.y() < height;
    }

    public int indexOf(Position position) {
        return position.x() + position.y() * width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return width == size.width && height == size.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
