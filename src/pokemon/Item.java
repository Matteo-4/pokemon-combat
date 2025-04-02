package pokemon;

public abstract class Item {
    protected String name;
    protected int quantity;

    public Item(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity() {
        if (quantity > 0) {
            quantity--;
        }
    }

    @Override
    public String toString() {
        return name + " (x" + quantity + ")";
    }

    public abstract void use(Trainer trainer, Pokemon pokemon);
}