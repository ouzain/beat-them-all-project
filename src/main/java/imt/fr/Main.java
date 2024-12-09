package imt.fr;

import imt.fr.service.Game;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;


public class Main {
    public static void main(String[] args) {

        // Beautify the output for the binome information
        System.out.println("***************************************");
        System.out.println("*           BEAT THEM ALL             *");
        System.out.println("***************************************");
        System.out.println("* Binôme :                            *");
        System.out.println("* 1. Ousmane DIALLO                   *");
        System.out.println("* 2. Abdoulaye AW                     *");
        System.out.println("***************************************");
        System.out.println();

        InputStream configFile = Main.class.getClassLoader().getResourceAsStream("./logging.properties");
        try {
            if (configFile != null) {
                LogManager.getLogManager().readConfiguration(configFile);
            } else {
                System.err.println("Configuration file not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialize and start the game
        final Game game = new Game();
        game.initializeGame();
        game.startGame();
    }
}
