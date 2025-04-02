package pokemon;

import pokemon.characters.Pokemon;
import pokemon.characters.Trainer;
import pokemon.items.Item;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PokemonBattle extends JFrame {
    private final JTextArea textArea = new JTextArea();
    private final JButton selectAttackButton = new JButton("Select Attack");
    private final JButton changePokemonButton = new JButton("Change Pokémon");
    private final JButton useItemButton = new JButton("Use Item");
    private Trainer currentTrainer, opponentTrainer;
    private boolean playerTurn = true;
    private JPanel battlePanel;

    public PokemonBattle(Trainer trainer1, Trainer trainer2) {
        this.currentTrainer = trainer1;
        this.opponentTrainer = trainer2;
        initializeInterface();
    }

    private void initializeInterface() {
        setTitle("Pokémon Battle");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        battlePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(115, 200, 255),
                        0, getHeight(), new Color(100, 180, 100)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        battlePanel.setLayout(new BorderLayout());
        add(battlePanel, BorderLayout.CENTER);

        textArea.setEditable(false);
        textArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 150));
        battlePanel.add(scrollPane, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel();

        selectAttackButton.setBackground(new Color(80, 130, 240));
        selectAttackButton.setForeground(Color.WHITE);
        selectAttackButton.setPreferredSize(new Dimension(120, 40));

        changePokemonButton.setBackground(new Color(80, 200, 120));
        changePokemonButton.setForeground(Color.WHITE);
        changePokemonButton.setPreferredSize(new Dimension(150, 40));

        useItemButton.setBackground(new Color(240, 200, 80));
        useItemButton.setForeground(Color.WHITE);
        useItemButton.setPreferredSize(new Dimension(120, 40));

        buttonPanel.add(selectAttackButton);
        buttonPanel.add(changePokemonButton);
        buttonPanel.add(useItemButton);
        add(buttonPanel, BorderLayout.SOUTH);

        selectAttackButton.addActionListener(e -> selectAttack());
        changePokemonButton.addActionListener(e -> changePokemon());
        useItemButton.addActionListener(e -> useItem());

        displayMessage("Battle begins between %s and %s", currentTrainer.getName(), opponentTrainer.getName());
    }

    private void opponentTurn() {
        if (playerTurn) {
            return;
        }

        Pokemon attacker = opponentTrainer.getActivePokemon();
        Pokemon defender = currentTrainer.getActivePokemon();

        Random rand = new Random();
        int attackIndex = rand.nextInt(3); // Pick attack (0, 1 or 2)
        String attackName = attacker.getAttackNames()[attackIndex];

        attacker.attack(defender, attackIndex);
        displayMessage("%s (Opponent) uses %s on %s (Remaining HP: %d)",
                attacker.getName(), attackName, defender.getName(), defender.getHealthPoints());

        if (defender.isKO()) {
            displayMessage("%s is KO.", defender.getName());
            if (currentTrainer.allKO()) {
                displayMessage("%s wins the battle!", opponentTrainer.getName());
                disableButtons();
                return;
            } else {
                forcePokemonChange(currentTrainer);
            }
        }
        switchTurn();
    }

    private void selectAttack() {
        if (!playerTurn) {
            return;
        }

        Pokemon activePokemon = currentTrainer.getActivePokemon();
        String[] attackNames = activePokemon.getAttackNames();

        String attackChoice = (String) JOptionPane.showInputDialog(this,
                "Choose an attack:", "Select Attack",
                JOptionPane.QUESTION_MESSAGE, null,
                attackNames, attackNames[0]);

        if (attackChoice != null) {
            int attackIndex = java.util.Arrays.asList(attackNames).indexOf(attackChoice);
            performSpecificAttack(attackIndex);
        }
    }

    private void performSpecificAttack(int attackIndex) {
        if (!playerTurn) {
            return;
        }

        Pokemon attacker = currentTrainer.getActivePokemon();
        Pokemon defender = opponentTrainer.getActivePokemon();

        String attackName = attacker.getAttackNames()[attackIndex];
        attacker.attack(defender, attackIndex);
        displayMessage("%s uses %s on %s (Remaining HP: %d)",
                attacker.getName(), attackName, defender.getName(), defender.getHealthPoints());

        if (defender.isKO()) {
            displayMessage("%s is KO.", defender.getName());
            if (opponentTrainer.allKO()) {
                displayMessage("%s wins the battle!", currentTrainer.getName());
                disableButtons();
                return;
            } else {
                forcePokemonChange(opponentTrainer);
            }
        }

        switchTurn();
        opponentTurn();
    }

    private void forcePokemonChange(Trainer trainer) {
        List<Pokemon> alivePokemons = trainer.getPokemons().stream()
                .filter(p -> !p.isKO())
                .collect(Collectors.toList());

        if (alivePokemons.isEmpty()) {
            displayMessage("All of %s's Pokémon are KO! Battle ends.", trainer.getName());
            disableButtons();
            return;
        }

        if (trainer == currentTrainer) {
            Pokemon choice = selectObject("Choose a Pokémon", alivePokemons);
            if (choice != null) {
                currentTrainer.changePokemon(choice);
                displayMessage("%s switches to %s", currentTrainer.getName(), choice.getName());
            }
        } else {
            Random rand = new Random();
            Pokemon choice = alivePokemons.get(rand.nextInt(alivePokemons.size()));
            trainer.changePokemon(choice);
            displayMessage("%s sends out %s!", trainer.getName(), choice.getName());
        }
    }

    private void changePokemon() {
        if (!playerTurn) {
            return;
        }

        List<Pokemon> alivePokemons = currentTrainer.getPokemons().stream()
                .filter(p -> !p.isKO() && p != currentTrainer.getActivePokemon())
                .collect(Collectors.toList());

        Pokemon choice = selectObject("Choose a Pokémon", alivePokemons);
        if (choice != null) {
            currentTrainer.changePokemon(choice);
            displayMessage("%s switches to %s", currentTrainer.getName(), choice.getName());

            switchTurn();
            opponentTurn();
        }
    }

    private void useItem() {
        if (!playerTurn) {
            return;
        }

        List<Item> items = currentTrainer.getBag().stream()
                .filter(i -> i.getQuantity() > 0)
                .collect(Collectors.toList());

        if (items.isEmpty()) {
            displayMessage("You have no items available!");
            return;
        }

        Item chosenItem = selectObject("Choose an item", items);

        if (chosenItem != null) {
            Pokemon chosenPokemon = selectObject("Choose a Pokémon", currentTrainer.getPokemons());
            if (chosenPokemon != null) {
                chosenItem.use(currentTrainer, chosenPokemon);
                displayMessage("%s uses %s on %s", currentTrainer.getName(), chosenItem.getName(), chosenPokemon.getName());

                if (chosenItem.getName().equals("Revive")) {
                    chosenPokemon.revive();
                    if (!chosenPokemon.isKO()) {
                        List<Pokemon> alivePokemons = currentTrainer.getPokemons().stream()
                                .filter(p -> !p.isKO())
                                .collect(Collectors.toList());

                        alivePokemons.add(chosenPokemon);
                        displayMessage("%s is revived and ready to fight!", chosenPokemon.getName());
                    }
                } else if (chosenItem.getName().equals("Potion")) {
                    int healAmount = chosenPokemon.getMaxHealthPoints() / 4;  // Adjust potion healing amount as needed
                    chosenPokemon.setHealthPoints(Math.min(chosenPokemon.getHealthPoints() + healAmount, chosenPokemon.getMaxHealthPoints()));
                    displayMessage("%s has been healed by %d HP! New HP: %d", chosenPokemon.getName(), healAmount, chosenPokemon.getHealthPoints());
                }

                switchTurn();
                opponentTurn();
            }
        }
    }

    private void switchTurn() {
        playerTurn = !playerTurn;
    }

    private void disableButtons() {
        selectAttackButton.setEnabled(false);
        changePokemonButton.setEnabled(false);
        useItemButton.setEnabled(false);
    }

    private void displayMessage(String format, Object... args) {
        textArea.append(String.format(format + "\n", args));
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    private <T> T selectObject(String message, List<T> objects) {
        if (objects.isEmpty()) {
            displayMessage("No options available for %s", message);
            return null;
        }
        return (T) JOptionPane.showInputDialog(this, message, "Selection",
                JOptionPane.QUESTION_MESSAGE, null, objects.toArray(), objects.get(0));

    }
}