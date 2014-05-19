package ru.spbau.amanov.drunkard;
import ru.spbau.amanov.drunkard.GameObjects.Empty;

import java.util.*;

/**
 * Class provides functions to find path on the game field.
 *
 * @author  Karim Amanov
 */
public class PathHelper {

    /**
     * Class constructor.
     * @param f game field.
     */
    public PathHelper(GameField f) {
        field = f;
    }

    /**
     * Get next position.
     * @param from from postion.
     * @param to to position.
     * @return next position.
     */
    public Position getNextPos(Position from, Position to) {
        field.initFieldObjects();
        Position pos = BFS(from, to);

        if (pos == null) {
            return null;
        } else if (pos.equals(from)) {
            return to;
        }

        Position out = pos;
        while (!pos.equals(from)) {
            out = pos;
            pos = field.getObject(pos).getPrev();
        }
        return out;
    }

    /**
     * Generate random move.
     * @param initPos
     * @return
     */
    public Position generateRandomMove(Position initPos) {
        Position to = initPos;
        do {
            int rand = randomGenerator.nextInt(4);
            switch (rand) {
                case 0 : to = initPos.decrRow();
                    break;
                case 1 : to = initPos.decrColumn();
                    break;
                case 2 : to = initPos.incRow();
                    break;
                case 3 : to = initPos.incColumn();
            }
        } while (!field.validatePosition(to));


        return to;
    }

    private List<Position> getPossibleSteps(Position pos) {
        LinkedList<Position> ls = new LinkedList<Position>();
        ls.add(pos.incColumn());
        ls.add(pos.incRow());
        ls.add(pos.decrColumn());
        ls.add(pos.decrRow());
        return ls;
    }

    private Position BFS(Position from, Position to) {
        Queue<Position> queue = new ArrayDeque<Position>();
        queue.add(from);
        Position pos;
        while (!queue.isEmpty()) {
            pos = queue.poll();
            List<Position> adjList = getAdjPos(pos, to);
            if (isPathFound) {
                isPathFound = false;
                return pos;
            }

            for(Position p : adjList) {
                if (!field.getObject(p).isVisited()) {
                    field.getObject(p).setVisited(true);
                    field.getObject(p).setPrev(pos);
                    queue.add(p);
                }
            }
            field.getObject(pos).setVisited(true);
        }
        return null;
    }

    private List<Position> getAdjPos(Position pos, Position to) {
        List<Position> ls = getPossibleSteps(pos);
        List<Position> out = new LinkedList<Position>();
        for (Position p : ls) {
            if (field.validatePosition(p)) {
                if (p.equals(to)) {
                    isPathFound = true;
                    return out;
                }

                if (field.getObject(p) instanceof Empty) {
                    out.add(p);
                }
            }
        }
        return out;
    }

    private Random randomGenerator = new Random();
    private boolean isPathFound = false;
    private GameField field;
}
