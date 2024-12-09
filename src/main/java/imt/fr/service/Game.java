
package imt.fr.service;

import imt.fr.entity.Cards.Card;
import imt.fr.entity.Cards.Location;
import imt.fr.entity.Enemies.*;
import imt.fr.entity.Hero;
import java.util.Scanner;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import java.util.logging.Logger;
import java.util.logging.LogManager;
import java.util.logging.LogManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

    private static final Logger logger = Logger.getLogger("Main.java");
    protected Card card;
    private Hero hero;
    private List<Enemy> enemies;

    /**
     * Initializes the game by setting up the map, creating enemies based on the chosen level,
     * and allowing the player to select a hero.
     * <p>
     * The player chooses a difficulty level (Level1, Level2, or Level3), which determines:
     * <ul>
     *     <li>The length of the map.</li>
     *     <li>The number and types of enemies created for the level.</li>
     * </ul>
     * The player also selects a hero from a list of predefined characters, each with unique attributes.
     * <p>
     * Enemies are distributed as follows:
     * <ul>
     *     <li><strong>Level1:</strong> 3 enemies, random mix of Catcher, Necromancer, and Skeleton.</li>
     *     <li><strong>Level2:</strong> 4 enemies, random mix of Catcher, Necromancer, and Skeleton.</li>
     *     <li><strong>Level3:</strong> 5 enemies, random mix of Catcher, Necromancer, Skeleton, and Gangster
     *     (Gangsters appear only in Level3).</li>
     * </ul>
     *
     * At the end of this method, the selected hero, map, and enemies are initialized and logged.
     */
    public void initializeGame() {
        logger.info("Initializing game...");

        // Choix du niveau de difficulté
        System.out.println("Choose the difficulty level:");
        System.out.println("1. Level1 (3 enemies)");
        System.out.println("2. Level2 (4 enemies)");
        System.out.println("3. Level3 (5 enemies)");

        Scanner scanner = new Scanner(System.in);
        int levelChoice = 0;

        while (levelChoice < 1 || levelChoice > 3) {
            System.out.print("Enter the number of your chosen level: ");
            if (scanner.hasNextInt()) {
                levelChoice = scanner.nextInt();
                if (levelChoice < 1 || levelChoice > 3) {
                    System.out.println("Invalid choice. Please select a number between 1 and 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        // Définir la longueur de la carte, le niveau choisi et le lieu du parcours
        int length = 3 + (levelChoice - 1); // Level1 -> 3, Level2 -> 4, Level3 -> 5
        String levelName = "Level" + levelChoice;
        Location location = switch (levelChoice) {
            case 1 -> Location.DESERT;
            case 2 -> Location.FOREST;
            case 3 -> Location.MOUNTAIN;
            default -> throw new IllegalStateException("Unexpected value: " + levelChoice);
        };

        // Création de la carte avec la location spécifiée
        card = new Card(location, levelName, length);
        System.out.println("You have chosen: " + levelName + " with a length of " + length);

        // Création des ennemis spécifiques
        enemies = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            if (levelChoice == 3 && i % 2 == 0) {
                // Ajouter des Gangsters dans le Level3
                enemies.add(new Gangster("Gangster " + i, 15, 8, 3));
            } else {
                // Ajouter aléatoirement des Catcher, Necromancer et Skeleton
                int enemyType = (int) (Math.random() * 3); // Générer un type aléatoire
                switch (enemyType) {
                    case 0 -> enemies.add(new Catcher("Catcher N°" + i, 20, 6, 5));
                    case 1 -> enemies.add(new Necromancer("Necromancer N°" + i, 18, 7, 4));
                    case 2 -> enemies.add(new Skeleton("Skeleton N°" + i, 16, 5, 2));
                }
            }
        }
        System.out.println(length + " enemies have been created for this level!");

        // Création de la liste des héros et choix du héros
        List<Hero> availableHeroes = new ArrayList<>();
        availableHeroes.add(new Hero("Captain-Ousmane", 10, 20, 3));
        availableHeroes.add(new Hero("Rock-Abdoulaye", 15, 15, 5));
        availableHeroes.add(new Hero("Archer", 20, 12, 2));
        availableHeroes.add(new Hero("Paladin", 22, 8, 6));
        availableHeroes.add(new Hero("Rogue", 17, 14, 4));

        System.out.println("Choose your hero:");
        for (int i = 0; i < availableHeroes.size(); i++) {
            System.out.println((i + 1) + ". " + availableHeroes.get(i).getName());
        }

        int heroChoice = 0;
        while (heroChoice < 1 || heroChoice > availableHeroes.size()) {
            System.out.print("Enter the number of your chosen hero: ");
            if (scanner.hasNextInt()) {
                heroChoice = scanner.nextInt();
                if (heroChoice < 1 || heroChoice > availableHeroes.size()) {
                    System.out.println("Invalid choice. Please select a number between 1 and " + availableHeroes.size() + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }

        hero = availableHeroes.get(heroChoice - 1);
        System.out.println("You have chosen: " + hero.getName());

        logger.info("Game initialized on trail");
        logger.info("Hero chosen: " + hero.getName() + " (HP: " + hero.getHealthPoints() + ", Attack: " + hero.getAttackPower() + ", Defense: " + hero.getDefense() + ")");
        logger.info(length + " enemies created for this level.");
    }



    /**
     * Starts the game and handles the main gameplay loop.
     * <p>
     * The method guides the hero through the list of enemies, handling combat mechanics
     * and special conditions for specific types of enemies. The game ends when either:
     * <ul>
     *     <li>The hero defeats all enemies and completes the level.</li>
     *     <li>The hero's health points drop to zero, resulting in a game over.</li>
     * </ul>
     * <p>
     * Key gameplay mechanics include:
     * <ul>
     *     <li><strong>Gangsters attack first:</strong> Gangsters always initiate combat with a single attack.</li>
     *     <li><strong>Hero's attacks:</strong> The hero attacks a random number of times (1 to 5) in each turn.</li>
     *     <li><strong>Counterattacks:</strong> Enemies counterattack if they survive the hero's attacks in a turn.</li>
     * </ul>
     *
     * Logs important events during the gameplay, such as:
     * <ul>
     *     <li>The appearance of enemies.</li>
     *     <li>The outcome of each attack or counterattack.</li>
     *     <li>The hero's death or the defeat of all enemies.</li>
     * </ul>
     *
     * @throws IllegalStateException if the game state is inconsistent, e.g., no hero or enemies initialized.
     */
    public void startGame() {
        logger.info("Initializing game...");
        logger.info(hero.getName() + " begins the adventure!");
        Random random = new Random();

        for (Enemy enemy : enemies) {
            if (!hero.isAlive()) {
                logger.info("The hero has fallen. Game over.");
                return;
            }

            logger.info("An enemy appears! It's a " + enemy.getName() + "!");

            // Vérifier si l'ennemi est un gangster
            boolean enemyAttacksFirst = enemy instanceof Gangster;

            while (hero.isAlive() && enemy.isAlive()) {
                // Cas spécial : les gangsters attaquent d'abord
                if (enemyAttacksFirst) {
                    logger.info(enemy.getName() + " attacks first!");
                    enemy.attack(hero);
                    enemyAttacksFirst = false; // Après la première attaque, le combat devient normal
                    if (!hero.isAlive()) {
                        logger.info("The hero has fallen. Game over.");
                        return;
                    }
                }

                // Le héros attaque entre 1 et 5 fois aléatoirement
                int heroAttackCount = random.nextInt(5) + 1; // Génère un nombre entre 1 et 5
                logger.info(hero.getName() + " attacks " + heroAttackCount + " times!");

                for (int i = 0; i < heroAttackCount && enemy.isAlive(); i++) {
                    hero.attack(enemy);
                }

                // Si l'ennemi est encore vivant, il attaque une fois
                if (enemy.isAlive()) {
                    logger.info(enemy.getName() + " counterattacks!");
                    enemy.attack(hero);
                }
            }

            if (!enemy.isAlive()) {
                logger.info("The " + enemy.getName() + " has been defeated!");
            }
        }

        // Fin du niveau
        if (hero.isAlive()) {
            logger.info("Congratulations, you completed the level!");
        } else {
            logger.info("Defeat. Try again!");
        }
    }

}

