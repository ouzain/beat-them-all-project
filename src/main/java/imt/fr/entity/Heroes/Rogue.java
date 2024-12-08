package imt.fr.entity.Heroes;

import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Hero;

public class Rogue extends Hero {

    public Rogue() {
        super("Rogue", 80, 25, 5);
    }

    @Override
    public void useSpecialAbility(Enemy target) {
        if (!isSpecialAbilityUsed()) {  // Vérifie si la capacité spéciale a été utilisée
            setSpecialAbilityUsed(true); // Met à jour l'état de la capacité spéciale
            attackPower += 15; // Augmente l'attaque pendant un tour
            System.out.println(name + " uses a sneak attack, increasing attack power to " + attackPower);
        } else {
            System.out.println(name + "'s special ability has already been used.");
        }
    }
}
