package imt.fr.service;

import imt.fr.entity.Actors;

import java.util.logging.Logger;

public interface Actions {
    Logger logger = Logger.getLogger("GameLogger");
    void attack(Actors target);
    void receiveDamage(int damage);

}
