package game;

import game.item.Item;
import game.item.Source;

import java.util.Random;

public class Destroy {
    private Source first;
    private Source second;
    private String itemName;

    public Destroy(Source first, Source second, String itemName) {
        this.first = first;
        this.second = second;
        this.itemName = itemName;
    }

    public boolean isPossible() {
        return contains(first) || contains(second);
    }

    public void perform() {
        if (!isPossible()) throw new IllegalStateException();
        
        if (contains(first)) {
            first.remove(new Item(itemName));
            return;
        }

        if (contains(second)) {
            second.remove(new Item(itemName));
            return;
        }

        if (contains(first) && contains(second)) {
            if (new Random().nextInt(2) == 0) {
                first.remove(new Item(itemName));
            } else {
                second.remove(new Item(itemName));
            }
        }
    }

    private boolean contains(Source carrier) {
        return carrier.find(itemName).isPresent();
    }
}
