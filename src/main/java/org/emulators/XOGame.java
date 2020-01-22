package org.emulators;

import java.util.UUID;

public class XOGame {
    private final String gameUUID = UUID.randomUUID().toString();
    private String playerX = "xPlayer";
    private String playerO = "oPlayer";

    public XOLifeCycle getXoLifeCycle() {
        return xoLifeCycle;
    }

    private XOLifeCycle xoLifeCycle = XOLifeCycle.STARTED;
    private int[][] field = new int[][]{{0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}, {0}};
    private byte player = 1;

    public XOGame() {

    }

    public int[] getValidAction() {
        Integer[] actions = new Integer[9];
        int c = 0;
        for (int i = 0; i < 9; i++) {
            if (field[i][0] == 0) {
                actions[i] = i;
                c++;
            }
        }
        if (c > 0) {
            int[] copy = new int[c];
            int startIndex = 0;
            for (int i = 0; i < actions.length; i++) {
                if (actions[i] != null) {
                    copy[startIndex] = actions[i];
                    startIndex++;
                }
            }
            return copy;
        } else {
            return null;
        }
    }

    public void action(int action) {
        if (xoLifeCycle!= XOLifeCycle.FINISHED) {
            //x
            if (player == 1) {
                player = 0;
                field[action][0] = 1;
            }
            //o
            else {
                player = 1;
                field[action][0] = 2;
            }
            //validation
            if (field[0][0] == field[1][0] && field[2][0] == field[0][0]) {
                System.out.println("WIN");
                xoLifeCycle = XOLifeCycle.FINISHED;
            }
        }else {
            System.out.println("[WARN] Game Finished");
        }
    }

    public int[][] getField() {
        return field;
    }

    public String getGameUUID() {
        return gameUUID;
    }

    enum XOLifeCycle {FINISHED, IN_PROGRESS, STARTED}
}
