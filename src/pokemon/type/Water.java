package pokemon.type;

import pokemon.Pokemon;
import pokemon.PokemonBattle;

import static pokemon.type.EnumType.WATER;

public class Water extends Pokemon {
    public Water(String name, int healthPoints, int attackPoints, int defense) {
        super(name, healthPoints, attackPoints, defense, WATER);
        skills[0] = "Water Gun";
        skills[1] = "Water Jet";
        skills[2] = "Rain Dance";
    }
    @Override
    public void attack(Pokemon target) {

    }
    @Override
    public void attack(Pokemon target, PokemonBattle battle) {
        double bonus = type.getBonus(target.getType());
        int damage = (int) (attackPoints * bonus);
        target.takeDamage(damage);
    }
}
