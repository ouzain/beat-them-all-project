package imt.fr.entity.Cards;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Card {
    private Location location;
    private String name;
    private int length;



    public Card(Location location, String name, int length) {
        this.location = location;
        this.name = name;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Card: " + name + " at " + location + " (length: " + length + ")";
    }


}