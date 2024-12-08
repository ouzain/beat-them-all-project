package imt.fr.entity.Heroes;
import imt.fr.entity.Enemies.Enemy;
import imt.fr.entity.Hero;

public class Paladin extends Hero {

    public Paladin() {
        super("Paladin", 100, 15, 20);
    }

    @Override
    public void useSpecialAbility(Enemy target) {
        if (!isSpecialAbilityUsed()) {  // Vérifie si la capacité spéciale a été utilisée
            setSpecialAbilityUsed(true); // Met à jour l'état de la capacité spéciale
            healthPoints += 20; // Soigne 20 PV
            System.out.println(name + " uses divine healing, restoring 20 HP. Current HP: " + healthPoints);
        } else {
            System.out.println(name + "'s special ability has already been used.");
        }
    }
}
