package imt.fr.service;

public class Game {
    private Card card;
    private Hero hero;
    private List<Enemy> enemies;

    public void initializeGame() {
        card = new Card("Forest", "Danger Zone", 10);
        hero = new Hero("Champion", 100, 20, 5);
        enemies = new ArrayList<>();
        enemies.add(new Bandit("Bandit 1", 30, 10));
        enemies.add(new Bandit("Bandit 2", 40, 15));

        System.out.println("Game initialized on " + card);
    }

    public void startGame() {
        System.out.println(hero.name + " begins the adventure!");
        for (Enemy enemy : enemies) {
            if (!hero.isAlive()) {
                System.out.println("The hero has fallen. Game over.");
                return;
            }

            System.out.println("An enemy appears!");
            while (hero.isAlive() && enemy.isAlive()) {
                hero.attack((Character) enemy);
                if (enemy.isAlive()) {
                    enemy.attack(hero);
                }
            }

            if (!enemy.isAlive()) {
                System.out.println("The enemy has been defeated!");
            }
        }

        if (hero.isAlive()) {
            System.out.println("Congratulations, you completed the level!");
        } else {
            System.out.println("Defeat. Try again!");
        }
    }
}
