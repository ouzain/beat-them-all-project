package imt.fr.entity;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Card {
    private String location;
    private String name;
    private int start;
    private int end;
    private int length;

    public Card(String location, String name, int length) {
        this.location = location;
        this.name = name;
        this.start = 0;
        this.end = length;
        this.length = length;
    }

    @Override
    public String toString() {
        return "Card: " + name + " at " + location + " (length: " + length + ")";
    }


}
