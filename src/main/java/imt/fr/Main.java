package imt.fr;

import imt.fr.service.Game;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;


public class Main {
    public static void main(String[] args) {

        InputStream configFile = Main.class.getClassLoader().getResourceAsStream("./logging.properties");
        try{
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (IOException e){
            e.printStackTrace();
        }

        final  Game game= new Game();
        game.initializeGame();
        game.startGame();
    }
}