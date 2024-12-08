package imt.fr;

import imt.fr.service.Game;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {

        // Afficher les noms et prénoms du binôme dans la console
        System.out.println("BEAT THEM ALL");
        System.out.println("Binôme : Abdoulaye AW et Ousmane Diallo");

        // Configuration du logger
        InputStream configFile = Main.class.getClassLoader().getResourceAsStream("./logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Initialisation et démarrage du jeu
        final Game game = new Game();
        game.initializeGame();
        game.startGame();
    }
}
