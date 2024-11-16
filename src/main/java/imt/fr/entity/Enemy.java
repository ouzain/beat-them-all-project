package imt.fr.entity;

import imt.fr.service.Actions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Enemy implements Actions {
    private String name;
    private int healthPoints;
    private int attackPower;

    public Enemy(String name, int healthPoints, int attackPower) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
    }

    @Override
    public void attack(Actors target) {
        System.out.println(name + " attacks " + target.name + " with a power of " + attackPower);
        target.receiveDamage(attackPower);
    }

    @Override
    public void receiveDamage(int damage) {
        healthPoints -= damage;
        System.out.println(name + " takes " + damage + " damage, remaining HP: " + healthPoints);
    }

    @Override
    public boolean isAlive() {
        return healthPoints > 0;
    }
}
