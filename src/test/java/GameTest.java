import static org.junit.jupiter.api.Assertions.*;

import imt.fr.entity.Enemies.Catcher;
import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Enemies.Gangster;
import imt.fr.entity.Hero;
import imt.fr.service.Game;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameTest {

    @Test
    public void testHeroKillsEnemy() {
        Hero hero = new Hero("TestHero", 30, 13, 5); // Hero with 10 attack
        Enemy enemy = new Enemy("TestEnemy", 10, 5, 3); // Enemy with 10 HP

        hero.attack(enemy);

        assertFalse(enemy.isAlive(), "Enemy should be dead after the attack.");
        assertEquals(0, enemy.getHealthPoints(), "Enemy's HP should be 0.");
    }

    @Test
    public void testHeroDiesBeforeLevelEnd() {
        Hero hero = new Hero("TestHero", 5, 10, 2); // Hero with low HP
        Enemy enemy = new Enemy("TestEnemy", 15, 6, 1); // Enemy with high attack

        // attaque ennemie suffisante pour tuer le héro
        enemy.attack(hero);
        enemy.attack(hero);

        assertFalse(hero.isAlive(), "Hero should be dead.");
    }

    @Test
    public void testGangsterAttacksFirst() {
        Game game = new Game();
        Hero hero = new Hero("Hero", 20, 10, 5);
        Gangster gangster = new Gangster("Gangster", 15, 7, 3);

        game.setHero(hero);
        game.setEnemies(List.of(gangster));

        // Vérifier les HP initiaux du héros et du gangster
        int initialHeroHP = hero.getHealthPoints();
        int initialGangsterHP = gangster.getHealthPoints();

        // cas où le gangster doit attaquer en premier
        game.startGame();

        // Vérifier si le gangster a attaqué en premier en réduisant les HP du héros
        assertTrue(hero.getHealthPoints() < initialHeroHP, "The gangster did not attack first!");
        assertTrue(gangster.getHealthPoints() <= initialGangsterHP, "The gangster lost health before the hero's turn!");

        // Vérifier si le jeu continue correctement après l'attaque
        assertTrue(hero.isAlive() || !gangster.isAlive(), "The game did not handle the combat correctly.");

    }


    @Test
    public void testEnemyReceivesDamage() {
        // Arrange
        Enemy enemy = new Enemy("TestEnemy", 20, 5, 3); // Defense = 3
        int damage = 10;

        enemy.receiveDamage(damage);

        assertEquals(13, enemy.getHealthPoints(), "Enemy should lose 7 HP after 10 damage minus 3 defense.");
    }

    @Test
    void testHeroSpecialAbility(){
        // Initialisation de la classe Game
        Game game = new Game();
        Hero hero = new Hero("Test-Hero", 8, 10, 5); // Héros en état critique (HP < 10)
        Enemy enemy = new Catcher("Test-Catcher", 20, 7, 3);
        game.setHero(hero);
        game.setEnemies(List.of(enemy));

        int initialHeroHP = hero.getHealthPoints();
        int initialEnemyHP = enemy.getHealthPoints();

        // Lancer une simulation du jeu pour forcer l'utilisation du pouvoir spécial
        game.startGame();

        // Vérifier que le pouvoir spécial a été utilisé (hypothèse : le héros inflige un effet notable)
        assertTrue(hero.isSpecialAbilityUsed(), "The hero did not use their special ability!");

        // Vérifier que l'ennemi a subi des dégâts ou un effet spécial
        assertTrue(enemy.getHealthPoints() < initialEnemyHP, "The enemy was not affected by the hero's special ability!");

        // Vérifier que le héros a survécu grâce à son pouvoir spécial
        assertTrue(hero.getHealthPoints() >= initialHeroHP, "The hero did not benefit from their special ability!");
    }


}
