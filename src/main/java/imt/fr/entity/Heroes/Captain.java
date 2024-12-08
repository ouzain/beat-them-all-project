package imt.fr.entity.Heroes;

import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Hero;

public class Captain extends Hero {

    public Captain() {
        super("Captain", 110, 20, 10);
    }

    @Override
    public void useSpecialAbility(Enemy target) {
        if (!isSpecialAbilityUsed()) {  // Vérifie si la capacité spéciale a été utilisée
            setSpecialAbilityUsed(true); // Met à jour l'état de la capacité spéciale
            attackPower += 10; // Augmente l'attaque pendant un tour
            System.out.println(name + " uses tactical strike, increasing attack power to " + attackPower);
        } else {
            System.out.println(name + "'s special ability has already been used.");
        }
    }
}
