package pokemon.types;

import pokemon.characters.Pokemon;
import pokemon.PokemonBattle;

import static pokemon.types.EnumType.ELECTRIC;

public class Electric extends Pokemon {
    public Electric(String name, int healthPoints, int attackPoints, int defense) {
        super(name, healthPoints, attackPoints, defense, ELECTRIC);
        skills[0] = "Thunder Shock";
        skills[1] = "Thunderbolt";
        skills[2] = "Charge";
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