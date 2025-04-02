Pokémon Battle - README
Description
Pokémon Battle is a game where two trainers battle each other with their Pokémon. Each player can choose actions such as attacking, switching Pokémon, or using items during their turn. The game is turn-based, where each player's actions affect the state of their Pokémon and the outcome of the battle.

Key Features
1. Select an Attack
   During their turn, a trainer can choose an attack from the available moves of their active Pokémon.

The attack deals damage to the opponent's Pokémon and may have special effects depending on the type of the attacking and defending Pokémon.

2. Switch Pokémon
   A trainer can switch their active Pokémon with another living Pokémon from their team.

This may be necessary when the active Pokémon is knocked out (KO) or when a strategic change is needed.

3. Use an Item
   During their turn, a player can use an item from their bag. For example, a "Revive" item can bring a KO'd Pokémon back to life and heal it for a portion of its health points.

Items are limited in quantity and are consumed upon use.

4. Opponent's Turn
   The opponent (either AI or another player) will take their turn automatically by attacking, switching Pokémon, or using an item based on the state of the game.

5. KO Management
   When a Pokémon is KO'd, it is removed from the battle. The player must then choose another living Pokémon to continue fighting.

If all of a trainer's Pokémon are KO'd, the battle ends and the other trainer wins.

6. Graphical User Interface
   The game uses a graphical interface to display battle messages, allow players to select actions, and visualize the Pokémon's status during the battle.

Buttons allow players to interact with the game, choosing an attack, switching Pokémon, or using items.

Code Overview
Main Classes
PokemonBattle: Manages the logic of the battle between two trainers. This class uses a graphical interface (JFrame) to display information and allow player actions.

Trainer: Represents a trainer and their collection of Pokémon. It contains methods for managing active Pokémon and the actions available during the battle.

Pokemon: Represents a Pokémon. It has attributes like health points, attack, defense, and methods for attacking, taking damage, and managing status effects.

Item: A parent class for items used during the battle. For example, "Repel" and "Revive" are items that affect Pokémon.

Key Methods
selectAttack(): Allows the trainer to choose an attack for their active Pokémon.

performSpecificAttack(int attackIndex): Executes the selected attack on the opponent's Pokémon.

changePokemon(): Allows a trainer to switch their active Pokémon.

useItem(): Allows a trainer to use an item (e.g., "Revive" to bring back a KO'd Pokémon).

opponentTurn(): Manages the opponent's turn. An opponent's Pokémon performs an attack on the player's Pokémon.

forcePokemonChange(Trainer trainer): Forces a Pokémon switch when a trainer's active Pokémon is KO'd.

Graphical Interface
JFrame: The main window containing buttons to select actions (attack, change Pokémon, use item) and a text area to display battle messages.

JTextArea: Displays battle messages (e.g., attacks and Pokémon switches).

JButton: Buttons allow players to select actions on their turn (attack, switch Pokémon, use items).

Conclusion
Pokémon Battle offers a classic strategic battle experience between two trainers. The actions are based on the player's decisions, the types of Pokémon, and the use of items. The graphical interface allows easy interaction with the game using buttons and displays the battle messages in real-time.