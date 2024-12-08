package imt.fr.entity.Heroes;

import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Hero;

public class Rock extends Hero {

    public Rock() {
        super("Rock", 120, 18, 15);
    }

    @Override
    public void useSpecialAbility(Enemy target) {
        if (!isSpecialAbilityUsed()) {  // Vérifie si la capacité spéciale a été utilisée
            setSpecialAbilityUsed(true); // Met à jour l'état de la capacité spéciale
            defense += 10; // Augmente la défense pendant un tour
            System.out.println(name + " raises a shield, increasing defense to " + defense);
        } else {
            System.out.println(name + "'s special ability has already been used.");
        }
    }
}
