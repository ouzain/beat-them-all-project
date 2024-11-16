package imt.fr.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Actors {
    protected String name;
    protected int healthPoints;
    protected int attackPower;
    protected int defense;

    public Actors(String name, int healthPoints, int attackPower, int defense) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.attackPower = attackPower;
        this.defense = defense;
    }

    public abstract void attack(Actors target);

    public void receiveDamage(int damage) {
        int effectiveDamage = Math.max(0, damage - defense);
        healthPoints -= effectiveDamage;
        System.out.println(name + " takes " + effectiveDamage + " damage, remaining HP: " + healthPoints);
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }
}

