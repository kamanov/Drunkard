package ru.spbau.amanov.drunkard;

/**
 *  Class provides run game method.
 *
 *  @author  Karim Amanov
 */
public final class Game {

    public void run() {
        do {
            printer.reprintField();
            gameLogic.nextStep();
            gameStep++;
        } while (gameStep != GameConfig.MAX_GAME_STEPS);
    }


    private int gameStep = 0;
    private GameField field = new GameField();
    private Printer printer = new Printer(field);
    private GameLogic gameLogic = new GameLogic(field);

}

