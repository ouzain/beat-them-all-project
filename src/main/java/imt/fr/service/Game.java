package imt.fr.service;

import imt.fr.entity.Cards.Card;
import imt.fr.entity.Cards.Location;
import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Enemies.Gangster;
import imt.fr.entity.Hero;
import imt.fr.entity.Heroes.Captain;
import imt.fr.entity.Heroes.Rock;
import imt.fr.entity.Heroes.Paladin;
import imt.fr.entity.Heroes.Rogue;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Game {

    private static final Logger logger = Logger.getLogger("Main.java");
    protected Card card;
    private Hero hero;
    private List<Enemy> enemies;

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

        // Définir la longueur de la carte et la localisation selon le niveau choisi
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

        // Création des ennemis
        enemies = new ArrayList<>();
        for (int i = 1; i <= length; i++) {
            enemies.add(new Enemy("Monster " + i, 4 + i * 2, 3 + i, 2 + i)); // PV, Attaque, Défense évolutifs
        }
        System.out.println(length + " enemies have been created for this level!");

        // Création de la liste des héros et choix du héros
        List<Hero> availableHeroes = new ArrayList<>();
        availableHeroes.add(new Captain());
        availableHeroes.add(new Rock());
        availableHeroes.add(new Paladin());
        availableHeroes.add(new Rogue());

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
