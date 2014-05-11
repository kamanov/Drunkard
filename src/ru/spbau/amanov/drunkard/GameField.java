package ru.spbau.amanov.drunkard;

import ru.spbau.amanov.drunkard.GameObjects.Empty;
import ru.spbau.amanov.drunkard.GameObjects.GameObject;

/**
 *  Class provides square field of the game.
 *
 *  @author  Karim Amanov
 */
public class GameField {

    /**
     *  Class constructor.
     * @param h field horizontal size.
     * @param v field vertical size.
     */
    public GameField() {
        WIDTH = GameConfig.FIELD_WIDTH;
        HEIGHT = GameConfig.FIELD_HEIGHT;
        field = new GameObject[HEIGHT][WIDTH];
        initField();
    }

    /**
     *  Visit all field objects.
     * @param visitor
     */
    public void visitFieldObjects(ICollisionVisitor visitor) {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j].accept(visitor);
            }
        }
    }

    /**
     *  Add object to the game field.
     * @param obj added object.
     * @param pos coordinate of the added object.
     */
    public void addObject(GameObject obj, Position pos) {
        field[pos.getRow()][pos.getColumn()] = obj;
    }

    /**
     *  Remove object from the game field.
     * @param coord object coordinate.
     */
    public void removeObject(Position pos) {
        addObject(new Empty(), pos);
    }

    /**
     *  Get object from the game field.
     * @param coord object coordinate.
     * @return corresponding object.
     */
    public GameObject getObject(Position pos) {
        return getObject(pos.getRow(), pos.getColumn());
    }

    /**
     *  Get object from the game field.
     * @param row
     * @param col
     * @return corresponding object.
     */
    public GameObject getObject(int row, int col) {
        return field[row][col];
    }

    /**
     * Checks that the coordinate belongs to the field.
     */
    public boolean validatePosition(Position pos) {
        return (pos.getRow() >= 0 && pos.getColumn() >= 0 && pos.getRow() < WIDTH && pos.getColumn() < HEIGHT);
    }

    /**
     *  Set all objects isVisited parameter to false.
     */
    public void initFieldObjects() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                field[i][j].setVisited(false);
            }
        }
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    private void initField() {
        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < WIDTH; j++) {
                addObject(new Empty(), new Position(i, j));
            }
        }
    }

    private final int WIDTH;
    private final int HEIGHT;
    private GameObject field[][];
}
