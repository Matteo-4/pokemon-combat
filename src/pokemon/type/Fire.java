package pokemon.type;

import pokemon.Pokemon;
import pokemon.PokemonBattle;

import static pokemon.type.EnumType.FIRE;

public class Fire extends Pokemon {
    public Fire(String name, int healthPoints, int attackPoints, int defense) {
        super(name, healthPoints, attackPoints,defense, FIRE);
        skills[0] = "Flamethrower";
        skills[1] = "Intimidation";
        skills[2] = "Rage";
    }

    @Override
    public void attack(Pokemon target) {
        double bonus = type.getBonus(target.getType());
        int damage = (int)(attackPoints * bonus);
        target.takeDamage(damage);
    }

    @Override
    public void attack(Pokemon target, PokemonBattle battle) {

    }
}
