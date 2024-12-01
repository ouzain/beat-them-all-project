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
        this.defense= defense;
    }

    public void attack(Actors actor){}
    public void receiveDamage(int damage) {
    }

    public boolean isAlive() {
        return healthPoints > 0;
    }

    @Override
    public String toString() {
        return "name=" + name + ", healthPoints=" + healthPoints;
    }
}
