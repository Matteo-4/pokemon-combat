import pokemon.*;
import pokemon.characters.Pokemon;
import pokemon.characters.Trainer;
import pokemon.items.Potion;
import pokemon.items.Repel;
import pokemon.types.Electric;
import pokemon.types.Fire;
import pokemon.types.Plant;
import pokemon.types.Water;

import javax.swing.*;

public static void main(String[] args) {

    Trainer trainer1 = new Trainer("Ondine");
    Pokemon charmander = new Fire("Charmander", 100, 20, 10);
    Pokemon bulbasaur = new Plant("Bulbasaur", 110, 18, 12);
    Pokemon squirtle = new Water("Squirtle", 105, 19, 15);

    trainer1.addPokemon(charmander);
    trainer1.addPokemon(bulbasaur);
    trainer1.addPokemon(squirtle);

    trainer1.addItem(new Potion(3, 20));
    trainer1.addItem(new Repel(2));

    Trainer opponentTrainer = new Trainer("Sacha");
    Pokemon pikachu = new Electric("Pikachu", 90, 22, 8);
    Pokemon Turtwig = new Plant("Turtwig", 100, 17, 12);
    Pokemon Arcanine = new Fire("Arcanine", 95, 20, 10);

    opponentTrainer.addPokemon(pikachu);
    opponentTrainer.addPokemon(Turtwig);
    opponentTrainer.addPokemon(Arcanine);

    opponentTrainer.addItem(new Potion(3, 20));
    opponentTrainer.addItem(new Repel(2));

    SwingUtilities.invokeLater(() -> {
        PokemonBattle battle = new PokemonBattle(trainer1, opponentTrainer);
        battle.setVisible(true);
    });
}
