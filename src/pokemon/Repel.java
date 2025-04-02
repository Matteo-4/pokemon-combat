package pokemon;

public class Repel extends Item {

    public Repel(int quantity) {
        super("Repel", quantity);
    }

    @Override
    public void use(Trainer trainer, Pokemon pokemon) {
        if (pokemon.isAlive()) {
            System.out.println(pokemon.getName() + " is already alive and cannot be revived with Repel.");
        } else {
            pokemon.revive();

            int healAmount = pokemon.getMaxHealthPoints() / 2;
            pokemon.setHealthPoints(Math.min(pokemon.getHealthPoints() + healAmount, pokemon.getMaxHealthPoints()));
            System.out.println(pokemon.getName() + " has been revived and healed by " + healAmount + " HP. New HP: " + pokemon.getHealthPoints());

            reduceQuantity();
            System.out.println("Remaining Repels: " + getQuantity());
        }
    }
}