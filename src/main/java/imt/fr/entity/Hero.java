package imt.fr.entity;

import imt.fr.entity.Enemies.Enemy;
import imt.fr.service.Actions;
import lombok.Getter;

@Getter
public abstract class Hero extends Actors implements Actions {
    private boolean specialAbilityUsed;

    public Hero(String name, int healthPoints, int attackPower, int defense) {
        super(name, healthPoints, attackPower, defense);
        this.specialAbilityUsed = false;
    }

    // Getter et Setter pour specialAbilityUsed
    public boolean isSpecialAbilityUsed() {
        return specialAbilityUsed;
    }

    public void setSpecialAbilityUsed(boolean specialAbilityUsed) {
        this.specialAbilityUsed = specialAbilityUsed;
    }

    public abstract void useSpecialAbility(Enemy target);

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
        // Calcul des dégâts après prise en compte de la défense
        int effectiveDamage = Math.max(0, damage - this.defense);
        this.healthPoints -= effectiveDamage;
        this.healthPoints = Math.max(0, this.healthPoints);  // Assurer que les HP ne soient pas négatifs

        if (effectiveDamage > 0) {
            logger.info(name + " takes " + effectiveDamage + " damage (reduced by defense), remaining HP: " + healthPoints);
        } else {
            logger.info(name + " blocks the attack completely with their defense. No damage taken. Remaining HP: " + healthPoints);
        }
    }
}
