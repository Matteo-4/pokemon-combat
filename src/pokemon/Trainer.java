package pokemon;

import java.util.ArrayList;
import java.util.List;

public class Trainer {
    private String name;
    private List<Pokemon> pokemons;
    private List<Item> bag;
    private Pokemon activePokemon;

    public Trainer(String name) {
        this.name = name;
        this.pokemons = new ArrayList<>();
        this.bag = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        if (pokemons.size() < 3) {
            pokemons.add(pokemon);
            if (activePokemon == null) {
                activePokemon = pokemon;
            }
            System.out.println(pokemon.getName() + " has been added to your team!");
        } else {
            System.out.println("Cannot add more than 3 Pokémon");
        }
    }

    public void changePokemon(Pokemon pokemon) {
        if (pokemons.contains(pokemon) && !pokemon.isKO()) {
            activePokemon = pokemon;
            System.out.println(name + " switches to Pokémon " + pokemon.getName());
        } else {
            System.out.println("Cannot switch Pokémon");
        }
    }

    public boolean allKO() {
        return pokemons.stream().noneMatch(Pokemon::isKO);
    }

    public String getName() {
        return name;
    }

    public Pokemon getActivePokemon() {
        return activePokemon;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Item> getBag() {
        return bag;
    }

    public void addItem(Potion heal) {
        bag.add(heal);
    }

    public void useItem(String name, Pokemon chosenPokemon) {
    }

    public void addItem(Repel repel){bag.add(repel);} {
    }
}
