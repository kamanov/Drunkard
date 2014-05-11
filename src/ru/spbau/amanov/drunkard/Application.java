package ru.spbau.amanov.drunkard;

import java.util.Timer;

/**
 *  Class Application provides entry point to the programm.
 *
 *  @author  Karim Amanov
 */
public class Application {

    /**
     * Entry point to the programm.
     * @param args  The command line parameters.
     */
    public static void main(String[] args) {
        Timer time = new Timer();
        Game game = new Game();
        final long TIME_PERIOD = 500;
        time.schedule(game, 0, TIME_PERIOD);
    }
}
