package imt.fr.entity.Cards;

import lombok.Getter;

@Getter
public enum Location {
    FOREST("Forest"),
    DESERT("Desert"),
    MOUNTAIN("Mountain");

    private final String name;

    Location(String name) {
        this.name = name;
    }

}