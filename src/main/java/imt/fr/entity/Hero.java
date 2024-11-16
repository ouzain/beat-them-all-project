package imt.fr.entity;

public class Hero extends Actors {
    private boolean specialAbilityUsed;

    public Hero(String name, int healthPoints, int attackPower, int defense) {
        super(name, healthPoints, attackPower, defense);
        this.specialAbilityUsed = false;
    }

    @Override
    public void attack(Character target) {
        System.out.println(name + " attacks " + target.name + " with a power of " + attackPower);
        target.receiveDamage(attackPower);
    }

    public void useSpecialAbility() {
        if (!specialAbilityUsed) {
            System.out.println(name + " uses their special ability!");
            healthPoints += 50; // Example: healing as a special ability
            specialAbilityUsed = true;
        } else {
            System.out.println("Special ability already used.");
        }
    }
}
