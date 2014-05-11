package ru.spbau.amanov.drunkard;

import java.util.TimerTask;

/**
 *  Class provides run game method.
 *
 *  @author  Karim Amanov
 */
public final class Game extends TimerTask {

    @Override
    public void run() {
        printer.reprintField();
        gameLogic.nextStep();
    }

    private GameField field = new GameField();
    private Printer printer = new Printer(field);
    private GameLogic gameLogic = new GameLogic(field);

}

