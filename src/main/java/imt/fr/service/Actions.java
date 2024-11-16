package imt.fr.service;

public interface Actions {
    void attack(Character target);
    void receiveDamage(int damage);
    boolean isAlive();
}
