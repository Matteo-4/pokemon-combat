package pokemon;

public class Potion extends Item {

    private final int healthPoints;

    public Potion(int quantity, int healthPoints) {
        super("Potion", quantity);
        this.healthPoints = healthPoints;
    }

    @Override
    public void use(Trainer trainer, Pokemon pokemon) {
        if (getQuantity() > 0) {
            pokemon.setHealthPoints(Math.min(pokemon.getHealthPoints() + healthPoints, pokemon.getMaxHealthPoints()));
            System.out.println(pokemon.getName() + " has been healed by " + healthPoints + " HP");
            reduceQuantity();
            System.out.println("Remaining potions: " + getQuantity());
        } else {
            System.out.println("No potions left!");
        }
    }
}
