package game.world.item;

import java.util.List;

public interface Carrier extends Source, Target {
    List<Item> items();
}
