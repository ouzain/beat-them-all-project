package imt.fr.entity;

import imt.fr.entity.Enemies.Enemy;
import imt.fr.service.Actions;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter
public class Hero extends Actors implements Actions {
    private boolean specialAbilityUsed;

    public enum SpecialAbility {
        MATRIX, // Dodge bullets for 2 turns
        HEAL,   // Restore health points
        ONE_SHOT // Kill all enemies in one hit
    }


    public Hero(String name, int healthPoints, int attackPower, int defense) {
        super(name, healthPoints, attackPower, defense);
        this.specialAbilityUsed = false;
    }

    @Override
    public void attack(Actors target) {
        if (target instanceof Enemy) {
            Enemy enemyTarget = (Enemy) target;
            logger.info(name + " attacks " + enemyTarget.getName() + " with a power of " + attackPower);
            enemyTarget.receiveDamage(attackPower);
        } else if (target != null) {
            logger.warning(name + " tried to attack a non-enemy target: " + target.getName());
        } else {
            logger.warning(name + " tried to attack, but the target is null.");
        }
    }


    @Override
    public void receiveDamage(int damage) {
        int effectiveDamage = Math.max(0, damage - this.defense);
        this.healthPoints -= effectiveDamage;
        this.healthPoints = Math.max(0, this.healthPoints);

        if (effectiveDamage > 0) {
            logger.info(name + " takes " + effectiveDamage + " damage (reduced by defense), remaining HP: " + healthPoints);
        } else {
            logger.info(name + " blocks the attack completely with their defense. No damage taken. Remaining HP: " + healthPoints);
        }
    }


    private SpecialAbility getRandomSpecialAbility() {
        SpecialAbility[] abilities = SpecialAbility.values();
        Random random = new Random();
        return abilities[random.nextInt(abilities.length)];
    }

    public void useSpecialAbility(Enemy target) {
        if (specialAbilityUsed) {
            logger.info("Special ability already used.");
            return;
        }

        SpecialAbility ability = getRandomSpecialAbility();

        switch (ability) {
            case MATRIX:
                logger.info(name + " uses Matrix and dodges attacks for 1 turns!");
                this.healthPoints+= target.attackPower;
                break;
            case HEAL:
                logger.info(name + " uses Heal and restores health!");
                this.healthPoints += 10;
                break;
            case ONE_SHOT:
                if (target != null) {
                    logger.info(name + " uses One Shot and defeats " + target.getName() + " instantly!");
                    target.receiveDamage(target.getHealthPoints()); // Reduce enemy health to zero
                }
                break;
            default:
                logger.info("Unknown ability.");
                break;
        }

        this.specialAbilityUsed = true;
    }

}
