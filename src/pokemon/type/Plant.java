package pokemon.type;

import pokemon.Pokemon;
import pokemon.PokemonBattle;

import static pokemon.type.EnumType.PLANT;

public class Plant extends Pokemon {

    public Plant(String name, int healthPoints, int defense, int attackPoints) {
        super(name, healthPoints, attackPoints, defense, PLANT);
        skills[0] = "Vine Whip";
        skills[1] = "Toxic Powder";
        skills[2] = "Synthesis";
    }

    @Override
    public void attack(Pokemon target) {
        double bonus = type.getBonus(target.getType());
        int damage = (int) (attackPoints * bonus);
        target.takeDamage(damage);
    }

    @Override
    public void attack(Pokemon target, PokemonBattle battle) {

    }
}
