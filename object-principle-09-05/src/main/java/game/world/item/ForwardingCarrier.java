package game.world.item;

import java.util.List;
import java.util.Optional;

public abstract class ForwardingCarrier implements Carrier {
    private Carrier carrier;

    public ForwardingCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    @Override
    public List<Item> items() {
        return carrier.items();
    }

    @Override
    public Optional<Item> find(String itemName) {
        return carrier.find(itemName);
    }

    @Override
    public void add(Item item) {
        carrier.add(item);
    }

    @Override
    public void remove(Item item) {
        carrier.remove(item);
    }
}