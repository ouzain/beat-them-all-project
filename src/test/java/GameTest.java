import static org.junit.jupiter.api.Assertions.*;

import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Enemies.Gangster;
import imt.fr.entity.Hero;
import imt.fr.service.Game;
import org.junit.jupiter.api.Test;

public class GameTest {

    @Test
    public void testHeroKillsEnemy() {
        // Arrange
        Hero hero = new Hero("TestHero", 30, 13, 5); // Hero with 10 attack
        Enemy enemy = new Enemy("TestEnemy", 10, 5, 3); // Enemy with 10 HP

        // Act
        hero.attack(enemy);

        // Assert
        assertFalse(enemy.isAlive(), "Enemy should be dead after the attack.");
        assertEquals(0, enemy.getHealthPoints(), "Enemy's HP should be 0.");
    }

    @Test
    public void testHeroDiesBeforeLevelEnd() {
        // Arrange
        Hero hero = new Hero("TestHero", 5, 10, 2); // Hero with low HP
        Enemy enemy = new Enemy("TestEnemy", 15, 6, 1); // Enemy with high attack

        // Simuler une attaque ennemie suffisante pour tuer le héros
        enemy.attack(hero);
        enemy.attack(hero);

        assertFalse(hero.isAlive(), "Hero should be dead.");
    }

    @Test
    public void testGangsterAttacksFirst() {
        Hero hero = new Hero("TestHero", 30, 10, 5); // Hero with 30 HP
        Gangster gangster = new Gangster("TestGangster", 20, 5, 3); // Gangster with 5 attack
        //FIXME: logique ?
    }


    @Test
    public void testEnemyReceivesDamage() {
        // Arrange
        Enemy enemy = new Enemy("TestEnemy", 20, 5, 3); // Defense = 3
        int damage = 10;

        enemy.receiveDamage(damage);

        assertEquals(13, enemy.getHealthPoints(), "Enemy should lose 7 HP after 10 damage minus 3 defense.");
    }
}
