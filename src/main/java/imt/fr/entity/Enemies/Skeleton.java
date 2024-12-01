package imt.fr.entity.Enemies;

import imt.fr.entity.Actors;
import imt.fr.entity.Enemies.Enemy;

public class Skeleton extends Enemy {
    public Skeleton() {
        super("Skeleton", 15, 9,8);  // Appel au constructeur parent
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
