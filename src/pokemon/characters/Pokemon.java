package pokemon.characters;

import pokemon.PokemonBattle;
import pokemon.types.EnumType;

public abstract class Pokemon {

    private String name;
    private int healthPoints;
    private int maxHealthPoints;
    protected int attackPoints;
    private int baseAttackPoints;
    private int defense;
    private boolean isKO;
    protected EnumType type;
    protected String[] skills = new String[3];

    public Pokemon(String name, int maxHealthPoints, int attackPoints, int defense, EnumType type) {
        this.name = name;
        this.maxHealthPoints = maxHealthPoints;
        this.healthPoints = maxHealthPoints;
        this.attackPoints = attackPoints;
        this.baseAttackPoints = attackPoints;
        this.defense = defense;
        this.isKO = false;
        this.type = type;
        this.skills[0] = "Offensive Attack"; // Skill 1: offensive attack
        this.skills[1] = "Lower Defense";   // Skill 2: lower target defense
        this.skills[2] = "Increase Attack"; // Skill 3: increase attack
    }

    public void attack(Pokemon target, int skillIndex) {
        if (!isAlive()) {
            System.out.println(name + " is K.O. and cannot attack!");
            return;
        }

        if (skillIndex >= 0 && skillIndex < 3 && skills[skillIndex] != null) {
            System.out.println(name + " uses " + skills[skillIndex] + "!");

            switch (skillIndex) {
                case 0:
                    offensiveAttack(target);
                    break;
                case 1:
                    lowerDefense(target);
                    break;
                case 2:
                    increaseAttack();
                    break;
            }
        } else {
            System.out.println("Skill not available!");
        }
    }

    private void offensiveAttack(Pokemon target) {
        double bonus = type.getBonus(target.getType());

        int baseDamage = (int) (attackPoints * 0.1);
        int damage = (int) (baseDamage * bonus * (100.0 / target.getDefense()));

        damage = Math.max(1, damage);

        if (bonus > 1) {
            System.out.println("It's super effective!");
        } else if (bonus < 1) {
            System.out.println("It's not very effective...");
        }

        target.takeDamage(damage);
    }

    private void lowerDefense(Pokemon target) {
        int newDefense = (int) (target.getDefense() * 0.8);
        target.setDefense(newDefense);
        System.out.println(target.getName() + "'s defense is lowered!");
        System.out.println(target.getName() + " Defense: " + newDefense + "%");
    }

    private void increaseAttack() {
        int newAttack = (int) (attackPoints * 1.2);
        attackPoints = newAttack;
        System.out.println(name + "'s attack increases!");
        System.out.println("Attack of " + name + ": " + attackPoints + " (Base: " + baseAttackPoints + ")");
    }

    public void takeDamage(int damage) {
        healthPoints -= damage;

        if (healthPoints <= 0) {
            healthPoints = 0;
            isKO = true;
            System.out.println(name + " is K.O.");
        } else {
            System.out.println(name + " has " + healthPoints + "/" + maxHealthPoints + " HP remaining.");
        }
    }

    public boolean isAlive() {
        return !isKO;
    }

    public boolean isKO() {
        return isKO;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public int getMaxHealthPoints() {
        return maxHealthPoints;
    }

    public int getDefense() {
        return defense;
    }

    public EnumType getType() {
        return type;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public String[] getAttackNames() {
        return skills;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public abstract void attack(Pokemon target);

    @Override
    public String toString() {
        return this.name;
    }

    public abstract void attack(Pokemon target, PokemonBattle battle);

    public void revive() {
    }
}
