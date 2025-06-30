package game.world;

import game.world.item.Source;
import game.world.item.Target;

public class Transfer {
    private Source source;
    private Target target;
    private String itemName;

    public Transfer(Source source, Target target, String itemName) {
        this.source = source;
        this.target = target;
        this.itemName = itemName;
    }

    public boolean isPossible() {
        return source.find(itemName).isPresent();
    }

    public boolean transfer() {
        return source.find(itemName).map(
                item -> {
                    source.remove(item);
                    target.add(item);
                    return true;
                }).orElse(false);
    }

    public void perform() {
        source.find(itemName).ifPresent(
                item -> {
                    source.remove(item);
                    target.add(item);
                });
    }
}
