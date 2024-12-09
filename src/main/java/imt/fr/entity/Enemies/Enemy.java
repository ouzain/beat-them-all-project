package imt.fr.entity.Enemies;

import imt.fr.entity.Actors;
import imt.fr.entity.Hero;
import imt.fr.service.Actions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy extends Actors implements Actions {
    public Enemy(String name, int healthPoints, int attackPower, int defense) {
        super(name, healthPoints, attackPower, defense);
    }

    @Override
    public void attack(Actors target) {
        if (target instanceof Hero heroTarget) {
            logger.info(name + " attacks " + heroTarget.getName() + " with a power of " + attackPower);
            heroTarget.receiveDamage(attackPower);
        } else if (target != null) {
            logger.warning(name + " tried to attack a non-hero target: " + target.getName());
        } else {
            logger.warning(name + " tried to attack, but the target is null.");
        }
    }



    @Override
    public void receiveDamage(int damage) {
        int effectiveDamage = Math.max(0, damage - this.defense);
        if (effectiveDamage > 0) {
            this.healthPoints -= effectiveDamage;
            this.healthPoints = Math.max(0, this.healthPoints); //Avoiding negatives HP
            logger.info(name + " takes " + effectiveDamage + " damage after defense, remaining HP: " + healthPoints);
        } else {
            logger.info(name + " blocked the attack with their defense. No damage taken. Remaining HP: " + healthPoints);
        }
    }

}