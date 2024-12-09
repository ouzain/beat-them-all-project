package imt.fr.entity.Enemies;

import imt.fr.entity.Actors;
import imt.fr.entity.Enemies.Enemy;

public class Skeleton extends Enemy {
    public Skeleton(String name, int healthPoints, int attackPower, int defense) {
        super(name, healthPoints, attackPower, defense);
    }


    @Override
    public String toString() {
        return "Skeleton{" +
                "name='" + name + '\'' +
                ", healthPoints=" + healthPoints +
                ", attackPower=" + attackPower +
                ", defense=" + defense +
                '}';
    }
}
